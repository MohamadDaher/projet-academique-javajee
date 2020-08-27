<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	
        <meta charset="utf-8" />
        <title>créer une disponibilité de travail</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
        <%@ page import="servlets.ServletsCommons" %>
 		<% ServletsCommons.setEdit(request,response); %>
    </head>
    <body>
        <form id="empForm" method="post" action='createDispoTravail'>
        <input type="hidden" name="id" id="id" value="${dispo.id}"/>
            <fieldset>
                <legend>Disponibilité de travail</legend>
                <p>Vous pouvez créer des horaires où vous êtes disponibles.</p>

                <label for="date">Date de la disponibilité <span class="requis">*</span></label>
                <input type="date" id="date" name="date" value="<c:out value="${dispo.date}"/>" placeholder="yyyy-mm-jj" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['date']}</span>
                <br />
				
				<label for="debut"> Heure de début<span class="requis">*</span></label>
                <input type="time" id="debut" name="debut" value="<c:out value="${dispo.heureDebut}"/>" placeholder="hh:mm" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['debut']}</span>
                <br />
                
                <label for="fin">Heure de fin<span class="requis">*</span></label>
                <input type="time" id="fin" name="fin" value="<c:out value="${dispo.heureFin}"/>" placeholder="hh:mm" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['fin']}</span>
                <br />

                
                <button type="submit" value="" class="sansLabel">
                	Création
                </button>
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
    </body>
</html>