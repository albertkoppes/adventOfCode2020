package adventofcode.year2020.day10;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day10.AdventOfCode2020Day10;

class AdventOfCode2020Day10Test
{
    @Test
    void testTestInput()
    {
        assertEquals(35, AdventOfCode2020Day10.oneDiffTimesThreeDif("inputDay10Test1.txt"));
        assertEquals(220, AdventOfCode2020Day10.oneDiffTimesThreeDif("inputDay10Test.txt"));
    }

    @Test
    void testNumberOfArrangementsTestFile()
    {
        assertEquals(8, AdventOfCode2020Day10.getNumberOfArrangements("inputDay10Test1.txt"));
        assertEquals(19208, AdventOfCode2020Day10.getNumberOfArrangements("inputDay10Test.txt"));
        assertEquals(31581162962944L, AdventOfCode2020Day10.getNumberOfArrangements("inputDay10.txt"));

    }

}