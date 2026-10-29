package serveur;

import jakarta.xml.ws.Endpoint;
import service.Calculatrice;

public class ServeurJWS {

    public static final String URL = "http://localhost:8585/calculatrice";

    public static void main(String[] args) {
        Endpoint.publish(URL, new Calculatrice());
        System.out.println("Service web publie sur : " + URL);
        System.out.println("WSDL disponible sur    : " + URL + "?wsdl");
        System.out.println("XSD disponible sur     : " + URL + "?xsd=1");
    }
}
