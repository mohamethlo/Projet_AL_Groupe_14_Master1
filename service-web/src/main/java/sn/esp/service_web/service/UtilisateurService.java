package sn.esp.service_web.service;

import sn.esp.service_web.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService 
{
    Utilisateur creer(Utilisateur utilisateur);
    List<Utilisateur> lister();
    Utilisateur modifier(Long id, Utilisateur utilisateur);
    void supprimer(Long id);
}

