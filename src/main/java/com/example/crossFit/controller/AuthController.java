package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.response.SuccessResponse;
import com.example.crossFit.security.AuthDoubleDTO;
import com.example.crossFit.security.AuthenticationRequestDTO;
import com.example.crossFit.security.JwtTokenProvider;
import com.example.crossFit.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.UUID;

@Api(tags = SwaggerConfig.AUTH_TAG)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${fitness.mail.username}")
    private String mail;
    @Value("${doubleCheckAuthMin}")
    private int min;
    @Value("${doubleCheckAuthMax}")
    private int max;


    private static final String subject = "Подтверждение аутентификации";
    private static final String text = "Для подтверждения аутентификации введите данный код: ";

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailSender mailSender;
    private final ClientService clientService;
    private final ClientRepo clientRepo;

    private static final HashMap<String, String> tokenAuth = new HashMap<>();
    private Integer checkCode = 0;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          MailSender mailSender, ClientRepo clientRepo, ClientService clientService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mailSender = mailSender;
        this.clientRepo = clientRepo;
        this.clientService = clientService;
    }

    @ApiOperation("Метод для входа")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            Authentication user = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getAuthorities().toString());

            tokenAuth.put("email", request.getEmail());
            tokenAuth.put("token", token);


            if (user.getAuthorities().toString().equals("[ROLE_USER]")) {
                Client client = clientRepo.findByEmail(request.getEmail());
                if (client.isDoubleCheckAuth()) {

                    sendMessage(request.getEmail(), subject, text);

                    return ResponseEntity.ok(new SuccessResponse("Ожидается двухфакторная аутентификация",
                            HttpStatus.OK.value()));
                } else {
                    return ResponseEntity.ok(tokenAuth);
                }
            }

        } catch (AuthenticationException e) {
            throw new AuthenticationException("Неверный логин или пароль!") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
        return ResponseEntity.ok(tokenAuth);
    }

    @ApiOperation("Метод для двухфакторной аутентификации")
    @PostMapping("/doubleCheck")
    public ResponseEntity<HashMap<String, String>> doubleAuthCheck(@RequestBody AuthDoubleDTO authDoubleDTO) {
        if (checkCode.toString().equals(authDoubleDTO.getCode())) {
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


    @ApiOperation("Метод для выхода")
    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);

        return ResponseEntity.ok(new SuccessResponse("Пользователь вышел. Токен аннулирован!",
                HttpStatus.OK.value()));
    }

    @ApiOperation("Метод для отключения двухфакторной аутентификации")
    @GetMapping
    public ResponseEntity<SuccessResponse> offDoubleCheckAuth(@RequestBody UUID id, Boolean check) {
        return ResponseEntity.ok(clientService.setDoubleCheckAuth(id, check));
    }

    /**
     * Метод для генерации и отправки кода на почту для двухфакторной аутентификации
     */
    public void sendMessage(String to, String subject, String text) {
        int resultCode = (int) Math.round(min + (Math.random() * max));
        checkCode = resultCode;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text + resultCode);

        mailSender.send(mailMessage);
    }


}
