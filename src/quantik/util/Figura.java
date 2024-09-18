package quantik.util;

/**
 * Enumerado de las figuras que vamos a utilizar.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 */
public enum Figura {
	/**
	 * Figura CILINDRO("CL").
	 */
	CILINDRO("CL"),
	/**
	 * Figura CONO("CN").
	 */
	CONO("CN"),
	/**
	 * Figura CUBO("CB").
	 */
	CUBO("CB"),
	/**
	 * Figura ESFERA("ES").
	 */
	ESFERA("ES");

	/**
	 * Texto asociado a la figura.
	 */
	private String texto;

	/**
	 * Contructor de la figura.
	 * 
	 * @param texto Cadena de texto asociada a la figura
	 */
	private Figura(String texto) {
		this.texto = texto;
	}

	/**
	 * Obtiene el texto de una figura.
	 * 
	 * @return texto correspondiente a una figura
	 */
	public String aTexto() {
		return texto;
	}
}
