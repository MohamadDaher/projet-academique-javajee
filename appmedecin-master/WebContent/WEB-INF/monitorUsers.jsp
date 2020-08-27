<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	
        <meta charset="utf-8" />
        <title>Monitor</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
 		<script>
			function reset(){
				document.getElementById('emp').value='false'; 
				document.getElementById('dis').value='false';
				document.getElementById('edi').value='false'; 
				document.getElementById('spr').value='false';
				}
 		</script>
    </head>
    <body onload="reset();">
    <form id="actionUsers" method="post" action="MonitorUsers">
    	<input type="hidden" id="todosmt" name="todosmt"> 
    	<input type="hidden" id="edi" name="edi" value="false"
			onload="this.value='false';" oncuechange="this.value='false"> <input type="hidden" id="spr" name="spr" value="false" onload="this.value='false';">
    	
    	<input type="hidden" id="emp" name="emp" value="false"  onload="this.value='false';">
    	<input type="hidden" id="dis" name="dis" value="false"  onload="this.value='false';">
		<table id="table-1">
		<tr>
			<th style="border:1px solid #000;">Mail</th>
			<th style="border:1px solid #000;">T&eacute;l&eacute;phone</th>
			<th style="border:1px solid #000;">Mot de passe</th>
			<th style="border:1px solid #000;">Profil</th>
			<th style="border:1px solid #000;">Disponibilit&eacute;s</th>
			<th style="border:1px solid #000;">Edit</th>
			<th style="border:1px solid #000;">Supress</th>
		</tr>
    	<c:forEach items="${tablearguments}" var="usr">
	    	<tr>
	    	
	    	<td style="border:1px solid #000;">
	    		<c:out value="${usr.mail}"></c:out>
	    	</td>
			<td style="border:1px solid #000;"><c:out value="0${usr.telephone}"></c:out></td>
			<td style="border:1px solid #000;"><c:out value="XXX"></c:out></td>
			<td style="border:1px solid #000;"><button id="bt_prof" type="button" value="false" onclick="reset();document.getElementById('emp').value='true'; document.getElementById('todosmt').value='<c:out value="${usr.id}"></c:out>';">Profil</button></td>
			<td style="border:1px solid #000;"><button id="bt_disp" type="submit" value="false" onclick="reset();document.getElementById('dis').value='true'; document.getElementById('todosmt').value='<c:out value="${usr.id}"></c:out>';" >Disponibilités</button></td>
			<td style="border:1px solid #000;"><button id="bt_edit" type="submit" value="false" onclick="reset();document.getElementById('edi').value='true'; document.getElementById('todosmt').value='<c:out value="${usr.id}"></c:out>';">Edit</button></td>
			<td style="border:1px solid #000;"><button id="bt_supr" type="submit" value="false" onclick="reset();document.getElementById('spr').value='true'; document.getElementById('todosmt').value='<c:out value="${usr.id}"></c:out>';" >Supress</button></td> 	
	    	</tr>
	    	
	    	
		</c:forEach>
		</table>
	</form>
    </body>
</html>