package sn.esp.service_web.controller;

import sn.esp.service_web.entity.Image;
import sn.esp.service_web.service.ImageService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin("*")
public class ImageController 
{

    private final ImageService imageService;

    public ImageController(ImageService imageService) 
    {
        this.imageService = imageService;
    }

    @GetMapping("/article/{articleId}")
    public List<Image> getImagesByArticle(@PathVariable Long articleId) 
    {
        return imageService.getImagesByArticle(articleId);
    }

    @PostMapping(value = "/upload/article/{articleId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Image> uploadImagesToArticle(
            @PathVariable Long articleId,
            @RequestParam("files") List<MultipartFile> files
    ) throws IOException 
    {
        return imageService.saveImages(articleId, files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) 
    {
        Image image = imageService.getImageById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getTypeMime()));
        headers.setContentDispositionFormData("inline", image.getNomFichier());

        return new ResponseEntity<>(image.getDonnees(), headers, HttpStatus.OK);
    }

}


