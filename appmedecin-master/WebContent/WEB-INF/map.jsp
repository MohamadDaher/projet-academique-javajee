<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Map</title>
<style>
      #map {
        width: 90%;
	margin-left:5%;
        height: 500px;
        background-color: grey;
	border:4px solid #000000;
	border-radius:10px;
      }
</style>
<script>
var miexec = false;
var map; // var globale de la carte pour l'utiliser partout 
var latlng = 1; //initialise la pos client pour la recupérée une fois changée
var maps_api_charger = false;//variable de chargement du js de google
function initMap() {
	var paris = {lat: 	48.864716, lng: 2.349014};
        map = new google.maps.Map(document.getElementById('map'), {
    	    zoom: 6,
    	    center: paris
        });
    maps_api_charger = true;    	
}

function when(conditionFunc, execFunc, interval){//fonction when 
    if (conditionFunc()){
        return(execFunc());
    }else{
        setTimeout(function(){ when(conditionFunc, execFunc, interval);}, interval);
    }
}

function when2(lt,lg,id,interval){//fonction when 
    if (maps_api_charger){
        requete_MI(lt,lg,id);
    }else{
        setTimeout(function(){ when2(lt,lg,id,interval);}, interval);
    }
}

function condi(){return (maps_api_charger == true);}//vérifie que l'api est chargé

function requete_MI(l,lg,id){
	var uluru = {lat: l, lng: lg};
	  // The marker, positioned at Uluru
	try{
		var marker = new google.maps.Marker({position: uluru,
			icon: "medi2.png",
			map: map});
		 var infowindow = new google.maps.InfoWindow({
			    content: "<a href='/appMedecin/monitorMission?mi_id="+id+"'>Accéder à la mission</a>"
			  });
		 marker.addListener('click', function() {
			    infowindow.open(map, marker);
			  });
					  
					
		miexec = true;
		
	}catch(Exception){
		miexec = false;	
	}
}
</script>
</head>
<body>
<a href='<c:out value="/appMedecin/MonitorUsers"></c:out>'><c:out value="Profil"></c:out></a> 
<!--test-->
	<c:forEach items="${cm}" var="usr">
	<script type="text/javascript">
		when2(${usr.employeur.longitude},${usr.employeur.lattitude},${usr.id},10);
			
	</script>
	</c:forEach>
	<div id="map">
	</div>
	
	<div style="display : inline;">
		
		<jsp:include page='${pp}'></jsp:include>
	</div>

<script async="true" defer src="https://maps.googleapis.com/maps/api/js?libraries=visualization&key=AIzaSyC49edhgqgAHNw-99qj15X2UJinq5XIF6U&callback=initMap" type="text/javascript">
</script>
</body>
</html>