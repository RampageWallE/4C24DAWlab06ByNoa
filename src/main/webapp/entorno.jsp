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
        <title>SistemaWeb</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <frameset rows="80,*" border="0" frameborder="0" y framespacing="40">
          <frame src=titulo.html name=titulo>
          <frameset cols="250,*">
              <frame src=opciones.jsp name=izquierda>
              <frame src=contenido.jsp name=derecha>
          </frameset>
       </frameset> 
    </head>
</html>

