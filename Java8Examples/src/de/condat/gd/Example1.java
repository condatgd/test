package de.condat.gd;

import java.util.Arrays;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example1 {

	public static void main(String[] args) {

		List<Integer> myList = Arrays.asList(2, 3, 1);

		mapExample(myList);
		filterExample(myList);
		reduceExample(myList);
		countExample(myList);
		appendExample1(myList);
		addExample(myList);

		sequentialTest();
		parallelTest();
	}

	private static void mapExample(List<Integer> myList) {
		test("Map Test: element -> element + 3", myList,
				myList.stream().map(element -> element + 3).collect(Collectors.toList()));
	}

	private static void filterExample(List<Integer> myList) {
		System.out.println("Filter Test: element -> element > 2");
		List<Integer> resultList = myList.stream().filter(element -> element > 2).collect(Collectors.toList());
		resultList.forEach((Integer element) -> System.out.println(element));
		System.out.println();
	}

	private static void reduceExample(List<Integer> myList) {
		System.out.println("Reduce Test: reduce (0, (element1,element2) -> element1+element2");
		Integer sum = myList.stream().reduce(0, (element1, element2) -> element1 + element2);
		System.out.println(sum);
		System.out.println();
	}

	private static void countExample(List<Integer> myList) {
		System.out.println("Count Test: count()");
		long count = myList.stream().count();
		System.out.println(count);
		System.out.println();
	}

	private static void appendExample1(List<Integer> myList) {
		System.out.println("Append Test: map(e -> e.toString()).reduce(\"\", (e1,e2) -> e1+e2)");
		String result = myList.stream().map(e -> e.toString()).reduce("", (e1, e2) -> e1 + e2);
		System.out.println(result);
		System.out.println();
	}

	private static void addExample(List<Integer> myList) {
		System.out.println("Add Test: .reduce(0, Example1::plus)");
		Integer result = myList.stream().reduce(0, Example1::plus); // nur
																	// assoziative
																	// accumulatoren!
		System.out.println(result);
		System.out.println();
	}

	public static Integer plus(Integer s1, Integer s2) {
		return s1 + s2;
	}

	private static void sequentialTest() {
		System.out.println("Sequential Test: rangeClosed(1, 10)");
		IntStream s = IntStream.rangeClosed(1, 10);
		s.forEach((int element) -> System.out.println(element));
		System.out.println();
	}

	private static void parallelTest() {
		System.out.println("Parallel Test: rangeClosed(1, 10).parallel()");
		IntStream s = IntStream.rangeClosed(1, 10).parallel();
		s.forEach((int element) -> System.out.println(element));
		System.out.println();
	}

	private static <T> void test(String testInfo, List<T> myList, List<T> resultList) {
		System.out.println(testInfo);
		TwoLists<T, T> twoLists = new TwoLists<T, T>(myList, resultList);
		twoLists.forEach((e1, e2) -> {
			System.out.print(e1);
			System.out.print("->");
			System.out.println(e2);
		});
		System.out.println();
	}

}
