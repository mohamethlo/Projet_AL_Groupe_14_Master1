package sn.esp.service_web.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import sn.esp.service_web.entity.Utilisateur;
import sn.esp.service_web.entity.Role;
import sn.esp.service_web.repository.UtilisateurRepository;
import sn.esp.service_web.soap.wsdl.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Endpoint
public class UtilisateurCrudEndpoint 
{

    private static final String NAMESPACE_URI = "http://sn.esp.service_web/gen";

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // CreateUtilisateur
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateUtilisateurRequest")
    @ResponsePayload
    public JAXBElement<CreateUtilisateurResponse> createUtilisateur(@RequestPayload JAXBElement<CreateUtilisateurRequest> request) 
    {
        CreateUtilisateurRequest req = request.getValue();

        Utilisateur user = Utilisateur.builder()
            .nom(req.getNom())
            .prenom(req.getPrenom())
            .email(req.getEmail())
            .motDePasse(req.getMotDePasse())
            .role(Role.valueOf(req.getRole()))
            .build();

        utilisateurRepository.save(user);

        CreateUtilisateurResponse response = new CreateUtilisateurResponse();
        response.setMessage("Utilisateur créé avec succès");

        QName qName = new QName(NAMESPACE_URI, "CreateUtilisateurResponse");
        return new JAXBElement<>(qName, CreateUtilisateurResponse.class, response);
    }

    // GetUtilisateur
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUtilisateurRequest")
    @ResponsePayload
    public JAXBElement<GetUtilisateurResponse> getUtilisateur(@RequestPayload JAXBElement<GetUtilisateurRequest> request) 
    {
        String email = request.getValue().getEmail();

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        GetUtilisateurResponse response = new GetUtilisateurResponse();
        response.setNom(utilisateur.getNom());
        response.setPrenom(utilisateur.getPrenom());
        response.setRole(utilisateur.getRole().name());

        QName qName = new QName(NAMESPACE_URI, "GetUtilisateurResponse");
        return new JAXBElement<>(qName, GetUtilisateurResponse.class, response);
    }

    // UpdateUtilisateur
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateUtilisateurRequest")
    @ResponsePayload
    public JAXBElement<UpdateUtilisateurResponse> updateUtilisateur(@RequestPayload JAXBElement<UpdateUtilisateurRequest> request) 
    {
        UpdateUtilisateurRequest req = request.getValue();

        Utilisateur utilisateur = utilisateurRepository.findByEmail(req.getEmail())
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateur.setNom(req.getNom());
        utilisateur.setPrenom(req.getPrenom());
        utilisateur.setMotDePasse(req.getMotDePasse());
        utilisateur.setRole(Role.valueOf(req.getRole()));

        utilisateurRepository.save(utilisateur);

        UpdateUtilisateurResponse response = new UpdateUtilisateurResponse();
        response.setMessage("Utilisateur mis à jour avec succès");

        QName qName = new QName(NAMESPACE_URI, "UpdateUtilisateurResponse");
        return new JAXBElement<>(qName, UpdateUtilisateurResponse.class, response);
    }

    // DeleteUtilisateur
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteUtilisateurRequest")
    @ResponsePayload
    public JAXBElement<DeleteUtilisateurResponse> deleteUtilisateur(@RequestPayload JAXBElement<DeleteUtilisateurRequest> request) {
        String email = request.getValue().getEmail();

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateurRepository.delete(utilisateur);

        DeleteUtilisateurResponse response = new DeleteUtilisateurResponse();
        response.setMessage("Utilisateur supprimé avec succès");

        QName qName = new QName(NAMESPACE_URI, "DeleteUtilisateurResponse");
        return new JAXBElement<>(qName, DeleteUtilisateurResponse.class, response);
    }
}
