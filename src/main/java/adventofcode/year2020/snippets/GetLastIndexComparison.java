package adventofcode.year2020.snippets;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GetLastIndexComparison
{
    @Test
    @Disabled("interim test")
    void testPerformanceLastIndexOf()
    {
        getPerformanceLastIndexOf(10000000);
        getPerformanceLastIndexOf(100000000);
        getPerformanceLastIndexOf(200000000);
        getPerformanceLastIndexOf(2147483647);
    }

    private void getPerformanceLastIndexOf(int numberOfInts)
    {
        int[] array = IntStream.iterate(1, x -> x + 1).limit(numberOfInts).toArray();
        Integer[] objectArray = ArrayUtils.toObject(array);
        List<Integer> list = Arrays.asList(objectArray);
        long start1 = System.currentTimeMillis();
        getLastIndexOf(array, 2);
        long start = System.currentTimeMillis();
        getLastIndexOf(objectArray, 2);
        long between = System.currentTimeMillis();
        list.lastIndexOf(2);
        long end = System.currentTimeMillis();
        System.out.println("Performance for list size " + numberOfInts);
        System.out.println("int[] " + (start-start1));
        System.out.println("Integer[] " + ( between-start));
        System.out.println("List<Integer> " + ( end-between));
        assertTrue(start-start1 < between-start);
        assertTrue(between-start < end-between);
    }

    int getLastIndexOf(Integer[] array, int number)
    {
        for (int i = array.length-1; i >=0 ; i--)
        {
            if (array[i] == number)
            {
                return i;
            }

        }
        return -1;
    }


    int getLastIndexOf(int[] array, int number)
    {
        for (int i = array.length-1; i >=0 ; i--)
        {
            if (array[i] == number)
            {
                return i;
            }

        }
        return -1;
    }

}
