package sn.esp.service_web.controller;

import sn.esp.service_web.entity.Utilisateur;
import sn.esp.service_web.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin("*")
public class UtilisateurController 
{

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) 
    {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public Utilisateur creer(@RequestBody Utilisateur utilisateur) 
    {
        return utilisateurService.creer(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> lister() 
    {
        return utilisateurService.lister();
    }

    @PutMapping("/{id}")
    public Utilisateur modifier(@PathVariable Long id, @RequestBody Utilisateur utilisateur) 
    {
        return utilisateurService.modifier(id, utilisateur);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) 
    {
        utilisateurService.supprimer(id);
    }
}

