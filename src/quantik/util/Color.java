package quantik.util;

/**
 * Enumeración para los colores blanco y negro.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 */
public enum Color {
	/**
	 * Color BLANCO('B').
	 */
	BLANCO('B'),
	/**
	 * Color NEGRO('N').
	 */
	NEGRO('N');

	/**
	 * Inicializa el caracter asignado al color.
	 */
	private char letra;

	/**
	 * Asigna una letra al color.
	 * 
	 * @param letra a asignar
	 */
	private Color(char letra) {
		this.letra = letra;
	}

	/**
	 * Devuelve el caracter del color.
	 * 
	 * @return caracter del color
	 */
	public char toChar() {
		return letra;
	}

	/**
	 * Devuelve el color contrario.
	 * 
	 * @return color contrario
	 */
	public Color obtenerContrario() {
		return this.equals(BLANCO) ? NEGRO : BLANCO;
	}
}
