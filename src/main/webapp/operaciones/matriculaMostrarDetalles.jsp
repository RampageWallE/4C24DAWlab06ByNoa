<%-- 
    Document   : matriculaMostrarDetalles
    Created on : 21/10/2023, 11:29:08 PM
    Author     : User
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" 
              href="webjars/bootstrap/5.1.2/css/bootstrap.min.css" type="text/css" />
    </head>
    <body>
    <div class="card" style="width: 50rem; padding: 30px 0px 0px 30px;">
            <h4 class="display-8">Detalles de la matricula</h4>
            <table class="table table-striped table-hover">
                <tr><th>Codigo Matricula</th><th>Codigo del curso</th><th>Monto</th><th>Asistencias</th><th>Nota</th><th>Estado</th></tr>
                <c:forEach items="${arrDetalles}" var="detalle">
                    <tr>
                        <td><input name="xcodCurso" size="5" 
                             value="<c:out value='${detalle.codigo_matricula}'/>" readonly></td>
                        <td><input name="xnomCurso" size="40" 
                             value="<c:out value='${detalle.codigo_curso}'/>" readonly></td>
                        <td><input name="xmonto" size="9" 
                             value="<c:out value='${detalle.monto}'/>" ></td>
                        <td><input name="xmonto" size="9" 
                             value="<c:out value='${detalle.asistencias}'/>" ></td>
                        <td><input name="xmonto" size="9" 
                             value="<c:out value='${detalle.nota}'/>" ></td>
                         <td><input name="xmonto" size="9" 
                             value="<c:out value='${detalle.estado}'/>" ></td>
                    </tr>
                </c:forEach>
            </table>
    </div>
    </body>
</html>
