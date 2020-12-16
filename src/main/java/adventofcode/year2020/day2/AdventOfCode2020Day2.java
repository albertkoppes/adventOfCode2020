package adventofcode.year2020.day2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day2
{
    public static void main(String[] args)
    {
        List<String> lines = AdventOfCode2020Day2.readLines("inputDay2.txt");
        System.out.println("number of valid records: " + nofValidRecordsPart1(lines));
        System.out.println("number of valid records: " + nofValidRecordsPart2(lines));
    }

    static class PasswordRecordPart
    {
        int firstInt;
        int secondInt;
        char character;
        String password;
        Map<Character, Integer> occurrences = new HashMap<>();

        public PasswordRecordPart(String line)
        {
            // scan line, example
            // "1-3 a: abcde"
            try (Scanner scanner = new Scanner(line))
            {
                String one = scanner.next(); // "1-3"
                firstInt = Integer.parseInt(one.substring(0, one.indexOf("-")));
                secondInt = Integer.parseInt(one.substring(one.indexOf("-") + 1));
                String two = scanner.next(); // "a:"
                character = two.charAt(0);
                password = scanner.next();  // "abcde"
                mapCharOccurrences();
            }
        }

        private void mapCharOccurrences()
        {
            for (char ch : password.toCharArray())
            {
                Integer occ = occurrences.get(ch);
                if (occ == null)
                {
                    occ = 0;
                }
                occurrences.put(ch, ++occ);
            }
        }

        boolean isValidPart1()
        {
            Integer occ = occurrences.get(character);
            return occ != null && occ >= firstInt && occ <= secondInt;
        }

        boolean isValidPart2()
        {
            char[] charArray = password.toCharArray();
            return charArray[firstInt - 1] == character ^ charArray[secondInt - 1] == character;
        }

    }

    static List<String> readLines(String fileName)
    {
        return Utils.readLines(fileName);
    }

    static int nofValidRecordsPart1(List<String> lines)
    {
        List<PasswordRecordPart> records = lines.stream().map(PasswordRecordPart::new).collect(Collectors.toList());
        return (int)records.stream().filter(PasswordRecordPart::isValidPart1).count();

    }

    static int nofValidRecordsPart2(List<String> lines)
    {
        List<PasswordRecordPart> records = lines.stream().map(PasswordRecordPart::new).collect(Collectors.toList());
        return (int)records.stream().filter(PasswordRecordPart::isValidPart2).count();

    }

}
