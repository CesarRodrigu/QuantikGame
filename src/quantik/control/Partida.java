package quantik.control;

import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.GestorGrupos;
import quantik.modelo.Pieza;
import quantik.modelo.Tablero;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * La clase de Partida para la gestión de la partida.
 *
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 2.0
 * @since 1.0
 * @see quantik.modelo
 * @see quantik.util
 */
public class Partida {

	/** La caja de las piezas blancas. */
	private Caja cajaBlancas;

	/** La caja caja de las piezas cajaNegras. */
	private Caja cajaNegras;

	/** El gestor de grupos de la partida. */
	private GestorGrupos gestor;

	/** El numero de jugadas de la partida actual. */
	private int numJugadas = 0;

	/** El tablero sobre el que se juega la partida. */
	private Tablero tablero;
	/** El turno actual. */
	private Color turno;

	/**
	 * Constructor de la clase partida.
	 *
	 * @param tablero     tablero de la partida
	 * @param cajaBlancas caja de las piezas blancas
	 * @param cajaNegras  caja de las piezas negras
	 * @see quantik.modelo.GestorGrupos
	 * @see quantik.util.Color
	 */
	public Partida(Tablero tablero, Caja cajaBlancas, Caja cajaNegras) {

		this.tablero = tablero;
		this.cajaBlancas = cajaBlancas;
		this.cajaNegras = cajaNegras;
		this.gestor = new GestorGrupos(tablero);
		turno = Color.BLANCO;

	}

	/**
	 * Cambia el turno.
	 * 
	 * @see quantik.util.Color
	 */
	public void cambiarTurno() {
		turno = turno.obtenerContrario();
	}

	/**
	 * Clona la partida profundamente.
	 *
	 * @return la partida clonada
	 */
	public Partida clonar() {
		Partida partidaClonada = new Partida(tablero.clonar(), cajaBlancas.clonar(), cajaNegras.clonar());
		if (partidaClonada.turno != this.turno) {
			partidaClonada.cambiarTurno();
		}
		partidaClonada.numJugadas = this.numJugadas;
		return partidaClonada;
	}

	/**
	 * Coloca la pieza del color del turno actual en la fila y columna pasadas,
	 * colocando la figura pasada en el tablero.
	 *
	 * @param fila    fila en la que se va a colocar la pieza
	 * @param columna columna en la que se va a colocar la pieza
	 * @param figura  tipo de figura a colocar
	 * @throws CoordenadasIncorrectasException la excepción que nos indica que las
	 *                                         coordenadsa son incorrectas
	 * @see quantik.util.Color
	 */
	public void colocarPiezaEnTurnoActual(int fila, int columna, Figura figura) throws CoordenadasIncorrectasException {
		try {
			tablero.colocar(fila, columna, obtenerCajaDelTurno(turno).retirar(figura));
			numJugadas++;
		} catch (CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException(
					"Excepción en colocarPiezaEnTurnoActual, coordenadas fuera del tablero", e);
		}

	}

	/**
	 * Consulta la caja de piezas blancas.
	 *
	 * @return la caja de piezas blancas
	 */
	public Caja consultarCajaBlancas() {
		return cajaBlancas.clonar();
	}

	/**
	 * Consulta la caja de piezas negras.
	 *
	 * @return la caja de piezas negras
	 */
	public Caja consultarCajaNegras() {
		return cajaNegras.clonar();
	}

	/**
	 * Consulta el ganador.
	 *
	 * @return el color que ha ganado
	 * @see quantik.util.Color
	 * @see quantik.modelo.GestorGrupos
	 */
	public Color consultarGanador() {

		if (gestor.hayGrupoGanador() == true) {
			return turno;
		}
		if (estaBloqueadoTurnoActual() == true || obtenerCajaDelTurno(turno).contarPiezasActuales() == 0) {
			return turno.obtenerContrario();
		}
		return null;

	}

	/**
	 * Consulta el número jugada.
	 *
	 * @return el número de jugada actual
	 */
	public int consultarNumeroJugada() {
		return numJugadas;
	}

	/**
	 * Consulta el tablero.
	 *
	 * @return el tablero actual quantik.modelo.Tablero
	 */
	public Tablero consultarTablero() {
		return tablero.clonar();
	}

