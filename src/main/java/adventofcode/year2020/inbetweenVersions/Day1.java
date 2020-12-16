package adventofcode.year2020.inbetweenVersions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import adventofcode.year2020.utils.Utils;

public class Day1
{

    public static void main(String[] args) throws IOException
    {
        System.out.println(getReportRepair("inputDay1.txt", 2, 2020));
        System.out.println(getReportRepair("inputDay1.txt", 3, 2020));
        System.out.println(getReportRepair2("inputDay1.txt", 2, 2020));
        System.out.println(getReportRepair2("inputDay1.txt", 3, 2020));
    }

    private static long getReportRepair(String fileName, int nrOfInts, int sum) throws IOException
    {
        List<Integer> input = getInput(fileName);
        IntTuple tuple = nrOfInts == 2 ? findTupleWithSum2(input, sum) : findTupleWithSum3(input, sum);
        return tuple.product();
    }

    private static long getReportRepair2(String fileName, int nrOfInts, int sum) throws IOException
    {
        List<Integer> input = getInput(fileName);
        List<Integer> tuple = findTupleWithSum(input, nrOfInts, sum);
        return tuple.stream().mapToInt(i -> i).reduce(1, (a, b) -> a * b);
    }

    private static List<Integer> findTupleWithSum(List<Integer> input, int nrOfInts, int sum)
    {
        List<List<Integer>> tuples = findTuples(input, nrOfInts, -1);
        return getTupleWithSum(tuples, sum);
    }

    private static List<Integer> getTupleWithSum(List<List<Integer>> tuples, int sum)
    {
        for (List<Integer> lst : tuples)
        {
            if (sumOfItems(lst) == sum)
            {
                return lst;
            }
        }
        throw new IllegalArgumentException("no result");
    }

    private static int sumOfItems(List<Integer> ints)
    {
        return ints.stream().mapToInt(i -> i).sum();
    }

    private static IntTuple findTupleWithSum3(List<Integer> input, int sum)
    {

        for (int i = 0; i < input.size() - 2; i++)
        {
            for (int j = i + 1; j < input.size() - 1; j++)
            {
                for (int k = j + 1; k < input.size(); k++)
                {
                    int first = input.get(i);
                    int second = input.get(j);
                    int third = input.get(k);
                    if ((first + second + third) == sum)
                    {
                        return new IntTuple(first, second, third);
                    }
                }
            }
        }
        throw new IllegalArgumentException(String.format("Input %s has no pair with sum %d", input, sum));
    }

    private static IntTuple findTupleWithSum2(List<Integer> input, int sum)
    {
        for (int i = 0; i < input.size() - 2; i++)
        {
            for (int j = i + 1; j < input.size() - 1; j++)
            {
                int first = input.get(i);
                int second = input.get(j);
                if ((first + second) == sum)
                {
                    return new IntTuple(first, second);
                }
            }
        }
        throw new IllegalArgumentException(String.format("Input %s has no pair with sum %d", input, sum));
    }

    private static List<List<Integer>> findTuples(List<Integer> input, int nofInts)
    {
        List<List<Integer>> tuples = findTuples(input, nofInts, -1);
        return tuples.stream().filter(item -> (item.size() == nofInts)).collect(Collectors.toList());
    }

    private static List<List<Integer>> findTuples(List<Integer> input, int nofInts, int exclude)
    {
        List<List<Integer>> theList = new ArrayList<>();
        if (nofInts == 0)
        {
            return theList;
        }
        else
        {
            for (int i = 0; i < input.size(); i++)
            {
                if (i > exclude)
                {
                    int newInt = input.get(i);
                    List<Integer> newInput = new ArrayList<>(input);
                    newInput.remove(0);
                    List<List<Integer>> newList = findTuples(newInput, nofInts - 1, i - 1);
                    if (newList.isEmpty())
                    {
                        List<Integer> subList = new ArrayList<>(List.of(newInt));
                        theList.add(subList);
                    }
                    else
                    {
                        for (int j = 0; j < newList.size(); j++)
                        {
                            List<Integer> subList = new ArrayList<>(List.of(newInt));
                            subList.addAll(newList.get(j));
                            theList.add(subList);
                        }
                    }
                }
            }
        }
        return theList;
    }

    private static List<List<Integer>> removeListsWithDuplicates(List<List<Integer>> theList)
    {
        List<List<Integer>> filtered = new ArrayList<>();
        for (List<Integer> subList : theList)
        {
            if (subList.size() == (Set.copyOf(subList)).size())
            {
                filtered.add(subList);
            }
        }
        return filtered;
    }

    private static List<Integer> getInput(String fileName) throws IOException
    {
        List<String> inputLines = Utils.readLines(fileName);
        return inputLines.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    private static class IntTuple
    {
        List<Integer> tupleList;

        public IntTuple(int... ints)
        {
            tupleList = IntStream.of(ints).boxed().collect(Collectors.toList());
        }

        public long product()
        {
            return tupleList.stream().reduce(1, (a, b) -> a * b);
        }
    }

//    @Test
//    void testExample() throws IOException
//    {
//        Assertions.assertEquals(514579, getReportRepair("inputDay1test.txt", 2, 2020));
//        Assertions.assertEquals(241861950, getReportRepair("inputDay1test.txt", 3, 2020));
//    }
//
//    @Test
//    void testExample2() throws IOException
//    {
//        Assertions.assertEquals(514579, getReportRepair2("inputDay1test.txt", 2, 2020));
//        Assertions.assertEquals(241861950, getReportRepair2("inputDay1test.txt", 3, 2020));
//    }
//
//    @Test
//    void testGetTuples() throws IOException
//    {
//        List<Integer> input = getInput("inputDay1test.txt");
//        List<List<Integer>> tuples = findTuples(input, 2);
//    }
//
}
