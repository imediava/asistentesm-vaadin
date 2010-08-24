package com.imediava.asistentesm2.database.stub;

import java.util.ArrayList;
import java.util.Collection;

import com.imediava.asistentesm2.domain.Jugador;

public class FachadaBaseDatos {
	
	private static final String[] NOMBRES_BASES = { "Jose Manuel", "Ricard", "Sergio" };
	private static final String[] NOMBRES_ALEROS = { "La bomba", "Navarro", "El mejor" };
	private static final String[] NOMBRES_PIVOTS = { "Marc", "Pau", "Felipón" };
	
	public Collection<Jugador> getBases(){
		return generarColeccion(NOMBRES_BASES);
	}
	
	public Collection<Jugador> getAleros(){
		return generarColeccion(NOMBRES_ALEROS);
	}
	
	public Collection<Jugador> getPivots(){
		return generarColeccion(NOMBRES_PIVOTS);
	}
	
	private Collection<Jugador> generarColeccion(String[] nombres){
		Collection<Jugador> bases = new ArrayList<Jugador>();
		for (String nombre: nombres){
			Jugador jug = new Jugador();
			jug.setNombre(nombre);
			bases.add(jug);
		}
		return bases;
	}

}