	/**
	 * Consulta el turno.
	 *
	 * @return el color actual
	 */
	public Color consultarTurno() {
		return turno;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		return Objects.equals(cajaBlancas, other.cajaBlancas) && Objects.equals(cajaNegras, other.cajaNegras)
				&& Objects.equals(gestor, other.gestor) && numJugadas == other.numJugadas
				&& Objects.equals(tablero, other.tablero) && turno == other.turno;
	}

	/**
	 * Comprueba si es jugada legal en el turno actual.
	 *
	 * @param fila    la fila sobre la que se quiere comprobar si es legal la jugada
	 * @param columna la columna sobre la que se quiere comprobar si es legal la
	 *                jugada
	 * @param figura  la figura sobre la que se quiere comprobar si es legal la
	 *                jugada
	 * @return true, si la jugada es legal, false, si no
	 * @see quantik.modelo.Caja
	 * @see quantik.modelo.Tablero
	 * @see quantik.modelo.GestorGrupos
	 * @see quantik.util.Color
	 */
	public boolean esJugadaLegalEnTurnoActual(int fila, int columna, Figura figura) {
		if (tablero.estaEnTablero(fila, columna) == true) {
			try {
				Caja caja = obtenerCajaDelTurno(turno);
				if (tablero.consultarCelda(fila, columna).estaVacia() == false || caja.estaDisponible(figura) == false
						|| gestor.hayConflictoEnGruposDeCelda(tablero.consultarCelda(fila, columna), figura,
								turno) == true) {
					return false;
				}
			} catch (CoordenadasIncorrectasException e) {
				throw new RuntimeException("Excepción en esJugadaLegalEnTurnoActual, coordenadas fuera del tablero", e);
			}
//				throw new CoordenadasIncorrectasException(
//						"Excepción en esJugadaLegalEnTurnoActual, coordenadas fuera del tablero", e);

			return true;
		} else {
			return false;
		}

	}

	/**
	 * Comprueba si esta acabada partida.
	 *
	 * @return true, si la partida está acabada, false si no lo está
	 * @see quantik.util.Color
	 */
	public boolean estaAcabadaPartida() {

		if (hayAlgunGrupoCompleto() == true || estaBloqueadoTurnoActual() == true
				|| obtenerCajaDelTurno(turno).contarPiezasActuales() == 0) {
			return true;
		}

//			throw new CoordenadasIncorrectasException("Excepción en estaAcabadaPartida, coordenadas fuera del tablero",
//					e);

		return false;
	}

	/**
	 * Comprueeba si esta bloqueado el turno actual.
	 *
	 * @return true, si está bloqueado, false en caso contrario
	 * @see quantik.modelo.Caja
	 * @see quantik.modelo.Pieza
	 * @see quantik.modelo.Tablero
	 */
	public boolean estaBloqueadoTurnoActual() {

		Caja caja = obtenerCajaDelTurno(turno);
		if (caja.contarPiezasActuales() > 0) {
			List<Pieza> piezas = caja.consultarPiezasDisponibles();
			for (int i = 0; i < tablero.consultarNumeroFilas(); i++) {
				for (int j = 0; j < tablero.consultarNumeroColumnas(); j++) {
					for (int k = 0; k < piezas.size(); k++) {
						if (esJugadaLegalEnTurnoActual(i, j, piezas.get(k).consultarFigura()) == true) {
							return false;

						}
					}
				}
			}
		}

//			throw new CoordenadasIncorrectasException(
//					"Excepción en estaBloqueadoTurnoActual, coordenadas fuera del tablero", e);

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cajaBlancas, cajaNegras, gestor, numJugadas, tablero, turno);
	}

	/**
	 * Devuelve si hay algún grupo completo.
	 *
	 * @return true, si hay algún grupo completo, false si no
	 * @see quantik.modelo.GestorGrupos
	 */
	public boolean hayAlgunGrupoCompleto() {
		return gestor.hayGrupoGanador();
	}

	/**
	 * Obtiene la caja del turno pasado.
	 *
	 * @param turno el turno del que se quiere obtener la caja
	 * @return la caja correspondiente al turno pasado
	 */
	private Caja obtenerCajaDelTurno(Color turno) {
		if (turno == Color.BLANCO) {
			return cajaBlancas;
		} else {
			return cajaNegras;
		}
	}

	@Override
	public String toString() {
		return "Partida [gestor=" + gestor + ", turno=" + turno + ", tablero=" + tablero + ", numJugadas=" + numJugadas
				+ ", cajaBlancas=" + cajaBlancas + ", cajaNegras=" + cajaNegras + "]";
	}
}
