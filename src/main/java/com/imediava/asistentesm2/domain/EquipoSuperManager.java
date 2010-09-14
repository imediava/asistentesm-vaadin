package com.imediava.asistentesm2.domain;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.imediava.asistentesm2.domain.Jugador.PosicionJugador;
import com.imediava.asistentesm2.domain.rules.PredicadoPosicionJugador;

/**
 * Equipo del SM creado por un usuario.
 * 
 * @author imediava
 * 
 */
public class EquipoSuperManager extends AbstractSet<Jugador> {

	/**
	 * Dinero en caja disponible del equipo para fichajes.
	 */
	private Integer dineroDisponible = 0;

	/**
	 * 
	 */
	private Set<Jugador> jugadores = null;

	/**
	 * Devuelve el dinero que el equipo tiene en caja y que puede utilizar para
	 * hacer fichajes.
	 * 
	 * @return El dinero en caja del equipo
	 */
	public Integer getDineroDisponible() {
		return dineroDisponible;
	}

	/**
	 * 
	 * Establece el dinero que el equipo dispondra para fichajes.
	 * 
	 * @param dineroDisponible
	 *            the dineroDisponible to set
	 */
	public final void setDineroDisponible(Integer dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}

	public EquipoSuperManager() throws IOException {
		jugadores = new HashSet<Jugador>();
		setDineroDisponible(leerDineroInicialDisponible());
	}

	/**
	 * Metodo que lee del fichero de propiedades el dinero inicial que tiene que
	 * tener cada equipo que se crea en el SM y lo devuelve.
	 * 
	 * @return Devuelve el dinero con el que debe partir inicialmente cada
	 *         equipo
	 * @throws IOException
	 */
	private Integer leerDineroInicialDisponible() throws IOException {
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream(
				"/ConfiguracionSuperManager.properties"));
		return Integer.valueOf(properties
				.getProperty("DineroInicialDisponible"));
	}

	/**
	 * Comprueba si segun las reglas del juego se puede agregar el jugador
	 * pasado al equipo.
	 * 
	 * @param jugador
	 *            Jugador que se quiere comprobar
	 * @return Devuelve si el jugador se puede fichar o no
	 */
	public boolean esFichajeValido(Jugador jugador) {
		return !superaLimiteJugadores(jugador) && !(jugador.getPrecio() > this.getDineroDisponible()); 
	}

	public static final int MAXIMO_NUMERO_BASES_EQUIPO = 3;
	public static final int MAXIMO_NUMERO_ALEROS_EQUIPO = 4;
	public static final int MAXIMO_NUMERO_PIVOTS_EQUIPO = 4;

	public static final ImmutableMap<PosicionJugador, Integer> MAPA_MAX_JUGADORES_POSICION = ImmutableMap
			.of(PosicionJugador.BASE, MAXIMO_NUMERO_BASES_EQUIPO,
					PosicionJugador.ALERO, MAXIMO_NUMERO_ALEROS_EQUIPO,
					PosicionJugador.PIVOT, MAXIMO_NUMERO_PIVOTS_EQUIPO);

	/**
	 * Devuelve si el agregar al jugador al equipo supondria sobrepasar el
	 * limite de jugadores en alguna de las posiciones del equipo.
	 * 
	 * @param jugador
	 *            Un jugador
	 * @return Si se supera el limite de jugadores
	 */
	public boolean superaLimiteJugadores(Jugador jugador) {
		Integer maxJugadoresPosicion = MAPA_MAX_JUGADORES_POSICION.get(jugador
				.getPosicion());
		return obtenerJugadoresPosicion(jugador.getPosicion()).size() + 1 > maxJugadoresPosicion;
	}

	/**
	 * Agrega un jugador al equipo. Si por alguna razon el jugador no puede ser
	 * agregado al equipo el jugador no se agrega y el metodo devuelve falso
	 * para indicarlo. Antes de llamar a este metodo se deberia comprobar que el
	 * jugador se puede agregar llamando a {@link:esFichajeValido}
	 * esFichajeValido.
	 * 
	 * @param jugador
	 *            Jugador a agregar
	 * @return Si el jugador se ha podido agregar o no
	 */
	@Override
	public synchronized boolean add(Jugador jugador) {
		boolean valido = esFichajeValido(jugador);
		if (valido) {
			jugadores.add(jugador);
		}
		this.setDineroDisponible(getDineroDisponible() - jugador.getPrecio());
		return valido;

	}
	
	/**
	 * Elimina al jugador del equipo.
	 * 
	 * @param jugador
	 *            Jugador a eliminar
	 * @return Si el jugador se ha podido eliminar o no
	 */
	@Override
	public synchronized boolean remove(Object jugador) {
		
		if (jugador instanceof Jugador && contains((Jugador)jugador)){
			Jugador j = (Jugador)jugador;
			jugadores.remove(j);
			setDineroDisponible(getDineroDisponible() + j.getPrecio());
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si el equipo contiene el jugador pasado.
	 * 
	 * @param j
	 *            Jugador del que se quiere comprobar si esta en el equipo
	 * @return Si el jugador ya esta en el equipo o no
	 */
	public boolean contains(Jugador j) {
		return jugadores.contains(j);
	}

	// TODO: Implementar el metodo equals

	/**
	 * Dos equipos son el mismo cuando tienen mismo id.
	 */
	public boolean equals(Object o1, Object o2) {
		return false;
	}

	/**
	 * Permite recorrer los jugadores del equipo. No se implementa ningun tipo
	 * de orden al hacerlo.
	 * 
	 * @return Iterador con los jugadores del equipo
	 */
	@Override
	public Iterator<Jugador> iterator() {
		return jugadores.iterator();
	}

	/**
	 * Devuelve el numero total de jugadores actualmente en el equipo.
	 * 
	 * @return Numero de jugadores en el equipo
	 */
	@Override
	public int size() {
		return jugadores.size();
	}

	/**
	 * Obtiene los jugadores del equipo que ocupan una determinada posicion.
	 * 
	 * @param pos
	 *            Posicion de la que se quieren seleccionar los jugadores
	 * @return Conjunto de jugadores del equipo en esa posicion.
	 */
	public Set<Jugador> obtenerJugadoresPosicion(PosicionJugador pos) {
		return Sets.filter(jugadores, new PredicadoPosicionJugador(pos));
	}

}
