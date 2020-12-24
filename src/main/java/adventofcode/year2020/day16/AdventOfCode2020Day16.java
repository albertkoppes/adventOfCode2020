package adventofcode.year2020.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day16
{
    public static void main(String[] args)
    {
        AdventOfCode2020Day16.Notes notes = AdventOfCode2020Day16.readNotes("day16/inputDay16.txt");
        System.out.println(notes.getTotalOfDepartureValues());
    }

    static class RangesDefinition
    {
        String name;
        List<Range> ranges = new ArrayList<>();

        public boolean inRange(Integer value)
        {
            return ranges.stream().anyMatch(r->value>=r.min&&value<=r.max);
        }

        @Override
        public String toString()
        {
            return name;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof RangesDefinition))
                return false;
            RangesDefinition that = (RangesDefinition)o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(name);
        }
    }

    static class Range
    {
        int min;
        int max;

        public Range(int min, int max)
        {
            this.min = min;
            this.max = max;
        }
    }

    static class Ticket
    {
        List<Integer> values = new ArrayList<>();

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Ticket))
                return false;
            Ticket ticket = (Ticket)o;
            return values.equals(ticket.values);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(values);
        }
    }

    static class Notes
    {
        List<RangesDefinition> rangesDefinitions = new ArrayList<>();
        Ticket myTicket = new Ticket();
        List<Ticket> nearbyTickets = new ArrayList<>();

        List<Integer> getInvalidNumbers(Ticket ticket)
        {
            List<Integer> invalidNumbers = new ArrayList<>();
            for (Integer value : ticket.values)
            {
                if (!isValidValueForAnyRange(value))
                {
                    invalidNumbers.add(value);
                }
            }
            return invalidNumbers;
        }

        private boolean isValidValueForAnyRange(Integer value)
        {
            for (RangesDefinition rangesDefinition : rangesDefinitions)
            {
                if (rangesDefinition.inRange(value))
                {
                    return true;
                }
            }
            return false;
        }

        List<Integer> getAllInvalidNumbers()
        {
            List<Integer> invalidNumbers = new ArrayList<>();
            for (Ticket nearbyTicket : nearbyTickets)
            {
                invalidNumbers.addAll(getInvalidNumbers(nearbyTicket));
            }
            return invalidNumbers;
        }

        void removeInvalidTickets()
        {
            List<Ticket> copy = new ArrayList<>(nearbyTickets);
            for (Ticket ticket : copy)
            {
                if (!getInvalidNumbers(ticket).isEmpty())
                {
                    nearbyTickets.remove(ticket);
                }
            }
        }

        List<List<Integer>> transposed = transposeNearbyTickets();

        List<List<Integer>> transposeNearbyTickets()
        {
            List<List<Integer>> outerList = new ArrayList<>();

            for (Ticket nearbyTicket : nearbyTickets)
            {
                for (int i = 0; i < nearbyTicket.values.size(); i++)
                {
                    if (outerList.size()<i+1)
                    {
                        List<Integer> innerList = new ArrayList<>();
                        outerList.add(innerList);
                    }
                    outerList.get(i).add(nearbyTicket.values.get(i));
                }
            }
            return outerList;
        }

        Map<RangesDefinition, Integer> mapRangesDefinitionToIndex()
        {
            Map<RangesDefinition, List<Integer>> map = new HashMap<>();
            Map<RangesDefinition, Integer> mapRangesDefinitionToIndex = new HashMap<>();
            removeInvalidTickets();
            List<RangesDefinition> rangeDefs = new ArrayList<>(rangesDefinitions);
            while (mapRangesDefinitionToIndex.size()< transposed.size())
            {
                for (int i = 0; i < transposed.size(); i++)
                {
                    if (mapRangesDefinitionToIndex.containsValue(i))
                    {
                        continue;
                    }
                    List<RangesDefinition> rangeDefinitions = getMatchingRangeDefinitions(transposed.get(i), rangeDefs);
                    for (RangesDefinition rangesDefinition : rangeDefinitions)
                    {
                        List<Integer> thisList = map.get(rangesDefinition);
                        if (thisList == null)
                        {
                            thisList = new ArrayList<>();
                        }
                        thisList.add(i);
                        map.put(rangesDefinition, thisList);
                    }
                }
                filterSingleMatchesAndBuildMap(map, mapRangesDefinitionToIndex);
            }
            return mapRangesDefinitionToIndex;
        }

        private void filterSingleMatchesAndBuildMap(
            Map<RangesDefinition, List<Integer>> map,
            Map<RangesDefinition, Integer> mapRangesDefinitionToIndex)
        {
            Map<RangesDefinition, List<Integer>>localMap = new HashMap<>(map);
            for (Map.Entry<RangesDefinition, List<Integer>> entry :localMap.entrySet())
            {
                if (entry.getValue().size() == 1)
                {
                    mapRangesDefinitionToIndex.put(entry.getKey(), entry.getValue().get(0));
                }
                map.remove(entry.getKey());
            }
        }

        private List<RangesDefinition> getMatchingRangeDefinitions(List<Integer> integers, List<RangesDefinition> rangeDefs)
        {
            List<RangesDefinition> matchingRangeDefs = new ArrayList<>();
            for (RangesDefinition rangesDefinition : rangeDefs)
            {
                if (integers.stream().allMatch(rangesDefinition::inRange))
                {
                    matchingRangeDefs.add(rangesDefinition);
                }
            }
            return matchingRangeDefs;
        }

        long getTotalOfDepartureValues()
        {
            long total = 1;
            removeInvalidTickets();
            transposed = transposeNearbyTickets();
            Map<RangesDefinition, Integer> map = mapRangesDefinitionToIndex();
            for (RangesDefinition def : rangesDefinitions)
            {
                if (def.name.startsWith("departure"))
                {
                    total*=myTicket.values.get(map.get(def));
                }
            }
            return total;
        }



        int getTicketScanningRate()
        {
            List<Integer> invalidNumbers = getAllInvalidNumbers();
            return invalidNumbers.stream().mapToInt(x->x).sum();
        }
    }

    static Notes readNotes(String fileName)
    {
        List<String> lines = Utils.readLines(fileName);
        Notes notes = new Notes();
        int index = 0;
        String line = lines.get(index);
        while (!line.isBlank())
        {
            RangesDefinition def = new RangesDefinition();

            try (Scanner scanner = new Scanner(line).useDelimiter(": "))
            {
                def.name = scanner.next();
                String ranges = scanner.next();
                int min1 = Integer.parseInt(ranges.substring(0, ranges.indexOf("-")));
                int max1 = Integer.parseInt(ranges.substring(ranges.indexOf("-")+1, ranges.indexOf("or")-1));
                def.ranges.add(new Range(min1, max1));
                int min2 = Integer.parseInt(ranges.substring( ranges.indexOf("or")+3, ranges.indexOf("-", ranges.indexOf(" or"))));
                int max2 = Integer.parseInt(ranges.substring(ranges.indexOf("-",ranges.indexOf("or"))+1));
                def.ranges.add(new Range(min2, max2));
                notes.rangesDefinitions.add(def);
            }
            line = lines.get(++index);
        }
        index++;
        line = lines.get(index++);
        assert(line.equals("your ticket:"));
        notes.myTicket.values = getTicketValuesFromLine(lines.get(index++));
        line = lines.get(index++);
        assert(line.isBlank());
        line = lines.get(index++);
        assert(line.equals("nearby tickets:"));
        for (int i = index; i < lines.size(); i++)
        {
            Ticket ticket = new Ticket();
            ticket.values =getTicketValuesFromLine(lines.get(i));
            notes.nearbyTickets.add(ticket);
        }
        return notes;
    }

    private static List<Integer> getTicketValuesFromLine(String line)
    {
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(line).useDelimiter(","))
        {
            while (scanner.hasNext())
            {
                list.add(scanner.nextInt());
            }
        }
        return list;
    }
}
