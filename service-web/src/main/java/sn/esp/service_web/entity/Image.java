package sn.esp.service_web.entity;


import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomFichier;

    private String typeMime;

    @Lob
    @Column(name = "donnees", columnDefinition = "LONGBLOB") 
    private byte[] donnees;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonBackReference
    private Article article;
}

