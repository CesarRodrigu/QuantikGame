package quantik.undo;

import java.util.Date;

/**
 * La clase abstracta de la Maquina Del Tiempo.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 */
public abstract class MaquinaDelTiempoAbstracta implements MecanismoDeDeshacer {

	/** La fecha de inicialización de la máquina del tiempo. */
	private Date fecha;

	/** Las filas de la máquina del tiempo. */
	private int filas;

	/** Las columnas de la máquina del tiempo. */
	private int columnas;

	/** El número de jugadas que se han hecho. */
	private int numJugadas = 0;

	/**
	 * Constructor de la maquina del tiempo.
	 *
	 * @param fecha    la fecha de inicio
	 * @param filas    las filas que tiene el tablero
	 * @param columnas las columnas que tiene el tablero
	 */
	public MaquinaDelTiempoAbstracta(Date fecha, int filas, int columnas) {
		this.fecha = fecha;
		this.filas = filas;
		this.columnas = columnas;
	}

	@Override
	public Date obtenerFechaInicio() {
		return fecha;
	}

	/**
	 * Obtiene las filas.
	 *
	 * @return el número de filas
	 */
	protected int obtenerFilas() {
		return filas;
	}

	/**
	 * Obtiene las columnas.
	 *
	 * @return el número de columnas
	 */
	protected int obtenerColumnas() {
		return columnas;
	}

	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return numJugadas;
	}

	/**
	 * Modifica el número de jugadas.
	 *
	 * @param suma el número que se quiere sumar al número de jugadas
	 */
	protected void modificarNumJugadas(int suma) {
		numJugadas = numJugadas + suma;
	}
}
