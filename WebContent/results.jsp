
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="da.DA" %>
<%@ page import="da.Disease" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="shortcut icon" type="image/ico" href="da_logo.ico" />
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <style>
    .header,body{padding-bottom:20px}.header,.jumbotron{border-bottom:1px solid #e5e5e5}body{padding-top:20px}.footer,.header,.marketing{padding-right:15px;padding-left:15px}.header h3{margin-top:0;margin-bottom:0;line-height:40px}.footer{padding-top:19px;color:#777;border-top:1px solid #e5e5e5}@media (min-width:768px){.container{max-width:730px}}.container-narrow>hr{margin:30px 0}.jumbotron{text-align:center}.jumbotron .btn{padding:14px 24px;font-size:21px}.marketing{margin:40px 0}.marketing p+h4{margin-top:28px}@media screen and (min-width:768px){.footer,.header,.marketing{padding-right:0;padding-left:0}.header{margin-bottom:30px}.jumbotron{border-bottom:0}}
    </style>
    <style>
      .jumbotron .btn {
        height: 34px;
        padding: 6px 12px;
        margin-bottom: 16px;
        margin-left: 10px;
        font-weight: 400;
        font-size: 16px;
        line-height: 16px;
      }
      footer {
      position:absolute;bottom:0;width:100%;height:60px;
      }
	b {
		margin-right: 10px;
		margin-bottom: 10px;
	}
	xmp {
		display: inline-block;
		margin: 0;
	}
    </style>
  </head>
  <!-- -->
  <!-- -->
  <body>
    <div class="container">
      <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="index.jsp">Home</a></li>
            <li role="presentation"><a href="upload_file.jsp">Upload file</a></li>
          </ul>
        </nav>
        <img src="da_logo.png" style="width:130px;" />
      </div>
	  <!--  -->
	  <!--  -->
	  <%
	  	//get the da object from the session 
	  	DA da = (DA) session.getAttribute("da");
	  	da.populateModel();
	  	
	  	//get the patientURI from the session
	  	String patientURI = String.valueOf(session.getAttribute("patientURI"));
	  	
	  	//get the symptoms from the submitted form
	  	String symptoms = request.getParameter("patient_symptoms");
	  	
        //put the symptoms in the appropriate format e.g. { "s1" "s2" }
        symptoms = da.convertToValuesFormat(symptoms);
        
        //get the possible diseases given the symptoms
        ArrayList<Disease> possibleDiseases = new ArrayList<Disease>();
        possibleDiseases = da.getPossibleDiseases(symptoms);
        
		//give a disease probability to each Disease in the array
		da.InitializeDiseaseProbability(possibleDiseases);
		
        // recursivly check the family of the patient & accordingly update the disease probability
        da.recursiveFamilyCheck(patientURI, possibleDiseases);
        
        //just for testing..it will print the possible diseases in the console
        da.printResults(possibleDiseases);
	  %>
<!--  -->

		<div class="panel panel-default">
		  	<!-- Default panel contents -->
		  	<div class="panel-heading">Possible Diseases</div>
			<table class="table table-bordered">
				<tbody>
		
					<%for(int i=0; i< possibleDiseases.size(); i++){ 
						Disease disease = possibleDiseases.get(i);				
					%>
					<tr>
						<td><%= i + 1%></td>
						<td><b>Disease name:</b> <%= disease.getName() %>
							<br/>
							<b>Disease URI:</b> <xmp><%= disease.getDiseaseURI() %></xmp>
							<br/>
							<b>Disease probability:</b> <%= disease.getProbability() %>
							<br/>
							<b>Family members with the same disease:</b>
							<br/>
								<% for(int j=0; j< disease.getPeople().size(); j++){%>
									<xmp style="margin:0 0 10px 20px;"><%= disease.getPeople().get(j) %></xmp>
									<br>
								<% } %>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>

<!--  -->

      <footer class="footer">
        <p>&copy; 2016 CSEN 915 Semi-Structured Data and the Web</p>
      </footer>

    </div> <!-- /container -->
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <!-- Bootstrap latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
    </script>
  </body>

</html>