package sn.esp.service_web.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import sn.esp.service_web.entity.Image;

import java.io.IOException;
import java.util.List;

public interface ImageService 
{

    List<Image> getImagesByArticle(Long articleId);

    List<Image> saveImages(Long articleId, List<MultipartFile> files) throws IOException;

    ResponseEntity<byte[]> getImageData(Long id);

    Image getImageById(Long id) ;

}
