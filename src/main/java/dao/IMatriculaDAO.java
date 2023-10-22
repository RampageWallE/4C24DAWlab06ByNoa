/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import java.util.List;
import modelos.Alumnos;
import modelos.Cursos;
import modelos.Detalles;
import modelos.Matriculas;

/**
 *
 * @author User
 */
public interface IMatriculaDAO {
    public List<Alumnos> buscarAlumnos(Alumnos alumno);
    public List<Cursos> buscarCursos();
    public List<Matriculas> listarMatriculas();
    public List<Matriculas> buscarMatriculas(Matriculas matricula);
    public boolean grabarMatricula(String[] datosMatricula, String[] codigoCursos, String[] montos);
    public List<Detalles> buscarDetalles(Matriculas matricula);

}
