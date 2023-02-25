package com.example.crossFit.security;

import com.example.crossFit.exceptions.AuthenticationInvalidException;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.response.SuccessAuthentication;
import com.example.crossFit.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {
    private final ClientRepo clientRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.header}")
    private String authorizationHeader;

    private static final String subject_double_check = "Подтверждение аутентификации";
    private static final String text_double_check = "Для подтверждения аутентификации введите данный код: ";
    private static final String subject_set_password = "Изменение пароля";
    private static final String text_set_password = "Пароль был изменен! Ваш новый пароль:  ";

    private static final HashMap<String, String> tokenAuth = new HashMap<>();
    private static final HashMap<String, String> doubleCheck = new HashMap();

    @Value("${fitness.mail.username}")
    private String mail;
    @Value("${doubleCheckAuthMin}")
    private int min;
    @Value("${doubleCheckAuthMax}")
    private int max;

    @Autowired
    public AuthService(ClientRepo clientRepo,
                       AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider, MailSender mailSender, PasswordEncoder passwordEncoder) {
        this.clientRepo = clientRepo;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SuccessAuthentication authenticate(AuthenticationRequestDTO request) {
        try {
            Authentication user = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));
            String token = jwtTokenProvider.createToken(request.getPhoneNumber(), user.getAuthorities().toString());

            tokenAuth.put(request.getPhoneNumber(), token);

            if (user.getAuthorities().toString().equals("[ROLE_USER]")) {
                ClientDetails clientDetails = (ClientDetails) user.getPrincipal();
                Client client = clientDetails.getClient();
                if (client.getAccountIsLocked()) {
                    throw new AuthenticationInvalidException("Пользователь заблокирован!");
                }
                if (client.isDoubleCheckAuth()) {
                    String code = generateSecretCode().toString();
                    doubleCheck.put(client.getPhoneNumber(), code);

                    sendMessage(client.getEmail(), subject_double_check, text_double_check + code);

                    return new SuccessAuthentication(null, "Ожидается двухфакторная аутентификация",
                            HttpStatus.OK.value());
                } else {
                    return new SuccessAuthentication(tokenAuth.get(request.getPhoneNumber()), "Пользователь успешно авторизовался!",
                            HttpStatus.OK.value());
                }
            }
        } catch (AuthenticationException e) {
            throw new AuthenticationInvalidException("Неверный логин или пароль!");
        }
        return new SuccessAuthentication(tokenAuth.get(request.getPhoneNumber()), "Пользователь успешно авторизовался!",
                HttpStatus.OK.value());
    }

    @Transactional
    public SuccessAuthentication doubleCheck(AuthDoubleDTO authDoubleDTO) {
        if (doubleCheck.get(authDoubleDTO.getPhoneNumber()).equals(authDoubleDTO.getCode())) {
            return new SuccessAuthentication(tokenAuth.get(authDoubleDTO.getPhoneNumber()), "Пользователь успешно аутентифицировался!",
                    HttpStatus.OK.value());
        } else {
            throw new AuthenticationInvalidException("Неверный код!");
        }
    }

    @Transactional
    public SuccessResponse logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        jwtTokenProvider.addToBlackListToken(request.getHeader(authorizationHeader));
        securityContextLogoutHandler.logout(request, response, null);

        return new SuccessResponse("Пользователь вышел. Токен аннулирован!",
                HttpStatus.OK.value());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public SuccessResponse setDoubleCheckAuth() {
        ClientDetails clientDetails = (ClientDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientDetails.getClient();

        if (client.isDoubleCheckAuth()) {
            client.setDoubleCheckAuth(false);
            clientRepo.save(client);
            return new SuccessResponse("Двухфакторная проверка отключена!", HttpStatus.OK.value());
        } else {
            client.setDoubleCheckAuth(true);
            clientRepo.save(client);
            return new SuccessResponse("Двухфакторная проверка включена!", HttpStatus.OK.value());
        }
    }

    @Transactional
    public SuccessResponse recoveryPassword(String phoneNumber) {
        Optional<Client> clientOptional = clientRepo.findByPhoneNumber(phoneNumber);
        if (!clientOptional.isPresent()) {
            throw new ResourceNotFoundException("Пользователь с таким номером телефона: " + phoneNumber +
                    " не зарегистрирорван!");
        }
        Client client = clientOptional.get();
        String newPassword = generateSecretCode().toString();
        client.setPassword(passwordEncoder.encode(newPassword));
        clientRepo.save(client);

        sendMessage(client.getEmail(), subject_set_password, text_set_password + newPassword);
        return new SuccessResponse("Пароль изменен! Новый пароль отправлен пользователю на почту!",
                HttpStatus.OK.value());
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public SuccessResponse changePassword(String thisPassword, String newPassword) {

        ClientDetails clientDetails = (ClientDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientDetails.getClient();
        if (passwordEncoder.matches(thisPassword, client.getPassword())) {
            client.setPassword(passwordEncoder.encode(newPassword));
            clientRepo.save(client);
        } else {
            throw new AuthenticationInvalidException("Неверный действующий пароль");
        }
        return new SuccessResponse("Пароль успешно изменен!", HttpStatus.OK.value());
    }


    /**
     * Метод для генерации и отправки кода на почту для двухфакторной аутентификации
     */
    public void sendMessage(String to, String subject, String text) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }


    public Integer generateSecretCode() {
        return (int) Math.round(min + (Math.random() * max));
    }


}
