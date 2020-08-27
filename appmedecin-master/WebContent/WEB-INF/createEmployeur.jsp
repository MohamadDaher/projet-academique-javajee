<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	
        <meta charset="utf-8" />
        <title>créer son compte employeur</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
 		
    </head>
    <body>
    <script type="text/javascript">
    var maps_api_charger = false;//variable de chargement du js de google

    function initMap() {
        charge();
    }
    
    function charge(){
    	maps_api_charger = true;
    }	

    function when(conditionFunc, execFunc, interval){//fonction when 
        if (conditionFunc()){
            return(execFunc());
        }else{
            setTimeout(function(){ when(conditionFunc, execFunc, interval);}, interval);
        }
    }

    function condi(){
        return (document.getElementById("ggm") != null);
	}//vérifie que l'api est chargé

    
 		function geocodeAddress(cp,ville) {
 			var geocoder = new google.maps.Geocoder();
 		    var address = cp+' '+ville;
 		    return geocoder.geocode({'address': address}, function(results, status) {
 				if (status === 'OK') {
 					var lat = results[0].geometry.location.lat();
 					var lng = results[0].geometry.location.lng();
 					document.getElementById('zipcode').value = lat;
 					document.getElementById('ville').value = lng;
					var x = document.createElement("input");
 					x.setAttribute("type", "hidden");
 					x.setAttribute("id","ggm");
 					x.setAttribute("value","true");
 					document.body.appendChild(x);
 				}else {
 					return(null,null); 
 				}
 		    });
 		}

 		function validateAddress(){
			var lat = document.getElementById('zipcode').value;
			var lng = document.getElementById('ville').value;
			geocodeAddress(lat,lng);
			when(condi,aexec,10);
						
 	 	}

 	 	function aexec(){
 	 		document.getElementById('empForm').submit();
 	 	 }
 		</script>
        <form id="empForm" method="post" action="createEmployeur">
            <fieldset>
                <legend>Compte employeur !</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>

                <label for="kmmax">Kilométrage maximum de recherche de collaborateurs <span class="requis">*</span></label>
                <input type="number" id="kmmax" name="kmmax" value="<c:out value="${employeur.kmmax}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['kmmax']}</span>
                <br />
				
				<label for="zipcode"> Code Postal<span class="requis">*</span></label>
                <input type="text" id="zipcode" name="zipcode" value="" size="20" maxlength="20" />
                
                <br />
                
                <label for="ville">Ville<span class="requis">*</span></label>
                <input type="text" id="ville" name="ville" value="" size="20" maxlength="20" />
                <br />

                
                <button type="button" value="Inscription" class="sansLabel"  onclick="validateAddress()">
                </button>
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
 
<script async="true" defer src="https://maps.googleapis.com/maps/api/js?libraries=visualization&key=AIzaSyC49edhgqgAHNw-99qj15X2UJinq5XIF6U&callback=initMap" type="text/javascript">
</script>
    </body>
</html>