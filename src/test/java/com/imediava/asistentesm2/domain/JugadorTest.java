package com.imediava.asistentesm2.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JugadorTest {

	private Jugador miJugador;
	private String miID;

	@Before
	public void setUp() throws Exception {
		miJugador = new Jugador();
		miID = "ACB";
		miJugador.setID(miID);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetPaginaWebJugadorEnWebACB() {
		assertTrue(miJugador.getPaginaWebJugadorEnWebACB().startsWith(
				Jugador.PREFIJO_URL_JUGADOR_WEB_ACB));
		assertTrue(miJugador.getPaginaWebJugadorEnWebACB().endsWith(miID));
	}

}
