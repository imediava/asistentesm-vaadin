package com.imediava.asistentesm2.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.junit.Before;
import org.junit.Test;

import com.imediava.asistentesm2.domain.Jugador.Nacionalidad;
import com.imediava.asistentesm2.domain.Jugador.PosicionJugador;

public class EquipoSuperManagerFichajesValidosTest {

	private EquipoSuperManager miEquipoVacio;
	private Collection<Jugador> misTresBases;
	private Collection<Jugador> misCuatroAleros;
	private Collection<Jugador> misCuatroPivots;

	@Before
	public void setUp() throws Exception {
		miEquipoVacio = new EquipoSuperManager();
		misTresBases = EquipoSuperManagerTestUtils
				.crearGrupoJugadoresNacionales(PosicionJugador.BASE,
						EquipoSuperManager.MAXIMO_NUMERO_BASES_EQUIPO);
		misCuatroAleros = EquipoSuperManagerTestUtils
				.crearGrupoJugadoresNacionales(PosicionJugador.ALERO,
						EquipoSuperManager.MAXIMO_NUMERO_ALEROS_EQUIPO);
		misCuatroPivots = EquipoSuperManagerTestUtils
				.crearGrupoJugadoresNacionales(PosicionJugador.PIVOT,
						EquipoSuperManager.MAXIMO_NUMERO_PIVOTS_EQUIPO);

	}

	// -- Agregando jugadores al limite

	@Test
	public void annadirTresBasesTest() {
		agregarJugadoresYComprobar(miEquipoVacio, misTresBases);
	}

	@Test
	public void annadirCuatroAlerosTest() {
		agregarJugadoresYComprobar(miEquipoVacio, misCuatroAleros);
	}

	@Test
	public void annadirCuatroPivotsTest() {
		agregarJugadoresYComprobar(miEquipoVacio, misCuatroPivots);
	}

	// Agregando jugadores en exceso

	@Test
	public void annadirCuatroBasesTest() {
		comprobarAgregarExcesoJugadores(misTresBases, PosicionJugador.PIVOT);
	}

	@Test
	public void annadirCincoAlerosTest() {
		comprobarAgregarExcesoJugadores(misCuatroAleros, PosicionJugador.BASE);
	}

	@Test
	public void annadirCincoPivotsTest() {
		comprobarAgregarExcesoJugadores(misCuatroPivots, PosicionJugador.ALERO);
	}

	@Test
	public void intentarEliminarJugadorInexistente() {
		Jugador jugadorCaro = EquipoSuperManagerTestUtils
				.crearJugadorNacionalExtra(PosicionJugador.ALERO,
						miEquipoVacio.getDineroDisponible() + 1);
		// Comprueba que no se puede eliminar un jugador de un equipo si no
		// pertenece al mismo
		assertFalse(miEquipoVacio.remove(jugadorCaro));
	}

	// Pruebas de fichar a jugadores nacionales, europeos y extracomunitarios

	@Test
	public void intentarFicharEquipoCompletoNacionales() {
		agregarJugadoresYComprobar(miEquipoVacio,
				EquipoSuperManagerTestUtils.crearGrupoJugadores(
						PosicionJugador.BASE, Nacionalidad.NACIONAL,
						EquipoSuperManager.MAXIMO_NUMERO_BASES_EQUIPO));
		agregarJugadoresYComprobar(miEquipoVacio,
				EquipoSuperManagerTestUtils.crearGrupoJugadores(
						PosicionJugador.ALERO, Nacionalidad.NACIONAL,
						EquipoSuperManager.MAXIMO_NUMERO_ALEROS_EQUIPO));
		agregarJugadoresYComprobar(miEquipoVacio,
				EquipoSuperManagerTestUtils.crearGrupoJugadores(
						PosicionJugador.PIVOT, Nacionalidad.NACIONAL,
						EquipoSuperManager.MAXIMO_NUMERO_PIVOTS_EQUIPO));

	}

	@Test
	public void intentarAgregarLimiteExtracomunitarios() {

		assert EquipoSuperManager.MAXIMO_NUMERO_EXTRACOMUNITARIOS <= EquipoSuperManager.MAXIMO_NUMERO_BASES_EQUIPO;
		// Agrego el maximo de jugadores extracomunitarios y compruebo que
		// funciona
		agregarJugadoresYComprobar(miEquipoVacio,
				EquipoSuperManagerTestUtils.crearGrupoJugadores(
						PosicionJugador.BASE, Nacionalidad.EXTRACOMUNITARIO,
						EquipoSuperManager.MAXIMO_NUMERO_EXTRACOMUNITARIOS));
	}

