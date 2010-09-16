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


	
	private EquipoSuperManager miEquipoVacio;
	
	
	@Before
	public void setUp() throws Exception {
		miEquipoVacio = new EquipoSuperManager();
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
	public void dineroDisponibleAumentaAlVenderJugadorTest(){
		int precioJugador = miEquipoVacio.getDineroDisponible() -1;
		Jugador jugadorPrecioBarato = EquipoSuperManagerTestUtils.crearJugadorNacionalExtra(PosicionJugador.ALERO, precioJugador);
		// Se comprueba que el jugador se ha fichado correctamente
		assertTrue(EquipoSuperManagerTestUtils.equipoAceptaFichaje(miEquipoVacio, jugadorPrecioBarato));
		
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
		Jugador jugadorPrecioBarato = EquipoSuperManagerTestUtils.crearJugadorNacionalExtra(PosicionJugador.ALERO, precioJugador);
		// Se comprueba que el jugador se ha fichado correctamente
		assertTrue(EquipoSuperManagerTestUtils.equipoAceptaFichaje(miEquipoVacio, jugadorPrecioBarato));
		// Se comprueba que el dinero del equipo ha disminuido
		assertEquals(dineroDisponibleInicial-precioJugador,miEquipoVacio.getDineroDisponible());
	}
	
	
	@Test
	public void intentarFicharConDineroSobraTest(){
		Jugador jugadorPrecioBarato = EquipoSuperManagerTestUtils.crearJugadorNacionalExtra(PosicionJugador.ALERO, miEquipoVacio.getDineroDisponible() -1);
		assertTrue(EquipoSuperManagerTestUtils.equipoAceptaFichaje(miEquipoVacio, jugadorPrecioBarato));
	}
	
	@Test
	public void intentarFicharConDineroJustoTest(){
		Jugador jugadorPrecioExacto = EquipoSuperManagerTestUtils.crearJugadorNacionalExtra(PosicionJugador.ALERO, miEquipoVacio.getDineroDisponible());
		assertTrue(EquipoSuperManagerTestUtils.equipoAceptaFichaje(miEquipoVacio, jugadorPrecioExacto));
	}
	
	@Test
	public void intentarFicharSinDineroSuficienteTest(){
		Jugador jugadorCaro = EquipoSuperManagerTestUtils.crearJugadorNacionalExtra(PosicionJugador.ALERO,miEquipoVacio.getDineroDisponible() + 1);
		assertFalse(EquipoSuperManagerTestUtils.equipoAceptaFichaje(miEquipoVacio,jugadorCaro));
	}
	



	
	/**
	 * 
	 * 
	 * TODO: Probar a intentar fichar a 3 no europeos
	 * 
	 * TODO: Probar a intentar fichar a 4 no nacionales (error) 
	 * TODO: Probar a intentar fichar a 5 no nacionales (error)
	 * 
	 * TODO: Probar que  el método addJugador no permite dos fichajes
	 * que se hacen concurrentemente y rompen las reglas
	 * 
	 */

}
