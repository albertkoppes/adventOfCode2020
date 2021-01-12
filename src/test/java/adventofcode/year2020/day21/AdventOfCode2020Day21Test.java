package adventofcode.year2020.day21;

import static org.junit.jupiter.api.Assertions.*;

import static adventofcode.year2020.day21.AdventOfCode2020Day21.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class AdventOfCode2020Day21Test
{
    @Test
    void testReadInput()
    {
        Map<Allergen, Set<Ingredient>> possibleMatches = new HashMap<>();
        List<Ingredient> allIngredients = new ArrayList<>();
        getAllPossibleMatches("day21/inputDay21Test.txt", possibleMatches, allIngredients);
        assertEquals(3, possibleMatches.keySet().size());
        Set<Ingredient> allValues = new HashSet<>();
        possibleMatches.values().forEach(allValues::addAll);
        String[] strings = new String[]{ "kfcds", "nhms", "sbzzf","trh" };
        for (String string : strings)
        {
            assertFalse(allValues.contains(new Ingredient(string)), string + " should be filtered out of possible ingredients");
        }
        assertEquals("mxmxvkd,sqjhc,fvjkl", getCanonicalDangerousIngredientList("day21/inputDay21Test.txt"));
    }

    @Test
    void testGetNumberOfIngredientsWithoutAllergen()
    {
        assertEquals(5, getNumberOfIngredientsWithNoAllergens("day21/inputDay21Test.txt"));
    }
}