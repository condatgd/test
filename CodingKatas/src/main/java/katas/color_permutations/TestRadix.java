package katas.color_permutations;

public class TestRadix {
    public static void main(String[] args) {
        int farben = 3;
        int stellen = 3;
        for(int i=0; i<Math.pow(farben, stellen); i++) {
            String zahl = Integer.toString(i, farben);
            System.out.println(zahl);
        }
    }
}
