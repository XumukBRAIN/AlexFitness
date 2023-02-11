package com.example.crossFit.controller;

import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.entity.Coach;
import com.example.crossFit.model.entity.Manager;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.CoachRepo;
import com.example.crossFit.repository.ManagerRepo;
import com.example.crossFit.security.AuthenticationRequestDTO;
import com.example.crossFit.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final ClientRepo clientRepo;
    private final ManagerRepo managerRepo;
    private final CoachRepo coachRepo;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, ClientRepo clientRepo,
                          ManagerRepo managerRepo, CoachRepo coachRepo, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.clientRepo = clientRepo;
        this.managerRepo = managerRepo;
        this.coachRepo = coachRepo;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            Client client = clientRepo.findByEmail(request.getEmail());
            Manager manager = managerRepo.findByEmail(request.getEmail());
            Coach coach = coachRepo.findByEmail(request.getEmail());
            String role = null;
            if (client != null) {
                role = client.getRole();
            }
            if (manager != null) {
                role = manager.getRole();
            }
            if (coach != null) {
                role = coach.getRole();

            }

            String token = jwtTokenProvider.createToken(request.getEmail(), role);
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Неверный логин или пароль!", HttpStatus.FORBIDDEN);
        }
    }


    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }


}
