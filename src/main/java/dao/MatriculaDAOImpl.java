/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import modelos.Alumnos;
import modelos.Cursos;
import modelos.Detalles;
import modelos.Matriculas;
import modelos.cBaseDatos;

/**
 *
 * @author User
 */
public class MatriculaDAOImpl implements IMatriculaDAO {

    @Override
    public List<Alumnos> buscarAlumnos(Alumnos alumno) {
        Connection co =null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM alumnos WHERE nombre LIKE'%" + alumno.getNombre() + "%'";
        List<Alumnos> listaAlumnos= new ArrayList<Alumnos>();

        try {			
                cBaseDatos con = new cBaseDatos();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Alumnos nalumno=new Alumnos();
                        nalumno.setCodigo(rs.getInt(1));
                        nalumno.setNombre(rs.getString(2));
                        nalumno.setDireccion(rs.getString(3));
                        nalumno.setEmail(rs.getString(4));
                        nalumno.setTelefono(rs.getString(5));
                        nalumno.setCelular(rs.getString(6));
                        nalumno.setSexo(rs.getString(7));
                        nalumno.setFec_nac(rs.getDate(8));
                        nalumno.setEstado(rs.getString(9));
                        listaAlumnos.add(nalumno);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "método buscarAlumnos");
        }
        return listaAlumnos;
    }

    @Override
    public List<Cursos> buscarCursos() {
        Connection co =null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM cursos ORDER BY codigo";
        List<Cursos> listaCursos= new ArrayList<Cursos>();

        try {			
                cBaseDatos con = new cBaseDatos();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Cursos curso=new Cursos();
                        curso.setCodigo(rs.getInt(1));
                        curso.setNombre(rs.getString(2));
                        curso.setCosto(rs.getFloat(3));
                        curso.setFec_ini(rs.getDate(4));
                        curso.setFec_fin(rs.getDate(5));
                        curso.setDuracion(rs.getInt(6));
                        curso.setSesiones(rs.getInt(7));
                        curso.setCapacidad(rs.getInt(8));
                        curso.setInscritos(rs.getInt(9));
                        curso.setEstado(rs.getString(10));
                        listaCursos.add(curso);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "método obtener");
        }
        return listaCursos;
    }

@Override
    public boolean grabarMatricula(String[] datosMatricula, String[] codigoCursos, String[] montos) {
        cBaseDatos co = new cBaseDatos();
        String xcodm = co.generarCodigo("matriculas","codigo");
        boolean registrar = true;
	Statement stm= null;
        Connection con=null;      
        
        String sql = "insert into matriculas (codigo,fecha,nro_doc," +
                 "codigo_alumno,total,estado) values (?,?,?,?,?,'A') ";
        String xfech = this.getFecha();
        String xndoc = datosMatricula[0];
        String xcoda = datosMatricula[1];
        String xtota = datosMatricula[2];
        try {			    
            con=co.Conectar();
            stm= con.createStatement();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, xcodm);
            ps.setString(2, xfech);
            ps.setString(3, xndoc);
            ps.setString(4, xcoda);
            ps.setString(5, xtota);
            ps.executeUpdate();
            
            for( int xc=0 ; xc < codigoCursos.length ; xc++ ){
                String xcodCurso = codigoCursos[xc];
                this.grabarNuevoDetalle(con,xcodm,xcodCurso,montos[xc]);
            }           
            stm.close();
            con.close();
        } catch (SQLException e) {
                System.out.println("Error: Clase MatriculaDaoImpl, "
                        + "método grabarMatricula");
                e.printStackTrace();
                return false;
        }
        return registrar;
    }
    
    public void grabarNuevoDetalle( Connection xcon,String xcodm, String xcodc,String xmonto ) 
        throws SQLException {
        String sql = "insert into detalles (codigo_matricula,codigo_curso," +
                     "monto,asistencias,nota,estado) values (?,?,?,0,0,'A') ";

        PreparedStatement ps = xcon.prepareStatement(sql);
        ps.setString(1, xcodm);
        ps.setString(2, xcodc);
        ps.setString(3, xmonto);
        ps.executeUpdate();

        // actualizar nro de inscritos en curso
        sql = "update cursos set inscritos=inscritos+1 where codigo=?";
        PreparedStatement psc = xcon.prepareStatement(sql);
        psc.setString(1, xcodc);
        psc.executeUpdate();
    }

    
    public String getFecha(){
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(date);
    }

    @Override
    public List<Matriculas> listarMatriculas() {
        Connection co =null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM matriculas";
        List<Matriculas> listaMatriculas = new ArrayList<Matriculas>();

        try {			
                cBaseDatos con = new cBaseDatos();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Matriculas matricula = new Matriculas();
                        matricula.setCodigo(rs.getInt(1));
                        matricula.setFecha(rs.getDate(2));
                        matricula.setNro_doc(rs.getString(3));
                        matricula.setCodigo_alumno(rs.getInt(4));
                        matricula.setTotal(rs.getFloat(5));
                        matricula.setEstado(rs.getString(6));
                        listaMatriculas.add(matricula);                        
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "metodo ListarMatriculas");
        }
        return listaMatriculas;
    }

    @Override
    public List<Matriculas> buscarMatriculas(Matriculas matricula) {
        Connection co =null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM matriculas WHERE nro_doc LIKE'%" + matricula.getNro_doc()+ "%'";
        List<Matriculas> listaMatriculas= new ArrayList<Matriculas>();
        
        try {			
                cBaseDatos con = new cBaseDatos();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Matriculas matriculan = new Matriculas();
                        matriculan.setCodigo(rs.getInt(1));
                        matriculan.setFecha(rs.getDate(2));
                        matriculan.setNro_doc(rs.getString(3));
                        matriculan.setCodigo_alumno(rs.getInt(4));
                        matriculan.setTotal(rs.getFloat(5));
                        matriculan.setEstado(rs.getString(6));
                        listaMatriculas.add(matriculan);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "método buscarMatricula");
        }
        return listaMatriculas;
    }
    
    public List<Detalles> buscarDetalles(Matriculas matricula){
        Connection co =null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM detalles WHERE codigo_matricula=" +matricula.getCodigo();
        List<Detalles> listaDetalles= new ArrayList<Detalles>();
        
        try {			
                cBaseDatos con = new cBaseDatos();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Detalles detalle = new Detalles();
                        detalle.setCodigo_matricula(rs.getInt(1));
                        detalle.setCodigo_curso(rs.getInt(2));
                        detalle.setMonto(rs.getFloat(3));
                        detalle.setAsistencias(rs.getInt(4));
                        detalle.setNota(rs.getInt(5));
                        detalle.setEstado(rs.getString(6));
                        listaDetalles.add(detalle);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "método buscarDetalles");
        }
        return listaDetalles;
    }
}
