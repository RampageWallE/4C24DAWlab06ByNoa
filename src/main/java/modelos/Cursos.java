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
public class Cursos {
    int codigo; 
    String nombre;
    Float costo;
    Date fec_ini;
    Date fec_fin;
    int duracion;
    int sesiones;
    int capacidad;
    int inscritos;
    String estado;
    
    public Cursos(){
        
    }

    public Cursos(int codigo, String nombre, Float costo, Date fec_ini, Date fec_fin, int duracion, int sesiones, int capacidad, int inscritos, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.fec_ini = fec_ini;
        this.fec_fin = fec_fin;
        this.duracion = duracion;
        this.sesiones = sesiones;
        this.capacidad = capacidad;
        this.inscritos = inscritos;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getCosto() {
        return costo;
    }

    public Date getFec_ini() {
        return fec_ini;
    }

    public Date getFec_fin() {
        return fec_fin;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getSesiones() {
        return sesiones;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getInscritos() {
        return inscritos;
    }

    public String getEstado() {
        return estado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public void setFec_ini(Date fec_ini) {
        this.fec_ini = fec_ini;
    }

    public void setFec_fin(Date fec_fin) {
        this.fec_fin = fec_fin;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setSesiones(int sesiones) {
        this.sesiones = sesiones;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
}
