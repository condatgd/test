package de.condat.gd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example3 {

	Function<Integer, String> intToString = Object::toString;   // IntFunction,
																// LongFunction,
																// DoubleFunction

	Map<String, Integer> nameMap = new HashMap<>();
	Integer value = nameMap.computeIfAbsent("John", String::length);

	Function<String, String> quote = s -> "'" + s + "'";

	Function<Integer, String> quoteIntToString = quote.compose(intToString);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Example3 ex3 = new Example3();
		System.out.println("nameMap: " + ex3.nameMap);
		System.out.println("value: " + ex3.value);
		System.out.println("quoteIntToString 3: " + ex3.quoteIntToString.apply(3));

		Short[] array = { 1, 2, 3 };
		Byte[] transformedArray = ex3.transformArray(array, s -> (byte) (s * 2));
		List<Byte> list = Arrays.stream(transformedArray).collect(Collectors.toList());
		Arrays.asList(transformedArray).forEach(x -> System.out.println(x));

		Map<String, Integer> salaries = new HashMap<>(); // BiFunction
		salaries.put("John", 40000);
		salaries.put("Freddy", 30000);
		salaries.put("Samuel", 50000);

		// Two-Arity Function
		salaries.replaceAll((name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 10000);

		System.out.println("salaries: " + salaries);

		// Suppliers
		Double valueSquared = squareLazy(ex3.lazyValue);

		ex3.fibonacci.limit(10).forEach(x -> System.out.println(x));

	}

	public Byte[] transformArray(Short[] array, ShortToByteFunction function) {
		Byte[] transformedArray = new Byte[array.length];
		for (int i = 0; i < array.length; i++) {
			transformedArray[i] = function.applyAsByte(array[i]);
		}
		return transformedArray;
	}

	public static double squareLazy(Supplier<Double> lazyValue) {
		return Math.pow(lazyValue.get(), 2);
	}

	Supplier<Double> lazyValue = () -> {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 9d;
	};

	long[] fibs = { 0, 1 };
	Stream<Long> fibonacci = Stream.generate(() -> {
		long result = fibs[1];
		long fib3 = fibs[0] + fibs[1];
		fibs[0] = fibs[1];
		fibs[1] = fib3;
		return result;
	});

}
