package sn.esp.service_web.service.impl;

import sn.esp.service_web.entity.Utilisateur;
import sn.esp.service_web.repository.UtilisateurRepository;
import sn.esp.service_web.service.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService 
{

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) 
    {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur creer(Utilisateur utilisateur) 
    {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> lister() 
    {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur modifier(Long id, Utilisateur utilisateur) 
    {
        utilisateur.setId(id);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void supprimer(Long id) 
    {
        utilisateurRepository.deleteById(id);
    }
}

