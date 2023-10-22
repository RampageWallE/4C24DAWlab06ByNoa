/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.cBaseDatos;
import modelos.*;
import dao.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
@WebServlet(name = "controladorPrincipal", urlPatterns = {"/controladorPrincipal"})
public class controladorPrincipal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            cBaseDatos objDatos = new cBaseDatos();
            String accion = request.getParameter("accion");
            IAlumnosDAO dao = new AlumnoDAOImpl();
            ICursosDAO dao1 = new CursoDAOImpl();
            IMatriculaDAO dao2 = new MatriculaDAOImpl();



            if (accion == null) {
                accion = "bienvenida";
            }
            if (accion.equals("bienvenida")) {
                request.getRequestDispatcher("/contenido.jsp").forward(request, response);
            } else if (accion.equals("listadoAreas")) {
                Vector arrAreas = (Vector) objDatos.getAreas();
                request.setAttribute("arrAreas", arrAreas);
                request.getRequestDispatcher("/mantenimientos/listadoAreas.jsp").forward(
                        request, response);
            } else if (accion.equals("NuevoEliminarArea")) {
                if (request.getParameter("boton").compareTo("Nuevo Registro") == 0) {
                    Vector registro = new Vector();
                    registro.add("");
                    registro.add("");
                    registro.add("");
                    registro.add("");

                    request.setAttribute("registro", registro);
                    request.setAttribute("operacion", "INSERT");
                    request.setAttribute("titulo", "Nueva Area");
                    request.getRequestDispatcher("/mantenimientos/editarAreas.jsp").forward(
                            request, response);
                }else {
                    String[] datos = request.getParameterValues("xcod");
                    objDatos.eliminarAreas(datos);
                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
                       request,response );
                }

            } else if (accion.compareTo("GRABAR_AREA") == 0) {
                if (request.getParameter("boton").compareTo("GRABAR") == 0) {
                    String operacion = request.getParameter("operacion");
                    if (operacion.equals("INSERT")) {
                        String[] datos = new String[3];
                        datos[0] = request.getParameter("xnom");
                        datos[1] = request.getParameter("xabr");
                        datos[2] = request.getParameter("xest");
                        objDatos.grabarNuevaArea(datos);    
                    }else {
                    String[] datos = new String[4];
                    datos[0] = request.getParameter("xcod");
                    datos[1] = request.getParameter("xnom");
                    datos[2] = request.getParameter("xabr");
                    datos[3] = request.getParameter("xest");
                    objDatos.modificarArea(datos);
                    }
                }
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
                        request, response);
                } 
                else if ( accion.compareTo( "modificarArea" ) == 0 ) {
                    String xcod = request.getParameter( "xcod" );
                    Vector registro = objDatos.buscarArea(xcod);
                    request.setAttribute( "registro", registro );
                    request.setAttribute( "operacion","UPDATE");
                    request.setAttribute( "titulo","Modificar Area");
                    request.getRequestDispatcher( "/mantenimientos/editarAreas.jsp" ).forward( 
                     request,response );
                }
                else if (accion.equals("listadoAlumnos")){
                    List<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
                    //IAlumnosDAO dao = new AlumnoDAOImpl();
                    arrAlumnos = dao.obtener();
                    request.setAttribute("arrAlumnos", arrAlumnos);
                    request.getRequestDispatcher("/mantenimientos/listadoAlumnos.jsp").forward(
                            request, response);
                } else if (accion.equals("NuevoEliminarAlumno")){
                    if(request.getParameter("boton").equals("Eliminar Registros")){
                        String[] codigos = request.getParameterValues("xcod");
                        dao.eliminar(codigos);
                        request.getRequestDispatcher("/controladorPrincipal?accion=listadoAlumnos").forward(
                            request, response);
                    }
                    else if(request.getParameter("boton").compareTo("Nuevo Registro") == 0){
                        Alumnos alumno = new Alumnos();
                        request.setAttribute("alumno", alumno);
                        request.setAttribute("operacion", "INSERT");
                        request.setAttribute("titulo","Nuevo Alumno");
                        request.getRequestDispatcher("/mantenimientos/editarAlumnos.jsp").forward(
                                request, response);
                    }
                } else if (accion.compareTo("GRABAR_ALUMNO")== 0){
                    if(request.getParameter("boton").compareTo("GRABAR")==0){
                    String operacion = request.getParameter("operacion");
                    String strDate = request.getParameter("xfec");
                    Date xfec = Date.valueOf(strDate);
                    Alumnos alumno = new Alumnos();
                    alumno.setCodigo(Integer.parseInt(request.getParameter("xcod")));
                    alumno.setNombre(request.getParameter("xnom"));
                    alumno.setDireccion(request.getParameter("xdir"));
                    alumno.setEmail(request.getParameter("xema"));
                    alumno.setTelefono(request.getParameter("xtel"));
                    alumno.setCelular(request.getParameter("xcel"));
                    alumno.setSexo(request.getParameter("xsex"));
                    alumno.setFec_nac(xfec);
                    alumno.setEstado(request.getParameter("xest"));
                    if(operacion.equals("INSERT")){
                        dao.registrar(alumno);}
                    else{
                        dao.actualizar(alumno);
                        }
                    }
                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoAlumnos").forward(
                            request, response);
                } else if (accion.compareTo("modificarAlumno")==0){
                    String xcod = request.getParameter("xcod").trim();
                    //IAlumnosDAO dao = new AlumnoDAOImpl();
                    Alumnos alumno = dao.buscar(Integer.parseInt(xcod));
                    request.setAttribute("alumno", alumno);
                    request.setAttribute("operacion", "UPDATE");
                    request.setAttribute("titulo", "Modificar Alumno");
                    request.getRequestDispatcher("/mantenimientos/editarAlumnos.jsp").forward(
                            request, response);
                }
                
                //Aca comienza la opcion Cursos
                else if (accion.equals("listadoCursos")){
                    List<Cursos> arrCursos = new ArrayList<Cursos>();
                    //IAlumnosDAO dao = new AlumnoDAOImpl();
                    arrCursos = dao1.obtener();
                    request.setAttribute("arrCursos", arrCursos);
                    request.getRequestDispatcher("/mantenimientos/listadoCursos.jsp").forward(
                            request, response);
                } else if (accion.equals("NuevoEliminarCurso")){
                    if(request.getParameter("boton").equals("Eliminar Registros")){
                        String[] codigos = request.getParameterValues("xcod");
                        dao1.eliminar(codigos);
                        request.getRequestDispatcher("/controladorPrincipal?accion=listadoCursos").forward(
                            request, response); 
                    }
                    else if(request.getParameter("boton").compareTo("Nuevo Registro") == 0){
                        Cursos curso = new Cursos();
                        request.setAttribute("curso", curso);
                        request.setAttribute("operacion", "INSERT");
                        request.setAttribute("titulo","Nuevo Curso");
                        request.getRequestDispatcher("/mantenimientos/editarCursos.jsp").forward(
                                request, response);
                    }
                } else if (accion.compareTo("GRABAR_CURSO")== 0){
                    if(request.getParameter("boton").compareTo("GRABAR")==0){
                    String operacion = request.getParameter("operacion");
                    
                    
                        try {
                            String strCodigo = request.getParameter("xcod");
                            String strNombre = request.getParameter("xnom");
                            String strCosto = request.getParameter("xcos");
                            String strDate1 = request.getParameter("xfec_ini");
                            String strDate2 = request.getParameter("xfec_fin");    
                            String strDuracion = request.getParameter("xdur");
                            String strSesiones = request.getParameter("xses");
                            String strCapacidad = request.getParameter("xcap");
                            String strInscritos = request.getParameter("xins");
                            String strEstado = request.getParameter("xest");


                            Date xfec_ini = Date.valueOf(strDate1);
                            Date xfec_fin = Date.valueOf(strDate2);
                            float costo = Float.parseFloat(strCosto);

                            Cursos curso = new Cursos();

                            curso.setCodigo(Integer.parseInt(strCodigo));
                            curso.setNombre(request.getParameter("xnom"));
                            curso.setCosto(costo);
                            curso.setFec_ini(xfec_ini);
                            curso.setFec_fin(xfec_fin);
                            curso.setDuracion(Integer.parseInt(strDuracion));
                            curso.setSesiones(Integer.parseInt(strSesiones));
                            curso.setCapacidad(Integer.parseInt(strCapacidad));
                            curso.setInscritos(Integer.parseInt(strInscritos));
                            curso.setEstado(request.getParameter("xest"));
                            System.out.println(curso.getNombre());
                            System.out.println(curso.getCosto());    
                            System.out.println(curso.getFec_ini());
                            System.out.println(curso.getFec_fin());  
                            System.out.println(curso.getDuracion());                            
                            System.out.println(curso.getSesiones());   
                            System.out.println(curso.getCapacidad());      
                            System.out.println((char) curso.getInscritos());
                            System.out.println(curso.getEstado());
                            if(operacion.equals("INSERT")){
                                dao1.registrar(curso);}
                            else{
                                dao1.actualizar(curso);
                                }
                        } catch (Exception e) {
                            System.out.println("**********************");
                            System.out.println( e.getMessage());
                            System.out.println("**********************");

                        }



                    }
                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoCursos").forward(
                            request, response);
                } else if (accion.compareTo("modificarCurso")==0){
                    String xcod = request.getParameter("xcod").trim();
                    //IAlumnosDAO dao = new AlumnoDAOImpl();
                    Cursos curso = dao1.buscar(Integer.parseInt(xcod));
                    request.setAttribute("curso", curso);
                    request.setAttribute("operacion", "UPDATE");
                    request.setAttribute("titulo", "Modificar Curso");
                    request.getRequestDispatcher("/mantenimientos/editarCursos.jsp").forward(
                            request, response);
                } 
                
                //Aca finaliza opcion Cursos
                else if (accion.compareTo("matriculaMostrarAlumnos") == 0){
                    if(request.getParameter("modo").compareTo("Lista")== 0){
                        List<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
                        Alumnos alumno = new Alumnos();
                        alumno.setNombre(" ");
                        //dao2 = IMatriculaDAO
                        arrAlumnos = dao2.buscarAlumnos(alumno);
                        request.getRequestDispatcher("/operaciones/matriculaMostrarAlumnos.jsp").forward(
                                request, response);
                    }else if(request.getParameter("boton").compareTo("Buscar") == 0){
                        List<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
                        Alumnos alumno = new Alumnos();
                        alumno.setNombre(request.getParameter("xalu"));
                        //dao2 = IMatriculaDAO
                        arrAlumnos = dao2.buscarAlumnos(alumno);
                        request.setAttribute("arrAlumnos", arrAlumnos);
                        request.getRequestDispatcher("/operaciones/matriculaMostrarAlumnos.jsp").forward(
                                request, response);   
                    }else{
                        int xcodAlumno = Integer.parseInt(request.getParameter("xcodAlumno"));
                        Alumnos alumno = new Alumnos();
                        alumno = dao.buscar(xcodAlumno);
                        
                        List<Cursos> arrCursos = new ArrayList<Cursos>();
                        arrCursos = dao2.buscarCursos();
                        
                        request.setAttribute("arrCursos", arrCursos);
                        request.setAttribute("alumno", alumno);
                        request.getRequestDispatcher("/operaciones/matriculaMostrarCursos.jsp").forward(
                                request, response);
                    }
                }
                else if(accion.compareTo("matriculaMostrarSubtotal") == 0 ){
                    String xcodCursos[] = request.getParameterValues("xcodCurso");
                    List<Cursos> arrCursos = new ArrayList<Cursos>();
                    //dao1 = ICursosDAO
                    double total = 0;
                    for (int xc = 0; xc < xcodCursos.length; xc++){
                        Cursos curso = new Cursos();
                        curso = dao1.buscar(Integer.parseInt(xcodCursos[xc]));
                        double costo = curso.getCosto();
                        total = total + costo; 
                        arrCursos.add(curso);
                    }
                    int xcodAlumno = Integer.parseInt(request.getParameter("xcodAlumno"));
                    Alumnos alumno = new Alumnos();
                    alumno = dao.buscar(xcodAlumno);
                    cBaseDatos co = new cBaseDatos();
                    String xcodMatricula = "MAT" + co.generarCodigo("matriculas", "codigo");
                    request.setAttribute("arrCursos", arrCursos);
                    request.setAttribute("alumno", alumno);
                    request.setAttribute("total", total);
                    request.setAttribute("xmatri", xcodMatricula);
                    request.getRequestDispatcher("/operaciones/matriculaMostrarSubtotal.jsp").forward(
                            request, response);
                }
                else if(accion.compareTo("matriculaGrabar") == 0){
                    if (request.getParameter("boton").compareTo("GRABAR") == 0){
                        String xcodAlumno = request.getParameter("xcodAlumno");
                        String xcodCursos[] = request.getParameterValues("xcodCurso");
                        String xmontos[] = request.getParameterValues("xmonto");
                        
                        String[] datosMatricula = new String[3];
                        datosMatricula[0] = request.getParameter("xndoc");
                        datosMatricula[1] = xcodAlumno;
                        datosMatricula[2] = request.getParameter("xtotal");
                        
                        boolean rpta = dao2.grabarMatricula(datosMatricula, xcodCursos, xmontos);
                        if (rpta){
                            out.println("<br><h2>Registro grabado correctamente!!</h2>");
                        }else{
                            out.println("<br><h2>Error al grabar Matricula!!</h2>");
                        }
                    }
                }
                //ACA INICIA BUSQUEDA DE MATRICULAS
                else if (accion.compareTo("matriculaMostrarMatriculas") == 0){
                    if(request.getParameter("modo").compareTo("Lista")== 0){
                        List<Matriculas> arrMatriculas = new ArrayList<Matriculas>();
                        //dao2 = IMatriculaDAO
                        arrMatriculas = dao2.listarMatriculas();
                        request.setAttribute("arrMatriculas", arrMatriculas);
                        request.getRequestDispatcher("/operaciones/matriculaMostrarMatriculas.jsp").forward(
                                request, response);
                    }else if(request.getParameter("boton").compareTo("Buscar") == 0){
                        List<Matriculas> arrMatriculas = new ArrayList<Matriculas>();
                        Matriculas matricula = new Matriculas();
                        matricula.setNro_doc(request.getParameter("xmat"));
                        //dao2 = IMatriculaDAO
                        arrMatriculas = dao2.buscarMatriculas(matricula);
                        request.setAttribute("arrMatriculas", arrMatriculas);
                        request.getRequestDispatcher("/operaciones/matriculaMostrarMatriculas.jsp").forward(
                                request, response);   
                    }else{
                        int xcodMatricula = Integer.parseInt(request.getParameter("xcodMatricula"));
                        Matriculas matricula = new Matriculas();
                        matricula.setCodigo(xcodMatricula);
                        List<Detalles> arrDetalles = new ArrayList<Detalles>();
                        arrDetalles = dao2.buscarDetalles(matricula);
                        request.setAttribute("arrDetalles", arrDetalles);
                        request.getRequestDispatcher("/operaciones/matriculaMostrarDetalles.jsp").forward(
                                request, response);
                    }
                }
                
                //ACA FINALIZA BUSQUEDA DE MATRICULAS                
                else {
                    out.println("Accion: (" + accion + ") no reconocida...");
                }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            out.close();
        }

    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
