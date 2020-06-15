package Lambda;

public class Lambda implements DefaultInterface {

	public static void main(final String[] args) {
//		Argumentos | Operador | Cuerpo
//
//		  () -> "Mi nombre es";
//		  (n) -> n * n ;
//		  (n) -> n == 2;

		System.out.println(System.getProperty("java.runtime.version"));

		// Ejemplo nombre Anom
		final MiNombre miNombreAnom = new MiNombre() {

			@Override
			public String miNombre() {
				return "Pedro Anom";
			}
		};
		System.out.println(miNombreAnom.miNombre());

		// Ejemplo nombre Lambda
		final MiNombre miNombreLambda = () -> "Pedro Lambda";
		System.out.println(miNombreLambda.miNombre());

		// Ejemplo suma Anom
		final Sumar sumaAnom = new Sumar() {

			@Override
			public int suma(final int a, final int b) {
				return a + b;
			}

		};
		System.out.println(sumaAnom.suma(2, 3));

		// Ejemplo suma Lambda
		final Sumar sumaLambda = (a, b) -> a + b;
		System.out.println(sumaLambda.suma(2, 3));

		// Otro ejemplo con operaciones dentro
		final Sumar sumaLambda_2 = (a, b) -> {
			a = b * b;
			a = a + b;
			System.out.println("Mensjae dentro de una lambda");
			return a;
		};
		System.out.println(sumaLambda_2.suma(2, 3));

		// Ejemplo de una Default Interface
		final Lambda defaultInterface = new Lambda();
		System.out.println(defaultInterface.nombrePorDefecto("Pedro"));

	}

	@Override
	public void mostrarNombre(final String nombre) {
		// TODO Auto-generated method stub

	}

}
