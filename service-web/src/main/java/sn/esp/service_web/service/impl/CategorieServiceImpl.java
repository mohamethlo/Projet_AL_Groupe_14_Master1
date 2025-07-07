package sn.esp.service_web.service.impl;

import sn.esp.service_web.entity.Categorie;
import sn.esp.service_web.repository.CategorieRepository;
import sn.esp.service_web.service.CategorieService;
import org.springframework.stereotype.Service;

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

    public Optional<Categorie> getCategorieById(Long id) 
    {
        return categorieRepo.findById(id);
    }

}

