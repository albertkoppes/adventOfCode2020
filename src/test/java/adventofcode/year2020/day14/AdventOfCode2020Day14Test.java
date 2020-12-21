package adventofcode.year2020.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdventOfCode2020Day14Test
{
    @Test
    void testReadInput()
    {
        assertEquals(165, AdventOfCode2020Day14.performUpdates("day14/inputDay14Test.txt"));
        assertEquals(7817357407588D, AdventOfCode2020Day14.performUpdates("day14/inputDay14.txt"));
    }
    @Test
    void testReadInputPart2()
    {
        assertEquals(208, AdventOfCode2020Day14.performUpdatesPart2("day14/inputDay14Test2.txt"));
        assertEquals(4335927555692D, AdventOfCode2020Day14.performUpdatesPart2("day14/inputDay14.txt"));
    }

}