package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase de la caja de piezas de un jugador.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 2.0
 * @since 1.0
 */
public class Caja {
	/**
	 * Lista de piezas de la caja.
	 */
	private List<Pieza> piezas = new ArrayList<Pieza>(0);
	/**
	 * Color de las piezas de la caja.
	 */
	private Color color;

	/**
	 * Crea una caja donde guarda un jugador sus piezas.
	 * 
	 * @param color de las piezas
	 * @see Pieza
	 */
	public Caja(Color color) {
		this.color = color;
		piezas.add(new Pieza(Figura.CILINDRO, color));
		piezas.add(new Pieza(Figura.CILINDRO, color));

		piezas.add(new Pieza(Figura.CONO, color));
		piezas.add(new Pieza(Figura.CONO, color));

		piezas.add(new Pieza(Figura.CUBO, color));
		piezas.add(new Pieza(Figura.CUBO, color));

		piezas.add(new Pieza(Figura.ESFERA, color));
		piezas.add(new Pieza(Figura.ESFERA, color));
	}

	/**
	 * Clona una caja profundamente.
	 * 
	 * @return caja clonada profundamente
	 * @see Pieza
	 */
	public Caja clonar() {
		Caja cajaClonada = new Caja(this.color);
		cajaClonada.piezas.clear();
		for (int i = 0; i < piezas.size(); i++) {
			if (this.piezas.get(i) != null) {
				cajaClonada.piezas.add(this.piezas.get(i).clonar());
			}
		}
		return cajaClonada;
	}

	/**
	 * Consulta el color de la caja.
	 *
	 * @return el color de la caja
	 */
	public Color consultarColor() {
		return color;

	}

	/**
	 * Consulta las piezas disponibles en la caja.
	 *
	 * @return las piezas que están disponibles en la caja
	 */
	public List<Pieza> consultarPiezasDisponibles() {
		List<Pieza> disponibles = new ArrayList<Pieza>(contarPiezasActuales());
		for (int i = 0; i < piezas.size(); i++) {
			if (piezas.get(i) != null) {
				disponibles.add(piezas.get(i).clonar());
			}
		}
		return disponibles;
	}

	/**
	 * Cuenta las piezas disponibles.
	 *
	 * @return el número de piezas disponibles en la caja
	 * @see Pieza
	 */
	public int contarPiezasActuales() {
		return piezas.size();

	}

	/**
	 * Consulta si está disponible.
	 *
	 * @param figura la figura a comprobar si está en la caja
	 * @return true, si está disponible, false si no
	 * @see Pieza
	 */
	public boolean estaDisponible(Figura figura) {
		boolean siONo = false;

		for (int i = 0; i < piezas.size(); i++) {
			if (piezas.get(i) != null && piezas.get(i).consultarFigura() == figura) {
				siONo = true;
				break;
			}
		}
		return siONo;

	}

	/**
	 * Retira una figura de la caja.
	 *
	 * @param figura la figura que queremos quitar de la caja
	 * @return la pieza retirada
	 */
	public Pieza retirar(Figura figura) {
		if (estaDisponible(figura) == true) {
			Pieza piezaExtraida = null;
			for (int i = 0; i < piezas.size(); i++) {
				if (piezas.get(i) != null && piezas.get(i).consultarFigura() == figura) {
					piezaExtraida = piezas.remove(i);
					break;
				}
			}
			return piezaExtraida;
		}
		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, piezas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caja other = (Caja) obj;
		return color == other.color && Objects.equals(piezas, other.piezas);
	}

	@Override
	public String toString() {
		return "Caja [piezas=" + piezas + ", color=" + color + "]";
	}

}
