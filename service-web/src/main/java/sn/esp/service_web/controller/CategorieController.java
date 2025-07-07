package sn.esp.service_web.controller;

import sn.esp.service_web.entity.Categorie;
import sn.esp.service_web.service.CategorieService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategorieController 
{

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) 
    {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Categorie> getAllCategories() 
    {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) 
    {
        Optional<Categorie> categorie = categorieService.getCategorieById(id);
        return categorie.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) 
    {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(categorieService.save(categorie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) 
    {
        return ResponseEntity.ok(categorieService.update(id, categorie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) 
    {
        categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

