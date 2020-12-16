package adventofcode.year2020.day2;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day2.AdventOfCode2020Day2;

class AdventOfCode2020Day2Test
{
    @Test
    void testPasswordRecordsPart1() throws IOException
    {
        List<String> lines = AdventOfCode2020Day2.readLines("inputDay2test.txt");
        assertEquals(3, lines.size());
        List<AdventOfCode2020Day2.PasswordRecordPart> records = lines.stream().map(AdventOfCode2020Day2.PasswordRecordPart::new).collect(Collectors.toList());
        AdventOfCode2020Day2.PasswordRecordPart record0 = records.get(0);
        assertEquals(1, record0.firstInt);
        assertEquals(3, record0.secondInt);
        assertEquals('a', record0.character);
        assertEquals("abcde", record0.password);
        assertEquals(5, record0.occurrences.keySet().size());
        assertEquals(1, record0.occurrences.get('a'));
        assertEquals(2, AdventOfCode2020Day2.nofValidRecordsPart1(lines));
    }
    @Test
    void testPasswordRecordsPart2() throws IOException
    {
        List<String> lines = AdventOfCode2020Day2.readLines("inputDay2test.txt");
        assertEquals(3, lines.size());
        List<AdventOfCode2020Day2.PasswordRecordPart> records = lines.stream().map(AdventOfCode2020Day2.PasswordRecordPart::new).collect(Collectors.toList());
        AdventOfCode2020Day2.PasswordRecordPart record0 = records.get(0);
        assertEquals(1, record0.firstInt);
        assertEquals(3, record0.secondInt);
        assertEquals('a', record0.character);
        assertEquals("abcde", record0.password);

        assertEquals(1, AdventOfCode2020Day2.nofValidRecordsPart2(lines));
    }

}