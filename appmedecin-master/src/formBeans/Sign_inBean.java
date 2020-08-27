package formBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Users;


public class Sign_inBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String CHAMP_ID ="id";
	private static final String CHAMP_EMAIL  = "email";
	private static final String CHAMP_PHONE  = "phone";
    private static final String CHAMP_PASS   = "motdepasse";
    private static final String CHAMP_CONF   = "confirmation";
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

	public Users inscrireUtilisateur( HttpServletRequest request ) throws Exception {
	    String id = getValeurChamp( request, CHAMP_ID);
		String email = getValeurChamp( request, CHAMP_EMAIL );
	    String phone = getValeurChamp( request, CHAMP_PHONE );
	    String motDePasse = getValeurChamp( request, CHAMP_PASS );
	    String confirmation = getValeurChamp( request, CHAMP_CONF );
	    
	    
	    Users utilisateur = new Users();
	    try {
	    utilisateur.setId(Long.parseLong(id));
	    }catch(Exception e) {}
	    try {
	        validationEmail( email );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_EMAIL, e.getMessage() );
	    }
	    utilisateur.setMail( email );
	    
	    try {
	        validationPhone( phone );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_PHONE, e.getMessage() );
	    }
	    utilisateur.setTelephone( Long.parseLong(phone));

	    try {
	        validationMotsDePasse( motDePasse, confirmation );
	        utilisateur.setMdp( motDePasse );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_PASS, e.getMessage() );
	        setErreur( CHAMP_CONF, null );
	    }
	    

	    if ( erreurs.isEmpty() ) {
	        resultat = "Succès de l'inscription.";
	    } else {
	        resultat = "Échec de l'inscription.";
	        throw new Exception("echec inscription");
	    }

	    return utilisateur;
	}

	private void validationPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		if ( phone != null ) {
	        if ( !phone.matches( "^0[0-9]{9}" ) ) {
	            throw new Exception( "Merci de saisir un numéro de téléphone valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir un numéro de téléphone." );
	    }
		
	}

	private void validationEmail( String email ) throws Exception {
	    if ( email != null ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}

	private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
	    if ( motDePasse != null && confirmation != null && motDePasse != "") {
	        if ( !motDePasse.equals( confirmation ) ) {
	            throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
	        } else if ( motDePasse.length() < 8 ) {
	            throw new Exception( "Les mots de passe doivent contenir au moins 8 caractères." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
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
