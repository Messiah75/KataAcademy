package Calculator;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BothNotArabicOrRomanException, WrongInputNumbersException, MoreOrLessOperandException, IncorrectOperandException, NegativeRomanNumberException {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите выражение, где каждое число должно быть не больше 10: ");
        String expression = input.nextLine().toUpperCase();
        calc(expression);
        input.close();
    }

    public static String calc (String input) throws BothNotArabicOrRomanException, WrongInputNumbersException, MoreOrLessOperandException, IncorrectOperandException, NegativeRomanNumberException {
        Convert convert = new Convert();
        //Инициализируем массив для нахождения знака операции в выражении.
        String[] operator = {"+", "-", "/", "*"};
        //Инициализируем массив арифметических операций для разбиения строки на операнды и оператор.
        String[] operatorRegex = {"\\+", "-", "/", "\\*"};
        //Убираем пробелы из строки для её дальнейшего сплита.
        input = input.replaceAll("\\s", "");
        //Объявление и инициализация массива для операндов и оператора.
        String[] exp = new String[2];
        int index = 0;
        //Счетчик операторов.
        int count = 0;

        //Подсчет количества знаков в строке.
        for (int i = 0; i < operator.length; i++) {
            String[] tmpArray = input.split("");
            for (int j = 0; j < input.length(); j++) {
                if (tmpArray[j].equals(operator[i])){
                    count++;
                }
            }
        }

        //Обработка требования к операнду и введённому выражению.
        if (count != 1) {
            throw new MoreOrLessOperandException("Было введено более одного знака операции или знак был пропущен.");
        }

        //Проверка на содержание корректного знака в выражении.
        for (int i = 0; i < operator.length; i++) {
            if (input.contains(operator[i])) {
                exp = input.split(operatorRegex[i]);
                index = i;
                break;
            } else if (i + 1 == operator.length){
                throw new IncorrectOperandException("Введён некорректный знак операции.");
            }
        }


        if (convert.isRoman(exp[0]) == convert.isRoman(exp[1])) {
            int operandOne;
            int operandTwo;
            int result = 0;

            boolean operandOneIsRoman = convert.isRoman(exp[0]);
            boolean operandTwoIsRoman = convert.isRoman(exp[1]);
            boolean operandsIsRoman = operandOneIsRoman && operandTwoIsRoman;

            if (operandsIsRoman) {
                operandOne = convert.fromRoman(exp[0]);
                operandTwo = convert.fromRoman(exp[1]);
            } else {
                operandOne = Integer.parseInt(exp[0]);
                operandTwo = Integer.parseInt(exp[1]);
            }

            if (operandOne > 10 || operandTwo > 10) {
                throw new WrongInputNumbersException("Числа должны быть не больше 10!");
            }

            switch (operator[index]) {
                case "+":
                    result = operandOne + operandTwo;
                    break;
                case "-":
                    result = operandOne - operandTwo;
                    break;
                case "/":
                    result = operandOne / operandTwo;
                    break;
                case "*":
                    result = operandOne * operandTwo;
                    break;
            }

            if(operandsIsRoman) {
                if (result > 0) {
                    System.out.printf("Ответ: %s", convert.toRoman(result));
                    return convert.toRoman(result);
                } else {
                    throw new NegativeRomanNumberException("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
                }

            } else {
                System.out.printf("Ответ: %s", Integer.toString(result));
                return Integer.toString(result);
            }


        } else {
            throw new BothNotArabicOrRomanException("Некорректное выражение. Числа должны быть одновременно либо римскими, либо арабскими!");
        }
    }
}
