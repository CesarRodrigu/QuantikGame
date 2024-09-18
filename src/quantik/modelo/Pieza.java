package quantik.modelo;

import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase de la pieza que vamos a utilizar en el quantik.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 */
public class Pieza {
	/**
	 * Figura que corresponde a la pieza.
	 */
	private Figura figura;
	/**
	 * Color que corresponde a la pieza.
	 */
	private Color color;

	/**
	 * Contructor que crea una pieza con un color y una figura.
	 * 
	 * @param figura de la pieza
	 * @param color  de la pieza
	 */
	public Pieza(Figura figura, Color color) {
		this.figura = figura;
		this.color = color;
	}

	/**
	 * Función que nos devuelve el resultado de concatenar los caracteres de la
	 * figura correspondiente con su color.
	 * 
	 * @return Devuelve la cadena de caractaeres que corresponde a la concatenación
	 *         de la figura con su color.
	 * @see quantik.util.Color
	 * @see quantik.util.Figura
	 */
	public String aTexto() {
		return figura.aTexto() + color.toChar();
	}

	/**
	 * Clona una pieza.
	 * 
	 * @return pieza clonada
	 */
	public Pieza clonar() {
		return new Pieza(this.figura, this.color);

	}

	/**
	 * Consulta el color de la pieza.
	 * 
	 * @return color de la pieza
	 */
	public Color consultarColor() {
		return color;
	}

	/**
	 * Consulta la figura de la pieza.
	 * 
	 * @return figura de la pieza
	 */
	public Figura consultarFigura() {
		return figura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, figura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return color == other.color && figura == other.figura;
	}

	@Override
	public String toString() {
		return "Pieza [figura=" + figura + ", color=" + color + "]";
	}

}
