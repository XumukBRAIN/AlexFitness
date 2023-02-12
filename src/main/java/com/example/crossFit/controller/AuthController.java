package com.example.crossFit.controller;

import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.CoachRepo;
import com.example.crossFit.repository.ManagerRepo;
import com.example.crossFit.response.SuccessResponse;
import com.example.crossFit.security.AuthDoubleDTO;
import com.example.crossFit.security.AuthenticationRequestDTO;
import com.example.crossFit.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${fitness.mail.username}")
    private String mail;

    private static final String subject = "Подтверждение аутентификации";
    private static final String text = "Для подтверждения аутентификации введите данный код: ";

    private final AuthenticationManager authenticationManager;
    private final ClientRepo clientRepo;
    private final ManagerRepo managerRepo;
    private final CoachRepo coachRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailSender mailSender;

    private HashMap<String, String> tokenAuth = new HashMap<>();
    private Integer checkCode = 0;

    public AuthController(AuthenticationManager authenticationManager, ClientRepo clientRepo,
                          ManagerRepo managerRepo, CoachRepo coachRepo, JwtTokenProvider jwtTokenProvider, MailSender mailSender) {
        this.authenticationManager = authenticationManager;
        this.clientRepo = clientRepo;
        this.managerRepo = managerRepo;
        this.coachRepo = coachRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mailSender = mailSender;
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            Authentication user = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getAuthorities().toString());

            tokenAuth.put("email", request.getEmail());
            tokenAuth.put("token", token);
            sendMessage(request.getEmail(), subject, text);
            return ResponseEntity.ok(new SuccessResponse("Ожидается двухфакторная аутентификация",
                    HttpStatus.OK.value()));

        } catch (AuthenticationException e) {
            throw new AuthenticationException("Неверный логин или пароль!") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }

    @PostMapping("/doubleCheck")
    public ResponseEntity<HashMap<String, String>> doubleAuthCheck(@RequestBody AuthDoubleDTO authDoubleDTO) {
        String code = authDoubleDTO.getCode();
        if (checkCode.toString().equals(code)) {
            return ResponseEntity.ok(tokenAuth);
        } else {
            throw new AuthenticationException("Неверный код!") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }


    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    public void sendMessage(String to, String subject, String text) {
        int min = 5;
        int max = 100;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1) + min;
        checkCode = i;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text + i);

        mailSender.send(mailMessage);
    }


}
