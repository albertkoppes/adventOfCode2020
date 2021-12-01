package adventofcode.year2020.day9;

import java.util.List;

import adventofcode.utils.Utils;

public class AdventOfCodeDay9
{
    public static void main(String[] args)
    {
        List<Long> numbers = Utils.toLongList(Utils.readLines("inputDay9.txt"));
        System.out.println(calcFirstAnomaly(numbers, 25));
        System.out.println(calcEncryptionWeakness(numbers, 25));
    }

    static long calcFirstAnomaly(List<Long> numberList, int preambleLength)
    {
        for (int i = preambleLength; i < numberList.size(); i++)
        {
            Long currentNumber = numberList.get(i);
            List<Long> subList = numberList.subList(Integer.max(0, i-preambleLength), i);
            if (!findSumOf2DifferentNumbers(subList, currentNumber))
            {
                return currentNumber;
            }

        }
        return -1;
    }
    private static boolean findSumOf2DifferentNumbers(List<Long> intList, long number)
    {
        for (int i = 0; i < intList.size()-1; i++)
        {
            for (int j = 1; j < intList.size(); j++)
            {
                long num1 = intList.get(i);
                long num2 = intList.get(j);
                if (i == j || num1 == num2)
                {
                    continue;
                }
                if (number == (num1 + num2))
                {
                    return true;
                }
            }
        }
        return false;
    }

    static long calcEncryptionWeakness(List<Long> numberList, int preambleLength)
    {
        for (int i = preambleLength; i < numberList.size(); i++)
        {
            Long currentNumber = numberList.get(i);
            List<Long> subList = numberList.subList(Integer.max(0, i-preambleLength), i);
            if (!findSumOf2DifferentNumbers(subList, currentNumber))
            {
                return findEncryptionWeakness(numberList.subList(0, i), currentNumber);
            }

        }
        return -1;
    }

    static long findEncryptionWeakness(List<Long> numberList, long invalidNumber)
    {
        for (int i = 0; i < numberList.size(); i++)
        {
            long subSum = numberList.get(i);
            for (int j = i+1; j < numberList.size(); j++)
            {
                subSum += numberList.get(j);
                if (subSum == invalidNumber)
                {
                    return encryptionWeakness(numberList.subList(i, j+1));
                }
                else if (subSum > invalidNumber)
                {
                    break;
                }
            }
        }
        return -1;
    }

    private static long encryptionWeakness(List<Long> intList)
    {
        long min = intList.stream().mapToLong(a->a).min().orElseThrow(IllegalArgumentException::new);
        long max = intList.stream().mapToLong(a->a).max().orElseThrow(IllegalArgumentException::new);
        return max + min;
    }

}
