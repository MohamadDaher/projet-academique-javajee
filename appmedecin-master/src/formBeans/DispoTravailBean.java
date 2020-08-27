package formBeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Dispotravail;
import model.Users;

public class DispoTravailBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_ID ="id";
	private static final String CHAMP_DAT  = "date";
	private static final String CHAMP_DEB  = "debut";
    private static final String CHAMP_FIN   = "fin";
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

	public Dispotravail createDispoTravail( HttpServletRequest request ,Users user) throws Exception {
		String id = getValeurChamp( request, CHAMP_ID);
	    String date = getValeurChamp( request, CHAMP_DAT );
	    String  debut = getValeurChamp( request, CHAMP_DEB );
	    String fin = getValeurChamp( request, CHAMP_FIN );
	    
	    System.out.println(date+":  :"+debut+": :"+fin);
	    Dispotravail dispo = new Dispotravail();
	    dispo.setUsers(user);
	    try {
	    	dispo.setId(Long.parseLong(id));
	    }catch(Exception e) {}
	    
	    LocalDate dateObj = null;
	    try {
	       dateObj = validationDate( date );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_DAT, e.getMessage() );
	    }
	    dispo.setDate(dateObj);
	    
	    LocalTime debutObj = null;
	    LocalTime finObj = null;
	    try {
	        validationTime(debut,fin);
	        debutObj = LocalTime.parse(debut);
	        finObj = LocalTime.parse(fin);
	    } catch ( Exception e ) {
	        setErreur( CHAMP_DEB, e.getMessage() );
	        setErreur( CHAMP_FIN, e.getMessage() );
	    }
	    dispo.setHeureDebut(debutObj);
	    dispo.setHeureFin(finObj);
	    

	    if ( erreurs.isEmpty() ) {
	        resultat = "Succès de l'inscription.";
	    } else {
	        resultat = "Échec de l'inscription.";
	        throw new Exception("echec inscription");
	    }

	    return dispo;
	}

	private LocalDate validationDate(String kmmax) throws Exception {
		// TODO Auto-generated method stub
		if ( kmmax != null ) {
	        if ( !kmmax.matches( "[0-9]{4}-[0-9]{2}-[0-9]{2}" ) ) {
	            throw new Exception( "Merci de saisir une date valide." );
	        }else {
	        	return LocalDate.parse(kmmax);
	        }
	    } else {
	        throw new Exception( "Merci de saisir une date." );
	    }
		
	}

	private void validationTime( String lat, String lng ) throws Exception {
	    if ( lat != null && lng != null ) {
	        if ( !lat.matches( "[0-9]{2}:[0-9]{2}" ) || !lng.matches( "[0-9]{2}:[0-9]{2}" )) {
	            throw new Exception( "Merci de saisir une heure valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une heure." );
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
