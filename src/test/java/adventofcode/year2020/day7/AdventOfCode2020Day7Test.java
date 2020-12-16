package adventofcode.year2020.day7;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day7.AdventOfCode2020Day7;

class AdventOfCode2020Day7Test
{
    @Test
    void testNumberOfOptionsInTestFile()
    {
        assertEquals(4, AdventOfCode2020Day7.numberOfOptionsInFile("inputDay7Test.txt", new AdventOfCode2020Day7.ColorSpec("shiny", "gold")));
        assertEquals(197, AdventOfCode2020Day7.numberOfOptionsInFile("inputDay7.txt", new AdventOfCode2020Day7.ColorSpec("shiny", "gold")));
    }

    @Test
    void testNumberOfBagsWithinColor()
    {
        assertEquals(32, AdventOfCode2020Day7.numberOfBagsWithinBagWithColor("inputDay7Test.txt", new AdventOfCode2020Day7.ColorSpec("shiny", "gold")));
        assertEquals(126, AdventOfCode2020Day7.numberOfBagsWithinBagWithColor("inputDay7Test2.txt", new AdventOfCode2020Day7.ColorSpec("shiny", "gold")));
        assertEquals(85324, AdventOfCode2020Day7.numberOfBagsWithinBagWithColor("inputDay7.txt", new AdventOfCode2020Day7.ColorSpec("shiny", "gold")));

    }

}