package adventofcode.year2020.day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day3.AdventOfCode2020Day3;

class AdventOfCode2020Day3Test
{
    @Test
    void testCountTrees() throws IOException
    {
        char[][] map = AdventOfCode2020Day3.getMapFromFile("inputDay3.txt");
        assertEquals(289, AdventOfCode2020Day3.countTrees(map, new AdventOfCode2020Day3.Slope(3, 1)));
    }

    @Test
    void testCountTreesTestInput() throws IOException
    {
        char[][] map = AdventOfCode2020Day3.getMapFromFile("inputDay3Test.txt");
        assertEquals(7, AdventOfCode2020Day3.countTrees(map, new AdventOfCode2020Day3.Slope(3, 1)));
    }

    @Test
    void testCountTreesRepeatingMapTestInput() throws IOException
    {
        char[][] map = AdventOfCode2020Day3.getMapFromFile("inputDay3Test1.txt");
        assertEquals(7, AdventOfCode2020Day3.countTrees(map, new AdventOfCode2020Day3.Slope(3, 1)));
    }

    @Test
    void testMultipleTreesForDifferentSlopesTestInput() throws IOException
    {
        assertEquals(336, AdventOfCode2020Day3.multiplyTreesForDifferentSlopes("inputDay3Test1.txt"));
    }
    @Test
    void testMultipleTreesForDifferentSlopes() throws IOException
    {
        assertEquals(5522401584D, AdventOfCode2020Day3.multiplyTreesForDifferentSlopes("inputDay3.txt"));
    }
}