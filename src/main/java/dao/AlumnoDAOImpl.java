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
import modelos.Alumnos;
import java.util.List;

public class AlumnoDAOImpl implements IAlumnosDAO {
    
    @Override 
    public boolean registrar(Alumnos alumno){
        cBaseDatos co = new cBaseDatos();
        String xcod = co.generarCodigo("alumnos", "codigo");
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO alumnos values (" + xcod + "," +
            "'" + alumno.getNombre() + "','" +alumno.getDireccion() + "',"+
            "'" + alumno.getEmail()+ "','" +alumno.getTelefono()+ "',"+    
            "'" + alumno.getCelular() + "','" +alumno.getSexo() + "',"+
            "'" + alumno.getFec_nac()+ "','" +alumno.getEstado()+ "');";
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImp, "
                    + "metodo registrar");
            e.printStackTrace();
            System.out.println(sql);
            System.out.println( e.getMessage());
        }
        
        return registrar;
    }
    
    @Override
    public List<Alumnos> obtener(){
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ALUMNOS ORDER BY codigo";
        List<Alumnos>  listaAlumnos = new ArrayList<Alumnos>();
        
        try{
            cBaseDatos con = new cBaseDatos();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                Alumnos alumno = new Alumnos();
                alumno.setCodigo(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setDireccion(rs.getString(3));
                alumno.setEmail(rs.getString(4));
                alumno.setTelefono(rs.getString(5));
                alumno.setCelular(rs.getString(6));
                alumno.setSexo(rs.getString(7));
                alumno.setFec_nac(rs.getDate(8));
                alumno.setEstado(rs.getString(9));
                listaAlumnos.add(alumno);
            }
            stm.close();
            rs.close();
            co.close();            
        }catch(SQLException e){
            System.out.println("Error:Clase AlumnoDaoImpl,"
                    + "metodo obtener");
        }
        return listaAlumnos;
        //throw new UnsupportedOperationException("Not supported yet ");
    }
    
    @Override
    public boolean actualizar(Alumnos alumno){
        cBaseDatos co = new cBaseDatos();
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE alumnos SET " +
            "nombre='" + alumno.getNombre() + "',direccion='" +alumno.getDireccion() + "',"+
            "email='" + alumno.getEmail()+ "',telefono='" +alumno.getTelefono()+ "',"+    
            "celular='" + alumno.getCelular() + "',sexo='" +alumno.getSexo() + "',"+
            "fec_nac='" + alumno.getFec_nac()+ "',estado='" +alumno.getEstado()+ "' "+
            "WHERE codigo="+alumno.getCodigo()+";";
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            actualizar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImp, "
                    + "metodo actualizar");
            //System.out.println(sql);
            e.printStackTrace();
        }
        return actualizar;
    }
    
    @Override
    public boolean eliminar(String[] codigos ){
        boolean inicio;
        boolean eliminar = false;
        if ( codigos.length <= 0 )
           return eliminar;
        String sql  = "DELETE FROM alumnos WHERE codigo in ( ";
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
            System.out.println("Error:Clase AlumnoDAOImpl,"
                    + "metodo eliminar");
            e.printStackTrace();            
        }
        System.out.println(eliminar);
        return eliminar;
        
    }

    @Override
    public Alumnos buscar(int codigo) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM alumnos WHERE codigo=" + codigo;
        Alumnos alumno = new Alumnos();
        try {
            cBaseDatos con = new cBaseDatos();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                alumno.setCodigo(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setDireccion(rs.getString(3));
                alumno.setEmail(rs.getString(4));
                alumno.setTelefono(rs.getString(5));
                alumno.setCelular(rs.getString(6));
                alumno.setSexo(rs.getString(7));
                alumno.setFec_nac(rs.getDate(8));
                alumno.setEstado(rs.getString(9));   
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase AlumnoDAOImpl,"
                    + "metodo buscar");
            e.printStackTrace();
        }
        return alumno;
    }
}

