package adventofcode.year2020.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day19
{
    public static void main(String[] args)
    {
        System.out.println(AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19.txt"));//263
        System.out.println(AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19_2.txt"));//118
        System.out.println(AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19Part2Test.txt"));//3
        System.out.println(AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19Part2Test2.txt"));//13
        System.out.println(AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19Part2Test3.txt"));//13
        AdventOfCode2020Day19.printMatchingMessages("day19/inputDay19Part2Test.txt");
        System.out.println("------------------");
        AdventOfCode2020Day19.printMatchingMessages("day19/inputDay19Part2Test2.txt");
        System.out.println("------------------");
        AdventOfCode2020Day19.printMatchingMessages("day19/inputDay19Part2Test3.txt");
    }

    static class Rule
    {
        Character character = null;
        List<Reference> beforeOr = new ArrayList<>();
        List<Reference> afterOr = new ArrayList<>();

        public Rule(Rule originalRule)
        {
            super();
            this.character = originalRule.character;
            for (Reference reference : originalRule.beforeOr)
            {
                Reference ref = new Reference(reference);
                this.beforeOr.add(ref);
            }
            for (Reference reference : originalRule.afterOr)
            {
                Reference ref = new Reference(reference);
                this.afterOr.add(ref);
            }
        }

        public Rule()
        {
            super();
        }

        public boolean hasUncheckedReferences()
        {
            boolean beforeChecked = beforeOr.stream().allMatch(r -> r.isChecked);
            if (afterOr.isEmpty())
            {
                return !beforeChecked;
            }
            else
            {
                boolean afterChecked = afterOr.stream().allMatch(r -> r.isChecked);
                return !(!beforeChecked && !afterChecked);
            }
        }

        public void unCheckAllReferences()
        {
            for (Reference reference : beforeOr)
            {
                reference.setChecked(false);
            }
            for (Reference reference : afterOr)
            {
                reference.setChecked(false);
            }
        }
    }

    static class Reference
    {
        int pointer;
        boolean isChecked = false;

        public Reference(int pointer)
        {
            this.pointer = pointer;
        }

        public Reference(Reference reference)
        {
            this.pointer = reference.pointer;
            this.isChecked = reference.isChecked;
        }

        void setChecked(boolean checked)
        {
            isChecked = checked;
        }
    }

    static void readInput(String fileName, Map<Integer, Rule> rules, List<String> messages)
    {
        List<String> lines = Utils.readLines(fileName);
        int i = 0;
        String line = lines.get(i++);
        while (!line.isBlank())
        {
            Rule rule = new Rule();
            int no;
            String next;
            try (Scanner scanner = new Scanner(line))
            {
                String number = scanner.next();
                no = Integer.parseInt(number.substring(0, number.indexOf(":")));
                next = scanner.next();
            }
            if (next.startsWith("\""))
            {
                rule.character = next.charAt(1);
            }
            else
            {
                rule = getRuleFor(line.substring(line.indexOf(":") + 1));
            }
            rules.put(no, rule);
            if (i == lines.size())
            {
                break;
            }
            line = lines.get(i++);
        }
        if (i < (lines.size()))
        {
            while (i < lines.size())
            {
                line = lines.get(i++);
                if (!line.isBlank())
                {
                    messages.add(line);
                }
            }
        }

    }

    private static Rule getRuleFor(String substring)
    {
        Rule rule = new Rule();
        boolean beforeOr = true;
        try (Scanner scanner = new Scanner(substring))
        {
            while (scanner.hasNext())
            {
                String next = scanner.next();
                if (next.equals("|"))
                {
                    beforeOr = false;
                }
                else
                {
                    int number = Integer.parseInt(next);
                    if (beforeOr)
                    {
                        rule.beforeOr.add(new Reference(number));
                    }
                    else
                    {
                        rule.afterOr.add(new Reference(number));
                    }
                }
            }
        }
        return rule;

    }

    static boolean matchRules(Map<Integer, Rule> rules, String message)
    {
        Map<Integer, Rule> localRules = new HashMap<>();
        for (Map.Entry<Integer, Rule> entry : rules.entrySet())
        {
            localRules.put(entry.getKey(), new Rule(rules.get(entry.getKey())));
        }
        final Result result;
        try
        {
            result = getSubResult(localRules, 0, message);
        }
        catch (EndOfMessageException e)
        {
            return !localRules.get(0).hasUncheckedReferences();
        }
        return result.validMessage && result.restMessage.isEmpty();
    }

    static Result getSubResult(Map<Integer, Rule> rules, int i, String message) throws EndOfMessageException
    {
        final Rule rule = rules.get(i);
        Result result = new Result(false, message);
        if (rule.character != null)
        {
            result = checkNextCharacterInMessage(message, rule);
        }
        else
        {
            result.restMessage = message;
            if (rule.afterOr.isEmpty())
            {
                result = checkSubtree(rules, message, result, rule.beforeOr);
            }
            else
            {
                result = checkSubtree(rules, message, result, rule.beforeOr);
                if (!result.validMessage)
                {
                    result = checkSubtree(rules, message, result, rule.afterOr);
                }
            }
            rule.unCheckAllReferences();
        }
        return result;
    }

    private static Result checkSubtree(
        Map<Integer, Rule> rules,
        String message,
        Result result, List<Reference> subNodes) throws EndOfMessageException
    {
        for (Reference reference : subNodes)
        {
            reference.setChecked(true);
            result = getSubResult(rules, reference.pointer, result.restMessage);
            if (!result.validMessage)
            {
                // incorrect branch reset message
                result.restMessage = message;
                break;
            }
        }
        return result;
    }

    private static Result checkNextCharacterInMessage(String message, Rule rule) throws EndOfMessageException
    {
        Result result;
        boolean partly = message.startsWith(rule.character.toString());
        if (!partly)
        {
            result = new Result(false, message);
        }
        else
        {
            final String substring = message.substring(1);
            if (substring.isEmpty())
            {
                throw new EndOfMessageException();
            }
            result = new Result(true, substring);
        }
        return result;
    }

    static class Result
    {
        boolean validMessage;
        String restMessage;

        public Result(boolean validMessage, String restMessage)
        {
            this.validMessage = validMessage;
            this.restMessage = restMessage;
        }
    }

    static long getNumberOfMatchingMessages(String fileName)
    {
        Map<Integer, AdventOfCode2020Day19.Rule> rules = new HashMap<>();
        List<String> messages = new ArrayList<>();
        readInput(fileName, rules, messages);
        return messages.stream().filter(message -> matchRules(rules, message)).count();
    }
    static void printMatchingMessages(String fileName)
    {
        Map<Integer, AdventOfCode2020Day19.Rule> rules = new HashMap<>();
        List<String> messages = new ArrayList<>();
        readInput(fileName, rules, messages);
        messages.stream().filter(message -> matchRules(rules, message)).forEach(System.out::println);
    }

    static class EndOfMessageException extends Exception
    {

    }

}
