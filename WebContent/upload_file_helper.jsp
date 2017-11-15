<%@page import="org.apache.jena.ontology.OntModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="da.DA" %>
<%@ page import="da.Disease" %>
<%@ page import="java.io.*"%>
<%
      String saveFile = "";
      String contentType = request.getContentType();
      if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
            DataInputStream in = new DataInputStream(request.getInputStream());
            int formDataLength = request.getContentLength();
            byte dataBytes[] = new byte[formDataLength];
            int byteRead = 0;
            int totalBytesRead = 0;
            while (totalBytesRead < formDataLength) {
                  byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                  totalBytesRead += byteRead;
            }
            String file = new String(dataBytes);
            saveFile = file.substring(file.indexOf("filename=\"") + 10);
            saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
            saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
            int lastIndex = contentType.lastIndexOf("=");
            String boundary = contentType.substring(lastIndex + 1, contentType.length());
            int pos;
            pos = file.indexOf("filename=\"");
            pos = file.indexOf("\n", pos) + 1;
            pos = file.indexOf("\n", pos) + 1;
            pos = file.indexOf("\n", pos) + 1;
            int boundaryLocation = file.indexOf(boundary, pos) - 4;
            int startPos = ((file.substring(0, pos)).getBytes()).length;
            int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
            String fileName = saveFile;
            saveFile = "/users/lin/desktop/" + saveFile;
           
            File ff = new File(saveFile);
            FileOutputStream fileOut = new FileOutputStream(ff);
            fileOut.write(dataBytes, startPos, (endPos - startPos));
            fileOut.flush();
            fileOut.close();
            
    	  	//get the da object from the session
    	  	DA da = (DA) session.getAttribute("da");
    	  	OntModel m = da.getM();
    	  	m.read(saveFile);
    	  	da.printRdfToFile();
    	  	
%>

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



      <div>
       <h4>File Uploaded Successfully: </h4> 
       <%
            out.println(fileName);
            }
      	%>
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