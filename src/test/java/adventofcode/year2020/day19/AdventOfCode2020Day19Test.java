package adventofcode.year2020.day19;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AdventOfCode2020Day19Test
{

    @Test
    void testReadInput1()
    {
        Map<Integer, AdventOfCode2020Day19.Rule> rules =  new HashMap<>();
        List<String> messages = new ArrayList<>();
        AdventOfCode2020Day19.readInput("day19/inputDay19Test1.txt", rules, messages);
        assertEquals(4, rules.keySet().size());
        assertEquals(3, messages.size());
        assertTrue(AdventOfCode2020Day19.matchRules(rules, messages.get(0)));
        assertTrue(AdventOfCode2020Day19.matchRules(rules, messages.get(1)));
        assertFalse(AdventOfCode2020Day19.matchRules(rules, messages.get(2)));
        assertEquals(2, AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19Test1.txt"));
    }

    @Test
    void testReadInput2()
    {
        Map<Integer, AdventOfCode2020Day19.Rule> rules =  new HashMap<>();
        List<String> messages = new ArrayList<>();
        AdventOfCode2020Day19.readInput("day19/inputDay19Test2.txt", rules, messages);
        assertEquals(6, rules.keySet().size());
        assertEquals(5, messages.size());
        assertTrue(AdventOfCode2020Day19.matchRules(rules, messages.get(0)));
        assertFalse(AdventOfCode2020Day19.matchRules(rules, messages.get(1)));
        assertTrue(AdventOfCode2020Day19.matchRules(rules, messages.get(2)));
        assertFalse(AdventOfCode2020Day19.matchRules(rules, messages.get(3)));
        assertFalse(AdventOfCode2020Day19.matchRules(rules, messages.get(4)));
        assertEquals(2, AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19Test2.txt"));
    }

    @Test
    void testRealInput()
    {
        Map<Integer, AdventOfCode2020Day19.Rule> rules =  new HashMap<>();
        List<String> messages = new ArrayList<>();
        AdventOfCode2020Day19.readInput("day19/inputDay19.txt", rules, messages);
        assertEquals(133, rules.keySet().size());
        assertEquals(367, messages.size());
        assertEquals("bbabbbbbbbbaabbaaaaaabaaaabbaaaa", messages.get(0));
        assertEquals("babaabbbbbbbaaabbaababaa", messages.get(messages.size()-1));
        assertEquals(118, AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19_2.txt"));
        assertEquals(263, AdventOfCode2020Day19.getNumberOfMatchingMessages("day19/inputDay19.txt"));
    }

}