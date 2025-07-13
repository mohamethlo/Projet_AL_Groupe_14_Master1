package sn.esp.service_web.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import sn.esp.service_web.entity.Utilisateur;
import sn.esp.service_web.repository.UtilisateurRepository;
import sn.esp.service_web.soap.wsdl.GetUtilisateurRequest;
import sn.esp.service_web.soap.wsdl.GetUtilisateurResponse;

@Endpoint
public class UtilisateurEndpoint {

    private static final String NAMESPACE_URI = "http://sn.esp.service_web/gen";

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUtilisateurRequest")
    @ResponsePayload
    public JAXBElement<GetUtilisateurResponse> getUtilisateur(@RequestPayload JAXBElement<GetUtilisateurRequest> request) {
        GetUtilisateurRequest req = request.getValue();

        Utilisateur user = utilisateurRepository
            .findByEmail(req.getEmail())
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        GetUtilisateurResponse response = new GetUtilisateurResponse();
        response.setNom(user.getNom());
        response.setPrenom(user.getPrenom());
        response.setRole(user.getRole().name());

        QName qName = new QName(NAMESPACE_URI, "GetUtilisateurResponse");
        return new JAXBElement<>(qName, GetUtilisateurResponse.class, response);
    }

}
