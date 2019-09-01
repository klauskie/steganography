import java.util.*;

public class Base {

    public final int x_index = 0;

    public String binaryStringToChar(String bin) {
        String result = "";
        for(int i = 0; i < bin.length(); i+=8) {
            int parseInt = Integer.parseInt(bin.substring(i, i + 8), 2);
            result += (char)parseInt;
        }
        return result;
    }

    public String getTwoByIndex(String str, int index) {
        if (str.length() - index > 2) {
            return str.length() < 2 ? str : str.substring(index, index + 2);
        } else {
            return str.length() < 2 ? str : str.substring(str.length() - 2);
        }

    }

    public String getBinaryText(String str) {
        StringBuilder result = new StringBuilder();
        char[] messChar = str.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            result.append('0');
            result.append(Integer.toBinaryString(messChar[i]));
        }

        //System.out.println(result);
        return result.toString();
    }

    public int alterPixel(int color, String two) {
        String color_bin = Integer.toBinaryString(color);
        int size = color_bin.length();

        if (size > 2) {
            char[] colorModed = color_bin.toCharArray();
            colorModed[size-1] = two.charAt(1);
            colorModed[size-2] = two.charAt(0);
            color_bin = String.valueOf(colorModed);
        }

        return Integer.parseInt(color_bin, 2);
    }
}
