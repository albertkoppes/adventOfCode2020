package adventofcode.year2020.day7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import adventofcode.utils.Utils;

public class AdventOfCode2020Day7
{
    public static void main(String[] args)
    {
        System.out.println(numberOfOptionsInFile("inputDay7.txt", new ColorSpec("shiny", "gold")));
        System.out.println(numberOfBagsWithinBagWithColor("inputDay7.txt", new ColorSpec("shiny", "gold")));
    }

    public static int numberOfOptionsInFile(String filename, ColorSpec colorSpec)
    {
        List<Rule> rules = readRules(filename);
        return numberOfOptions(rules, colorSpec);

    }
    public static int numberOfOptions(List<Rule> allRules, ColorSpec colorSpec)
    {
        int number = 0;
        for (Rule rule : allRules)
        {
            if (rule.canContain(colorSpec, allRules))
            {
                number++;
            }
        }
        return number;
    }

    static class Rule
    {
        // drab green bags contain 4 dull white bags, 1 posh indigo bag.
        ColorSpec outerColorSpec;
        Map<ColorSpec, Integer> content = new HashMap<>();

        public Rule(String line)
        {
            try (Scanner scanner = new Scanner(line))
            {
                String nuance = scanner.next();
                String color = scanner.next();
                outerColorSpec = new ColorSpec(nuance, color);
                scanner.next(); // bags
                scanner.next(); // contain
                while (scanner.hasNext())
                {
                    String next = scanner.next();
                    if (next.equals("no"))
                    {
                        break;
                    }
                    int times = Integer.parseInt(next);
                    nuance = scanner.next();
                    color = scanner.next();
                    ColorSpec colorSpec = new ColorSpec(nuance, color);
                    content.put(colorSpec, times);
                    scanner.next();
                }
            }
        }

        @Override
        public String toString()
        {
            return "Rule{" + "outerColorSpec=" + outerColorSpec + ", content=" + content + '}';
        }

        public boolean canContain(ColorSpec colorSpec, List<Rule> allRules)
        {
            if (content.isEmpty())
            {
                return false;
            }
            else if (content.containsKey(colorSpec))
            {
                return true;
            }
            else
            {
                for (ColorSpec spec : content.keySet())
                {
                    Optional<Rule> rule = getRuleFromSpec(allRules, spec);
                    if (rule.isPresent() && rule.get().canContain(colorSpec, allRules))
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        private Optional<Rule> getRuleFromSpec(List<Rule> allRules, ColorSpec spec)
        {
            return allRules.stream().filter(rule -> rule.outerColorSpec.equals(spec)).findFirst();
        }
    }

    static class ColorSpec
    {
        String nuance;
        String color;

        public ColorSpec(String nuance, String color)
        {
            this.nuance = nuance;
            this.color = color;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof ColorSpec))
                return false;
            ColorSpec colorSpec = (ColorSpec)o;
            return nuance.equals(colorSpec.nuance) &&
                color.equals(colorSpec.color);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(nuance, color);
        }

        @Override
        public String toString()
        {
            return "{" + nuance + '\'' + color + '}';
        }
    }

    static List<Rule> readRules(String filename)
    {
        List<String> lines = Utils.readLines(filename);
        return  lines.stream().map(Rule::new).collect(Collectors.toList());
    }
    public static long numberOfBagsWithinBagWithColor(String filename, ColorSpec color)
    {
        List<Rule> rules = readRules(filename);
        return numberOfBagsWithinBagWithColor(rules, color);
    }

    public static long numberOfBagsWithinBagWithColor(List<Rule> rules, ColorSpec color)
    {
        Map<ColorSpec, Rule> map = new HashMap<>();
        for (Rule rule:rules)
        {
            map.put(rule.outerColorSpec, rule);
        }
        Rule theRule = map.get(color);
        return  getNumberOfBagsWithinRule(map, theRule);
    }

    private static long getNumberOfBagsWithinRule(Map<ColorSpec, Rule> map, Rule theRule)
    {
        long total = 0;
        final Map<ColorSpec, Integer> content = theRule.content;
        if (content.isEmpty())
        {
            return 0;
        }
        else
        {
            for (Map.Entry<ColorSpec, Integer> entry: content.entrySet())
            {
                int number = entry.getValue();
                Rule newRule = map.get(entry.getKey());
                if (newRule == null)
                {
                    total += number;
                }
                else
                {
                    total += number * ((getNumberOfBagsWithinRule(map, newRule)) +1);
                }

            }
        }
        return total;

    }

}
