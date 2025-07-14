package model;

public class Utilisateur 
{

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String role;

    public Utilisateur() 
    {
    }

    public Utilisateur(String nom, String prenom, String email, String motDePasse, String role) 
    {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters
    public String getNom() 
    {
        return nom;
    }

    public String getPrenom() 
    {
        return prenom;
    }

    public String getEmail() 
    {
        return email;
    }

    public String getMotDePasse() 
    {
        return motDePasse;
    }

    public String getRole() 
    {
        return role;
    }

    // Setters
    public void setNom(String nom) 
    {
        this.nom = nom;
    }

    public void setPrenom(String prenom) 
    {
        this.prenom = prenom;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) 
    {
        this.motDePasse = motDePasse;
    }

    public void setRole(String role) 
    {
        this.role = role;
    }

    @Override
    public String toString() 
    {
        return nom + " " + prenom + " (" + role + ")";
    }
}
