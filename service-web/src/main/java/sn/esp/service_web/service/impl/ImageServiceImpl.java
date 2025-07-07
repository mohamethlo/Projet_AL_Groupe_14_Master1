package sn.esp.service_web.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sn.esp.service_web.entity.Article;
import sn.esp.service_web.entity.Image;
import sn.esp.service_web.repository.ArticleRepository;
import sn.esp.service_web.repository.ImageRepository;
import sn.esp.service_web.service.ImageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService 
{

    private final ImageRepository imageRepo;
    private final ArticleRepository articleRepo;

    public ImageServiceImpl(ImageRepository imageRepo, ArticleRepository articleRepo) 
    {
        this.imageRepo = imageRepo;
        this.articleRepo = articleRepo;
    }

    @Override
    public List<Image> getImagesByArticle(Long articleId) 
    {
        return imageRepo.findByArticleId(articleId);
    }

    @Override
    public List<Image> saveImages(Long articleId, List<MultipartFile> files) throws IOException 
    {
        Article article = articleRepo.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article introuvable"));

        List<Image> images = new ArrayList<>();

        for (MultipartFile file : files) 
        {
            Image image = Image.builder()
                    .nomFichier(file.getOriginalFilename())
                    .typeMime(file.getContentType())
                    .donnees(file.getBytes())
                    .article(article)
                    .build();

            images.add(image);
        }

        return imageRepo.saveAll(images);
    }

    @Override
    public ResponseEntity<byte[]> getImageData(Long id) 
    {
        Image image = imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image non trouvée"));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, image.getTypeMime());
        headers.setContentDispositionFormData("attachment", image.getNomFichier());

        return new ResponseEntity<>(image.getDonnees(), headers, HttpStatus.OK);
    }

    @Override
    public Image getImageById(Long id) 
    {
        return imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image non trouvée"));
    }

}
