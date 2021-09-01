package co.com.peliculas.datos;

import java.util.List;

import co.com.peliculas.domain.Pelicula;
import co.com.peliculas.excepciones.AccesoDatosEx;
import co.com.peliculas.excepciones.EscrituraDatosEx;
import co.com.peliculas.excepciones.LecturaDatosEx;

public interface IAccesoDatos {
	
	boolean existe(String nombreArchivo) throws AccesoDatosEx;
	
	List<Pelicula> Listar(String nombre) throws LecturaDatosEx;
	
	void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx;
	
	String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx;
	
	void crear(String nombreArchivo) throws AccesoDatosEx;
	
	void borrar(String nombreArchivo) throws AccesoDatosEx;
}
