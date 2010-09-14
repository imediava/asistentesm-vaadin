package com.imediava.asistentesm2.domain;

/**
 * Jugador profesional de un equipo ACB y por tanto seleccionable para un equipo
 * del supermanager.
 * 
 * @author imediava
 * 
 */
public class Jugador {

	/**
	 * Prefijo de la URL con las estadisticas del jugador en la
	 * web de la acb.
	 */
	public static final String PREFIJO_URL_JUGADOR_WEB_ACB = "http://www.acb.com/jugador.php?id=";

	/**
	 * Posiciones o demarcaciones que puede ocupar un jugador en el SM.
	 * 
	 * @author imediava
	 * 
	 */
	public enum PosicionJugador {
		BASE, ALERO, PIVOT
	}

	/**
	 * Posibles estatus de la nacionalidad en la ficha de un jugador.
	 * 
	 * @author imediava
	 * 
	 */
	public enum Nacionalidad {
		NACIONAL, EUROPEO, EXTRACOMUNITARIO
	}

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
	 * Lo que el precio variaria si el jugador siguiera su promedio de las
	 * ultimas tres jornadas.
	 */
	private Integer variacionPrecioEsperada = 0;

	/**
	 * Precio actual del jugador.
	 */
	private Integer precio = 0;

	/**
	 * Posicion del jugador (base, alero o pivot).
	 */
	private PosicionJugador posicion = null;

	/**
	 * Estatus de la nacionalid del jugador. Si es nacional, europeo o
	 * extracomunitario.
	 */
	private Nacionalidad statusNacionalidad = null;

	/**
	 * Codigo identificador unico del jugador dentro de la pagina web
	 * {@linkplain=www.acb.com} acb.com . Es un codigo util porque sirve por
	 * ejemplo para acceder a la pagina con las estadisticas completas del
	 * jugador en la mencionada web.
	 */
	private String id = null;
	
	public Jugador(String id){
		this.id = id;
	}

	/**
	 * Obtiene el codigo de identificador unico del jugador en la pagina web de
	 * acb.com.Es un codigo util porque sirve por ejemplo para acceder a la
	 * pagina con las estadisticas completas del jugador en la mencionada web.
	 * 
	 * @return Identificador unico
	 */
	public String getId() {
		return id;
	}

	/**
	 * Establece el codigo identificador del jugador en acb.com.
	 * 
	 * @param iD
	 *            Codigo identificador
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtiene la URL de la pagina web del jugador en la web de acb.com.
	 * 
	 * @return URL desde la que se acceden a las estadisticas del jugador en la
	 *         acb
	 */
	public String getPaginaWebJugadorEnWebACB() {
		return PREFIJO_URL_JUGADOR_WEB_ACB + getId();
	}

	/**
	 * Obtener nacionalidad del jugador.
	 * 
	 * @return Nacionalidad del jugador
	 */
	public Nacionalidad getStatusNacionalidad() {
		return statusNacionalidad;
	}

	/**
	 * Establecer la nacionalidad del jugador.
	 * 
	 * @param statusNacionalidad
	 *            Nueva nacionalidad del jugador
	 */
	public void setStatusNacionalidad(Nacionalidad statusNacionalidad) {
		this.statusNacionalidad = statusNacionalidad;
	}

	/**
	 * Devuelve la posicion del jugador en la cancha.
	 * 
	 * @return Posicion del jugador
	 */
	public PosicionJugador getPosicion() {
		return posicion;
	}

	/**
	 * Establece la posicion del jugador en la cancha.
	 * 
	 * @param posicion
	 *            Posicion del jugador
	 */
	public void setPosicion(PosicionJugador posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
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
	 * @param equipo
	 *            the equipo to set
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
	 * @param valUltimoPartido
	 *            the valUltimoPartido to set
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
	 * @param valUltimosTresPartidos
	 *            the valUltimosTresPartidos to set
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
	 * @param valoracionMedia
	 *            the valoracionMedia to set
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
	 * @param variacionPrecioEsperada
	 *            the variacionPrecioEsperada to set
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
	 * @param precio
	 *            the precio to set
	 */
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	

	/**
	 * Dos jugadores son el mismo si tienen el mismo codigo
	 * de jugador (ID) - (No importan mayusc y minusc).
	 * 
	 * @param o1 Jugador 1
	 * @param o2 Jugador 2
	 * @return
	 */
	public boolean equals(Object o1, Object o2){
		Jugador j1 = (Jugador)o1;
		Jugador j2 = (Jugador)o2;
		return j1.getId().equalsIgnoreCase(j2.getId());
	}

}
