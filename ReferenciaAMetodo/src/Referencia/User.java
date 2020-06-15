package Referencia;

public class User {

	private final String nombre;

	public User(final String nombre) {
		this.nombre = nombre;
	}

	public static void referenciaAMetodoEstatico() {
		System.out.println("Probando referencia a método estático");
	}

	public void referenciaAMetodoParticular() {
		System.out.println("Probando referencia a método de objeto particular");
	}

	public void mostrarNombre() {
		System.out.println(nombre);
	}

}