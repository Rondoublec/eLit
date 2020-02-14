package fr.clientui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;


public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response reponse) {

        if(reponse.status() == 404 ) {
            return new OuvrageNotFoundException("Erreur : ouvrage non trouvé ");
        }
        if(reponse.status() == 400 ) {
            return new OuvrageBadRequestException("Erreur : Requête incorrecte ");
        }

        return defaultErrorDecoder.decode(invoqueur, reponse);
    }

}
