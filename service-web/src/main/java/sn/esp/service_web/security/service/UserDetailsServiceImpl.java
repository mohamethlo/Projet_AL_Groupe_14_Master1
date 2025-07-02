package sn.esp.service_web.security.service;

import sn.esp.service_web.entity.Utilisateur;
import sn.esp.service_web.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    public UserDetailsServiceImpl(UtilisateurRepository repo) {
        this.utilisateurRepository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        return User.builder()
                .username(user.getEmail())
                .password(user.getMotDePasse())
                .roles(user.getRole().name())
                .build();
    }
}

