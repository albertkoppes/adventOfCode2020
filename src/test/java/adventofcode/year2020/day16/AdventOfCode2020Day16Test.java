package adventofcode.year2020.day16;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AdventOfCode2020Day16Test
{
    @Test
    void testTicketTranslationPart1()
    {
        AdventOfCode2020Day16.Notes notes = AdventOfCode2020Day16.readNotes("day16/inputDay16Test.txt");
        assertEquals(71, notes.getTicketScanningRate());
        notes = AdventOfCode2020Day16.readNotes("day16/inputDay16.txt");
        assertEquals(22073, notes.getTicketScanningRate());
    }

    @Test
    void testTicketTranslationPart2()
    {
        AdventOfCode2020Day16.Notes notes = AdventOfCode2020Day16.readNotes("day16/inputDay16.txt");
        assertEquals(1346570764607D, notes.getTotalOfDepartureValues());
    }

}