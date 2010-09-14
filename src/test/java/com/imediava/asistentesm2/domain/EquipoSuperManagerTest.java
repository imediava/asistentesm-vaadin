package com.imediava.asistentesm2.domain;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.imediava.asistentesm2.domain.Jugador.PosicionJugador;

public class EquipoSuperManagerTest {

	private static final int COSTE_CERO = 0;
	private static final String NOMBRE_JUGADOR_EXTRA = "jugadorExtra";
	private static final String ID_PRUEBA_JUGADOR_EXTRA = "ZZZ";
	
	private EquipoSuperManager miEquipoVacio;
	private Collection<Jugador> misTresBases;
	private Collection<Jugador> misCuatroAleros;
	private Collection<Jugador> misCuatroPivots;
	
	
	@Before
	public void setUp() throws Exception {
		miEquipoVacio = new EquipoSuperManager();
		misTresBases = crearGrupoJugadores(PosicionJugador.BASE, EquipoSuperManager.MAXIMO_NUMERO_BASES_EQUIPO);
		misCuatroAleros = crearGrupoJugadores(PosicionJugador.ALERO, EquipoSuperManager.MAXIMO_NUMERO_ALEROS_EQUIPO);
		misCuatroPivots = crearGrupoJugadores(PosicionJugador.PIVOT, EquipoSuperManager.MAXIMO_NUMERO_PIVOTS_EQUIPO);
		
	}
	
	/**
	 * Crea una coleccion de jugadores de una posicion dada.
	 * 
	 * @param posicion Posicion
	 * @param numeroJugadores Numero de jugadores a crear
	 * @return La coleccion de jugadores
	 */
	private Collection<Jugador> crearGrupoJugadores(PosicionJugador posicion, int numeroJugadores){
		List<Jugador> jugadores = new ArrayList<Jugador>();
		for(int i=0; i<numeroJugadores;i++){
			jugadores.add(crearJugador("00" + String.valueOf(i),posicion.name()+String.valueOf(i), posicion));
		}
		return jugadores;
	}

	/**
	 * Crea y devuelve un jugador asignandole un nombre y una posicion.
	 * @param id codigo identificador unico del jugador
	 * @param nombre Nombre a asignar al jugador
	 * @param posicion Posicion del jugador
	 * @return Jugador creado
	 */
	private Jugador crearJugador(String ID, String nombre, PosicionJugador pos) {
		Jugador miJugador = new Jugador(ID);
		miJugador.setNombre(nombre);
		miJugador.setPosicion(pos);
		return miJugador;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void dineroInicialCorrectoTest() throws IOException{
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("/ConfiguracionSuperManager.properties"));
		assertEquals(Integer.valueOf(properties.getProperty("DineroInicialDisponible")),miEquipoVacio.getDineroDisponible());
	}
	
	// -- Agregando jugadores al limite
	
	@Test
	public void annadirTresBasesTest(){
		comprobarAgregarConjuntoJugadoresLimite(misTresBases);
	}

	@Test
	public void annadirCuatroAlerosTest(){
		comprobarAgregarConjuntoJugadoresLimite(misCuatroAleros);
	}
	
	@Test
	public void annadirCuatroPivotsTest(){
		comprobarAgregarConjuntoJugadoresLimite(misCuatroPivots);
	}

	// Agregando jugadores en exceso
	
	@Test
	public void annadirCuatroBasesTest(){
		comprobarAgregarExcesoJugadores(misTresBases, PosicionJugador.PIVOT);
	}
	
	@Test
	public void annadirCincoAlerosTest(){
		comprobarAgregarExcesoJugadores(misCuatroAleros, PosicionJugador.BASE);
	}
	
