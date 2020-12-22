package adventofcode.year2020.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AdventOfCode2020Day15Test
{
    @Test
    void testResultRecursive()
    {
        List<Integer> list = AdventOfCode2020Day15.convertToIntegerList("day15/inputDay15Test1.txt");
        assertEquals(3, list.size());
        assertEquals(436, AdventOfCode2020Day15.calcNthNumberFromFileRecursive("day15/inputDay15Test1.txt", 2020));
        assertEquals(1, AdventOfCode2020Day15.calcNthNumberFromFileRecursive("day15/inputDay15Test2.txt", 2020));
        assertEquals(10, AdventOfCode2020Day15.calcNthNumberFromFileRecursive("day15/inputDay15Test3.txt", 2020));
        assertEquals(27, AdventOfCode2020Day15.calcNthNumberFromFileRecursive("day15/inputDay15Test4.txt", 2020));
        assertEquals(78, AdventOfCode2020Day15.calcNthNumberFromFileRecursive("day15/inputDay15Test5.txt", 2020));
        assertEquals(438, AdventOfCode2020Day15.calcNthNumberFromFileRecursive("day15/inputDay15Test6.txt", 2020));
        assertEquals(1836, AdventOfCode2020Day15.calcNthNumberFromFileRecursive("day15/inputDay15Test7.txt", 2020));
    }

    @Test
    void testResult()
    {
        List<Integer> list = AdventOfCode2020Day15.convertToIntegerList("day15/inputDay15Test1.txt");
        assertEquals(3, list.size());
        assertEquals(436, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test1.txt", 2020));
        assertEquals(1, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test2.txt", 2020));
        assertEquals(10, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test3.txt", 2020));
        assertEquals(27, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test4.txt", 2020));
        assertEquals(78, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test5.txt", 2020));
        assertEquals(438, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test6.txt", 2020));
        assertEquals(1836, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test7.txt", 2020));
    }

    @Test
    @Disabled("takes too long")
    void testPart2()
    {
        assertEquals(175594, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test1.txt", 30000000));
        assertEquals(2578, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test2.txt", 30000000));
        assertEquals(3544142, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test3.txt", 30000000));
        assertEquals(261214, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test4.txt", 30000000));
        assertEquals(6895259, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test5.txt", 30000000));
        assertEquals(18, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test6.txt", 30000000));
        assertEquals(362, AdventOfCode2020Day15.calcNthNumberFromFile("day15/inputDay15Test7.txt", 30000000));
    }



}