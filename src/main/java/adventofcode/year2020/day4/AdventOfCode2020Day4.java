package adventofcode.year2020.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import adventofcode.utils.Utils;

public class AdventOfCode2020Day4
{

    public static final int NOF_YEAR_DIGITS = 4;

    public static void main(String[] args)
    {
        System.out.println(numberOfValidPassportEntries("inputDay4.txt"));
        //        AdventOfCode2020Day4.numberOfValidPassportEntries("inputDay4Test.txt"));
    }

    static final String sep = ":";

    enum PassportField
    {
        byr("Birth Year", true)
            {
                @Override
                boolean isValid(String value)
                {
                    return isValidYear(value, 1920, 2002);
                }
            },
        iyr("Issue Year", true)
            {
                @Override
                boolean isValid(String value)
                {
                    return isValidYear(value, 2010, 2020);
                }
            },
        eyr("Expiration Year", true)
            {
                @Override
                boolean isValid(String value)
                {
                    return isValidYear(value, 2020, 2030);
                }
            },
        hgt("Height", true)
            {
                @Override
                boolean isValid(String value)
                {
                    return isValidHeight(value);
                }
            },
        hcl("Hair Color", true)
            {
                @Override
                boolean isValid(String value)
                {
                    return isValidHairColor(value);
                }
            },
        ecl("Eye Color", true)
            {
                @Override
                boolean isValid(String value)
                {
                    return isValidEyeColor(value);
                }
            },
        pid("Passport ID", true)
            {
                @Override
                boolean isValid(String value)
                {
                    return isValidPasswordId(value);
                }
            },
        cid("Country ID", false)
            {
                @Override
                boolean isValid(String value)
                {
                    return true;
                }
            };

        static boolean isValidPasswordId(String value)
        {
            return getNumberStr(value).length() == 9;
        }

        static boolean isValidEyeColor(String value)
        {
            return List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value);
        }

        String label;
        boolean required;

        PassportField(String label, boolean required)
        {
            this.label = label;
            this.required = required;
        }

        abstract boolean isValid(String value);
    }

    protected static boolean isValidHairColor(String value)
    {
        if (!value.startsWith("#"))
        {
            return false;
        }
        String subString = value.substring(1);
        if (subString.length() != 6)
        {
            return false;
        }
        if (subString.replaceAll("[^a-f0123456789]", "").length() == 6)
        {
            return true;
        }
        return false;
    }

    private static boolean isValidHeight(String value)
    {
        String numberStr = getNumberStr(value);
        int number = Integer.parseInt(numberStr);
        String str = value.replaceAll("\\d", "");
        if (str.equals("in"))
        {
            return number >= 59 && number <= 76;
        }
        else if (str.equals("cm"))
        {
            return number >= 150 && number <= 193;
        }
        return false;
    }

    private static String getNumberStr(String value)
    {
        return value.replaceAll("[^\\.0123456789]", "");
    }

    private static boolean isValidYear(String value, int minimum, int maximum)
    {
        if (!isValidYearLength(value))
            return false;
        int year = Integer.valueOf(value);
        return year >= minimum && year <= maximum;
    }

    private static boolean isValidYearLength(String value)
    {
        return ((value.length() == NOF_YEAR_DIGITS));
    }

    static class PassPort
    {
        Map<String, String> values = new HashMap<>();

        public PassPort(String passportLine)
        {
            Scanner scanner = new Scanner(passportLine);
            while (scanner.hasNext())
            {
                String word = scanner.next();
                int indexSep = word.indexOf(sep);
                String key = word.substring(0, indexSep);
                String value = word.substring(indexSep + 1);
                values.put(key, value);
            }
        }

        boolean isValid()
        {
            for (PassportField field : PassportField.values())
            {
                if (!(values.containsKey(field.name()) && field.isValid(values.get(field.name())) || !field.required))
                {
                    return false;
                }
            }
            return true;
        }
    }

    static List<String> readPassPortsLinesFromFile(String fileName)
    {
        List<String> passPortLines = new ArrayList<>();
        List<String> lines = Utils.readLines(fileName);
        StringBuilder buf = new StringBuilder();
        for (String line : lines)
        {
            String sep = " ";
            if (!line.isBlank())
            {
                buf.append(line).append(sep);
            }
            else
            {
                passPortLines.add(buf.toString());
                buf = new StringBuilder();
            }
        }
        if (buf.length() > 0)
        {
            passPortLines.add(buf.toString());
        }
        return passPortLines;
    }

    static long numberOfValidPassportEntries(String filename)
    {
        List<String> lines = readPassPortsLinesFromFile(filename);
        List<PassPort> passPorts = lines.stream().map(PassPort::new).collect(Collectors.toList());
        return passPorts.stream().filter(PassPort::isValid).count();
    }

}
