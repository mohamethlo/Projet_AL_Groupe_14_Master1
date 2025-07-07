package sn.esp.service_web.repository;


import sn.esp.service_web.entity.Article;
import sn.esp.service_web.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> 
{
    List<Article> findByCategorie(Categorie categorie);
    List<Article> findByCategorieId(Long categorieId);
    List<Article> findByDatePublicationAfter(LocalDateTime date);

}

