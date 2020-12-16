package adventofcode.year2020.day4;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day4.AdventOfCode2020Day4;

class AdventOfCode2020Day4Test
{
    @Test
    void testReadPassportLines()
    {
        List<String> lines = AdventOfCode2020Day4.readPassPortsLinesFromFile("inputDay4Test.txt");
        assertEquals(4, lines.size());
    }

    @Test
    void testNumberOfValidPassports()
    {
//        assertEquals(1, AdventOfCode2020Day4.numberOfValidPassportEntries("inputDay4Test.txt"));
        assertEquals(4, AdventOfCode2020Day4.numberOfValidPassportEntries("inputDay4ValidPassports.txt"));
        assertEquals(0, AdventOfCode2020Day4.numberOfValidPassportEntries("inputDay4InValidPassports.txt"));
    }


    boolean isValid(String str)
    {
        AdventOfCode2020Day4.PassPort passPort = new AdventOfCode2020Day4.PassPort(str);
        return isValid(str);
    }

}