package adventofcode.year2020.day6;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day6.AdventOfCode2020Day6;

class AdventOfCode2020Day6Test
{
    @Test
    void testSumOfCountsTestFile()
    {
        assertEquals(11, AdventOfCode2020Day6.getSumOfCounts("inputDay6Test.txt", false));
        assertEquals(6, AdventOfCode2020Day6.getSumOfCounts("inputDay6Test.txt", true));
    }

    @Test
    void testSumOfCountsRealData()
    {
        assertEquals(6273, AdventOfCode2020Day6.getSumOfCounts("inputDay6.txt", false));
        assertEquals(3254, AdventOfCode2020Day6.getSumOfCounts("inputDay6.txt", true));
    }

}