package adventofcode.year2020.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day5
{
    public static void main(String[] args)
    {
        List<String> lines = Utils.readLines("inputDay5.txt");
        int highest = 0;
        List<Integer> totalList = getTheTotalList();
        for (String line : lines)
        {
            int current = calcSeatID(line);
            totalList.remove(Integer.valueOf(current));
            if (current > highest)
            {
                highest = current;
            }
        }
        System.out.println("list: " + totalList.stream().sorted().collect(Collectors.toList()));

    }
    static List<Integer> getTheTotalList()
    {
        List<Integer> list = new ArrayList<>();
        long limit = 1024;
        for (int x = 0; ; x = x + 1)
        {
            if (limit-- == 0)
                break;
            list.add(x);
        }
        return list;
    }

    static class Range
    {
        int lower;
        int upper;

        public Range(int lower, int upper)
        {
            this.lower = lower;
            this.upper = upper;
        }

        Range getNewRange(char specification)
        {
            int newLower = lower;
            int newUpper = upper;
            switch (specification)
            {
                case 'F':
                case 'L':
                    newUpper = (lower+upper)/2;
                    return new Range(newLower, newUpper);
                case 'B':
                case 'R':
                    newLower = 1 + (lower+upper)/2;
                    return new Range(newLower, newUpper);
                default:
                    throw new IllegalArgumentException("Specification not good: " + specification);
            }
        }

        @Override
        public String toString()
        {
            return "Range{" + "lower=" + lower +
                ", upper=" + upper +
                "}";
        }
    }
    static int calcRow(String specification)
    {
        char[] input = specification.toCharArray();
        Range range = new Range(0,127);
        for (int i=0; i< 7; i++)
        {
            range = range.getNewRange(input[i]);
        }
        assert(range.lower == range.upper);
        return range.lower;
    }

    static int calcSeatID(String specification)
    {
        int row = calcRow(specification);
        int column = calcColumn(specification);
        return row * 8 + column;
    }


    static int calcColumn(String specification)
    {
        char[] input = specification.toCharArray();
        Range range = new Range(0,7);
        for (int i=7; i< 10; i++)
        {
            range = range.getNewRange(input[i]);
        }
        assert(range.lower == range.upper);
        return range.lower;
    }
}
