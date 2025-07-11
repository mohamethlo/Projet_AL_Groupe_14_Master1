package sn.esp.service_web.service.impl;

import sn.esp.service_web.entity.Categorie;
import sn.esp.service_web.repository.CategorieRepository;
import sn.esp.service_web.service.CategorieService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImpl implements CategorieService 
{

    private final CategorieRepository categorieRepo;

    public CategorieServiceImpl(CategorieRepository categorieRepo) 
    {
        this.categorieRepo = categorieRepo;
    }

    @Override
    public List<Categorie> getAllCategories() 
    {
        return categorieRepo.findAll();
    }

    @Override
    public Optional<Categorie> getCategorieById(Long id) 
    {
        return categorieRepo.findById(id);
    }

    @Override
    public Categorie save(Categorie categorie) 
    {
        return categorieRepo.save(categorie);
    }

    @Override
    public Categorie update(Long id, Categorie categorie) 
    {
        Categorie existing = categorieRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

        existing.setNom(categorie.getNom());
        return categorieRepo.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) 
    {
        Categorie categorie = categorieRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

        if (categorie.getArticles() != null && !categorie.getArticles().isEmpty()) 
        {
            throw new RuntimeException("Impossible de supprimer : cette catégorie est liée à des articles.");
        }

        categorieRepo.deleteById(id);
    }

}

