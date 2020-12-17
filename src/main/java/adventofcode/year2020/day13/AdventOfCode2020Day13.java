package adventofcode.year2020.day13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day13
{
    public static void main(String[] args)
    {
        ShuttleSearch shuttleSearch = new ShuttleSearch("inputDay13.txt");
        System.out.println(shuttleSearch.calcSmallestWaitTimeTimesBusId());
        System.out.println(shuttleSearch.calcEarliestTimeStamp()); // 825305207525452 (takes hours)

    }

    static class ShuttleSearch
    {
        int timestamp;
        List<Integer> busIds = new ArrayList<>();
        List<Integer> delays = new ArrayList<>();
        Map<Integer, Integer> delayPerBusId = new HashMap<>();

        public ShuttleSearch()
        {
            super();
        }

        public ShuttleSearch(String fileName)
        {
            List<String> lines = Utils.readLines(fileName);
            timestamp = Integer.parseInt(lines.get(0));
            getBusIdsFromLine(lines.get(1));
        }

        public static ShuttleSearch getShuttleSearchFromLine(String line)
        {
            ShuttleSearch shuttleSearch = new ShuttleSearch();
            shuttleSearch.getBusIdsFromLine(line);
            return shuttleSearch;
        }

        private void getBusIdsFromLine(String line)
        {
            int delay = 0;
            try (Scanner scanner = new Scanner(line).useDelimiter(","))
            {
                while (scanner.hasNext())
                {
                    String next = scanner.next();
                    if (!"x".equals(next))
                    {
                        final Integer busId = Integer.valueOf(next);
                        busIds.add(busId);
                        delays.add(delay);
                        delayPerBusId.put(busId, delay);
                    }
                    delay++;
                }
            }
        }

        long calcEarliestTimeStamp()
        {
            long multiplier = 1;
            int largestBusId = delayPerBusId.keySet().stream().max(Comparator.naturalOrder()).orElseThrow(() -> new IllegalArgumentException("illegal input"));
            long timeStamp = largestBusId*multiplier++ - delayPerBusId.get(largestBusId);
            boolean found = doAllBusIdsHaveCorrectDelay(delayPerBusId, timeStamp);
            while (!found)
            {
                timeStamp = largestBusId*multiplier++ - delayPerBusId.get(largestBusId);
                found = doAllBusIdsHaveCorrectDelay(delayPerBusId, timeStamp);
            }
            return timeStamp;
        }

        private boolean doAllBusIdsHaveCorrectDelay(Map<Integer, Integer> delayPerBusId, long timeStamp)
        {
            for (Map.Entry<Integer,Integer> delPerBusId : delayPerBusId.entrySet())
            {
                if (((timeStamp + delPerBusId.getValue()) % delPerBusId.getKey()) != 0)
                {
                    return false;
                }
            }
            return true;
        }

        int calcSmallestWaitTimeTimesBusId()
        {
            int smallestWaitTime = Integer.MAX_VALUE;
            int busId = 0;
            for (Integer id : busIds)
            {
                int divider = timestamp / id;
                if ((divider * id) == timestamp)
                {
                    return 0;
                }
                else
                {
                    int waitTime = (id * (divider + 1)) - timestamp;
                    if (waitTime < smallestWaitTime)
                    {
                        smallestWaitTime = waitTime;
                        busId = id;
                    }
                }
            }
            return smallestWaitTime * busId;
        }
    }
}
