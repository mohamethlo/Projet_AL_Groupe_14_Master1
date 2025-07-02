package sn.esp.service_web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    
    @Column(unique = true, nullable = false)
    private String email;

    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role;
}

