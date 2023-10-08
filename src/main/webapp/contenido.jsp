<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->

<%
HttpSession misession= (HttpSession) request.getSession();
String usuario = (String) misession.getAttribute("usuario");  
String estado = (String) misession.getAttribute("estado"); 
if(misession == null || misession.getAttribute("usuario") == null){
      out.print("<link rel=\"stylesheet\" \n"
                + "              href=\"webjars/bootstrap/5.1.2/css/bootstrap.min.css\" type=\"text/css\" />");
      out.println("<center>");           
      out.println("<h3>No tiene permisos para acceder a esta seccion</h3>");
      out.println("<a class='btn btn-primary' href='/WebSistema/index.html'>Ir a pagina de acceso</a>");
      out.println("</center>");
      return;
  }    
%>

<html>
    <head>
        <link rel="stylesheet" 
              href="webjars/bootstrap/5.1.2/css/bootstrap.min.css" type="text/css" />
    </head>
    <body>
        <br>
        <div class="container">
            <h1 class="display-50">Contenido de la Aplicacion</h1>
        </div>
    </body>
</html>