	@Test
	public void annadirCincoPivotsTest(){
		comprobarAgregarExcesoJugadores(misCuatroPivots, PosicionJugador.ALERO);
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
	private Jugador crearJugadorExtra(PosicionJugador pos, int precioJugador) {
		Jugador jugador = crearJugador(ID_PRUEBA_JUGADOR_EXTRA + pos.toString(), NOMBRE_JUGADOR_EXTRA, pos);
		jugador.setPrecio(precioJugador);
		return jugador;
	}
	
	/**
	 * Prueba que si se intenta agregar el numero maximo de jugadores
	 * permitido de una posicion, los jugadores se agregan correctamente
	 * y no ocurre ningun error.
	 * 
	 * @param jugadores Jugadores a agregar.
	 */
	private void comprobarAgregarConjuntoJugadoresLimite(Collection<? extends Jugador> jugadores) {
		// 1. Agrego los tres bases 
		miEquipoVacio.addAll(jugadores);
		
		// 2. Compruebo que los tres bases estan en el equipo
		assertTrue(miEquipoVacio.containsAll(jugadores));
	}
	
	/**
	 * Prueba en que si se intenta agregar un numero de jugadores en 
	 * exceso y que comprueba que solo se agregan los necesarios.
	 * 
	 * @param jugadores Jugadores a agregar.
	 */
	private void comprobarAgregarExcesoJugadores(Collection<Jugador> jugadores,PosicionJugador posJugExtra) {
		//TODO: Probar a añadir 4 bases (error)
		// 1. Agrego los cuatro bases 
		miEquipoVacio.addAll(jugadores);
		
		// 2.  Compruebo
		// Que no se puede agregar otro base
		PosicionJugador posicionPrueba = jugadores.iterator().next().getPosicion();
		assertFalse(miEquipoVacio.add(crearJugadorExtra(posicionPrueba, COSTE_CERO)));
		// Que el equipo solo tiene tres jugadores (los 3 bases agregados)
		assertTrue(miEquipoVacio.size() == EquipoSuperManager.MAPA_MAX_JUGADORES_POSICION.get(posicionPrueba));
		// Que si se puede agregar un alero
		assertTrue(miEquipoVacio.add(crearJugadorExtra(posJugExtra, COSTE_CERO)));
	}
	
	@Test
	public void dineroDisponibleAumentaAlVenderJugadorTest(){
		int precioJugador = miEquipoVacio.getDineroDisponible() -1;
		Jugador jugadorPrecioBarato = crearJugadorExtra(PosicionJugador.ALERO, precioJugador);
		// Se comprueba que el jugador se ha fichado correctamente
		assertTrue(equipoAceptaFichaje(miEquipoVacio, jugadorPrecioBarato));
		
		int dineroTrasFichaje = miEquipoVacio.getDineroDisponible();
		// Se elimina el jugador
		assertTrue(miEquipoVacio.remove(jugadorPrecioBarato));
		// Se comprueba que el dinero es el correcto
		assertEquals(dineroTrasFichaje + precioJugador, miEquipoVacio.getDineroDisponible());
	}
	
	@Test
	public void dineroDisponibleDisminuyeAlFicharTest(){
		int precioJugador = miEquipoVacio.getDineroDisponible() -1;
		int dineroDisponibleInicial = miEquipoVacio.getDineroDisponible();
		Jugador jugadorPrecioBarato = crearJugadorExtra(PosicionJugador.ALERO, precioJugador);
		// Se comprueba que el jugador se ha fichado correctamente
		assertTrue(equipoAceptaFichaje(miEquipoVacio, jugadorPrecioBarato));
		// Se comprueba que el dinero del equipo ha disminuido
		assertEquals(dineroDisponibleInicial-precioJugador,miEquipoVacio.getDineroDisponible());
	}
	
	
	@Test
	public void intentarFicharConDineroSobraTest(){
		Jugador jugadorPrecioBarato = crearJugadorExtra(PosicionJugador.ALERO, miEquipoVacio.getDineroDisponible() -1);
		assertTrue(equipoAceptaFichaje(miEquipoVacio, jugadorPrecioBarato));
	}
	
	@Test
	public void intentarFicharConDineroJustoTest(){
		Jugador jugadorPrecioExacto = crearJugadorExtra(PosicionJugador.ALERO, miEquipoVacio.getDineroDisponible());
		assertTrue(equipoAceptaFichaje(miEquipoVacio, jugadorPrecioExacto));
	}
	
	@Test
	public void intentarFicharSinDineroSuficienteTest(){
		Jugador jugadorCaro = crearJugadorExtra(PosicionJugador.ALERO,miEquipoVacio.getDineroDisponible() + 1);
		assertFalse(equipoAceptaFichaje(miEquipoVacio,jugadorCaro));
	}
	
	@Test
	public void intentarEliminarJugadorInexistente(){
		Jugador jugadorCaro = crearJugadorExtra(PosicionJugador.ALERO,miEquipoVacio.getDineroDisponible() + 1);
		//Comprueba que no se puede eliminar un jugador de un equipo si no pertenece al mismo
		assertFalse(miEquipoVacio.remove(jugadorCaro));
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
	private boolean equipoAceptaFichaje(EquipoSuperManager equipo, Jugador jugadorCaro) {
		int numeroJugadoresEquipo = equipo.size();
		return equipo.esFichajeValido(jugadorCaro) &&  equipo.add(jugadorCaro) && equipo.size() == numeroJugadoresEquipo + 1;
	}
	
	/**
	 * 
	 * 
	 * TODO: Probar intentar fichar a 2 no europeos TODO: Probar a intentar
	 * fichar a 3 no europeos
	 * 
	 * TODO: Probar a intentar fichar a 4 no nacionales (error) TODO: Probar a
	 * intentar fichar a 5 no nacionales (error)
	 * 
	 * TODO: Probar que  el método addJugador no permite dos fichajes
	 * que se hacen concurrentemente y rompen las reglas
	 * 
	 */

}