	@Test
	public void intentarAgregarExcesoExtracomunitarios() {
		assert EquipoSuperManager.MAXIMO_NUMERO_EXTRACOMUNITARIOS + 1 <= EquipoSuperManager.MAXIMO_NUMERO_BASES_EQUIPO;

		assertTrue(miEquipoVacio.addAll(EquipoSuperManagerTestUtils
				.crearGrupoJugadores(PosicionJugador.BASE,
						Nacionalidad.EXTRACOMUNITARIO,
						EquipoSuperManager.MAXIMO_NUMERO_EXTRACOMUNITARIOS)));
		assertFalse(miEquipoVacio.add(EquipoSuperManagerTestUtils
				.crearJugadorExtra(PosicionJugador.BASE,
						EquipoSuperManagerTestUtils.COSTE_CERO,
						Nacionalidad.EXTRACOMUNITARIO)));
		assertEquals(EquipoSuperManager.MAXIMO_NUMERO_EXTRACOMUNITARIOS,
				miEquipoVacio.size());
	}

	@Test
	public void intentarAgregarLimiteNoNacionales() {
		// El numero maximo de nacionales (7) es igual al numero max de bases
		// (3) mas el de aleros (4) ->
		assert EquipoSuperManager.MAXIMO_NUMERO_NO_NACIONALES == misTresBases
				.size() + misCuatroAleros.size();

		agregarJugadoresYComprobar(miEquipoVacio, misTresBases);
		agregarJugadoresYComprobar(miEquipoVacio, misCuatroAleros);
	}

	@Test
	public void intentarAgregarExcesoNoNacionales() {
		// El numero maximo de nacionales (7) es igual al numero max de bases
		// (3) mas el de aleros (4)

		Collection<Jugador> basesNoNacionales = EquipoSuperManagerTestUtils
				.crearGrupoJugadores(PosicionJugador.BASE,
						Nacionalidad.EUROPEO,
						EquipoSuperManager.MAXIMO_NUMERO_BASES_EQUIPO);
		Collection<Jugador> alerosNoNacionales = EquipoSuperManagerTestUtils
				.crearGrupoJugadores(PosicionJugador.ALERO,
						Nacionalidad.EUROPEO,
						EquipoSuperManager.MAXIMO_NUMERO_ALEROS_EQUIPO);

		assert EquipoSuperManager.MAXIMO_NUMERO_NO_NACIONALES == basesNoNacionales
				.size() + alerosNoNacionales.size();

		agregarJugadoresYComprobar(miEquipoVacio, basesNoNacionales);
		agregarJugadoresYComprobar(miEquipoVacio, alerosNoNacionales);
		assertFalse(miEquipoVacio.add(EquipoSuperManagerTestUtils
				.crearJugadorExtra(PosicionJugador.PIVOT,
						EquipoSuperManagerTestUtils.COSTE_CERO,
						Nacionalidad.EUROPEO)));
		assertEquals(EquipoSuperManager.MAXIMO_NUMERO_NO_NACIONALES,
				miEquipoVacio.size());
	}

	// Metodos de utilidad

	/**
	 * Agrega un conjunto de jugadores al equipo y luego comprueba que se han
	 * agregado correctamente.
	 * 
	 * OJO: Este metodo tiene efectos colaterales sobre el equipo ya que los
	 * jugadores son agregados al equipo de forma efectiva.
	 * 
	 * @param equipo
	 *            Equipo al que se agregan los jugadores
	 * @param jugadores
	 *            Jugadores a agregar.
	 * 
	 */
	private void agregarJugadoresYComprobar(EquipoSuperManager equipo,
			Collection<? extends Jugador> jugadores) {
		// 1. Agrego los tres bases
		equipo.addAll(jugadores);

		// 2. Compruebo que los tres bases estan en el equipo
		assertTrue(equipo.containsAll(jugadores));
	}

	/**
	 * Prueba en que si se intenta agregar un numero de jugadores en exceso y
	 * que comprueba que solo se agregan los necesarios.
	 * 
	 * @param jugadores
	 *            Jugadores a agregar.
	 */
	private void comprobarAgregarExcesoJugadores(Collection<Jugador> jugadores,
			PosicionJugador posJugExtra) {
		// 1. Agrego los jugadores de la coleccion
		miEquipoVacio.addAll(jugadores);

		// 2. Compruebo
		// Que no se puede agregar otro jugador extra
		PosicionJugador posicionPrueba = jugadores.iterator().next()
				.getPosicion();
		assertFalse(miEquipoVacio.add(EquipoSuperManagerTestUtils
				.crearJugadorNacionalExtra(posicionPrueba,
						EquipoSuperManagerTestUtils.COSTE_CERO)));
		// Que el equipo solo tiene tres jugadores (los 3 bases agregados)
		assertTrue(miEquipoVacio.size() == EquipoSuperManager.MAPA_MAX_JUGADORES_POSICION
				.get(posicionPrueba));
		// Que si se puede agregar un alero
		assertTrue(miEquipoVacio.add(EquipoSuperManagerTestUtils
				.crearJugadorNacionalExtra(posJugExtra,
						EquipoSuperManagerTestUtils.COSTE_CERO)));
	}

}
