package com.imediava.asistentesm2.ui.tablas;

import com.imediava.asistentesm2.domain.Jugador;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

public abstract class TablaMiEquipo extends Table{
	
	private BeanItemContainer<Jugador> container;

	public TablaMiEquipo(){
		super();
		// Creo un container
		container = new BeanItemContainer<Jugador>(Jugador.class);
		super.setContainerDataSource(container);
	}
	
	/**
	 * Obtiene el maximo numero de jugadores a introducir
	 * en la tabla.
	 * 
	 * @return
	 */
	public abstract Integer getMaximoNumeroJugadores();
	
	/**
	 * Inserta un jugador al final de la tabla.
	 * 
	 * @param jugador El jugador a insertar
	 */
	public final void addJugador(Jugador jugador) throws RuntimeException{
		if (this.size() >= getMaximoNumeroJugadores()){
			throw new UnsupportedOperationException("Se esta intentando incluir mas jugadores de los permitidos en la tabla.");
		}
		container.addBean(jugador);
	}

}
