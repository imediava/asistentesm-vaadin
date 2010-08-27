/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.imediava.asistentesm2;

import java.util.Collection;


import com.imediava.asistentesm2.database.stub.FachadaBaseDatos;
import com.imediava.asistentesm2.domain.Jugador;
import com.vaadin.Application;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application
{
    private Window window;

    @Override
    public void init()
    {
        window = new Window("My Vaadin Application");
        setMainWindow(window);
        window.addComponent(new Button("Click Me"));
        insertarTablas(window);
       
    }

	private void insertarTablas(Window mainWindow) {
		FachadaBaseDatos db = new FachadaBaseDatos();
		window.addComponent(crearRellenarTabla(db.getBases(),"Bases"));
		window.addComponent(crearRellenarTabla(db.getAleros(), "Aleros"));
		window.addComponent(crearRellenarTabla(db.getPivots(), "Pivots"));
		
	}

	private Table crearRellenarTabla(Collection<Jugador> jugadores, String NombreTabla) {
		// Creo un container
		BeanItemContainer<Jugador> container = new BeanItemContainer<Jugador>(Jugador.class);
		        
		// Bind it to a component
		Table table = new Table();
		table.setCaption(NombreTabla);
		table.setContainerDataSource(container);
		
		for(Jugador j: jugadores){
			container.addBean(j);
		}
		table.setPageLength(jugadores.size());
		return table;
	}
    
}
