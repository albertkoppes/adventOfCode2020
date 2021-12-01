package adventofcode.year2020.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import adventofcode.utils.Utils;

public class AdventOfCode2020Day15
{
    public static void main(String[] args)
    {
        System.out.println(AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15.txt", 2020)); // 1009
        System.out.println(AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15.txt", 30000000)); // 62714
    }

    static Integer calcNthNumberSpokenRecursive(List<Integer> input, int restCount)
    {
        if (restCount == 0)
        {
            return input.get(input.size()-1);
        }
        int lastNumberSpoken = input.get(input.size()-1);
        List<Integer> inputMinLast = new ArrayList<>(input);
        inputMinLast.remove(input.size()-1);
        final int lastIndexOf = inputMinLast.lastIndexOf(lastNumberSpoken);
        int nextNumber = 0;
        if (lastIndexOf != -1)
        {
            nextNumber = input.size() - lastIndexOf - 1;
        }
        input.add(nextNumber);
        return calcNthNumberSpokenRecursive(input, restCount-1);
    }

    static Integer calcNthNumberSpoken(List<Integer> input, int restCount)
    {
        Map<Integer, Integer> previousIndex = new HashMap<>();
        Map<Integer, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < input.size(); i++)
        {
            lastIndex.put(input.get(i), i + 1);
        }
        while (restCount > 0)
        {
            int lastNumberSpoken = input.get(input.size() - 1);
            input.remove(input.size() - 1);
            int nextNumber = 0;
            Integer previousIdx = previousIndex.get(lastNumberSpoken);
            if (previousIdx != null)
            {
                final int previousIndexOf = previousIdx;
                nextNumber = input.size() - previousIndexOf + 1;
            }
            input.add(lastNumberSpoken);
            input.add(nextNumber);
            previousIndex.put(nextNumber, lastIndex.get(nextNumber));
            lastIndex.put(nextNumber, input.size());
            restCount--;
        }
        return input.get(input.size() - 1);
    }

    static List<Integer> convertToIntegerList(String fileName)
    {
        List<Integer> list = new ArrayList<>();
        String line = Utils.readString(fileName);
        try (Scanner scanner = new Scanner(line).useDelimiter(","))
        {
            while (scanner.hasNext())
            {
                list.add(scanner.nextInt());
            }
        }
        return list;

    }

    static int calcNthNumberFromFileRecursive(String fileName, int restCount)
    {
        List<Integer> list = convertToIntegerList(fileName);
        return calcNthNumberSpokenRecursive(list, restCount-list.size());
    }
    static int calcNthNumberFromFile(String fileName, int restCount)
    {
        List<Integer> list = convertToIntegerList(fileName);
        return calcNthNumberSpoken(list, restCount-list.size());
    }

}
