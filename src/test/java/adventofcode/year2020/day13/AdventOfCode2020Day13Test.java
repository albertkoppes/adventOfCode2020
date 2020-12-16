package adventofcode.year2020.day13;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day13.AdventOfCode2020Day13;

class AdventOfCode2020Day13Test
{
    @Test
    void testReadBusIds()
    {
        AdventOfCode2020Day13.ShuttleSearch shuttleSearch = new AdventOfCode2020Day13.ShuttleSearch("inputDay13Test.txt");
        assertEquals(939, shuttleSearch.timestamp);
        assertEquals(5, shuttleSearch.busIds.size());
    }

    @Test
    void testSmallestWaitTimeTimesBusId()
    {
        AdventOfCode2020Day13.ShuttleSearch shuttleSearch = new AdventOfCode2020Day13.ShuttleSearch("inputDay13Test.txt");
        assertEquals(295, shuttleSearch.calcSmallestWaitTimeTimesBusId());
        shuttleSearch = new AdventOfCode2020Day13.ShuttleSearch("inputDay13.txt");
        assertEquals(4722, shuttleSearch.calcSmallestWaitTimeTimesBusId());
    }

}