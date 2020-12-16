package adventofcode.year2020.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day1
{
    public static final String INPUT_DAY_1_TXT = "inputDay1.txt";

    public static void main(String[] args)
    {
        System.out.println(getReportRepair(INPUT_DAY_1_TXT, 2, 2020));
        System.out.println(getReportRepair(INPUT_DAY_1_TXT, 3, 2020));
    }

    public static long getReportRepair(String fileName, int nrOfInts, int sum)
    {
        List<Integer> input = getInput(fileName);
        List<Integer> tuple = findTupleWithSum(input, nrOfInts, sum);
        return tuple.stream().mapToInt(i -> i).reduce(1, (a, b) -> a * b);
    }

    private static List<Integer> findTupleWithSum(List<Integer> input, int nrOfInts, int sum)
    {
        List<List<Integer>> tuples = findTuples(input, nrOfInts);
        return getTupleWithSum(tuples, sum);
    }

    private static List<Integer> getTupleWithSum(List<List<Integer>> tuples, int sum)
    {
        return tuples.stream().filter(lst->(sumOfItems(lst)==sum)).findFirst().orElseThrow(() -> new IllegalArgumentException("No tuple with sum " + sum));
    }

    private static int sumOfItems(List<Integer> ints)
    {
        return ints.stream().mapToInt(i -> i).sum();
    }

    /**
     * Recursively get the number of tuples of length nofInts
     * For performance take notice of number of tuples for a list of numbers of a certain length:
     * nof tuples of length 2 from a list of 200 = 200*199/(1*2) = 19900
     * nof tuples of length 3 from a list of 200 = 200*199*198/(1*2*3) = 1,313,400
     * nof tuples of length 4 from a list of 200 = 200*199*198*197/(1*2*3*4) = 64,684,950
     * Note:
     *
     * @param input    list of integers
     * @param nofInts  length of tuple to search
     * @return list of list of tuples
     */
    static List<List<Integer>> findTuples(List<Integer> input, int nofInts)
    {
        return findTuples(input, nofInts, -1);
    }

    private static List<List<Integer>> findTuples(List<Integer> input, int nofInts, int excludeUptoIndex)
    {
        List<List<Integer>> theList = new ArrayList<>();
        if (nofInts == 0)
        {
            return theList;
        }
        else
        {
            // already processed all numbers upto excludeUptoIndex, deUptoIndex, start with next
            // don't process last nofInt numbers, they cannot be start of a tuple
            // for each item, add all tuples (of length -1) in the input after this item, with the item as start number
            for (int i = excludeUptoIndex + 1; i < (input.size()-(nofInts-1)); i++)
            {
                List<Integer> newInput = new ArrayList<>(input);
                int newInt = input.get(i);
                newInput.remove(0);
                List<List<Integer>> newList = findTuples(newInput, nofInts - 1, i - 1);
                if (newList.isEmpty()) // no tuples after this item, add a list with just this item
                {
                    List<Integer> subList = new ArrayList<>(List.of(newInt));
                    theList.add(subList);
                }
                else
                {
                    // add all tuples (of length -1) in the input after this item, with the item as start number
                    for (List<Integer> integers : newList)
                    {
                        List<Integer> subList = new ArrayList<>(List.of(newInt));
                        subList.addAll(integers);
                        theList.add(subList);
                    }
                }
            }
        }
        return theList;
    }

    static List<Integer> getInput(String fileName)
    {
        List<String> inputLines = Utils.readLines(fileName);
        return inputLines.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

}
