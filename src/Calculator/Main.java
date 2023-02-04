package Calculator;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BothNotArabicOrRomanException, WrongInputNumbersException, MoreOrLessOperandException, IncorrectOperandException, NegativeRomanNumberException {
        Scanner input = new Scanner(System.in);
        System.out.print("������� ���������, ��� ������ ����� ������ ���� �� ������ 10: ");
        String expression = input.nextLine().toUpperCase();
        calc(expression);
        input.close();
    }

    public static String calc (String input) throws BothNotArabicOrRomanException, WrongInputNumbersException, MoreOrLessOperandException, IncorrectOperandException, NegativeRomanNumberException {
        Convert convert = new Convert();
        //�������������� ������ ��� ���������� ����� �������� � ���������.
        String[] operator = {"+", "-", "/", "*"};
        //�������������� ������ �������������� �������� ��� ��������� ������ �� �������� � ��������.
        String[] operatorRegex = {"\\+", "-", "/", "\\*"};
        //������� ������� �� ������ ��� � ����������� ������.
        input = input.replaceAll("\\s", "");
        //���������� � ������������� ������� ��� ��������� � ���������.
        String[] exp = new String[2];
        int index = 0;
        //������� ����������.
        int count = 0;

        //������� ���������� ������ � ������.
        for (int i = 0; i < operator.length; i++) {
            String[] tmpArray = input.split("");
            for (int j = 0; j < input.length(); j++) {
                if (tmpArray[j].equals(operator[i])){
                    count++;
                }
            }
        }

        //��������� ���������� � �������� � ��������� ���������.
        if (count != 1) {
            throw new MoreOrLessOperandException("���� ������� ����� ������ ����� �������� ��� ���� ��� ��������.");
        }

        //�������� �� ���������� ����������� ����� � ���������.
        for (int i = 0; i < operator.length; i++) {
            if (input.contains(operator[i])) {
                exp = input.split(operatorRegex[i]);
                index = i;
                break;
            } else if (i + 1 == operator.length){
                throw new IncorrectOperandException("����� ������������ ���� ��������.");
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
                throw new WrongInputNumbersException("����� ������ ���� �� ������ 10!");
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
                    System.out.printf("�����: %s", convert.toRoman(result));
                    return convert.toRoman(result);
                } else {
                    throw new NegativeRomanNumberException("����������� ������ ������������ � �������� ������� ����� ���� ������ ������������� �����");
                }

            } else {
                System.out.printf("�����: %s", Integer.toString(result));
                return Integer.toString(result);
            }


        } else {
            throw new BothNotArabicOrRomanException("������������ ���������. ����� ������ ���� ������������ ���� ��������, ���� ���������!");
        }
    }
}
