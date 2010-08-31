package com.imediava.asistentesm2.domain;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

/**
 * Equipo del SM creado por un
 * usuario.
 * 
 * @author imediava
 *
 */
public class EquipoSuperManager {
	
	/**
	 * Dinero en caja disponible del equipo
	 * para fichajes.
	 */
	private Integer dineroDisponible = 0;
	
	
	/**
	 * 
	 */
	private Collection<Jugador> bases = null;
	

	/**
	 * Devuelve el dinero que el equipo tiene en 
	 * caja y que puede utilizar para hacer fichajes.
	 * 
	 * @return El dinero en caja del equipo 
	 */
	public Integer getDineroDisponible() {
		return dineroDisponible;
	}

	/**
	 * 
	 * Establece el dinero que el equipo dispondra
	 * para fichajes.
	 * 
	 * @param dineroDisponible the dineroDisponible to set
	 */
	public void setDineroDisponible(Integer dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}
	
	public EquipoSuperManager() throws IOException{
		setDineroDisponible(leerDineroInicialDisponible());
	}

	/**
	 * Metodo que lee del fichero de propiedades el dinero
	 * inicial que tiene que tener cada equipo que se crea en el SM
	 * y lo devuelve.
	 * @return Devuelve el dinero con el que debe partir inicialmente cada equipo
	 * @throws IOException
	 */
	private Integer leerDineroInicialDisponible() throws IOException {
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("/ConfiguracionSuperManager.properties"));
		return Integer.valueOf(properties.getProperty("DineroInicialDisponible"));
	}
	
	
	/**
	 * Comprueba si segun las reglas del juego se puede agregar
	 * el jugador pasado al equipo.
	 * 
	 * @param jugador Jugador que se quiere comprobar
	 * @return Devuelve si el jugador se puede fichar o no
	 */
	public boolean esFichajeValido(Jugador jugador){
		return false;
	}
	
	/**
	 * Agrega un jugador al equipo. Si por alguna razon
	 * el jugador no puede ser agregado al equipo salta una
	 * excepcion porque antes de llamar a este metodo se deberia
	 * comprobar que el jugador se puede agregar llamando a
	 * {@link:esFichajeValido} esFichajeValido.
	 * 
	 * @param jugador Jugador a agregar
	 * @throws IllegalArgumentException cuando se intenta agregar un jugador
	 *              que no se puede agregar
	 */
	public void addJugador(Jugador jugador) throws IllegalArgumentException {
		
	}
	
	//TODO: Implementar el metodo contains (para ver si un equipo tiene a un jugador.
	
	//TODO: Implementar el metodo equals
	
	/**
	 * Dos equipos son el mismo cuando tienen mismo id.
	 */
	public boolean equals(Object o1, Object o2){
		return false;
	}
	
	

}
