package katas.textumbruch.v0;

public class TextFormatter {
    public static String wrapText(String text, int maxLength) {
        if(text ==  null || maxLength <= 0)
            throw new IllegalArgumentException("Invalid input");

        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            while(word.length() > maxLength){
                result.append(word.substring(0, maxLength)).append("\n");
                word = word.substring(maxLength);
            }
            if((line.length() > 0 && (line.length() + word.length() + 1) > maxLength) || word.length() == maxLength){
                result.append(line).append("\n");
                line.setLength(0);
            }
            if(line.length() > 0){
                line.append(" ");
            }
            line.append(word);
        }

        if(line.length() > 0){
            result.append(line).append("\n");
        }

        return result.toString().trim();
    }
    public static void main(String[] args) {
        String text = "The night is blue, the little stars are blinking, snowflakes are quietly sinking down.";
        int maxLength = 14;
        System.out.println(wrapText(text, maxLength));
    }
}