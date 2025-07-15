package controller;

import sn.esp.soap.gen.*;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class UtilisateurController 
{

    private final UtilisateurPort utilisateurPort;

    public UtilisateurController() 
    {
        try 
        {
            URL wsdlUrl = new URL("http://localhost:8081/ws/utilisateurWsdl.wsdl");
            QName qname = new QName("http://sn.esp.service_web/gen", "UtilisateurPortService");

            Service service = Service.create(wsdlUrl, qname);
            utilisateurPort = service.getPort(UtilisateurPort.class);
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Erreur lors de l'accès au service SOAP : " + e.getMessage(), e);
        }
    }

    // Methode qui permet de recuperrer la liste de tous les utilisateurs
    public List<GetAllUtilisateursResponse.Utilisateurs> getListeUtilisateurs() 
    {
        GetAllUtilisateursRequest request = new GetAllUtilisateursRequest();
        GetAllUtilisateursResponse response = utilisateurPort.getAllUtilisateurs(request);
        return response.getUtilisateurs(); 
    }

    // Methode qui permet de lire un utilisateur par email
    public GetUtilisateurResponse getUtilisateur(String email) 
    {
        GetUtilisateurRequest request = new GetUtilisateurRequest();
        request.setEmail(email);
        return utilisateurPort.getUtilisateur(request);
    }

    // Methode qui permet de creer un nouvel utilisateur
    public CreateUtilisateurResponse createUtilisateur(String nom, String prenom, String email, String motDePasse, String role) 
    {
        CreateUtilisateurRequest request = new CreateUtilisateurRequest();
        request.setNom(nom);
        request.setPrenom(prenom);
        request.setEmail(email);
        request.setMotDePasse(motDePasse);
        request.setRole(role);
        return utilisateurPort.createUtilisateur(request);
    }

    // Methode qui permet de mettre a jour un utilisateur existant
    public UpdateUtilisateurResponse updateUtilisateur(String email, String nom, String prenom, String motDePasse, String role) 
    {
        UpdateUtilisateurRequest request = new UpdateUtilisateurRequest();
        request.setEmail(email);
        request.setNom(nom);
        request.setPrenom(prenom);
        request.setMotDePasse(motDePasse);
        request.setRole(role);
        return utilisateurPort.updateUtilisateur(request);
    }

    // Methode qui permet de Supprimer un utilisateur par email
    public boolean supprimerUtilisateur(String email) 
    {
        try 
        {
            DeleteUtilisateurRequest request = new DeleteUtilisateurRequest();
            request.setEmail(email);
            DeleteUtilisateurResponse response = utilisateurPort.deleteUtilisateur(request);
            return response.getMessage().contains("succes");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }

}
