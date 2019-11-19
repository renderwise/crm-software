<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Main" method="post">

<table>                 
<tr>
    <td>Day</td>
    <td>Start</td>
    <td>End</td>
    <td>Date</td>
    <td>&nbsp;</td>                                                          
</tr>               
<tr>
   <td><select name="availableDay">  
       <!--Listing days-->                                                                                         
       </select></td>
   <td><input type="text"   name="username"/></td>
  <td><input type="date"   name="availableDate" /></td>
  <td><input type="date"   name="availableDate1" /></td>
   <td><input type="submit" class="add" name="action" value="Add More"></td>
</tr>
</table>

</form>
</body>
</html>