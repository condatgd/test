package katas.jsonpath.v2;

import org.json.simple.JSONObject;

import java.util.List;

public class JSONPathTests {

    public static void main(String[] args) {
        String json =
                "{ \"store\": {\n" +
                "    \"book\": [ \n" +
                "      { \"category\": \"reference\",\n" +
                "        \"author\": \"Nigel Rees\",\n" +
                "        \"title\": \"Sayings of the Century\",\n" +
                "        \"price\": 8.95\n" +
                "      },\n" +
                "      { \"category\": \"fiction\",\n" +
                "        \"author\": \"Evelyn Waugh\",\n" +
                "        \"title\": \"Sword of Honour\",\n" +
                "        \"price\": 12.99\n" +
                "      },\n" +
                "      { \"category\": \"fiction\",\n" +
                "        \"author\": \"Herman Melville\",\n" +
                "        \"title\": \"Moby Dick\",\n" +
                "        \"isbn\": \"0-553-21311-3\",\n" +
                "        \"price\": 8.99\n" +
                "      },\n" +
                "      { \"category\": \"fiction\",\n" +
                "        \"author\": \"J. R. R. Tolkien\",\n" +
                "        \"title\": \"The Lord of the Rings\",\n" +
                "        \"isbn\": \"0-395-19395-8\",\n" +
                "        \"price\": 22.99\n" +
                "      }\n" +
                "    ],\n" +
                "    \"bicycle\": {\n" +
                "      \"color\": \"red\",\n" +
                "      \"price\": 19.95\n" +
                "    },\n" +
                "    \"str\": \"avalue\",\n" +
                "    \"num\": 123,\n" +
                "    \"bool\": true\n" +
                "  }\n" +
                "}";

        List<String> positiveRules = List.of(
                "$.store.book[?(@.price < 10)].title",
                "$.store.bicycle",
                "$.store.str",
                "$.store.num",
                "$.store.bool"
        );

        JSONObject jsonObjPositive = JSONPathProcessor.applyRulesPositive(json, positiveRules);
        System.out.println("jsonObjPositive: " + jsonObjPositive);

        List<String> negativeRules = List.of(
                "$.store.bicycle.price",
                "$.store.str"
        );

        JSONObject jsonObjNegative = JSONPathProcessor.applyRulesNegative(jsonObjPositive.toJSONString(), negativeRules);
        System.out.println("jsonObjNegative: " + jsonObjNegative);

    }


}
