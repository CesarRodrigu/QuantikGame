package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * La clase grupo que se encarga de un grupo.
 *
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 2.0
 * @since 1.0
 */
public class Grupo {

	/** Las celdas del grupo. */
	private List<Celda> celdas = new ArrayList<Celda>(4);

	/**
	 * Contructor de grupo que crea un nuevo grupo a partir de las celdas pasadas.
	 *
	 * @param celdas las celdas con las que se quiere crear el grupo
	 */
	public Grupo(List<Celda> celdas) {
		this.celdas.addAll(celdas);
	}

	/**
	 * Clona el grupo.
	 *
	 * @return el grupo clonado
	 * @see Celda
	 */
	public Grupo clonar() {
		Grupo grupoClonado = new Grupo(celdas);
		grupoClonado.celdas.clear();
		for (int i = 0; i < celdas.size(); i++) {
			if (celdas.get(i) != null) {
				grupoClonado.celdas.add(celdas.get(i).clonar());
			}
		}
		return grupoClonado;
	}

	/**
	 * Consulta el número de celdas en el grupo.
	 *
	 * @return el número de celdas del grupo
	 * @see Celda
	 */
	public int consultarNumeroCeldas() {
		return celdas.size();
	}

	/**
	 * Consulta el número de piezas en el grupo.
	 *
	 * @return el número de piezas que hay en el grupo
	 * @see Celda
	 */
	public int consultarNumeroPiezas() {
		int contador = 0;
		for (int i = 0; i < celdas.size(); i++) {
			if (celdas.get(i) != null && celdas.get(i).estaVacia() == false) {
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Comprueba si el grupo tiene una celda en específico.
	 *
	 * @param celdaABuscar la celda que se quiere comprobar si está en el grupo
	 * @return true, si está en el grupo, false si no está en el grupo
	 */
	public boolean contieneCelda(Celda celdaABuscar) {
		for (int i = 0; i < celdas.size(); i++) {
			if (celdas.get(i) != null && celdaABuscar != null && celdas.get(i).equals(celdaABuscar) == true) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Comprueba si el grupo está completo con figuras diferentes.
	 *
	 * @return true, si está completo con figuras diferentes, false en caso
	 *         contrario
	 * @see Celda
	 * @see quantik.util.Figura
	 */
	public boolean estaCompletoConFigurasDiferentes() {
		boolean tieneCL = false;
		boolean tieneCN = false;
		boolean tieneCB = false;
		boolean tieneES = false;
		for (int i = 0; i < celdas.size(); i++) {
			if (celdas.get(i) != null && celdas.get(i).estaVacia() == false) {
				if (celdas.get(i).consultarPieza().consultarFigura() == Figura.CILINDRO) {
					tieneCL = true;
				}
				if (celdas.get(i).consultarPieza().consultarFigura() == Figura.CONO) {
					tieneCN = true;
				}
				if (celdas.get(i).consultarPieza().consultarFigura() == Figura.CUBO) {
					tieneCB = true;
				}
				if (celdas.get(i).consultarPieza().consultarFigura() == Figura.ESFERA) {
					tieneES = true;
				}
			}
		}
		if (tieneCL == true && tieneCN == true && tieneCB == true && tieneES == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Comprueba si existe misma pieza del color contrario.
	 *
	 * @param figura la figura con la que buscaremos una del color contrario
	 * @param color  el color de la figura
	 * @return true, si existe la misma pieza en el grupo de color contrario, false
	 *         en caso contrario
	 * @see Celda
	 */
	public boolean existeMismaPiezaDelColorContrario(Figura figura, Color color) {
		for (int i = 0; i < celdas.size(); i++) {
			if (celdas.get(i) != null && celdas.get(i).estaVacia() == false
					&& celdas.get(i).consultarPieza().consultarFigura() == figura
					&& celdas.get(i).consultarPieza().consultarColor() == color.obtenerContrario()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(celdas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		return Objects.equals(celdas, other.celdas);
	}

	@Override
	public String toString() {
		return "Grupo [celdas=" + celdas + "]";
	}

}
