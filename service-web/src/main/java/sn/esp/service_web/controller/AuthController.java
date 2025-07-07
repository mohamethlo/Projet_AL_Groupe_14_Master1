package sn.esp.service_web.controller;

import sn.esp.service_web.entity.Utilisateur;
import sn.esp.service_web.repository.UtilisateurRepository;
import sn.esp.service_web.security.jwt.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController 
{

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UtilisateurRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UtilisateurRepository userRepo, PasswordEncoder encoder) 
    {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody Utilisateur request) 
    {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse()));
        return jwtService.generateToken(request.getEmail());
    }

    @PostMapping("/register")
    public Utilisateur register(@RequestBody Utilisateur user) 
    {
        user.setMotDePasse(encoder.encode(user.getMotDePasse()));
        return userRepo.save(user);
    }
}
