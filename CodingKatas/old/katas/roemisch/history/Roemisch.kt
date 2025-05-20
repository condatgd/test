package katas.roemisch.history

class Roemisch {


    companion object {
        @JvmStatic
        fun toInt(roemisch: String): Int {
            return when {
                roemisch.startsWith("X")  -> 10 + toInt(roemisch.substring(1))
                roemisch.startsWith("IX") -> 9  + toInt(roemisch.substring(2))
                roemisch.startsWith("V")  -> 5  + toInt(roemisch.substring(1))
                roemisch.startsWith("IV") -> 4  + toInt(roemisch.substring(2))
                roemisch.startsWith("I")  -> 1  + toInt(roemisch.substring(1))
                else -> 0
            }
        }

        @JvmStatic
        fun toRoman(num: Int): String {
            return when {
                num >= 10 -> "X"  + toRoman(num - 10)
                num ==  9 -> "IX" + toRoman(0)
                num >=  5 -> "V"  + toRoman(num - 5)
                num ==  4 -> "IV" + toRoman(0)
                num >=  1 -> "I"  + toRoman(num - 1)
                else -> ""
            }
        }
    }




}