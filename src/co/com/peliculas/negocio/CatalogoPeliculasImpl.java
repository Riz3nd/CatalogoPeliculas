package co.com.peliculas.negocio;

import co.com.peliculas.datos.AccesoDatosImpl;
import co.com.peliculas.datos.IAccesoDatos;
import co.com.peliculas.domain.Pelicula;
import co.com.peliculas.excepciones.AccesoDatosEx;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas{
	private final IAccesoDatos datos;
	
	public CatalogoPeliculasImpl() {
		this.datos = new AccesoDatosImpl();
	}
	
	@Override
	public void agregarPelicula(String nombrePelicula) {
		Pelicula pelicula = new Pelicula(nombrePelicula);
		boolean anexar = false;
		try {
			anexar = datos.existe(NOMBRE_RECURSO);
			datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
		} catch (AccesoDatosEx e) {
			System.out.println("Error de acceso a datos");
			e.printStackTrace(System.out);
		} 
	}

	@Override
	public void listarPeliculas() {
		try {
			var peliculas = this.datos.Listar(NOMBRE_RECURSO);
			for (Pelicula pelicula: peliculas) {
				System.out.println("Pelicula: " + pelicula);
			}
			
		} catch (AccesoDatosEx e) {
			System.out.println("Error de acceso datos");
			e.printStackTrace(System.out);
		}
	}

	@Override
	public void buscarPelicula(String buscar) {
		String resultado = null;
		try {
			resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
		} catch (AccesoDatosEx e) {
			System.out.println("Error de acceso datos");
			e.printStackTrace(System.out);
		}
		System.out.println("Resultado: " + resultado);
		
	}

	@Override
	public void iniciarCatalogoPeliculas() {
		try {
			if(this.datos.existe(NOMBRE_RECURSO)) {
				datos.borrar(NOMBRE_RECURSO);
				datos.crear(NOMBRE_RECURSO);
			}else {
				datos.crear(NOMBRE_RECURSO);
			}
		} catch (AccesoDatosEx e) {
			System.out.println("Error al iniciar catalogo de peliculas");
			e.printStackTrace(System.out);
		}
	}
	
}
