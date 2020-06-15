package Lambda;

public interface DefaultInterface {

	void mostrarNombre(String nombre);

	default String nombrePorDefecto(final String nombre) {
		return nombre + " González";
	}

}
