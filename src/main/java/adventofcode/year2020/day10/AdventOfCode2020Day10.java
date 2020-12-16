package adventofcode.year2020.day10;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day10
{
    public static void main(String[] args)
    {
        System.out.println("one diff times three diff " + oneDiffTimesThreeDif("inputDay10.txt"));
        System.out.println("number of arrangements " + getNumberOfArrangements("inputDay10.txt"));
    }

    public static int oneDiffTimesThreeDif(String filename)
    {
        List<Integer> sortedList = Utils.toIntegerList(Utils.readLines(filename));
        sortedList.sort(Comparator.comparingInt(a -> a));
        return oneDiffTimesThreeDif(sortedList);
    }

    public static long getNumberOfArrangements(String filename)
    {
        resetMap();
        List<Integer> sortedList = Utils.toIntegerList(Utils.readLines(filename));
        sortedList.sort(Comparator.comparingInt(a -> a));
        sortedList.add(0, 0);
        sortedList.add(sortedList.get(sortedList.size() - 1) + 3);
        return getNumberOfArrangements(sortedList);
    }

    private static int oneDiffTimesThreeDif(List<Integer> sortedList)
    {
        Map<Integer, Integer> diffs = new HashMap<>();
        sortedList.add(0, 0);
        sortedList.add(sortedList.get(sortedList.size() - 1) + 3);
        for (int i = 0; i < sortedList.size() - 1; i++)
        {
            int dif = sortedList.get(i + 1) - sortedList.get(i);
            Integer nofOccurrences = diffs.get(dif);
            if (nofOccurrences == null)
            {
                nofOccurrences = 0;
            }
            diffs.put(dif, ++nofOccurrences);
        }

        return diffs.get(1) * diffs.get(3);
    }

    static void resetMap()
    {
        arrangementsPerListLength = new HashMap<>();
        arrangementsPerListLength.put(1, (long)1);
        arrangementsPerListLength.put(2, (long)1);
    }

    static Map<Integer, Long> arrangementsPerListLength = new HashMap<>();

    static long getNumberOfArrangements(List<Integer> sortedList)
    {
        int size = sortedList.size();
        Long totalNumber = arrangementsPerListLength.get(size);
        if (!allSmallerSizesWereHit(arrangementsPerListLength, size)) // avoid recalculation of already analyzed lists
        {
            totalNumber = 0L;
            if (sortedList.isEmpty() || sortedList.size() == 1)
            {
                return totalNumber;
            }
            if (sortedList.size() == 2 && ((sortedList.get(1) - sortedList.get(0)) <= 3))
            {
                return 1;
            }
            if (sortedList.size() > 2)
            {
                int first = sortedList.get(0);
                int second = sortedList.get(1);
                if ((second - first) <= 3)
                {
                    totalNumber += getNumberOfArrangements(sortedList.subList(1, sortedList.size()));
                    second = sortedList.get(2);
                    if ((second - first) <= 3)
                    {
                        totalNumber += getNumberOfArrangements(sortedList.subList(2, sortedList.size()));
                        if (sortedList.size() > 3)
                        {
                            second = sortedList.get(3);
                            if ((second - first) <= 3)
                            {
                                totalNumber += getNumberOfArrangements(sortedList.subList(3, sortedList.size()));
                            }
                        }
                    }
                }
            }
            arrangementsPerListLength.put(size, totalNumber);
        }
        return totalNumber;
    }

    private static boolean allSmallerSizesWereHit(Map<Integer, Long> arrangementsPerListLength, int size)
    {
        return IntStream.iterate(size, i -> i > 2, i -> i - 1).noneMatch(i -> arrangementsPerListLength.get(i) == null);
    }

}
