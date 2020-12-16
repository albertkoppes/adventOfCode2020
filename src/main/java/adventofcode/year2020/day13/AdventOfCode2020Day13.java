package adventofcode.year2020.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day13
{
    public static void main(String[] args)
    {
        ShuttleSearch shuttleSearch = new ShuttleSearch("inputDay13.txt");
        System.out.println(shuttleSearch.calcSmallestWaitTimeTimesBusId());

    }
    static class ShuttleSearch
    {
        int timestamp;
        List<Integer> busIds;

        public ShuttleSearch(String fileName)
        {
            List<String> lines = Utils.readLines(fileName);
            timestamp = Integer.parseInt(lines.get(0));
            busIds = getBusIdsFromLine(lines.get(1));
        }

        private static List<Integer> getBusIdsFromLine(String line)
        {
            List<Integer> busIds = new ArrayList<>();
            try (Scanner scanner = new Scanner(line).useDelimiter(","))
            {
                while (scanner.hasNext())
                {
                    String next = scanner.next();
                    if (!"x".equals(next))
                    {
                        busIds.add(Integer.valueOf(next));
                    }
                }
            }
            return busIds;
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
                    int waitTime = (id * (divider+1))-timestamp;
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
