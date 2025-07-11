package sn.esp.service_web.service;

import sn.esp.service_web.dto.ArticleDto;
import sn.esp.service_web.entity.Article;

import java.util.List;

public interface ArticleService 
{
    List<Article> getAllArticles();
    Article getArticleById(Long id);
    List<Article> getArticlesByCategorie(Long categorieId);
    List<Article> getRecentArticles();
    Article save(Article article);
    Article update(Long id, Article article);
    void delete(Long id);
    Article createFromDto(ArticleDto dto);
    Article updateFromDto(Long id, ArticleDto dto);

}
