package adventofcode.year2020.day9;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;

class AdventOfCodeDay9Test
{
    @Test
    void testEncodingErrorTestInput()
    {
        List<Long> lines = Utils.toLongList(Utils.readLines("inputDay9Test.txt"));
        assertEquals(127, AdventOfCodeDay9.calcFirstAnomaly(lines, 5));
    }

    @Test
    void testEncryptionWeaknessTestInput()
    {
        List<Long> lines = Utils.toLongList(Utils.readLines("inputDay9Test.txt"));
        assertEquals(62, AdventOfCodeDay9.calcEncryptionWeakness(lines, 5));
    }

    @Test
    void testRealInput()
    {
        List<Long> lines = Utils.toLongList(Utils.readLines("inputDay9.txt"));
        assertEquals(14360655, AdventOfCodeDay9.calcFirstAnomaly(lines, 25));
        assertEquals(1962331, AdventOfCodeDay9.calcEncryptionWeakness(lines, 25));
    }

}