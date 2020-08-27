package formBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Employeur;
import model.Users;

public class EmployeurBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String CHAMP_KmMax  = "kmmax";
	private static final String CHAMP_LNG  = "zipcode";
    private static final String CHAMP_LTD   = "ville";
    private static final String CHAMP_CAP   = "cartepro";
	private String              resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();

	public String getResultat() {
	    return resultat;
	}
	
	public void setResultat(String rslt) {
	    resultat = rslt;
	}

	public Map<String, String> getErreurs() {
	    return erreurs;
	}

	public Employeur inscrireEmployeur( HttpServletRequest request ,Users user) throws Exception {
	    String kmmax = getValeurChamp( request, CHAMP_KmMax );
	    String  longitude = getValeurChamp( request, CHAMP_LNG );
	    String lattitude = getValeurChamp( request, CHAMP_LTD );
	    String cartePro = getValeurChamp( request, CHAMP_CAP );
	    
	    
	    Employeur employeur = new Employeur();
	    employeur.setUsers(user);

	    try {
	        validationKmMax( kmmax );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_KmMax, e.getMessage() );
	    }
	    employeur.setKmmax( new Long(kmmax) );
	    
	    try {
	        validationLatLng( lattitude,longitude );
	        employeur.setLongitude(new Double(longitude));
	 	    employeur.setLattitude(new Double(lattitude));
	    } catch ( Exception e ) {
	        setErreur( CHAMP_LNG, e.getMessage() );
	        setErreur( CHAMP_LTD, e.getMessage() );
	    }
	  
//	    try {
//	        validationMotsDePasse( motDePasse, confirmation );
//	        utilisateur.setMdp( motDePasse );
//	    } catch ( Exception e ) {
//	        setErreur( CHAMP_PASS, e.getMessage() );
//	        setErreur( CHAMP_CONF, null );
//	    }
	    

	    if ( erreurs.isEmpty() ) {
	        resultat = "Succès de l'inscription.";
	    } else {
	        resultat = "Échec de l'inscription.";
	        throw new Exception("echec inscription");
	    }

	    return employeur;
	}

	private void validationKmMax(String kmmax) throws Exception {
		// TODO Auto-generated method stub
		if ( kmmax != null ) {
	        if ( !kmmax.matches( "[0-9]*" ) ) {
	            throw new Exception( "Merci de saisir un kilométrage maximum valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir un kilométrage maximum." );
	    }
		
	}

	private void validationLatLng( String lat, String lng ) throws Exception {
	    if ( lat != null && lng != null ) {
	        if ( !lat.matches( "[0-9]*.[0-9]*" ) || !lng.matches( "[0-9]*.[0-9]*" )) {
	            throw new Exception( "Merci de saisir une adresse valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse." );
	    }
	}
	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur( String champ, String message ) {
	    erreurs.put( champ, message );
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}

}
