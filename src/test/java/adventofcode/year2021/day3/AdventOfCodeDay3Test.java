package adventofcode.year2021.day3;

import static org.junit.jupiter.api.Assertions.*;

import static adventofcode.year2021.day3.AdventOfCodeDay3.INPUT_DAY_3A_TXT;
import static adventofcode.year2021.day3.AdventOfCodeDay3.INPUT_DAY_3_TXT;
import static adventofcode.year2021.day3.AdventOfCodeDay3.getBinaryCodesFromLines;
import static adventofcode.year2021.day3.AdventOfCodeDay3.getCO2ScrubberRating;
import static adventofcode.year2021.day3.AdventOfCodeDay3.getLifeSupportRating;
import static adventofcode.year2021.day3.AdventOfCodeDay3.getMostCommonBits;
import static adventofcode.year2021.day3.AdventOfCodeDay3.getMirroredBinArray;
import static adventofcode.year2021.day3.AdventOfCodeDay3.getOxygenGeneratorString;
import static adventofcode.year2021.day3.AdventOfCodeDay3.getPowerConsumption;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;
import adventofcode.year2021.Constants;

class AdventOfCodeDay3Test
{

    @Test
    void testGetBinaryCodesFromLines()
    {
        List<String> lines = Utils.readLines(Constants.BASE_DIR,INPUT_DAY_3_TXT);
        int[][] binaryCodes = getBinaryCodesFromLines(lines);
        assertEquals(12, binaryCodes.length);
        assertEquals(5, binaryCodes[0].length);
        assertArrayEquals(new int[]{0,0,1,0,0}, binaryCodes[0]);
        int[] mostCommonBits = getMostCommonBits(binaryCodes);
        assertArrayEquals(new int[]{1,0,1,1,0}, mostCommonBits);
        assertArrayEquals(new int[]{1,0,1,0,1}, getMirroredBinArray(new int[]{0,1,0,1,0}));
        assertEquals(198, getPowerConsumption(binaryCodes));
    }

    @Test
    void testPart2()
    {
        List<String> lines = Utils.readLines(Constants.BASE_DIR,INPUT_DAY_3_TXT);
        int[][] binaryCodes = getBinaryCodesFromLines(lines);
        assertArrayEquals(new int[]{1,0,1,1,1}, getOxygenGeneratorString(binaryCodes));
        binaryCodes = getBinaryCodesFromLines(lines);
        assertArrayEquals(new int[]{0,1,0,1,0}, getCO2ScrubberRating(binaryCodes));
        assertEquals(230, getLifeSupportRating(binaryCodes));
        lines = Utils.readLines(Constants.BASE_DIR,INPUT_DAY_3A_TXT);
        binaryCodes = getBinaryCodesFromLines(lines);
        assertEquals(2498354, getPowerConsumption(binaryCodes));
        assertEquals(3277956, getLifeSupportRating(binaryCodes));
    }
}