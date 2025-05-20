package katas.color_permutations;

import java.util.stream.Collectors;

public class ColorPermutations2 {

    enum color { a,b,c,d,e }
    public static void main(String[] args) {
        color[] colorValues = color.values();
        int colors = 5;
        long start = System.currentTimeMillis();
        for(int i=0; i<=Math.pow(colors,10); i++) {
            String str = Integer.toString(i, colors);
            String colorStr = genColorStr(str, colorValues);
            if(i%100000==0) {
                System.out.println(str);
                System.out.println(colorStr);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end-start));
    }

    private static String genColorStr1(String str, color[] colorValues) {
        return str.chars().boxed().map(c -> {
            int i = c-48;
            if(i<=9) {
                return colorValues[i].toString();
            } else {
                return colorValues[c-97].toString();
            }
        }).collect(Collectors.joining(","));
    }

    private static String genColorStr(String str, color[] colorValues) {
        StringBuilder s = new StringBuilder();
        for(int i=0; i< str.length(); i++) {
            char c = str.charAt(i);
            int j = c-48;
            if(j<=9) {
                s.append(colorValues[j].toString());
            } else {
                s.append(colorValues[c - 97].toString());
            }
        }
        return s.toString();
    }


}
