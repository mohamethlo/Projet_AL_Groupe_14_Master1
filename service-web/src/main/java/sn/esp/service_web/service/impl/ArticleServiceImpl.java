package sn.esp.service_web.service.impl;

import sn.esp.service_web.entity.Article;
import sn.esp.service_web.repository.ArticleRepository;
import sn.esp.service_web.service.ArticleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService 
{

    private final ArticleRepository articleRepo;

    public ArticleServiceImpl(ArticleRepository articleRepo) 
    {
        this.articleRepo = articleRepo;
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
        existing.setAuteur(article.getAuteur()); 
        return articleRepo.save(existing);
    }

    @Override
    public void delete(Long id) 
    {
        if (!articleRepo.existsById(id)) {
            throw new RuntimeException("Article introuvable");
        }
        articleRepo.deleteById(id);
    }

}

