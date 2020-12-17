package adventofcode.year2020.day13;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
        assertEquals(5, shuttleSearch.delays.size());
        assertEquals(List.of(7, 13, 59, 31, 19), shuttleSearch.busIds);
        assertEquals(List.of(0, 1, 4, 6, 7), shuttleSearch.delays);
    }

    @Test
    void testSmallestWaitTimeTimesBusId()
    {
        AdventOfCode2020Day13.ShuttleSearch shuttleSearch = new AdventOfCode2020Day13.ShuttleSearch("inputDay13Test.txt");
        assertEquals(295, shuttleSearch.calcSmallestWaitTimeTimesBusId());
        shuttleSearch = new AdventOfCode2020Day13.ShuttleSearch("inputDay13.txt");
        assertEquals(4722, shuttleSearch.calcSmallestWaitTimeTimesBusId());
       assertEquals(1068781, shuttleSearch.calcEarliestTimeStamp());
    }
    @Test
    void testSmallestTimeStamp()
    {
        AdventOfCode2020Day13.ShuttleSearch shuttleSearch = new AdventOfCode2020Day13.ShuttleSearch("inputDay13Test.txt");
        assertEquals(1068781, shuttleSearch.calcEarliestTimeStamp());
        shuttleSearch = AdventOfCode2020Day13.ShuttleSearch.getShuttleSearchFromLine("17,x,13,19");
        assertEquals(3417, shuttleSearch.calcEarliestTimeStamp());
        shuttleSearch = AdventOfCode2020Day13.ShuttleSearch.getShuttleSearchFromLine("67,7,59,61");
        assertEquals(754018, shuttleSearch.calcEarliestTimeStamp());
        shuttleSearch = AdventOfCode2020Day13.ShuttleSearch.getShuttleSearchFromLine("67,x,7,59,61");
        assertEquals(779210, shuttleSearch.calcEarliestTimeStamp());
        shuttleSearch = AdventOfCode2020Day13.ShuttleSearch.getShuttleSearchFromLine("67,7,x,59,61");
        assertEquals(1261476, shuttleSearch.calcEarliestTimeStamp());
        shuttleSearch = AdventOfCode2020Day13.ShuttleSearch.getShuttleSearchFromLine("1789,37,47,1889");
        assertEquals(1202161486, shuttleSearch.calcEarliestTimeStamp());
    }

}