<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	
        <meta charset="utf-8" />
        <title>Monitor</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
 		
    </head>
    <body>
    <form id="actionUsers" method="post" action="monitorMission">
    	<input type="hidden" id="todosmt" name="todosmt"> 
    	<input type="hidden" id="edi" name="edi" value="false" onload="this.value='false';" oncuechange="this.value='false"> 
		<input type="hidden" id="spr" name="spr" value="false" onload="this.value='false';">
    	
		<table id="table-1">
		<tr>
			<th style="border:1px solid #000;">visiteDomicile</th>
			<th style="border:1px solid #000;">kmMaxVisite</th>
			<th style="border:1px solid #000;">activiteSeule</th>
			<th style="border:1px solid #000;">retrocession</th>
			<th style="border:1px solid #000;">secreteriat</th>
			<th style="border:1px solid #000;">typePatient</th>
			<th style="border:1px solid #000;">description</th>
			<th style="border:1px solid #000;">periode</th>
			<th style="border:1px solid #000;">Edit</th>
			<th style="border:1px solid #000;">Supress</th>
		</tr>
    	<c:forEach items="${tablearguments}" var="usr">
	    	<tr>
			<td style="border:1px solid #000;"><c:out value="${usr.visiteDomicile}"></c:out></td>
			<td style="border:1px solid #000;"><c:out value="${usr.kmMaxVisite}"></c:out></td>
			<td style="border:1px solid #000;"><c:out value="${usr.activiteSeule}"></c:out></td>
			<td style="border:1px solid #000;"><c:out value="${usr.retrocession}"></c:out></td>
			<td style="border:1px solid #000;"><c:out value="${usr.secreteriat}"></c:out></td>
			<td style="border:1px solid #000;"><c:out value="${usr.typePatient}"></c:out></td>
			<td style="border:1px solid #000;"><c:out value="${usr.description}"></c:out></td>
			<td style="border:1px solid #000;"><c:out value="${usr.periode}"></c:out></td>
			<td style="border:1px solid #000;"><button id="bt_edit" type="submit" value="false" onclick="document.getElementById('edi').value='true'; document.getElementById('todosmt').value='<c:out value="${usr.id}"></c:out>';">Edit</button></td>
			<td style="border:1px solid #000;"><button id="bt_supr" type="submit" value="false" onclick="document.getElementById('spr').value='true'; document.getElementById('todosmt').value='<c:out value="${usr.id}"></c:out>';" >Supress</button></td> 	
	    	</tr>
	    	
	    	
		</c:forEach>
		</table>
	</form>
    </body>
</html>