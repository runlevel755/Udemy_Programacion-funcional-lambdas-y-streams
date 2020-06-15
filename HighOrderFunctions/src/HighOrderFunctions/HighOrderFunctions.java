package HighOrderFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HighOrderFunctions implements SumarInterface {

	public static void main(final String[] args) {

		final HighOrderFunctions hof = new HighOrderFunctions();

		// ------------------FUNCION------------------//
		System.out.println(hof.suma(2, 3));

		// ------------------INTERFAZ------------------//
		System.out.println(hof.apply(2, 4));

		// ------------------HIGH ORDER FUNCTION------------------//
//		final SumarInterface sumarInterface = new SumarInterface() {
//
//			@Override
//			public int apply(final int a, final int b) {
//				return a + b;
//			}
//		};

		final SumarInterface sumarLambda = (a, b) -> a + b;
		System.out.println(sumarLambda.apply(10, 10));

		// Esto hace lo mismo que la línea de arriba
		// System.out.println(hof.sumarHighOrderFun(sumarLambda, 2, 5));

		// ------------------INTERFAZ FUNCIONAL FUNCTION<T,R>------------------//

//		interface Function<T t, R t>{
//	  		R apply(T t)
//	  	}

		final Function<String, String> convertirAMayusculas = e -> e.toUpperCase();
		hof.imprimirMayusculas(convertirAMayusculas, "pedro");

		// ------------------INTERFAZ FUNCIONAL BIFUNCTION<T,U,R>------------------//

//		interface BiFunction<T,U,R>{
//			R apply(T t, U u);
//		}

//		interface Predicate<T>{
//			Boolean Test (T t);
//		}

		final List<Integer> numeros = Arrays.asList(6, 23, -5, 4, 68, -9, -67, 46);

		BiFunction<List<Integer>, Predicate<Integer>, List<Integer>> filtrar;

		filtrar = (lista, predicado) -> lista.stream().filter(e -> predicado.test(e)).collect(Collectors.toList());

		System.out.println(filtrar.apply(numeros, e -> e > 0));

		// ------------------INTERFAZ FUNCIONAL CONSUMER<T>------------------//
//		interface Consumer<T>{
//			void accept (T t);
//		}

		final List<String> nombres = new ArrayList<>();
		nombres.add("Alberto");
		nombres.add("María");
		nombres.add("Paco");
		hof.filtrar(nombres, e -> System.out.println(e), 6);
	}

	public void filtrar(final List<String> lista, final Consumer<String> consumer, final int maximoCaracteres) {
		lista.stream().filter(logicaPredicado(maximoCaracteres)).forEach(consumer);
	}

	public Predicate<String> logicaPredicado(final int maximoCaracteres) {
		return e -> e.length() < maximoCaracteres;
	}

	public int suma(final int a, final int b) {
		return a + b;
	}

	@Override
	public int apply(final int a, final int b) {
		return a + b;
	}

	public int sumarHighOrderFun(final SumarInterface sumar, final int a, final int b) {
		return sumar.apply(a, b);
	}

	public void imprimirMayusculas(final Function<String, String> function, final String nombre) {
		System.out.println(function.apply(nombre));
	}
}
