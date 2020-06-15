package streamPrueba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPrueba {

	private static List<User> users;

	public static void main(final String[] args) {

		final Stream stream = Stream.of(users);
		// users.stream();

		// FOR EACH
		System.out.println("------------------- USER LIST ------------------------");
		setUpUser();

		users.stream().forEach(e -> e.setName(e.getName() + " Lastname"));

		imprimirLista();

		// MAP Y COLLECTORS.TOLIST
		// Pasamos una lista de usuarios a una lista de String
		System.out.println("------------------- STRING LIST ------------------------");
		setUpUser();

		final List<String> listaString = users.stream().map(e -> e.getId() + " " + e.getName())
				.collect(Collectors.toList());

		listaString.stream().forEach(e -> System.out.println(e));

		// FILTER
		System.out.println("------------------- FILTERS ------------------------");
		setUpUser();

		final List<User> userFilter = users.stream().filter(e -> e.getName() != "Elon Musk").filter(e -> e.getId() < 3)
				.collect(Collectors.toList());

		userFilter.stream().forEach(e -> System.out.println(e.getId() + " " + e.getName()));

		System.out.println("------------------- FIND FIRST ------------------------");
		setUpUser();

		final User user = users.stream().filter(e -> e.getName().equals("Elon Musk")).findFirst().orElse(null);

		System.out.println(user.getId() + " " + user.getName());

		// FLATMAP
		// Juntar varias listas en una única
		System.out.println("------------------- FLAT MAP ------------------------");

		final List<List<String>> nombreVariasListas = new ArrayList<List<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("Alberto", "Isabel", "Pedro")),
						new ArrayList<String>(Arrays.asList("Monica", "Pablo"))));

		final List<String> nombreUnicaLista = nombreVariasListas.stream().flatMap(e -> e.stream())
				.collect(Collectors.toList());

		nombreUnicaLista.stream().forEach(e -> System.out.println(e));

		// PEEK
		// Similar a un foreach pero es una operación intermedia, no final
		System.out.println("------------------- PEEK ------------------------");
		setUpUser();

		final List<User> user2 = users.stream().peek(e -> e.setName(e.getName() + " Apellido"))
				.collect(Collectors.toList());

		user2.stream().forEach(e -> System.out.println(e.getName()));

		System.out.println("------------------- COUNT ------------------------");
		setUpUser();

		final long numeroFiltrado = users.stream().filter(e -> e.getId() < 3).count();
		System.out.println(numeroFiltrado);

		// SKIP Y LIMIT
		System.out.println("------------------- SKIP Y LIMIT ------------------------");

		final String[] abc = { "a", "b", "c", "d", "e", "f", "g" };

		final List<String> abcSkipLimit = Arrays.stream(abc).skip(2).limit(4).collect(Collectors.toList());

		abcSkipLimit.stream().forEach(e -> System.out.println(e));

		// SORTED
		System.out.println("------------------- SORTED ------------------------");
		setUpUser();

		users = users.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());

		imprimirLista();

		// MIN Y MAX
		System.out.println("------------------- MIN Y MAX ------------------------");
		setUpUser();

		final User userMin = users.stream().min(Comparator.comparing(User::getId)).orElse(null);

		System.out.println(userMin.getId());

		// DISTINCT
		System.out.println("------------------- DISTINCT ------------------------");
		final String[] abc1 = { "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d" };

		final List<String> abcDistinct = Arrays.stream(abc1).distinct().collect(Collectors.toList());

		abcDistinct.stream().forEach(e -> System.out.println(e));

		// ALLMATCH, ANYMATCH, NONEMATCH
		System.out.println("------------------- ALLMATCH, ANYMATCH, NONEMATCH ------------------------");
		final List<Integer> listaNumeros = Arrays.asList(100, 300, 900, 5000);

		final boolean allMatch = listaNumeros.stream().allMatch(e -> e > 301);
		final boolean anyMatch = listaNumeros.stream().anyMatch(e -> e > 301);
		final boolean noneMatch = listaNumeros.stream().noneMatch(e -> e > 10000);

		System.out.println("-AllMatch: \n" + allMatch + "\n-Anymatch: \n" + anyMatch + "\n-NoneMatch: \n" + noneMatch);

		// SUM, AVERAGE, RANGE
		System.out.println("------------------- SUM, AVERAGE, RANGE ------------------------");
		setUpUser();

		final double resultSum = users.stream().mapToInt(User::getId).sum();
		final double resultAverage = users.stream().mapToInt(User::getId).average().orElse(0);

		System.out.println("Sum: " + resultSum + " Average: " + resultAverage);

		System.out.println("Suma de 0 a 100: " + IntStream.range(0, 100).sum());

		// REDUCE
		System.out.println("------------------- REDUCE ------------------------");
		setUpUser();

		final int suma = users.stream().map(User::getId).reduce(0, Integer::sum);

		System.out.println(suma);

		// JOINING
		System.out.println("------------------- JOINING------------------------");
		setUpUser();

		final String names = users.stream().map(User::getName).collect(Collectors.joining(" - ")).toString();
		System.out.println(names);

		// TO SET
		System.out.println("------------------- TO SET ------------------------");
		setUpUser();

		final Set<String> setName = users.stream().map(User::getName).collect(Collectors.toSet());
		setName.stream().forEach(e -> System.out.println(e));

		// SUMMARIZING DOUBLE
		System.out.println("------------------- SUMMARIZING DOUBLE ------------------------");
		setUpUser();

		final DoubleSummaryStatistics statistics = users.stream().collect(Collectors.summarizingDouble(User::getId));
		// Otra forma de hacerlo
		final DoubleSummaryStatistics statisticsOther = users.stream().mapToDouble(User::getId).summaryStatistics();

		System.out.println("Average: " + statistics.getAverage() + " Max: " + statistics.getMax() + " Min: "
				+ statistics.getMin() + " Count: " + statistics.getCount() + " Sum: " + statistics.getSum());

		System.out.println("Average: " + statisticsOther.getAverage() + " Max: " + statisticsOther.getMax() + " Min: "
				+ statisticsOther.getMin() + " Count: " + statisticsOther.getCount() + " Sum: "
				+ statisticsOther.getSum());

		// PARTITIONING BY
		System.out.println("------------------- PARTITIONING BY ------------------------");
		setUpUser();

		final List<Integer> numeros = Arrays.asList(5, 7, 34, 56, 2, 3, 67, 4, 98);
		final Map<Boolean, List<Integer>> esMayor = numeros.stream().collect(Collectors.partitioningBy(e -> e > 10));

		esMayor.get(true).stream().forEach(e -> System.out.println("Cumplen el predicado: " + e));
		esMayor.get(false).stream().forEach(e -> System.out.println("No cumplen el predicado: " + e));

		// GROUPING BY
		System.out.println("------------------- GROUPING BY ------------------------");
		setUpUser();

		final Map<Character, List<User>> grupoAlfabetico = users.stream()
				.collect(Collectors.groupingBy(e -> new Character(e.getName().charAt(0))));
		grupoAlfabetico.get('S').stream().forEach(e -> System.out.println(e.getName()));
		grupoAlfabetico.get('M').stream().forEach(e -> System.out.println(e.getName()));
		grupoAlfabetico.get('L').stream().forEach(e -> System.out.println(e.getName()));

		// MAPPING
		System.out.println("------------------- MAPPING ------------------------");
		setUpUser();

		final List<String> personas = users.stream().collect(Collectors.mapping(User::getName, Collectors.toList()));

		personas.stream().forEach(e -> System.out.println(e));

		// STREAMS EN PARALELO
		System.out.println("------------------- STREAMS EN PARALELO ------------------------");
		setUpUser();

		final List<String> lista = users.stream().collect(Collectors.mapping(User::getName, Collectors.toList()));

		// Stream normal
		long tiempo1 = System.currentTimeMillis();
		lista.stream().forEach(e -> convertirAMayusculas(e));
		long tiempo2 = System.currentTimeMillis();
		System.out.println("Normal: " + (tiempo2 - tiempo1));

		// Parallel Stream
		tiempo1 = System.currentTimeMillis();
		lista.parallelStream().forEach(e -> convertirAMayusculas(e));
		tiempo2 = System.currentTimeMillis();
		System.out.println("Paralelo: " + (tiempo2 - tiempo1));

	}

	private static String convertirAMayusculas(final String nombre) {
		try {
			Thread.sleep(1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		return nombre.toUpperCase();
	}

	private static void setUpUser() {

		users = new ArrayList<>();

		users.add(new User(1, "Elon Musk"));
		users.add(new User(2, "Steve Jobs"));
		users.add(new User(3, "Mark Zuckerberg"));
		users.add(new User(4, "Satya Nadella"));
		users.add(new User(5, "Linus Torvalds"));
		users.add(new User(6, "Elon Musk"));
	}

	private static void imprimirLista() {
		users.stream().forEach(e -> System.out.println(e.getId() + " " + e.getName()));
	}
}
