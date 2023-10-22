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
public class Detalles {
    
    int codigo_matricula; 
    int codigo_curso;
    float monto;
    int asistencias;
    int nota;
    String estado;
    
    public Detalles(){}

    public Detalles(int codigo_matricula, int codigo_curso, float monto, int asistencias, int nota, String estado) {
        this.codigo_matricula = codigo_matricula;
        this.codigo_curso = codigo_curso;
        this.monto = monto;
        this.asistencias = asistencias;
        this.nota = nota;
        this.estado = estado;
    }

    public int getCodigo_matricula() {
        return codigo_matricula;
    }

    public int getCodigo_curso() {
        return codigo_curso;
    }

    public float getMonto() {
        return monto;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getNota() {
        return nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setCodigo_matricula(int codigo_matricula) {
        this.codigo_matricula = codigo_matricula;
    }

    public void setCodigo_curso(int codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
