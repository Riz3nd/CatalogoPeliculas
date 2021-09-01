package co.com.peliculas.datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import co.com.peliculas.domain.Pelicula;
import co.com.peliculas.excepciones.AccesoDatosEx;
import co.com.peliculas.excepciones.EscrituraDatosEx;
import co.com.peliculas.excepciones.LecturaDatosEx;

public class AccesoDatosImpl implements IAccesoDatos{

	@Override
	public boolean existe(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		return archivo.exists();
	}

	@Override
	public List<Pelicula> Listar(String nombre) throws LecturaDatosEx {
		File archivo = new File(nombre);
		List<Pelicula> peliculas = new ArrayList<>();
		
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			linea = entrada.readLine();
			while(linea != null) {
				Pelicula pelicula = new Pelicula(linea);
				peliculas.add(pelicula);
				linea = entrada.readLine();
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("Excepcion al listar peliculas: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("Excepcion al listar peliculas: " + e.getMessage());
		} 
		
		return peliculas;
	}

	@Override
	public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
		File archivo = new File(nombreArchivo);
		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar));
			salida.println(pelicula.toString());
			salida.close();
			System.out.println("Se añadio: " + pelicula.toString() + " al archivo.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new EscrituraDatosEx("Excepcion al añadir peliculas: " + e.getMessage());
		}
	}

	@Override
	public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
		File archivo = new File(nombreArchivo);
		String resultado = null;
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			System.out.println("Pelicula:");
			for(int i = 0; linea != null; i++) {
				if(buscar != null && buscar.equalsIgnoreCase(linea)) {
					resultado = i + " - " + linea;
					break;
				}
				linea = entrada.readLine();
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("Excepcion al buscar pelicula: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("Excepcion al buscar pelicula: " + e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public void crear(String nombreArchivo) throws AccesoDatosEx {
		File archivo = new File(nombreArchivo);
		try {
			PrintWriter salida = new PrintWriter(archivo);
			salida.close();
			System.out.println("Se creo el archivo");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new AccesoDatosEx("Excepcion al crear archivo: " + e.getMessage());
		}
	}

	@Override
	public void borrar(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		if(archivo.exists())
			archivo.delete();
		System.out.println("Se ha borrado el archivo");
	}

}
