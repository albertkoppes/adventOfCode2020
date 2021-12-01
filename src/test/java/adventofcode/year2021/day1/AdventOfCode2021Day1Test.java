package adventofcode.year2021.day1;

import static org.junit.jupiter.api.Assertions.*;

import static adventofcode.year2021.day1.AdventOfCode2021Day1.INPUT_DAY_1A_TXT;
import static adventofcode.year2021.day1.AdventOfCode2021Day1.INPUT_DAY_1_TXT;
import static adventofcode.year2021.day1.AdventOfCode2021Day1.getNrOfIncreases;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;
import adventofcode.year2021.Constants;

class AdventOfCode2021Day1Test
{
    @Test
    void testGetNumberOfIncreases()
    {

        List<String> lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_1_TXT);
        List<Integer> integers = Utils.toIntegerList(lines);
        assertEquals(7, getNrOfIncreases(integers));
        lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_1A_TXT);
        integers = Utils.toIntegerList(lines);
        assertEquals(1564, getNrOfIncreases(integers));
    }

    @Test
    void testGetNumberOfIncreasesWith3Window()
    {
        List<String> lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_1_TXT);
        List<Integer> integers = Utils.toIntegerList(lines);
        assertEquals(5, getNrOfIncreases(integers,3));
        lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_1A_TXT);
        integers = Utils.toIntegerList(lines);
        assertEquals(1611, getNrOfIncreases(integers,3));
    }

}