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
 * La clase de Maquina Del Tiempo que utiliza Jugadas para deshacer.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 */
public class MaquinaDelTiempoConJugadas extends MaquinaDelTiempoAbstracta {

	/** La lista de las jugadas hechas. */
	private List<Jugada> jugadas = new ArrayList<Jugada>();

	/**
	 * Constructor de maquina del tiempo con jugadas.
	 *
	 * @param fecha    la fecha de inicio
	 * @param filas    las filas que tiene el tablero
	 * @param columnas las columnas que tiene el tablero
	 */
	public MaquinaDelTiempoConJugadas(Date fecha, int filas, int columnas) {
		super(fecha, filas, columnas);
	}

	@Override
	public Partida consultarPartidaActual() {
		Partida partida = null;
		try {
			partida = new Partida(new Tablero(), new Caja(Color.BLANCO), new Caja(Color.NEGRO));
			for (int i = 0; i < jugadas.size(); i++) {
				partida.colocarPiezaEnTurnoActual(jugadas.get(i).consultarFila(), jugadas.get(i).consultarColumna(),
						jugadas.get(i).consultarFigura());

				partida.cambiarTurno();
			}
		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Excepción en consultarPartidaActual, coordenadas fuera del tablero", e);
		}

//			throw new CoordenadasIncorrectasException(
//					"Excepción en consultarPartidaActual, coordenadas fuera del tablero", e);
//
		return partida.clonar();
	}

	@Override
	public void deshacerJugada() {
		if (jugadas.size() > 0) {
			jugadas.remove(jugadas.size() - 1);
			modificarNumJugadas(-1);
		}
	}

	@Override
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException {
		if (fila >= 0 && fila < obtenerFilas() && columna >= 0 && columna < obtenerColumnas()) {
			jugadas.add(new Jugada(fila, columna, figura, color));
			modificarNumJugadas(+1);
		} else {
			throw new CoordenadasIncorrectasException(
					"Excepción en hacerJugada, coordenadas fuera del tablero, la posición cuya fila es: " + fila
							+ " y columna: " + columna + " no pertenece al tablero");
		}
	}
}
