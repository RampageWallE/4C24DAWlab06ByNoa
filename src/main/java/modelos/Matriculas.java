/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;

/**
 *
 * @author User
 */
public class Matriculas {
    int codigo;
    Date fecha; 
    String nro_doc;
    int codigo_alumno;
    float total; 
    String estado; 
    
    public Matriculas(){}
    
    public Matriculas(int codigo, Date fecha, String nro_doc, int codigo_alumno, float total, String estado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.nro_doc = nro_doc;
        this.codigo_alumno = codigo_alumno;
        this.total = total;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public int getCodigo_alumno() {
        return codigo_alumno;
    }

    public float getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public void setCodigo_alumno(int codigo_alumno) {
        this.codigo_alumno = codigo_alumno;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
