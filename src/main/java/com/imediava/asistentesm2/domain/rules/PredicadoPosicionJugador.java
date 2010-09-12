package com.imediava.asistentesm2.domain.rules;

import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.imediava.asistentesm2.domain.Jugador;
import com.imediava.asistentesm2.domain.Jugador.PosicionJugador;

public class PredicadoPosicionJugador implements Predicate<Jugador> {

	private PosicionJugador posicion;

	public PredicadoPosicionJugador(PosicionJugador pos) {
		posicion = pos;
	}

	public boolean apply(Jugador jugador) {
		return jugador.getPosicion().equals(posicion);
	}
	


}
