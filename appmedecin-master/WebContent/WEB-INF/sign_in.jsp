<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	
        <meta charset="utf-8" />
        <title>Sign-in</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
    </head>
    <body>
        <form method="post" action="sign_in">
        <input type="hidden" name="id" id="id" value="${utilisateur.id}"/>
            <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.mail}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                <br />
				
				<label for="phone">Numéro de téléphone <span class="requis">*</span></label>
                <input type="text" id="phone" name="phone" value="<c:out value="${utilisateur.telephone}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['phone']}</span>
                <br />
                
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
                <br />

				<label for="isEmp">Type de compte Employeur <span class="requis">*</span></label>
                <input type="checkbox" id="isEmp" name="isEmp" size="20" checked="<c:out value="${utilisateur.isemployeur}"/>"/>
                <br />
                
                <input type="submit" value="Inscription" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
    </body>
</html>