package quantik.modelo;

import java.util.Objects;

/**
 * Clase de la celda del juego.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 */
public class Celda {
	/**
	 * Pieza que está en la celda.
	 */
	private Pieza pieza;
	/**
	 * Fila de la celda.
	 */
	private int fila;
	/**
	 * Columna de la celda.
	 */
	private int columna;

	/**
	 * Constructor de las celdas.
	 * 
	 * @param fila    de la celda
	 * @param columna de la celda
	 */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.pieza = null;
	}

	/**
	 * Clona una celda profundamente.
	 * 
	 * @return celda clonada profundamente
	 */
	public Celda clonar() {
		Celda celdaClonada = new Celda(this.fila, this.columna);
		if (estaVacia() == false) {
			Pieza piezaClonada = pieza.clonar();
			celdaClonada.colocar(piezaClonada);
		}
		return celdaClonada;
	}

	/**
	 * Coloca una pieza en la celda.
	 * 
	 * @param pieza a colocar
	 */
	public void colocar(Pieza pieza) {
		this.pieza = pieza;
	}

	/**
	 * Consulta la columna de la celda.
	 * 
	 * @return nº de columna
	 */
	public int consultarColumna() {
		return columna;
	}

	/**
	 * Consulta la fila de la celda.
	 * 
	 * @return nº de fila
	 */
	public int consultarFila() {
		return fila;
	}

	/**
	 * Consulta la pieza de la celda.
	 * 
	 * @return pieza clonada de la celda
	 */
	public Pieza consultarPieza() {
		if (pieza == null) {
			return null;
		}
		return pieza.clonar();
	}

	/**
	 * Comprueba si la celda está vacía.
	 * 
	 * @return true si está vacía y false si está llena
	 */
	public boolean estaVacia() {
		if (pieza == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Celda [pieza=" + pieza + ", fila=" + fila + ", columna=" + columna + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(columna, fila, pieza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return columna == other.columna && fila == other.fila && Objects.equals(pieza, other.pieza);
	}

}
