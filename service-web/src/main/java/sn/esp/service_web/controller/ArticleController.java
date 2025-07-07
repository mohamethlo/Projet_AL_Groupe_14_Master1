package sn.esp.service_web.controller;

import sn.esp.service_web.entity.Article;
import sn.esp.service_web.service.ArticleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin("*")
public class ArticleController 
{

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) 
    {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() 
    {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) 
    {
        return articleService.getArticleById(id);
    }

    @GetMapping("/categorie/{id}")
    public List<Article> getArticlesByCategorie(@PathVariable Long id) 
    {
        return articleService.getArticlesByCategorie(id);
    }

    @GetMapping("/recent")
    public List<Article> getRecentArticles() 
    {
        return articleService.getRecentArticles();
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) 
    {
        Article saved = articleService.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) 
    {
        Article updated = articleService.update(id, article);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) 
    {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

