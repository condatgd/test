package de.berlin.gd;

public class CountUp {

    public static void main(String[] args) {
        String number = "0000";
        for(int i=0;i<100;i++) {
            number = addOne(number, 10);
            System.out.println(number);
        }
    }

    private static String addOne(String number, int numberSystem) {
        String result = "";
        for(int i=number.length()-1; i>=0; i--) {
            String digit = number.substring(i, i + 1);
            int digitInt = Integer.parseInt(digit);
            digitInt++;
            if(digitInt>=numberSystem)
                digitInt = 0;
            result = digitInt + result;
            if(digitInt!=0)
                break;
        }
        return number.substring(0,number.length()-result.length()) + result;
    }

}
