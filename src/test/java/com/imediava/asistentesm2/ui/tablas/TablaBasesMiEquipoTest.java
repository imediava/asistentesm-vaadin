package com.imediava.asistentesm2.ui.tablas;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class TablaBasesMiEquipoTest {

	private TablaBasesMiEquipo miTablaBases;

	@Before
	public void setUp() throws Exception {
		miTablaBases = new TablaBasesMiEquipo();
	}

	@Test
	public final void testGetMaximoNumeroJugadores() {
		assertEquals(TablaBasesMiEquipo.LIMITE_BASES, miTablaBases.getMaximoNumeroJugadores());
	}


}
