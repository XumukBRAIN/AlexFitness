package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.ClientDTO;
import com.example.crossFit.response.SuccessAuthentication;
import com.example.crossFit.response.SuccessResponse;
import com.example.crossFit.security.AuthDoubleDTO;
import com.example.crossFit.security.AuthService;
import com.example.crossFit.security.AuthenticationRequestDTO;
import com.example.crossFit.security.ChangePasswordDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = SwaggerConfig.AUTH_TAG)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("Метод для входа")
    @PostMapping("/login")
    public ResponseEntity<SuccessAuthentication> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @ApiOperation("Метод для выхода")
    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(authService.logout(request, response));
    }


    @ApiOperation("Метод для двухфакторной аутентификации")
    @PostMapping("/doubleCheck")
    public ResponseEntity<SuccessAuthentication> doubleAuthCheck(@RequestBody AuthDoubleDTO authDoubleDTO) {
        return ResponseEntity.ok(authService.doubleCheck(authDoubleDTO));
    }

    @ApiOperation("Метод для запроса на восстановление пароля")
    @PostMapping("/recoveryPassword")
    public ResponseEntity<SuccessResponse> recoveryPassword(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(authService.recoveryPassword(clientDTO.getClientPhoneNumber()));
    }

    @ApiOperation("Метод для восстановления пароля")
    @PostMapping("/changePassword")
    public ResponseEntity<SuccessResponse> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        return ResponseEntity.ok(authService.changePassword(changePasswordDTO.getThisPassword(),
                changePasswordDTO.getNewPassword()));
    }


    @ApiOperation("Метод для отключения двухфакторной аутентификации")
    @PostMapping("/setDoubleCheck")
    public ResponseEntity<SuccessResponse> setDoubleCheckAuth() {
        return ResponseEntity.ok(authService.setDoubleCheckAuth());
    }


}
