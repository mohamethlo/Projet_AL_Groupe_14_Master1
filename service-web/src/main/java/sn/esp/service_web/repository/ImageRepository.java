package sn.esp.service_web.repository;

import sn.esp.service_web.entity.Image;
import sn.esp.service_web.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> 
{
    List<Image> findByArticle(Article article);
    List<Image> findByArticleId(Long articleId);
}

