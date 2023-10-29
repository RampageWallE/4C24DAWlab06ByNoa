<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="modelos.cBaseDatos"%>

<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%
  cBaseDatos con = new cBaseDatos();
  File reportFile = new File(application.getRealPath("/reportes/listadoAlumnos.jasper"));
  Map parameters = new HashMap();
  //-------------------------------------------
  byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (),
                      parameters, con.Conectar());
 
  response.setContentType("application/pdf");
  response.setContentLength(bytes.length);
  ServletOutputStream ouputStream = response.getOutputStream();
  ouputStream.write(bytes, 0, bytes.length);
  ouputStream.flush();
%>
