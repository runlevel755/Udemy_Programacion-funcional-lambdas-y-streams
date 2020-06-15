package Referencia;

import java.util.ArrayList;

public class ReferenciaMetodo {

//	Tipos de métodos de referencia
//
//	TYPE								SYNTAX						METHOD REFERENCE					LAMBA EXPRESSION
//	-----------------------------------------------------------------------------------------------------------------------------
//	Referencia a					Class::staticMethod					Math::abs						n -> Math.abs(n);
//	statyc method
//	-----------------------------------------------------------------------------------------------------------------------------
//	Referencia a un					instancia::metodoInstancia			s:toString						() -> "string".toString;
//	método de instancia
//	de un objeto particular
//	-----------------------------------------------------------------------------------------------------------------------------
//	Referencia a un					Class::metodoInstancia				String::toString				s -> s.toString();
//	método de instancia
//	de un objeto arbitrario
//	de un tipo particular
//	-----------------------------------------------------------------------------------------------------------------------------
//	Referencia a un					Class::new							String:new						() ->  new String;
//	constructor
//	-----------------------------------------------------------------------------------------------------------------------------

	public static void main(final String[] args) {

		// REFERENCIA A MÉTODO ESTÁTICO

		// Traditional Method
		final Trabajo trabajo = new Trabajo() {

			@Override
			public void accion() {
				User.referenciaAMetodoEstatico();
			}
		};

		// Lambda Expression
		final Trabajo trabajoLambda = () -> User.referenciaAMetodoEstatico();

		// Method Reference
		final Trabajo trabajoMR = User::referenciaAMetodoEstatico;
		trabajoMR.accion();

		// REFERENCIA A MÉTODO DE INSTANCIA DE UN OBJETO PARTICULAR

		final User user = new User("Pedro");

		// Lambda Expression
		final Trabajo trabajoLambda_2 = () -> user.referenciaAMetodoParticular();

		// Method Reference
		final Trabajo trabajoMR_2 = user::referenciaAMetodoParticular;
		trabajoMR_2.accion();

		// REFERENCIA A UN MÉTODO DE INSTANCIA
		// DE UN OBJETO ARBITRARIO DE UN TIPO PARTICULAR

		// Lambda Expression
		final TrabajoString trabajoStringLambda = (palabra) -> palabra.toUpperCase();

		// Method Reference
		final TrabajoString trabajoStringMR = String::toUpperCase;
		System.out.println(trabajoStringMR.accion("esto lo va a convertir a mayúsculas"));

		// RECORRER ARRAYLIST USANDO LAMBDA Y METHOD REFERENCE
		final java.util.List<User> users = new ArrayList<>();
		users.add(new User("Cris"));
		users.add(new User("Pedro"));
		users.add(new User("Pepito"));
		users.add(new User("Bea"));

		// Lambda Expression
		users.forEach(nombre -> nombre.mostrarNombre());

		// Method Reference
		users.forEach(User::mostrarNombre);

		// REFERENCIA A UN CONSTRUCTOR

		final IUser user1 = new IUser() {
			@Override
			public User crear(final String nombre) {
				return new User(nombre);
			}
		};

		// Lambda Expression
		final IUser user2 = nombre -> new User(nombre);

		// Method Reference
		final IUser user3 = User::new;
	}
}
