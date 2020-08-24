<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p style="color:blue;">${Sucess}</p>

 <form:form action="save" method="post"  modelAttribute="hsa">
    
     
   <table>
       <tr>
      <td><form:hidden path="userId"/>
      </tr>
       <tr>
       <tr>
       <td>First Name</td>
       <td><form:input path="firstName"/> </td>
       </tr>
       
         
       <tr>
          <td>Last Name</td>
          <td><form:input path="lastName"/></td>
       </tr>
    
      <tr>
       <td>Email</td>
       
       <td><form:input path="emailId"/></td>
       </tr>
       
       <tr>
       <td>Gender</td>
       
       <td>
       <form:radiobuttons path="gender"  items="${genderList}"/>
   </td>
       </tr>
       
       <tr>
       <td>Role</td>
       <td>
       <form:select path="role">
        <form:option value="">--SELECT--</form:option>
		<form:options items="${roles}" />
       </form:select>
       </td>
       </tr>
       
       <tr>
       <td><input  type="reset"  value="REST">
       <td><input type="submit"  value="Create">
       </tr>
       
   </table>

 </form:form>
  
<div>
<a href="viewAllAccounts">View All Accounts</a>
</div>

</body>
</html>