package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * La clase gestor de grupos que se encarga de gestionar los distintos grupos
 * que tiene el quantik.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 2.0
 * @since 1.0
 */
public class GestorGrupos {

	/** Los grupos del gestor de grupos. */
	private List<Grupo> grupos = new ArrayList<Grupo>(12);

	/**
	 * Constructor del gestor de grupos.
	 *
	 * @param tablero el tablero sobre el que vamos a crear los grupos
	 * @see Celda
	 * @see Tablero
	 * @see Grupo
	 */
	public GestorGrupos(Tablero tablero) {
		try {
			List<List<Celda>> celdas = new ArrayList<>(12);

			// Vertical
			for (int i = 0; i < 4; i++) {
				celdas.add(new ArrayList<>(4));
				for (int j = 0; j < 4; j++) {
					celdas.get(i).add(tablero.obtenerCelda(j, i));
				}
			}
			// Htal
			for (int i = 4; i < 8; i++) {
				celdas.add(new ArrayList<>(4));
				for (int j = 0; j < 4; j++) {
					celdas.get(i).add(tablero.obtenerCelda(i - 4, j));
				}
			}
			// Cajas
			celdas = crearGruposCuadrados(tablero, celdas);
			for (int i = 0; i < 12; i++) {
				grupos.add(new Grupo(celdas.get(i)));
			}
		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Excepción en esJugadaLegalEnTurnoActual, coordenadas fuera del tablero", e);
		}
	}

	/**
	 * Crea los grupos cuadrados.
	 *
	 * @param tablero el tablero sobre el que se van a crear los grupos
	 * @param celdas  las celdas que componen los grupos
	 * @return las celdas de los grupos
	 */
	private List<List<Celda>> crearGruposCuadrados(Tablero tablero, List<List<Celda>> celdas) {

		try {
			for (int i = 0; i < 4; i++) {
				celdas.add(new ArrayList<>(4));
			}
			for (int i = 0; i < tablero.consultarNumeroFilas(); i++) {
				for (int j = 0; j < tablero.consultarNumeroColumnas(); j++) {
					if (i < tablero.consultarNumeroFilas() / 2) {
						if (j < tablero.consultarNumeroColumnas() / 2) {
							celdas.get(8).add(tablero.obtenerCelda(i, j));
						} else {
							celdas.get(9).add(tablero.obtenerCelda(i, j));
						}
					} else {
						if (j < tablero.consultarNumeroColumnas() / 2) {
							celdas.get(10).add(tablero.obtenerCelda(i, j));
						} else {
							celdas.get(11).add(tablero.obtenerCelda(i, j));
						}
					}
				}
			}
		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Excepción en esJugadaLegalEnTurnoActual, coordenadas fuera del tablero", e);
		}

//			throw new CoordenadasIncorrectasException(
//					"Excepción en crearGruposCuadrados, coordenadas fuera del tablero", e);

		return celdas;
	}

	/**
	 * Comprueba si hay conflicto en los grupos de una celda.
	 *
	 * @param celda  la celda sobre la que vamos a comprobar si hay confilcto en
	 *               alguno de sus grupos
	 * @param figura la figura sobre la que vamos a comprobar los conflictos
	 * @param turno  el turno actual
	 * @return true, si hay onflicto, false si no lo hay
	 * @see Grupo
	 */
	public boolean hayConflictoEnGruposDeCelda(Celda celda, Figura figura, Color turno) {
		List<Grupo> grupos = obtenerGruposConteniendoCelda(celda);
		for (int i = 0; i < grupos.size(); i++) {
			if (grupos.get(i).consultarNumeroPiezas() == grupos.get(i).consultarNumeroCeldas()
					|| grupos.get(i).existeMismaPiezaDelColorContrario(figura, turno) == true) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Comprueba si hay un grupo ganador.
	 *
	 * @return true, si hay grupo ganador, false en caso contrario
	 * @see Grupo
	 */
	public boolean hayGrupoGanador() {
		for (int i = 0; i < grupos.size(); i++) {
			if (grupos.get(i) != null && grupos.get(i).consultarNumeroPiezas() == grupos.get(i).consultarNumeroCeldas()
					&& grupos.get(i).estaCompletoConFigurasDiferentes() == true) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtiene los grupos que contienen una celda.
	 *
	 * @param celda la celda sobre la que queremos obtener los grupos
	 * @return los grupos que contienen la celda
	 */
	public List<Grupo> obtenerGruposConteniendoCelda(Celda celda) {
		List<Grupo> gruposContenidos = new ArrayList<Grupo>(3);
		for (int i = 0; i < grupos.size(); i++) {
			if (celda != null && grupos.get(i).contieneCelda(celda) == true) {
				gruposContenidos.add(grupos.get(i));
			}
		}
		return gruposContenidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(grupos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GestorGrupos other = (GestorGrupos) obj;
		return Objects.equals(grupos, other.grupos);
	}

	@Override
	public String toString() {
		return "GestorGrupos [grupos=" + grupos + "]";
	}

}
