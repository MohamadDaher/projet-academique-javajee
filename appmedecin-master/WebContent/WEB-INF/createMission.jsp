<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	
        <meta charset="utf-8" />
        <title>créer une mission</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
 		
    </head>
    <body>
        <form id="empForm" method="post" action="createMission">
        <input type="hidden" name="id" id="id" value="${mission.id}"/>
            <fieldset>
                <legend>Disponibilité de travail</legend>
                <p>Vous pouvez créer une mission où vous êtes disponibles.</p>
				
				<label for="vD">La mission inclus des visites à domicile </label>
                <input type="checkbox" id="vD" name="vD" size="20" checked="<c:out value="${mission.visiteDomicile}"/>" />
                <br />
                
                <label for="kmmax">Kilométrage maximum de la visite à domicile </label>
                <input type="number" id="kmmax" name="kmmax" value="<c:out value="${mission.kmMaxVisite}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['kmmax']}</span>
                <br />
                
                <label for="aS">La mission se pratique-t-elle seul  </label>
                <input type="checkbox" id="aS" name="aS" size="20" checked="<c:out value="${mission.activiteSeule}"/>"/>
                <br />
                
                 <label for="retro">Rémunération de la mission <span class="requis">*</span></label>
                <input type="number" id="retro" name="retro" value="<c:out value="${mission.retrocession}"/>" size="20" maxlength="60" min="0" />
                <span class="erreur">${form.erreurs['retro']}</span>
                <br />
                
                <label for="secr">Le lieu de travail possède un secreteriat</label>
                <input type="checkbox" id="secr" name="secr" size="20" checked="<c:out value="${mission.secreteriat}"/>"/>
                <br />
                
                <label for="desc">Description <span class="requis">*</span></label>
                <input type="text" id="desc" name="desc" value="<c:out value="${mission.description}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['desc']}</span>
                <br />
                  
                <label for="date">Date de la mission <span class="requis">*</span></label>
                <input type="date" id="date" name="date" value="<c:out value="${mission.periode}"/>" placeholder="yyyy-mm-jj" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['date']}</span>
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