package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;

/**
 * La clase Tablero que es el tablero sobre el que se jugará la partida.
 *
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 2.0
 * @since 1.0
 */
public class Tablero {

	/** El tablero sobre el que se va a jugar. */
	private List<List<Celda>> tablero = new ArrayList<>();

	/**
	 * Constructor del tablero.
	 * 
	 * @see Celda
	 */
	public Tablero() {
		final int tamaño = 4;
		for (int i = 0; i < tamaño; i++) {
			tablero.add(new ArrayList<>(tamaño));
			for (int j = 0; j < tamaño; j++) {
				tablero.get(i).add(new Celda(i, j));
			}
		}

	}

	/**
	 * Convierte el tablero actual a texto.
	 *
	 * @return la cadena de texto correspondiente al tablero
	 * 
	 */
	public String aTexto() {
		String textoTablero = "    0";
		for (int i = 1; i < tablero.size(); i++) {
			textoTablero = textoTablero + "     " + i;
		}
		textoTablero = textoTablero + "\n";
		for (int i = 0; i < tablero.size(); i++) {
			textoTablero = textoTablero + i;
			for (int j = 0; j < tablero.get(i).size(); j++) {
				if (tablero.get(i).get(j).consultarPieza() == null) {
					textoTablero = textoTablero + " -----";
				} else {
					textoTablero = textoTablero + " -"
							+ tablero.get(i).get(j).consultarPieza().consultarFigura().aTexto()
							+ tablero.get(i).get(j).consultarPieza().consultarColor().toChar() + "-";
				}
			}
			textoTablero = textoTablero + "\n";
		}
		return textoTablero;
	}

	/**
	 * Clona el tablero.
	 *
	 * @return el tablero clonado
	 */
	public Tablero clonar() {
		Tablero tableroClonado = new Tablero();
		for (int i = 0; i < tablero.size(); i++) {
			tableroClonado.tablero.get(i).clear();
			for (int j = 0; j < tablero.get(i).size(); j++) {
				if (tablero.get(i).get(j) != null) {
					tableroClonado.tablero.get(i).add(tablero.get(i).get(j).clonar());
				}
			}
		}
		return tableroClonado;

	}

	/**
	 * Colocar una pieza en una parte del tablero.
	 *
	 * @param fila    la fila donde quieres colocar la pieza
	 * @param columna la columna donde quieres colocar la pieza
	 * @param pieza   la pieza a colocar
	 * @throws CoordenadasIncorrectasException la excepción que nos indica que las
	 *                                         coordenadsa son incorrectas
	 */
	public void colocar(int fila, int columna, Pieza pieza) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna) == true) {
			if (tablero.get(fila).get(columna).estaVacia() == true) {
				tablero.get(fila).get(columna).colocar(pieza);
			}
		} else {
			throw new CoordenadasIncorrectasException(
					"Excepción en colocar, coordenadas fuera del tablero, la posición cuya fila es: " + fila
							+ " y columna: " + columna + " no pertenece al tablero");
		}

	}

	/**
	 * Consulta la celda deseada.
	 *
	 * @param fila    la fila de la celda que queremos consultar
	 * @param columna la columna de la celda que queremos consultar
	 * @return clon de la celda consultada
	 * @throws CoordenadasIncorrectasException la excepción que nos indica que las
	 *                                         coordenadsa son incorrectas
	 */
	public Celda consultarCelda(int fila, int columna) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna) == true) {
			return tablero.get(fila).get(columna).clonar();
		} else {
			throw new CoordenadasIncorrectasException(
					"Excepción en consultarCelda, coordenadas fuera del tablero, la posición cuya fila es: " + fila
							+ " y columna: " + columna + " no pertenece al tablero");
		}
	}

	/**
	 * Consulta el número de columnas.
	 *
	 * @return el número de columnas
	 */
	public int consultarNumeroColumnas() {
		return tablero.get(0).size();
	}

	/**
	 * Consulta el número de filas.
	 *
	 * @return el número de filas
	 */
	public int consultarNumeroFilas() {
		return tablero.size();
	}

	/**
	 * Comprueba si las coordenadas pasdas estan en el tablero.
	 *
	 * @param fila    la fila a comprobar
	 * @param columna la columna a comprobar
	 * @return true, si estám em el tablero false en caso contrario
	 */
	public boolean estaEnTablero(int fila, int columna) {
		if (fila >= 0 && fila < tablero.size() && columna >= 0 && columna < tablero.get(0).size()) {
			return true;
		}
		return false;
	}

	/**
	 * Obtiene la celda a partir de sus coordenadas.
	 *
	 * @param fila    la fila de la celda a comprobar
	 * @param columna la columna de la celda a comprobar
	 * @return la celda correnpondiente a la fila y columna pasada
	 * @throws CoordenadasIncorrectasException la excepción que nos indica que las
	 *                                         coordenadsa son incorrectas
	 */
	Celda obtenerCelda(int fila, int columna) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna) == true) {
			return tablero.get(fila).get(columna);

		} else {
			throw new CoordenadasIncorrectasException(
					"Excepción en obtenerCelda, coordenadas fuera del tablero, la posición cuya fila es: " + fila
							+ " y columna: " + columna + " no pertenece al tablero");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(tablero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Objects.equals(tablero, other.tablero);
	}

	@Override
	public String toString() {
		return "Tablero [tablero=" + tablero + "]";
	}

}
