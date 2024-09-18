
package quantik.undo;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * La clase Jugada, necesaria para la implemantación de
 * MaquinaDelTiempoConJugadas.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 * @see MaquinaDelTiempoConJugadas
 */
public class Jugada {

	/** La columna de la jugada. */
	private int columna;

	/** La fila de la jugada. */
	private int fila;

	/** La figura de la jugada. */
	private Figura figura;

	/** El turno/color de la jugada. */
	private Color color;

	/**
	 * Contructor de la clase jugada, que crea una nueva jugada.
	 *
	 * @param fila    la fila de la jugada
	 * @param columna la columna de la jugada
	 * @param figura  la figura de la jugada
	 * @param color   el color del turno de la jugada
	 */
	public Jugada(int fila, int columna, Figura figura, Color color) {
		this.fila = fila;
		this.columna = columna;
		this.figura = figura;
		this.color = color;
	}

	/**
	 * Consulta la fila de la jugada.
	 *
	 * @return la fila de la jugada
	 */
	public int consultarFila() {
		return fila;
	}

	/**
	 * Consulta la columna de la jugada.
	 *
	 * @return la columna de la jugada
	 */
	public int consultarColumna() {
		return columna;
	}

	/**
	 * Consulta la figura de la jugada.
	 *
	 * @return la figura de la jugada
	 */
	public Figura consultarFigura() {
		return figura;
	}

	/**
	 * Consulta el color del turno de la jugada.
	 *
	 * @return el color del turno de la jugada
	 */
	public Color consultarColor() {
		return color;
	}
}
