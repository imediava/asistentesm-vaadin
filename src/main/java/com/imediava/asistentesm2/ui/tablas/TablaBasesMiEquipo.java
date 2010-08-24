package com.imediava.asistentesm2.ui.tablas;

import com.imediava.asistentesm2.domain.Jugador;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

public class TablaBasesMiEquipo extends TablaMiEquipo{
	
	public static final Integer LIMITE_BASES = 3;
	
	@Override
	public Integer getMaximoNumeroJugadores() {
		return LIMITE_BASES;
	}
	
	
	
	
		
}
