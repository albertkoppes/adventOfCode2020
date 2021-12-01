package adventofcode.year2020.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import adventofcode.utils.Utils;

public class AdventOfCode2020Day14
{
    private AdventOfCode2020Day14()
    {
        // no op
    }
    static class DockingData
    {
        String mask = "";
        List<MemoryUpdate> memoryUpdates = new ArrayList<>();
        Map<Long, Character[]> localMemory = new HashMap<>();
        void performUpdateAndCalcProduct(Map<Long, Long> memory)
        {
            for (MemoryUpdate memoryUpdate : memoryUpdates)
            {
                long pointer = memoryUpdate.pointer;
                this.localMemory.put(pointer, applyMask(mask, memoryUpdate.value));
            }
            for (Map.Entry<Long, Character[]> entry: this.localMemory.entrySet())
            {
                long val = bitCharArrayToLong(entry.getValue());
                memory.put(entry.getKey(), val);
            }
        }

        void performUpdateAndCalcProductPart2(Map<Long, Long> memory)
        {
            for (MemoryUpdate memoryUpdate : memoryUpdates)
            {
                long pointer = memoryUpdate.pointer;
                long value = memoryUpdate.value;
                List<Long> addresses = applyMaskOnMemoryAddress(mask, pointer);
                for (Long address : addresses)
                {
                    memory.put(address, value);
                }
            }
        }

        /**
         * converts a char array with bit values (0 or 1) to long value
         * example 000000000000000000000000000000001011 = 11
         * @param chs character array of bits
         * @return long value of bit array
         */
        public static long bitCharArrayToLong(Character[] chs)
        {
            long val = 0;
            Collections.reverse(Arrays.asList(chs));
            for (int i = 0; i < chs.length; i++)
            {
                if ((chs[i]) == '1')
                {
                    val +=  Math.round(Math.pow(2, i));
                }
            }
            return val;
        }

        public static long bitCharArrayToInt(char[] array)
        {
            long val = 0;
            Character[] characterArray = ArrayUtils.toObject(array);
            Collections.reverse(Arrays.asList(characterArray));
            for (int i = 0; i < characterArray.length; i++)
            {
                if ((characterArray[i]).equals('1'))
                {
                    val +=  Math.round(Math.pow(2, i));
                }
            }
            return val;
        }

        /**
         * Applies a mask to a long value
         * @param mask mask
         * @param value  value
         * @return character array
         */
        public static Character[] applyMask(String mask, long value)
        {
            String withLeadingZeros = getStringRepresentationWithLeadingZeros(mask, value);
            char[] chars = withLeadingZeros.toCharArray();
            char[] maskChars = mask.toCharArray();
            assert(chars.length == maskChars.length);
            for (int i = 0; i < chars.length; i++)
            {
                char localMask = maskChars[i];
                switch (localMask)
                {
                    case '0':
                    case '1':
                        chars[i] = localMask;
                        break;
                    default:
                        break;
                }
            }
            return ArrayUtils.toObject(chars);
        }

        private static String getStringRepresentationWithLeadingZeros(String mask, long value)
        {
            String binaryString = Long.toBinaryString(value);
            return String.format("%"+ mask.length()+"s", binaryString).replace(' ', '0');
        }

    }

    public static List<Long>  applyMaskOnMemoryAddress(String mask, long address)
    {
        String binaryString = Long.toBinaryString(address);
        String withLeadingZeros = String.format("%"+mask.length()+"s", binaryString).replace(' ', '0');
        char[] chars = withLeadingZeros.toCharArray();
        char[] maskChars = mask.toCharArray();
        assert(chars.length == maskChars.length);
        for (int i = 0; i < chars.length; i++)
        {
            char localMask = maskChars[i];
            if (localMask == '1' || localMask == 'X')
            {
                chars[i] = localMask;
            }
        }
        String addressWithFloatingBits = String.copyValueOf(chars);
        List<String> listOfAddresses = getAllListOfAddresses(addressWithFloatingBits);
        return listOfAddresses.stream().map(String::toCharArray).map(DockingData::bitCharArrayToInt).collect(Collectors.toList());
    }

    private static List<String> getAllListOfAddresses(String addressWithFloatingBits)
    {
        List<String> listOfAddresses = new ArrayList<>();
        long firstX = addressWithFloatingBits.indexOf("X");
        if (firstX == -1)
        {
            return List.of(addressWithFloatingBits);
        }
        else
        {
            String address1 = addressWithFloatingBits.replaceFirst("X", "0");
            listOfAddresses.addAll(getAllListOfAddresses(address1));
            String address2 = addressWithFloatingBits.replaceFirst("X", "1");
            listOfAddresses.addAll(getAllListOfAddresses(address2));
        }
        return listOfAddresses;
    }


    static class MemoryUpdate
    {
        long pointer;
        long value;
    }

    static List<DockingData> getDockingDataFromFile(String filename)
    {
        List<String> lines = Utils.readLines(filename);
        List<DockingData> dockingDataList = new ArrayList<>();
        DockingData dockingData = new DockingData();
        for (String line : lines)
        {
            if (isMask(line))
            {
                if (!dockingData.mask.isBlank())
                {
                    dockingDataList.add(dockingData);
                }
                dockingData = new DockingData();
                dockingData.mask = getMask(line);
            }
            else if (isMem(line))
            {
                dockingData.memoryUpdates.add(getMemoryUpdate(line));
            }
        }
        dockingDataList.add(dockingData);
        return dockingDataList;
    }

    private static MemoryUpdate getMemoryUpdate(String line)
    {
        MemoryUpdate memoryUpdate = new MemoryUpdate();
        try (Scanner scanner = new Scanner(line))
        {
            String next = scanner.next();
            assert (next.startsWith("mem"));
            memoryUpdate.pointer = Long.parseLong(next.substring(4, next.length() - 1));
            next = scanner.next();
            assert ("=".equals(next));
            memoryUpdate.value = scanner.nextLong();
        }
        return memoryUpdate;
    }

    private static boolean isMem(String line)
    {
        return line.startsWith("mem");
    }

    private static String getMask(String line)
    {
        try (Scanner scanner = new Scanner(line))
        {
            String next = scanner.next();
            assert ("mask".equals(next));
            next = scanner.next();
            assert ("=".equals(next));
            return scanner.next();
        }
    }

    private static boolean isMask(String line)
    {
        return line.startsWith("mask");
    }

    static long performUpdates(String filename)
    {
        List<DockingData> dockingDataList = getDockingDataFromFile(filename);
        Map<Long, Long> memory = new HashMap<>();
        for (DockingData dockingData : dockingDataList)
        {
            dockingData.performUpdateAndCalcProduct(memory);
        }
        return memory.values().stream().reduce((long)0, Long::sum);
    }
    static long performUpdatesPart2(String filename)
    {
        List<DockingData> dockingDataList = getDockingDataFromFile(filename);
        Map<Long, Long> memory = new HashMap<>();
        for (DockingData dockingData : dockingDataList)
        {
            dockingData.performUpdateAndCalcProductPart2(memory);
        }
        return memory.values().stream().reduce((long)0, Long::sum);
    }
}
