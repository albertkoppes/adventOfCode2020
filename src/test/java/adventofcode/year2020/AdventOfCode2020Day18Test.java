package adventofcode.year2020;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AdventOfCode2020Day18Test
{

    @Test
    void testResultsNoOperationPreference()
    {
        AdventOfCode2020Day18.mathemathicPreference = AdventOfCode2020Day18.MathemathicPreference.NONE;
        assertEquals(26, AdventOfCode2020Day18.getResult("2 * 3 + (4 * 5)"));
        assertEquals(437, AdventOfCode2020Day18.getResult("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
        assertEquals(12240, AdventOfCode2020Day18.getResult("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
        assertEquals(13632, AdventOfCode2020Day18.getResult("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
        assertEquals(75592527415659D, AdventOfCode2020Day18.getSumOfResults("day18/inputday18.txt"));
    }
    @Test
    void testResultsPriorityAddition()
    {
        AdventOfCode2020Day18.mathemathicPreference = AdventOfCode2020Day18.MathemathicPreference.ADDITION;
        assertEquals(46, AdventOfCode2020Day18.getResult("2 * 3 + (4 * 5)"));
        assertEquals(1445, AdventOfCode2020Day18.getResult("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
        assertEquals(669060, AdventOfCode2020Day18.getResult("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
        assertEquals(23340, AdventOfCode2020Day18.getResult("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
        assertEquals(360029542265462D, AdventOfCode2020Day18.getSumOfResults("day18/inputday18.txt"));
    }
}