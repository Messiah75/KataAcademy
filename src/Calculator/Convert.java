package Calculator;

import java.util.Arrays;

 class Convert {
    static String[] arr1 = {"I", "V", "X", "L", "C"};
    static int[] arr2 = {1, 5, 10, 50, 100};

    public static int fromRoman(String exp) {

        int result = 0;

        String[] romanArray = exp.split("");
        for (int i = 0; i < romanArray.length; i++) {
            int currentNumber = arr2[Arrays.binarySearch(arr1, romanArray[i])];
            if (i + 1 != romanArray.length) {
                int nextNumber = arr2[Arrays.binarySearch(arr1, romanArray[i + 1])];
                if (currentNumber < nextNumber) {
                    result -= currentNumber;
                    continue;
                }
                result += arr2[Arrays.binarySearch(arr1, romanArray[i])];
            } else {
                result += arr2[Arrays.binarySearch(arr1, romanArray[i])];
            }
        }

        return result;
    }

    public static String toRoman(int intNumber){

        String result = "";
        int units = intNumber % 10;
        int tens = intNumber / 10;

        switch (tens) {
            case 0:
                break;
            case 1:
                result += "X";
                break;
            case 2:
                result += "XX";
                break;
            case 3:
                result += "XXX";
                break;
            case 4:
                result += "XL";
                break;
            case 5:
                result += "L";
                break;
            case 6:
                result += "LX";
                break;
            case 7:
                result += "LXX";
                break;
            case 8:
                result += "LXXX";
                break;
            case 9:
                result += "XC";
                break;
            case 10:
                result += "C";
                break;
        }

        switch (units) {
            case 1:
                result += "I";
                break;
            case 2:
                result += "II";
                break;
            case 3:
                result += "III";
                break;
            case 4:
                result += "IV";
                break;
            case 5:
                result += "V";
                break;
            case 6:
                result += "VI";
                break;
            case 7:
                result += "VII";
                break;
            case 8:
                result += "VIII";
                break;
            case 9:
                result += "IX";
                break;
        }

        return result;
    }

    static boolean isRoman(String arg) {
        int contains = Arrays.binarySearch(arr1, arg);
        boolean inArray = contains == -1 ? false : true;
        return inArray;
    }
}
