package katas.jsonpath.v2;

import com.jayway.jsonpath.*;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class JSONPathProcessor {

    public static final String MARKER_THIS_IS_A_LIST = "thisIsAList";

    public static JSONObject applyRulesPositive(String jsonStr, List<String> positiveRules) {
        final DocumentContext jsonContext = JsonPath.parse(jsonStr);
        EvalCallback evalListener = new EvalCallback(new JSONObject(new HashMap<>()));
        ReadContext readContext = jsonContext.withListeners(evalListener);
        for (String rule:positiveRules) {
            readContext.read(rule);
        }
        return new JSONObject(recreateJsonLists(evalListener.getJsonObj()));
    }

    public static JSONObject applyRulesNegative(String jsonStr, List<String> negativeRules) {
        final DocumentContext jsonContext = JsonPath.parse(jsonStr);
        for (String rule:negativeRules) {
            jsonContext.delete(rule);
        }
        return new JSONObject(jsonContext.json());
    }

    @SuppressWarnings("unchecked")
    private static Map recreateJsonLists(Map jsonObj) {
        for(Object key: jsonObj.keySet()) {
            Object o = jsonObj.get(key);
            Object o1 = recreateObjLists(o);
            jsonObj.put(key, o1);
        }
        return jsonObj;
    }

    @SuppressWarnings("unchecked")
    private static Object recreateObjLists(Object obj) {
        if(obj instanceof Map objMap) {
            if(objMap.get(MARKER_THIS_IS_A_LIST)!=null) {
                JSONArray array = new JSONArray();
                for(Object key: objMap.keySet()) {
                    if(!key.equals(MARKER_THIS_IS_A_LIST)) {
                        array.add(objMap.get(key));
                    }
                }
                return array;
            } else {
                return recreateJsonLists(objMap);
            }
        }
        return obj;
    }

    @Getter
    private static class EvalCallback implements EvaluationListener {

        private final JSONObject jsonObj;

        public EvalCallback(JSONObject jsonObj) {
            this.jsonObj = jsonObj;
        }
        @Override
        @SuppressWarnings("unchecked")
        public EvaluationContinuation resultFound(FoundResult foundResult) {
            System.out.println("found path: " + foundResult.path());
            System.out.println("found result: " + foundResult.result());
            System.out.println("found index: " + foundResult.index());
            if(foundResult.path().startsWith("$")) {
                String path = foundResult.path();
                String path1 = path.replaceAll("\\$", "").replaceAll("'", "");
                StringTokenizer tokenizer = new StringTokenizer(path1,"[]",false);
                Map map = jsonObj;
                while (tokenizer.hasMoreTokens()) {
                    String key = tokenizer.nextToken();
                    System.out.println("key: " + key);
                    if(tokenizer.hasMoreTokens()) {
                        Map subMap = new HashMap<>();
                        map.putIfAbsent(key, subMap);
                        if(isNumeric(key)) {
                            map.put(MARKER_THIS_IS_A_LIST, true);
                        }
                        map = (Map)map.get(key);
                    } else {
                        map.put(key, foundResult.result());
                    }
                }
            }
            System.out.println("jsonObj: " + jsonObj.toJSONString());
            return EvaluationContinuation.CONTINUE;
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

}
