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
import java.util.ArrayList;
import modelos.cBaseDatos;
import modelos.Cursos;
import java.util.List;

public class CursoDAOImpl implements ICursosDAO {
    
    @Override 
    public boolean registrar(Cursos curso){
        cBaseDatos co = new cBaseDatos();
        String xcod = co.generarCodigo("cursos", "codigo");
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO cursos values (" + xcod + "," +
            "'" + curso.getNombre() + "'," +curso.getCosto()+ ","+
            "'" + curso.getFec_ini()+ "','" +curso.getFec_fin()+ "',"+    
            "" + curso.getDuracion()+ ","+
            "" + curso.getSesiones()+ "," +curso.getCapacidad()+ ","+
            "" + curso.getInscritos()+ ",'" +curso.getEstado()+ "');";

        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("*************");
            System.out.println("Error: Clase CursoDaoImp, "
                    + "metodo registrar");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println(sql);
            System.out.println( e.getMessage());
            System.out.println("*************");

        }
        System.out.println(sql);
        return registrar;
    }
    
    @Override
    public List<Cursos> obtener(){
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos ORDER BY codigo";
        List<Cursos>  listaCursos = new ArrayList<Cursos>();
        
        try{
            cBaseDatos con = new cBaseDatos();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                Cursos curso = new Cursos();
                curso.setCodigo(rs.getInt(1));
                curso.setNombre(rs.getString(2));
                float temp = Float.parseFloat(rs.getString(3));
                curso.setCosto(temp);
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
        }catch(SQLException e){
            System.out.println("Error:Clase CursoDaoImpl,"
                    + "metodo obtener");
        }
        return listaCursos;
        //throw new UnsupportedOperationException("Not supported yet ");
    }
    
    @Override
    public boolean actualizar(Cursos curso){
        cBaseDatos co = new cBaseDatos();
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE cursos SET " +
            "nombre='" + curso.getNombre() + "',costo=" +curso.getCosto()+ ","+
            "fec_ini='" + curso.getFec_ini()+ "',fec_fin='" +curso.getFec_fin()+ "',"+    
            "duracion=" + curso.getDuracion()+ ",sesiones=" +curso.getSesiones()+ ","+
            "capacidad=" + curso.getCapacidad()+ ",inscritos=" +curso.getInscritos() + ","+
            "estado='" +curso.getEstado()+ "' "+"WHERE codigo="+curso.getCodigo()+";";
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            actualizar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("*************");
            System.out.println("Error: Clase CursoDaoImp, "
                    + "metodo actualizar");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println(sql);
            System.out.println( e.getMessage());
            System.out.println("*************");
        }
        System.out.println(sql);
        return actualizar;
    }
    
    @Override
    public boolean eliminar(String[] codigos ){
        boolean inicio;
        boolean eliminar = false;
        if ( codigos.length <= 0 )
           return eliminar;
        String sql  = "DELETE FROM cursos WHERE codigo in ( ";
        inicio = true;
        for( int xc = 0 ; xc < codigos.length ; xc++ ) {
            if ( inicio )
              sql += "?";
            else
              sql += ",?";
            inicio = false;
        }
        sql += ")";
        cBaseDatos con = new cBaseDatos();  
        try {
            Connection co = con.Conectar();
            PreparedStatement pstm = co.prepareStatement(sql);
            for( int xc = 0 ; xc < codigos.length ; xc++ ) 
                pstm.setString(xc+1, codigos[xc]);
            pstm.executeUpdate();
            eliminar = true;
            pstm.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase CursoDAOImpl,"
                    + "metodo eliminar");
            e.printStackTrace();            
        }
        return eliminar;
        
    }

    @Override
    public Cursos buscar(int codigo) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos WHERE codigo=" + codigo;
        Cursos curso = new Cursos();
        try {
            cBaseDatos con = new cBaseDatos();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                curso.setCodigo(rs.getInt(1));
                curso.setNombre(rs.getString(2));
                float temp = Float.parseFloat(rs.getString(3));
                curso.setCosto(temp);
                curso.setFec_ini(rs.getDate(4));
                curso.setFec_fin(rs.getDate(5));
                curso.setDuracion(rs.getInt(6));
                curso.setSesiones(rs.getInt(7));
                curso.setCapacidad(rs.getInt(8));
                curso.setInscritos(rs.getInt(9));
                curso.setEstado(rs.getString(10));
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase CursoDAOImpl,"
                    + "metodo buscar");
            e.printStackTrace();
        }
        return curso;
    }
}

