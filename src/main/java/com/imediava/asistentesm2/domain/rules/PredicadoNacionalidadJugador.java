package com.imediava.asistentesm2.domain.rules;

import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.imediava.asistentesm2.domain.Jugador;
import com.imediava.asistentesm2.domain.Jugador.Nacionalidad;
import com.imediava.asistentesm2.domain.Jugador.PosicionJugador;

public class PredicadoNacionalidadJugador implements Predicate<Jugador> {

	private Nacionalidad nacionalidad;

	public PredicadoNacionalidadJugador(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public boolean apply(Jugador jugador) {
		return jugador.getStatusNacionalidad().equals(nacionalidad);
	}
	


}
