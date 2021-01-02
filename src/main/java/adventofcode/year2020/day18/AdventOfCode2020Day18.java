package adventofcode.year2020.day18;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day18
{
    enum MathematicsPreference
    {
        MULTIPLY(AdventOfCode2020Day18::calcResultPreferMultiply),
        ADDITION(AdventOfCode2020Day18::calcResultPreferAddition),
        NONE(AdventOfCode2020Day18::calcResult);
        private final Function<String, Long> resultCalculation;

        MathematicsPreference(Function<String, Long> resultCalculation)
        {
            this.resultCalculation = resultCalculation;
        }
    }
    static MathematicsPreference mathematicsPreference = MathematicsPreference.NONE;
    public static void main(String[] args)
    {
        System.out.println(getSumOfResults("day18/inputDay18.txt")); //75592527415659
        mathematicsPreference = MathematicsPreference.ADDITION;
        System.out.println(getSumOfResults("day18/inputDay18.txt")); //360029542265462
    }

    public static final String SPACE = " ";
    public static final char OPEN_PARENTHESIS = '(';
    public static final char CLOSE_PARENTHESIS = ')';

    enum Operation
    {
        MULTIPLY("*"),
        ADD("+");

        String oper;
        Operation(String operation)
        {
            this.oper = operation;
        }

        static Operation getOperation(String oper)
        {
            for (Operation o : values())
            {
                if (o.oper.equals(oper))
                {
                    return o;
                }
            }
            throw new IllegalArgumentException("No operation fount for " + oper);
        }
    }
    static long getSumOfResults(String fileName)
    {
        long result = 0;
        List<String> lines = Utils.readLines(fileName);
        for (String line : lines)
        {
            result += getResult(line);
        }
        return result;
    }

    static long getResult(String line)
    {
        return Long.parseLong(replaceSubResult(line));
    }

    private static  String replaceSubResult(String line)
    {
        int indexOfOpenParenthesis = line.indexOf(OPEN_PARENTHESIS);
        if (indexOfOpenParenthesis == -1)
        {
            return String.valueOf(mathematicsPreference.resultCalculation.apply(line));
        }
        else
        {
            int indexOfCloseParenthesis = findIndexOfMatchingClose(line, indexOfOpenParenthesis);
            String subResult = replaceSubResult(line.substring(indexOfOpenParenthesis + 1, indexOfCloseParenthesis));
            String resultString = line.substring(0, indexOfOpenParenthesis) + SPACE + subResult + SPACE + line.substring(indexOfCloseParenthesis+1);
            return replaceSubResult(resultString);
        }
    }


    public static int findIndexOfMatchingClose(String line, int openPos) {
        char[] text = line.toCharArray();
        int closePos = openPos;
        int counter = 1;
        while (counter > 0) {
            char c = text[++closePos];
            if (c == OPEN_PARENTHESIS) {
                counter++;
            }
            else if (c == CLOSE_PARENTHESIS) {
                counter--;
            }
        }
        return closePos;
    }

    private static Long calcResult(String line)
    {
        Long result = null;
        try (Scanner scanner = new Scanner(line))
        {
            if (scanner.hasNext())
            {
                result = scanner.nextLong();
            }
            while (scanner.hasNext())
            {
                String operation = scanner.next();
                Long number = scanner.nextLong();
                result = performOperation(result, operation, number);
            }
        }
        return result;
    }

    private static Long calcResultPreferMultiply(String line)
    {
        List<String> items = new ArrayList<>();
        try (Scanner scanner = new Scanner(line))
        {
            while (scanner.hasNext())
            {
                items.add(scanner.next());
            }
        }
        int multiplyIndex = items.indexOf("*");
        while (multiplyIndex > -1)
        {
            long a = Long.parseLong(items.get(multiplyIndex-1));
            long b = Long.parseLong(items.get(multiplyIndex+1));
            long product = a*b;
            items.add(multiplyIndex-1, String.valueOf(product));
            items.remove(multiplyIndex);
            items.remove(multiplyIndex);
            items.remove(multiplyIndex);
            multiplyIndex = items.indexOf("*");
        }
        while(items.contains("+"))
        {
            items.remove("+");
        }
        return items.stream().mapToLong(Long::parseLong).sum();
    }

    private static Long calcResultPreferAddition(String line)
    {
        List<String> items = new ArrayList<>();
        try (Scanner scanner = new Scanner(line))
        {
            while (scanner.hasNext())
            {
                items.add(scanner.next());
            }
        }
        int addIndex = items.indexOf("+");
        while (addIndex > -1)
        {
            long a = Long.parseLong(items.get(addIndex-1));
            long b = Long.parseLong(items.get(addIndex+1));
            long sum = a+b;
            items.add(addIndex-1, String.valueOf(sum));
            items.remove(addIndex);
            items.remove(addIndex);
            items.remove(addIndex);
            addIndex = items.indexOf("+");
        }
        while(items.contains("*"))
        {
            items.remove("*");
        }
        long product = 1;
        for (String item : items)
        {
            product *= Long.parseLong(item);
        }
        return product;
    }

    private static  Long performOperation(Long result, String operation, Long number)
    {
        Operation oper = Operation.getOperation(operation);
        if (oper == Operation.MULTIPLY)
        {
            return result * number;
        }
        else if (oper == Operation.ADD)
        {
            return result + number;
        }
        throw new IllegalArgumentException("Unknown operation " + oper);
    }

}
