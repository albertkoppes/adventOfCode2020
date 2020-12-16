package adventofcode.year2020.day5;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day5.AdventOfCode2020Day5;

class AdventOfCode2020Day5Test
{
    @Test
    void testCalcRow()
    {
        assertEquals(44, AdventOfCode2020Day5.calcRow("FBFBBFFRLR"));
        assertEquals(70,AdventOfCode2020Day5.calcRow("BFFFBBFRRR"));
        assertEquals(14,AdventOfCode2020Day5.calcRow("FFFBBBFRRR"));
        assertEquals(102,AdventOfCode2020Day5.calcRow("BBFFBBFRLL"));
    }

    @Test
    void testCalcSeatID()
    {
        assertEquals(357, AdventOfCode2020Day5.calcSeatID("FBFBBFFRLR"));
        assertEquals(567,AdventOfCode2020Day5.calcSeatID("BFFFBBFRRR"));
        assertEquals(119,AdventOfCode2020Day5.calcSeatID("FFFBBBFRRR"));
        assertEquals(820,AdventOfCode2020Day5.calcSeatID("BBFFBBFRLL"));
    }

    private void calcRow(char[] input, int expected)
    {
        AdventOfCode2020Day5.Range range = new AdventOfCode2020Day5.Range(0,127);
        for (int i=0; i< 7; i++)
        {
            range = range.getNewRange(input[i]);
        }
        assertEquals(expected, range.lower);
        assertEquals(expected, range.upper);
    }

}