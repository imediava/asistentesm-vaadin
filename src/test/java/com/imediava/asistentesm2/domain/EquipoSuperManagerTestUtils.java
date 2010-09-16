package com.imediava.asistentesm2.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.imediava.asistentesm2.domain.Jugador.Nacionalidad;
import com.imediava.asistentesm2.domain.Jugador.PosicionJugador;

public class EquipoSuperManagerTestUtils {
	
	public static final int COSTE_CERO = 0;
	
	/**
	 * Crea y devuelve un jugador asignandole un nombre y una posicion.
	 * @param id codigo identificador unico del jugador
	 * @param nombre Nombre a asignar al jugador
	 * @param nacionalidad 
	 * @param posicion Posicion del jugador
	 * @return Jugador creado
	 */
	protected static Jugador crearJugador(String ID, String nombre, PosicionJugador pos, Nacionalidad nacionalidad) {
		Jugador miJugador = new Jugador(ID);
		miJugador.setNombre(nombre);
		miJugador.setPosicion(pos);
		miJugador.setStatusNacionalidad(nacionalidad);
		return miJugador;
	}
	
	
	/**
	 * Crea un jugador extra con una posicion y un id que depende
	 * del parametro pos. 
	 * 
	 * OJO! Dos llamadas a este metodo para la misma
	 * posicion crean el mismo jugador.
	 * 
	 * @param pos Posicion del jugador
	 * @param precioJugador Precio que se va a dar al jugador creado
	 * @param nacionalidad Nacionalidad de los jugadores a crear
	 * @return Jugador extra creado
	 */
	protected static Jugador crearJugadorExtra(PosicionJugador pos, int precioJugador, Nacionalidad nacionalidad) {
		final String NOMBRE_JUGADOR_EXTRA = "jugadorExtra";
		final String ID_PRUEBA_JUGADOR_EXTRA = "ZZZ";

		Jugador jugador = crearJugador(ID_PRUEBA_JUGADOR_EXTRA + pos.toString(), NOMBRE_JUGADOR_EXTRA, pos, nacionalidad);
		jugador.setPrecio(precioJugador);
		return jugador;
	}
	
	/**
	 * Crea un jugador extra con una posicion y un id que depende
	 * del parametro pos. 
	 * 
	 * OJO! Dos llamadas a este metodo para la misma
	 * posicion crean el mismo jugador.
	 * 
	 * @param pos Posicion del jugador
	 * @param precioJugador Precio que se va a dar al jugador creado
	 * @return Jugador extra creado
	 */
	protected static Jugador crearJugadorNacionalExtra(PosicionJugador pos, int precioJugador) {
		final String NOMBRE_JUGADOR_EXTRA = "jugadorExtra";
		final String ID_PRUEBA_JUGADOR_EXTRA = "ZZZ";

		Jugador jugador = crearJugador(ID_PRUEBA_JUGADOR_EXTRA + pos.toString(), NOMBRE_JUGADOR_EXTRA, pos, Nacionalidad.NACIONAL);
		jugador.setPrecio(precioJugador);
		return jugador;
	}
	
	/**
	 * Devuelve si el equipo acepta el fichaje del jugador y comprueba
	 * si ese fichaje se puede realizar probando distintos métodos
	 * de la interfaz de la clase EquipoSupermanager.
	 * 
	 * @param equipo Equipo al que se agregará el jugador
	 * @param jugadorCaro Jugador a agregar
	 * @return
	 */
	protected static boolean equipoAceptaFichaje(EquipoSuperManager equipo, Jugador jugadorCaro) {
		int numeroJugadoresEquipo = equipo.size();
		return equipo.esFichajeValido(jugadorCaro) &&  equipo.add(jugadorCaro) && equipo.size() == numeroJugadoresEquipo + 1;
	}
	
	
	/**
	 * Crea una coleccion de jugadores de una posicion dada.
	 * 
	 * @param posicion Posicion
	 * @param numeroJugadores Numero de jugadores a crear
	 * @return La coleccion de jugadores
	 */
	protected static Collection<Jugador> crearGrupoJugadores(PosicionJugador posicion,Nacionalidad nacionalidad, int numeroJugadores){
		List<Jugador> jugadores = new ArrayList<Jugador>();
		for(int i=0; i<numeroJugadores;i++){
			jugadores.add(crearJugador("00" + String.valueOf(i),posicion.name()+String.valueOf(i), posicion, nacionalidad));
		}
		return jugadores;
	}
	
	/**
	 * Crea una coleccion de jugadores nacionales de una posicion dada.
	 * 
	 * @param posicion Posicion
	 * @param numeroJugadores Numero de jugadores a crear
	 * @return La coleccion de jugadores
	 */
	protected static Collection<Jugador> crearGrupoJugadoresNacionales(PosicionJugador posicion, int numeroJugadores){
		return crearGrupoJugadores(posicion, Nacionalidad.NACIONAL, numeroJugadores);
	}
	
	
	
	
	

}
