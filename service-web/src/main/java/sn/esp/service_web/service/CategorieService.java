package sn.esp.service_web.service;

import sn.esp.service_web.entity.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieService 
{
    List<Categorie> getAllCategories();
    Optional<Categorie> getCategorieById(Long id);
}


