package quantik.undo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.Tablero;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * La clase de Maquina Del Tiempo que utiliza Partidas guardadas para deshacer.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 */
public class MaquinaDelTiempoConPartidas extends MaquinaDelTiempoAbstracta {

	/** La lista de los clones de las partidas que se guardan tras cada jugada. */
	private List<Partida> partidas = new ArrayList<Partida>(1);

	/**
	 * Constructor de maquina del tiempo con partidas.
	 *
	 * @param fecha    la fecha de inicio
	 * @param filas    las filas que tiene el tablero
	 * @param columnas las columnas que tiene el tablero
	 */
	public MaquinaDelTiempoConPartidas(Date fecha, int filas, int columnas) {
		super(fecha, filas, columnas);
		partidas.clear();
		partidas.add(new Partida(new Tablero(), new Caja(Color.BLANCO), new Caja(Color.NEGRO)).clonar());
	}

	@Override
	public Partida consultarPartidaActual() {
		if (partidas.size() > 1) {
			return partidas.get(partidas.size() - 1);
		}
		return partidas.get(0).clonar();
	}

	@Override
	public void deshacerJugada() {
		if (partidas.size() > 1) {
			partidas.remove(partidas.size() - 1);
			modificarNumJugadas(-1);
		}
	}

	@Override
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException {
		if (fila >= 0 && fila < obtenerFilas() && columna >= 0 && columna < obtenerColumnas()) {
			Partida partidaAnterior = partidas.get(partidas.size() - 1).clonar();
			if (color == partidaAnterior.consultarTurno()) {
				partidaAnterior.colocarPiezaEnTurnoActual(fila, columna, figura);
				partidaAnterior.cambiarTurno();
				modificarNumJugadas(+1);
				partidas.add(partidaAnterior);
			}

		} else {
			throw new CoordenadasIncorrectasException(
					"Excepción en hacerJugada, coordenadas fuera del tablero, la posición cuya fila es: " + fila
							+ " y columna: " + columna + " no pertenece al tablero");
		}
	}
}
