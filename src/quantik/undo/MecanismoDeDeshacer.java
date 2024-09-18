package quantik.undo;

import java.util.Date;

import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * La interfaz MecanismoDeDeshacer que implementa las clases necesarias para el
 * mecanismo de deshacer.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 */
public interface MecanismoDeDeshacer {

	/**
	 * Consulta el numero de jugadas en historico.
	 *
	 * @return el número de jugadas
	 */
	public abstract int consultarNumeroJugadasEnHistorico();

	/**
	 * Consulta la partida actual.
	 *
	 * @return la partida actual
	 */
	public abstract Partida consultarPartidaActual();

	/**
	 * Deshace una jugada.
	 */
	public abstract void deshacerJugada();

	/**
	 * Hacer una jugada.
	 *
	 * @param fila    la fila donde se quiere hacer la jugada
	 * @param columna la columna donde se quiere hacer la jugada
	 * @param figura  la figura con la que se quiere hacer la jugada
	 * @param color   el color/turno del jugador que hace la jugada
	 * @throws CoordenadasIncorrectasException la excepción que nos indica que las
	 *                                         coordenadsa son incorrectas
	 */
	public abstract void hacerJugada(int fila, int columna, Figura figura, Color color)
			throws CoordenadasIncorrectasException;

	/**
	 * Obtiene la fecha de inicio.
	 *
	 * @return la fecha
	 */
	public abstract Date obtenerFechaInicio();
}
