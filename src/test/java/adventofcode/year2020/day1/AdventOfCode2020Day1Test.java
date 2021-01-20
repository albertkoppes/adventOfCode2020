package adventofcode.year2020.day1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static adventofcode.year2020.day1.AdventOfCode2020Day1.getReportRepair;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day1.AdventOfCode2020Day1;
import adventofcode.year2020.utils.Utils;

class AdventOfCode2020Day1Test
{
    public static final String TEST_INPUT_FILE = "inputDay1test.txt";

    @Test
    void testExample() throws IOException
    {
        assertEquals(514579, getReportRepair(TEST_INPUT_FILE, 2, 2020));
        assertEquals(241861950, getReportRepair(TEST_INPUT_FILE, 3, 2020));
    }

    @Test
    void testRealInput() throws IOException
    {
        assertEquals(692916, getReportRepair(AdventOfCode2020Day1.INPUT_DAY_1_TXT, 2, 2020));
        assertEquals(289270976, getReportRepair(AdventOfCode2020Day1.INPUT_DAY_1_TXT, 3, 2020));
    }

}