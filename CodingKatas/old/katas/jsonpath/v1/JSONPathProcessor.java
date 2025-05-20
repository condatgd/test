package katas.jsonpath.v1;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.MapFunction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONPathProcessor {

    public static JSONObject applyRulesPositive(String jsonStr, List<String> positiveRules) {
        Map<String, Object> map = new HashMap<>();
        for (String rule:positiveRules) {
            JSONObject keepJson = JSONPathProcessor.keep(jsonStr, rule);
            deepMerge(map, keepJson);
        }
        return new JSONObject(map);
    }

    public static JSONObject applyRulesNegative(String jsonStr, List<String> negativeRules) {
        final DocumentContext jsonContext = JsonPath.parse(jsonStr);

        for (String rule:negativeRules) {
            jsonContext.delete(rule);
        }
        return new JSONObject(jsonContext.json());
    }


    public static JSONObject keep(String json, String jsonPathRule) {
        final DocumentContext jsonContext = JsonPath.parse(json);
        jsonContext.map(jsonPathRule, mapFunction);
        ArrayList<ArrayList<String>> keepLists =
                extractKeepPathes(new ArrayList<>(), jsonContext.json(), new ArrayList<>());
        return applyKeepOperation(json, keepLists);
    }

    public static void deepMerge(Map<String, Object> map1, Map<String, Object> map2) {
        for(String key : map2.keySet()) {
            Object value2 = map2.get(key);
            if (map1.containsKey(key)) {
                Object value1 = map1.get(key);
                if (value1 instanceof Map && value2 instanceof Map)
                    deepMerge((Map<String, Object>) value1, (Map<String, Object>) value2);
                else if (value1 instanceof List && value2 instanceof List)
                    map1.put(key, merge((List) value1, (List) value2));
                else map1.put(key, value2);
            } else map1.put(key, value2);
        }
    }

    private static List merge(List list1, List list2) {
        list2.removeAll(list1);
        list1.addAll(list2);
        return list1;
    }

    private static JSONObject applyKeepOperation(String json, ArrayList<ArrayList<String>> keepLists) {
        final DocumentContext jsonContext1 = JsonPath.parse(json);
        HashMap<String, Object> targetMap = new HashMap<>();
        for(ArrayList<String> path: keepLists) {
            createPathValue(targetMap, path);
        }
        JSONObject jsonObject = new JSONObject(targetMap);
        return jsonObject;
    }

    private static void createPathValue(HashMap<String, Object> targetMap, ArrayList<String> path) {
        Object currentObj = targetMap;
        String currentType = path.get(0);
        List<String> pathCurrent = path.subList(1, path.size());
        for(int i=0; i<pathCurrent.size();i+=2) {
            String currentKey = pathCurrent.get(i);
            String newValue;
            if(i==pathCurrent.size()-1) {
                newValue    = "value";
            } else {
                newValue    = pathCurrent.get(i+1);
            }
            if("hash".equals(currentType)) {
                HashMap<String, Object> currentMap = (HashMap<String, Object>) currentObj;
                if("hash".equals(newValue)) {
                    currentMap.putIfAbsent(currentKey, new HashMap<>());
                } else
                if("list".equals(newValue)) {
                    currentMap.putIfAbsent(currentKey, new ArrayList<>());
                } else {
                    try {
                        Object json =  new JSONParser().parse(newValue);
                        currentMap.putIfAbsent(currentKey, json);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                currentObj = currentMap.get(currentKey);
            }
            if("list".equals(currentType)) {
                if("value".equals(newValue)) {
                    try {
                        Object json =  new JSONParser().parse(currentKey);
                        ((List) currentObj).add(json);
                        currentObj = json;
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                if("hash".equals(currentKey)) {
                    Map<String, Object> map = new HashMap<>();
                    String aValue = pathCurrent.get(i + 2);
                    if(aValue.startsWith("\"") && aValue.endsWith("\"")) {
                        aValue = aValue.substring(1,aValue.length()-2);
                    }
                    map.put(newValue, aValue);
                    ((List) currentObj).add(map);
                }
            }
            currentType = newValue;
        }
    }

    static MapFunction mapFunction = new MapFunction() {
        @Override
        public Object map(Object currentValue, Configuration configuration) {
            String currentValueJson;
            if(currentValue instanceof String) {
                currentValueJson = "\"" + (String)currentValue + "\"";
            } else {
                currentValueJson = configuration.jsonProvider().toJson(currentValue);
            }
            String wrappedValue = "{ \"keep\": " + currentValueJson + "}";
            return wrappedValue;
        }
    };

    private static ArrayList<ArrayList<String>> extractKeepPathes(ArrayList<String> path, Object mappedJson, ArrayList<ArrayList<String>> keepLists) {
        if(mappedJson instanceof Map) {
            Map map = (Map)mappedJson;
            for (Object key : map.keySet()) {
                ArrayList<String> path1 = new ArrayList<>(path);
                path1.add("hash");
                path1.add(key.toString());
                extractKeepPathes(path1, map.get(key), keepLists);
            }
        } else if(mappedJson instanceof ArrayList) {
            ArrayList list = (ArrayList)mappedJson;
            for (int i=0; i<list.size(); i++) {
                Object o = list.get(i);
                ArrayList<String> path1 = new ArrayList<>(path);
                path1.add("list");
                // path1.add(""+i);
                extractKeepPathes(path1, o, keepLists);
            }

        } else if(mappedJson instanceof String) {
            String str = (String)mappedJson;
            String keepPraefix = "{ \"keep\": ";
            if (str.startsWith(keepPraefix)) {
                str = str.substring(keepPraefix.length(), str.length() - 1);
                ArrayList<String> path1 = new ArrayList<>(path);
                path1.add(str);
                keepLists.add(path1);
            }
        }
        /*
        else {
            System.out.println("other type: " + mappedJson.getClass().getName());
        }
         */
        return keepLists;
    }

}
