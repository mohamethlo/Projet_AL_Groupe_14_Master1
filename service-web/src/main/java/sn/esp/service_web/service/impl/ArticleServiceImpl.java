package sn.esp.service_web.service.impl;

import sn.esp.service_web.dto.ArticleDto;
import sn.esp.service_web.entity.Article;
import sn.esp.service_web.entity.Categorie;
import sn.esp.service_web.entity.Utilisateur;
import sn.esp.service_web.repository.ArticleRepository;
import sn.esp.service_web.repository.UtilisateurRepository;
import sn.esp.service_web.service.ArticleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService 
{

    private final ArticleRepository articleRepo;
    private final UtilisateurRepository utilisateurRepo; 

    public ArticleServiceImpl(ArticleRepository articleRepo, UtilisateurRepository utilisateurRepo) 
    {
        this.articleRepo = articleRepo;
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public List<Article> getAllArticles() 
    {
        return articleRepo.findAll();
    }

    @Override
    public Article getArticleById(Long id) 
    {
        return articleRepo.findById(id).orElse(null);
    }

    @Override
    public List<Article> getArticlesByCategorie(Long categorieId) 
    {
        return articleRepo.findByCategorieId(categorieId);
    }

    public List<Article> getRecentArticles() 
    {
        LocalDateTime seventyTwoHoursAgo = LocalDateTime.now().minusHours(72);
        return articleRepo.findByDatePublicationAfter(seventyTwoHoursAgo);
    }

    @Override
    public Article save(Article article) 
    {
        if (article.getAuteur() != null && article.getAuteur().getEmail() != null) 
        {
            Utilisateur auteur = utilisateurRepo.findByEmail(article.getAuteur().getEmail())
                .orElseThrow(() -> new RuntimeException("Auteur introuvable en base"));

            article.setAuteur(auteur); 
        } 
        else 
        {
            throw new RuntimeException("Aucune information d'auteur fournie");
        }

        article.setDatePublication(LocalDateTime.now());
        return articleRepo.save(article);
    }

    @Override
    public Article update(Long id, Article article) 
    {
        Article existing = articleRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Article introuvable"));

        existing.setTitre(article.getTitre());
        existing.setResume(article.getResume());
        existing.setContenu(article.getContenu());
        existing.setCategorie(article.getCategorie());

        if (article.getAuteur() != null && article.getAuteur().getEmail() != null) 
        {
            Utilisateur auteur = utilisateurRepo.findByEmail(article.getAuteur().getEmail())
                .orElseThrow(() -> new RuntimeException("Auteur introuvable"));
            existing.setAuteur(auteur);
        }

        return articleRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!articleRepo.existsById(id)) 
        {
            throw new RuntimeException("Article introuvable");
        }
        articleRepo.deleteById(id);
    }

    @Override
    public Article createFromDto(ArticleDto dto) 
    {
        Utilisateur auteur = utilisateurRepo.findByEmail(dto.getAuteurEmail())
            .orElseThrow(() -> new RuntimeException("Auteur non trouvé"));
        Categorie categorie = new Categorie();
        categorie.setId(dto.getCategorieId());

        Article article = new Article();
        article.setTitre(dto.getTitre());
        article.setResume(dto.getResume());
        article.setContenu(dto.getContenu());
        article.setCategorie(categorie); 
        article.setAuteur(auteur);
        article.setDatePublication(LocalDateTime.now());

        return articleRepo.save(article);
    }

    @Override
    public Article updateFromDto(Long id, ArticleDto dto) 
    {
        Article existing = articleRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Article introuvable"));

        existing.setTitre(dto.getTitre());
        existing.setResume(dto.getResume());
        existing.setContenu(dto.getContenu());

        Categorie cat = new Categorie();
        cat.setId(dto.getCategorieId());
        existing.setCategorie(cat);

        Utilisateur auteur = utilisateurRepo.findByEmail(dto.getAuteurEmail())
            .orElseThrow(() -> new RuntimeException("Auteur non trouvé"));
        existing.setAuteur(auteur);

        return articleRepo.save(existing);
    }

}
