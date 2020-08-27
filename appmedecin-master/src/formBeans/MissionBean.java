package formBeans;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Employeur;
import model.Mission;

public class MissionBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_ID ="id";
	private static final String CHAMP_KmMax  = "kmmax";
	private static final String CHAMP_ViD  = "vD";
    private static final String CHAMP_aS   = "aS";
    private static final String CHAMP_Ret   = "retro";
    private static final String CHAMP_Sec   = "secr";
    private static final String CHAMP_Des   = "desc";
    private static final String CHAMP_Dat   = "date";
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

	public Mission createMission( HttpServletRequest request ,Employeur employeur) throws Exception {
		String id = getValeurChamp( request, CHAMP_ID);
	    String kmmax = getValeurChamp( request, CHAMP_KmMax );
	    String  visiteDom = getValeurChamp( request, CHAMP_ViD );
	    String activiteSeule = getValeurChamp( request, CHAMP_aS );
	    String retrocession = getValeurChamp( request, CHAMP_Ret );
	    String secretariat = getValeurChamp( request, CHAMP_Sec );
	    String description = getValeurChamp( request, CHAMP_Des );
	    String periode = getValeurChamp( request, CHAMP_Dat );
	    
	    Mission mission = new Mission();
	    try {
	    mission.setId(Long.parseLong(id));
	    }catch(Exception e) {}
	    mission.setEmployeur(employeur);
	    mission.setVisiteDomicile(turnCheckBoxToBool(visiteDom));
	    mission.setActiviteSeule(turnCheckBoxToBool(activiteSeule));
	    mission.setSecreteriat(turnCheckBoxToBool(secretariat));
	    mission.setDescription(description);
	    try {
	        validationKmMax( kmmax );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_KmMax, e.getMessage() );
	    }
	    mission.setKmMaxVisite( new BigInteger(kmmax) );
	    
	    try {
	        validationRetrocession( retrocession);
	    } catch ( Exception e ) {
	        setErreur( CHAMP_Ret, e.getMessage() );
	    }
	    mission.setRetrocession(new BigInteger(retrocession));
	    
	    LocalDate dateObj = null;
	    try {
	       dateObj = validationDate( periode );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_Dat, e.getMessage() );
	    }
	    mission.setPeriode(dateObj);

	    if ( erreurs.isEmpty() ) {
	        resultat = "Succès de l'inscription.";
	    } else {
	        resultat = "Échec de l'inscription.";
	        throw new Exception("echec inscription");
	    }

	    return mission;
	}
	
	private LocalDate validationDate(String kmmax) throws Exception {
		// TODO Auto-generated method stub
		if ( kmmax != null ) {
	        if ( !kmmax.matches( "[0-9]{4}-[0-9]{2}-[0-9]{2}" ) ) {
	            throw new Exception( "Merci de saisir une Date valide." );
	        }else {
	        	return LocalDate.parse(kmmax);
	        }
	    } else {
	        throw new Exception( "Merci de saisir un kilométrage maximum." );
	    }
		
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
	
	private void validationRetrocession(String kmmax) throws Exception {
		// TODO Auto-generated method stub
		if ( kmmax != null ) {
	        if ( !kmmax.matches( "[0-9]*" ) ) {
	            throw new Exception( "Merci de saisir une retrocession valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une retrocession." );
	    }
		
	}
	
	private boolean turnCheckBoxToBool(String tt) {
		if(tt!=null) {
    		if(tt.equals("on")) {
    			return true;
    		}
    	}
		return false;
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
