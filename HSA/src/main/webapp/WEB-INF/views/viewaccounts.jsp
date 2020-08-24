<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(document).ready(function() {

		$("#roles").change(function(){
$("#response_table").empty();
			$("#roles").find('table').remove();
			var role=$("#roles").val();
			  $.ajax({
	                 type :"GET",
	                   url  : "viewAllAccounts?role="+role,
	                   success : function(res){
	                	  // alert("res  "+res);

	                	   var tr = '';
	                	   var action='';
	               var table =' <table id="response_table" border="1"><thead> <tr><th>Name</th><th>Email</th><th>Action</th></tr></thead><tbody>';
	                	     
	                	
	                                        $.each(res,function(key,value){
	                                	//alert("key  "+value.role+"   value"+value.role);

	                                	 var v=value.deleteSwitch;
	                                	// alert("val ::  "+v);
	                                	 //activateAcc

	                                	   if(v==='N'){
                                            action='<a href=editAcc?id='+value.userId+'>Edit</a>' +'||<a href=deleteAcc?id='+value.userId+'>Delete</a>';
	                                		 // action=' <a href="https://www.w3schools.com">Visit W3Schools.com!</a>';
		                                } else{
		                                	action='<a href=editAcc?id='+value.userId+'>Edit</a>' + '|| <a href=activateAcc?id='+value.userId+'>Activate</a>';
		                                	 //action=' <a href="https://www.w3schools.com">Visit W3Schools.com!</a>';
			                                }  
			                                //<td>'+action+'</td>
	                                	
	                                	 table += '<tr><td>' + value.firstName + '</td><td>' + value.emailId + '</td><td>' +action + '</td></tr>';
	                                     });

	                                     table += '  </tbody> </table>';

	               	                	$('#response_table').append(table);
	                                       // $('#response_table tbody').html(tr);
	                       }
	                
	                });
	    	  
	          });
	              
			
			});

	function deleteConfirm(){
		return confirm("Are you sure, you want to delete?");
	}
</script>
</head>
<body>


<div>
<p style="color:blue;">${msg}</p>
</div>

  <div>
   
    <table>
    <tr>
    <td>Select Role :: </td>
     <td><select name="role"  id="roles">
     <option value="-1">Select</option>
       <option value="ADMIN">ADMIN</option>
       <option value="CASE WORKER">CASE WORKER</option>
     </select></td>
    </tr>
    </table>
  </div>


<br><br>
<div id="response_table">
</div>
<!-- <table id="response_table" border="1">
  <thead>
    <tr>
      <th>Name</th>
      <th>Email</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    
  </tbody>
</table> -->	


<%-- <div>
     <a href="addContact">Add new Contact</a>
     </div>
     
	<table border="1" id="table_id">
		<thead>
			<tr>
				<th>Slno</th>
				<th>ContactName</th>
				<th>ContactEmail</th>
				<th>ContactNumber</th>
				<th>operations</th>
				<th>values</th>
			</tr>
		</thead>
		<tbody>
			<tr>

				<c:forEach items="${listContact}" var="contactObj" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${contactObj.contactName}</td>
						<td>${contactObj.contactEmail}</td>
						<td>${contactObj.contactNumber}</td>
						<td>
						<c:if test="${contactObj.contactName eq 'narasimha'}">
                       <input type="checkbox"  value="${contactObj.contactName}"  checked="checked"> ${contactObj.contactName }
                      </c:if>
                        
                        	
				
<c:choose>
    <c:when test="${contactObj.contactName=='wade'}">
           <input type="checkbox"  value="${contactObj.contactName}"  checked="checked"> ${contactObj.contactName}
    </c:when>    
    <c:otherwise>
      <input type="checkbox"  value="${contactObj.contactName}" > ${contactObj.contactName}
    </c:otherwise>
</c:choose>
     
                      	</td>
						<td><a href="editContact?cid=${contactObj.contactId}">Edit</a>
							|| <a href="deleteContact?cid=${contactObj.contactId}"   onclick="return  deleteConfirm()">Delete</a></td>
					</tr>



				</c:forEach>

			</tr>
		</tbody>
	</table>

 --%>

</body>

</html>