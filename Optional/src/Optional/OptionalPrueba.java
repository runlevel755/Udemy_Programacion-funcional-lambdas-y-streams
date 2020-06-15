package Optional;

import java.util.Optional;

public class OptionalPrueba {

	public static void main(final String[] args) {

		probarOptional("Pedro");
		orElseOptional(null);
		orElseThrow("Pedro");
		isPresent(null);

	}

	// Probar NullPointer
	public static void probarOptional(final String nombre) {
		System.out.println(nombre.length());
	}

	public static void crearOptional() {

		final Optional<String> optional = Optional.empty();
		optional.get();
	}

	// Si param null, devuelve otro valor
	public static void orElseOptional(final String nombre) {

		final Optional<String> optional = Optional.ofNullable(nombre);
		// final Optional<String> optional1 = Optional.of(nombre);

		final String nombreOfNullable = optional.orElse("Vacío");
		// final String nombreOf = optional1.orElse("Vacío");

		System.out.println(nombreOfNullable);
		// System.out.println(nombreOf);
	}

	// Si param null, lanza exception
	public static void orElseThrow(final String nombre) {

		final Optional<String> optional = Optional.ofNullable(nombre);
		optional.orElseThrow(NullPointerException::new);

		final String nombre1 = optional.get();
		System.out.println(nombre1);
	}

	public static void isPresent(final String nombre) {
		final Optional<String> optional = Optional.ofNullable(nombre);
		System.out.println(optional.isPresent());
	}

}
