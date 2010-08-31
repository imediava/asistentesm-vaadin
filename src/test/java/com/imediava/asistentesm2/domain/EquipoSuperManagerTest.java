package com.imediava.asistentesm2.domain;

import static org.junit.Assert.assertEquals;

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

	private static final int EXCESO_PIVOTS = 5;
	private static final int MAX_NUM_PIVOTS = 4;
	private static final int EXCESO_DE_ALEROS = 5;
	private static final int MAX_NUMERO_ALEROS = 4;
	private static final int EXCESO_DE_BASES = 4;
	private static final int MAX_NUM_BASES = 3;
	private EquipoSuperManager miEquipoVacio;
	private Collection<Jugador> misTresBases;
	private Collection<Jugador> misCuatroBases;
	private Collection<Jugador> misCuatroAleros;
	private Collection<Jugador> misCincoAleros;
	private Collection<Jugador> misCuatroPivots;
	private Collection<Jugador> misCincoPivots;
	
	
	@Before
	public void setUp() throws Exception {
		miEquipoVacio = new EquipoSuperManager();
		misTresBases = crearGrupoJugadores(PosicionJugador.BASE, MAX_NUM_BASES);
		misCuatroBases = crearGrupoJugadores(PosicionJugador.BASE, EXCESO_DE_BASES);
		misCuatroAleros = crearGrupoJugadores(PosicionJugador.BASE, MAX_NUMERO_ALEROS);
		misCincoAleros = crearGrupoJugadores(PosicionJugador.BASE, EXCESO_DE_ALEROS);
		misCuatroPivots = crearGrupoJugadores(PosicionJugador.BASE, MAX_NUM_PIVOTS);
		misCincoPivots = crearGrupoJugadores(PosicionJugador.BASE, EXCESO_PIVOTS);
		
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
	private Jugador crearJugador(String ID, String nombre, PosicionJugador posicion) {
		Jugador miJugador = new Jugador();
		miJugador.setNombre(nombre);
		miJugador.setPosicion(PosicionJugador.BASE);
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
	
	@Test
	public void annadirTresBasesTest(){
		//TODO: Probar a añadir 3 bases
		
		// 1. Agrego los tres bases 
		for(Jugador j:misTresBases){
			 miEquipoVacio.addJugador(j);
		 }
		
		// 2. Compruebo que los tres bases estan en el equipo
		// miEquipoVacio.contains(misTresBases(0))
		// miEquipoVacio.contains(misTresBases(1))
		// miEquipoVacio.contains(misTresBases(2))
	}
	
	
	@Test
	public void annadirCuatroBasesTest(){
		//TODO: Probar a añadir 4 bases (error)
		
		// 1. Agrego los cuatro bases 
		// 2. Compruebo que salta excepcion cuando intento con el cuarto
	}
	
	/**
	 * TODO: Probar a añadir 4 aleros 
	 * TODO: Probar a añadir 5 aleros (error) TODO:
	 * Probar a añadir 4 pivots TODO: Probar a añadir 5 pivots(error)
	 * 
	 * TODO: Probar a fichar con menos dinero que el que se tiene en caja TODO:
	 * Probar a fichar con el dinero justo. TODO: Probar a fichar con mas dinero
	 * del que se tiene en caja
	 * 
	 * 
	 * TODO: Probar intentar fichar a 2 no europeos TODO: Probar a intentar
	 * fichar a 3 no europeos
	 * 
	 * TODO: Probar a intentar fichar a 4 no nacionales (error) TODO: Probar a
	 * intentar fichar a 5 no nacionales (error)
	 * 
	 */

}
