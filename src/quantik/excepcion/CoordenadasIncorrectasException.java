package quantik.excepcion;

/**
 * La clase CoordenadasIncorrectasException que hereda de la clase Exception.
 * 
 * @author <a href="mailto:crv1002@alu.ubu.es">César Rodríguez Villagrá</a>
 * @version 1.0
 * @since 1.0
 * @see java.lang.Exception
 */
public class CoordenadasIncorrectasException extends Exception {

	/**
	 * La constante serialVersionUID, utilizada para la serialización de objetos.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la excepción coordenadas incorrectas.
	 */
	public CoordenadasIncorrectasException() {

	}

	/**
	 * Constructor de la excepción coordenadas incorrectas.
	 *
	 * @param message el mensaje que queremos pasar a la excepción
	 */
	public CoordenadasIncorrectasException(String message) {
		super(message);
	}

	/**
	 * Constructor de la excepción coordenadas incorrectas.
	 *
	 * @param throwable la excepción que ha provocado esta excepción
	 */
	public CoordenadasIncorrectasException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructor de la excepción coordenadas incorrectas.
	 * 
	 * @param message   el mensaje que queremos pasar a la excepción
	 * @param throwable la excepción que ha provocado esta excepción
	 */
	public CoordenadasIncorrectasException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
