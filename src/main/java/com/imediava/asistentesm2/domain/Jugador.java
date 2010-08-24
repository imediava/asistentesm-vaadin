package com.imediava.asistentesm2.domain;

public class Jugador {
	
	
	/**
	 * Nombre del jugador.
	 */
	private String nombre = "";
	
	/**
	 * Equipo del jugador.
	 */
	private String equipo = "";
	
	/**
	 * Valoracion en el ultimo partido.
	 */
	private Double valUltimoPartido = 0.0;
	
	/**
	 * Promedio de valoracion en los ultimos tres partidos.
	 */
	private Double valUltimosTresPartidos = 0.0;
	
	
	/**
	 * Promedio de valoracion en toda la temporada.
	 */
	private Double valoracionMedia = 0.0;
	
	/**
	 * Variacion de precio esperada.
	 * 
	 * Lo que el precio variaria si el jugador siguiera su
	 * promedio de las ultimas tres jornadas.
	 */
	private Integer variacionPrecioEsperada = 0;
	
	
	/**
	 * Precio actual del jugador.
	 */
	private Integer precio;


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the equipo
	 */
	public String getEquipo() {
		return equipo;
	}


	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}


	/**
	 * @return the valUltimoPartido
	 */
	public Double getValUltimoPartido() {
		return valUltimoPartido;
	}


	/**
	 * @param valUltimoPartido the valUltimoPartido to set
	 */
	public void setValUltimoPartido(Double valUltimoPartido) {
		this.valUltimoPartido = valUltimoPartido;
	}


	/**
	 * @return the valUltimosTresPartidos
	 */
	public Double getValUltimosTresPartidos() {
		return valUltimosTresPartidos;
	}


	/**
	 * @param valUltimosTresPartidos the valUltimosTresPartidos to set
	 */
	public void setValUltimosTresPartidos(Double valUltimosTresPartidos) {
		this.valUltimosTresPartidos = valUltimosTresPartidos;
	}


	/**
	 * @return the valoracionMedia
	 */
	public Double getValoracionMedia() {
		return valoracionMedia;
	}


	/**
	 * @param valoracionMedia the valoracionMedia to set
	 */
	public void setValoracionMedia(Double valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}


	/**
	 * @return the variacionPrecioEsperada
	 */
	public Integer getVariacionPrecioEsperada() {
		return variacionPrecioEsperada;
	}


	/**
	 * @param variacionPrecioEsperada the variacionPrecioEsperada to set
	 */
	public void setVariacionPrecioEsperada(Integer variacionPrecioEsperada) {
		this.variacionPrecioEsperada = variacionPrecioEsperada;
	}


	/**
	 * @return the precio
	 */
	public Integer getPrecio() {
		return precio;
	}


	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

}
