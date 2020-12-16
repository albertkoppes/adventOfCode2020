package adventofcode.year2020.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day12.AdventOfCode2020Day12;

class AdventOfCode2020Day12Test
{
    @Test
    void testTestInput()
    {
        assertEquals(25, AdventOfCode2020Day12.getDistanceAfterReplacements("inputDay12Test.txt"));
        assertEquals(1319, AdventOfCode2020Day12.getDistanceAfterReplacements("inputDay12.txt"));
        assertEquals(286, AdventOfCode2020Day12.getDistanceAfterReplacementsPart2("inputDay12Test.txt"));
        assertEquals(62434, AdventOfCode2020Day12.getDistanceAfterReplacementsPart2("inputDay12.txt"));
    }

}