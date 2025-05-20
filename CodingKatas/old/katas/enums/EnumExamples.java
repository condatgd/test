package katas.enums;

import lombok.ToString;

import java.util.List;

public class EnumExamples {

    // Einfache Aufzählung
    enum SingleState { NONE, LOVE, FIFTEEN, THIRTY, FOURTY, DEUCE, ADV1, ADV2, WIN1, WIN2}


    // Verwendung löst Konstruktion genau einmal aus:
    public enum OnlyOne {
        ONCE(true),
        ANOTHER(true);
        private OnlyOne(boolean b) {
            System.out.print("constructing: " + this + ", ");
        }
    }

    public static void main0(String[] args) {
        System.out.print("begin,");
        OnlyOne firstCall = OnlyOne.ONCE;
        OnlyOne secondCall = OnlyOne.ONCE;
        System.out.print("end");
    }

    // Beispiel Automat
    // mit Konstruktor
    @ToString
    enum StateTransition {
        LOVE("FIFTEEN", "FIFTEEN"),  // Warum Strings?
        FIFTEEN("THIRTY", "THIRTY"),
        THIRTY("FOURTY", "FOURTY"),
        FOURTY("WIN1", "WIN2"),
        DEUCE("ADV1", "ADV2"),
        ADV1("WIN1", "DEUCE"),
        ADV2("DEUCE", "WIN2"),
        WIN1("", ""),
        WIN2("", ""),
        NONE("", "");

        private final List<String> next;

        StateTransition(String next1, String next2) {
            this.next = List.of(next1, next2);
        }

        public StateTransition getNext(int playerIndex) {
            if(!next.get(playerIndex).isEmpty())
                return StateTransition.valueOf(next.get(playerIndex));   // Lösung: über valueOf zur Laufzeit auflösen
            else
                return this;
        }
    }

    public static void main1(String[] args) {
        for(var stateTransition: StateTransition.values()) {
            System.out.println(stateTransition.name() + " " + stateTransition.toString() + " " + stateTransition.ordinal());
        }
    }

    public static void main2(String[] args) {
        StateTransition next0 = StateTransition.DEUCE.getNext(0);
        StateTransition next1 = StateTransition.DEUCE.getNext(1);
        System.out.println("next0: " + next0.toString());
        System.out.println("next1: " + next1.toString());
    }

    // Methoden in Enums
    public enum Season {
        WINTER {
            public String getHours() { return "10am-3pm"; }
            public String getMore() { return "10am-3pm"; }
        },
        SPRING {
            public String getHours() { return "9am-5pm"; }
        },
        SUMMER {
            public String getHours() { return "9am-7pm"; }
        },
        FALL {
            public String getHours() { return "9am-4pm"; }
        };
        public abstract String getHours();

        // OTHER;
        // public String getHours() { return "9am-15pm"; }   // default für alle, die die Methode nicht haben.
    }

    public static void main3(String[] args) {
        System.out.println(Season.FALL.getHours());

        Season summer = Season.SUMMER;
        switch(summer) {
            case WINTER: // compiliert nicht: Season.WINTER:
                System.out.print("Get out the sled!");
                break;
            case SUMMER:
                System.out.print("Time for the pool!");
                break;
            default:
                System.out.print("Is it summer yet?");
        }
    }


    // Interfaces sind möglich
    public interface Weather { int getAverageTemperature(); }
    public enum Season1 implements Weather {
        WINTER, SPRING, SUMMER,
        FALL { public int getAverageTemperature() { return 18; } };
        public int getAverageTemperature() { return 30; }
    }

    public static void main4(String[] args) {
        Weather aWeather = Season1.FALL;
        System.out.println(aWeather.getAverageTemperature());
    }
}
