package sn.esp.service_web.dto;

public class ArticleDto 
{

    private String titre;
    private String resume;
    private String contenu;
    private Long categorieId;
    private String auteurEmail;


    public String getTitre() 
    {
        return titre;
    }

    public void setTitre(String titre) 
    {
        this.titre = titre;
    }

    public String getResume() 
    {
        return resume;
    }

    public void setResume(String resume) 
    {
        this.resume = resume;
    }

    public String getContenu() 
    {
        return contenu;
    }

    public void setContenu(String contenu) 
    {
        this.contenu = contenu;
    }

    public Long getCategorieId() 
    {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) 
    {
        this.categorieId = categorieId;
    }

    public String getAuteurEmail() 
    {
        return auteurEmail;
    }

    public void setAuteurEmail(String auteurEmail) 
    {
        this.auteurEmail = auteurEmail;
    }
}
