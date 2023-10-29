<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@ page import="modelos.cBaseDatos" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>

<%
 cBaseDatos con = new cBaseDatos();
 String accion = request.getParameter("accion");
 
if (accion.compareTo ("MOSTRAR") == 0) {
    File reportFile = new File(application.getRealPath( 
                                 "/reportes/listadoDetallesMatriculaAlumno.jasper"));
    Map parameters = new HashMap();
    //-------------------------------------------
    String nro_doc=request.getParameter("nro_doc");
    parameters.put("nro_docp", nro_doc);

    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),parameters, con.Conectar());
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream ouputStream = response.getOutputStream();
    ouputStream.write(bytes, 0, bytes.length);
    ouputStream.flush();
    ouputStream.close();
 }
%>
