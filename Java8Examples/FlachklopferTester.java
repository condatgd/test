package de.condat;

import java.io.*;
import java.text.ParseException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FlachklopferTester {

	public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
		// 1. Testdaten einlesen und json-simple-JSONObject erzeugen
		final String json = FileUtils.readFileToString(new File("./flachklopfer.json"));
		final JSONObject input = (JSONObject) new JSONParser().parse(json);

		// 2.) Flachklopfer aufrufen
		final Map<String, Object> flach = Flachklopfer.flachklopfen(input);

		// 3.) Ergebnis ausgeben
		System.out.println("Ergebnis flachgeklopft: ");
		flach.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareToIgnoreCase(e2.getKey())).forEach(e -> {
			System.out.println(String.format("%s: %s", e.getKey(), e.getValue()));
		});
	}

	public static class Flachklopfer {
		
		public static Map<String, Object> flachklopfen(final JSONObject pObj) {
			return flatten(pObj, "").collect(Collectors.toMap(e -> e.t1, e -> e.t2));
		}

		@SuppressWarnings("unchecked")
		private static Stream<GEntry<String, Object>> flatten(final Object pO, final String pPrefix) {
			return (pO instanceof JSONArray)
					? IntStream.range(0, ((JSONArray) pO).size())
							.mapToObj(i -> new GEntry<Integer, Object>(i, ((JSONArray) pO).get(i)))
							.flatMap(e -> flatten(e.t2, pPrefix + "[" + e.t1 + "]"))
					: (pO instanceof JSONObject)
							? ((Stream<GEntry<String, Object>>) ((JSONObject) pO).entrySet().stream()
									.map(e -> new GEntry<String, Object>(((Map.Entry<String, Object>) e).getKey(),
											((Map.Entry<String, Object>) e).getValue())))
													.flatMap(e -> flatten(e.t2,
															pPrefix + (pPrefix.length() > 0 ? "." : "") + e.t1))
							: Stream.of(new GEntry<String, Object>(pPrefix, pO));
		}

		private static class GEntry<T1, T2> {
			public T1 t1;
			public T2 t2;

			private GEntry(final T1 pT1, final T2 pT2) {
				this.t1 = pT1;
				this.t2 = pT2;
			}
		}
	}
}
