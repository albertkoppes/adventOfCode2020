package adventofcode.year2020.day21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day21
{
    public static void main(String[] args)
    {
        System.out.println(getNumberOfIngredientsWithNoAllergens("day21/inputDay21.txt"));
        System.out.println(getCanonicalDangerousIngredientList("day21/inputDay21.txt")); // bjpkhx,thn,snhph,nsnqf,sthnsg,zmfqpn,qrbnjtj,dbhfd
    }

    static String getCanonicalDangerousIngredientList(String filename)
    {
        Map<Allergen, Set<Ingredient>> possibleMatches = new HashMap<>();
        List<Ingredient> allIngredients = new ArrayList<>();
        getAllPossibleMatches(filename, possibleMatches, allIngredients);
        Set<Ingredient> ingredients = new LinkedHashSet<>();
        possibleMatches.keySet().stream().sorted().forEach(key->ingredients.addAll(possibleMatches.get(key)));
        StringBuilder canonicalDangerousIngredient = new StringBuilder();
        String sep = "";
        for (Ingredient ingredient : ingredients)
        {
            canonicalDangerousIngredient.append(sep).append(ingredient.name);
            sep = ",";
        }
        return canonicalDangerousIngredient.toString();
    }

    static long getNumberOfIngredientsWithNoAllergens(String filename)
    {
        Map<Allergen, Set<Ingredient>> possibleMatches = new HashMap<>();
        List<Ingredient> allIngredients = new ArrayList<>();
        getAllPossibleMatches(filename, possibleMatches, allIngredients);
        Set<Ingredient> allValues = new HashSet<>();
        possibleMatches.values().forEach(allValues::addAll);
        return allIngredients.stream().filter(ing->!allValues.contains(ing)).count();

    }

    static void getAllPossibleMatches(
        String filename,
        Map<Allergen, Set<Ingredient>> possibleMatches,
        List<Ingredient> allIngredients)
    {
        List<String> lines = Utils.readLines(filename);
        lines.forEach(line -> addToPossibleMatches(line, possibleMatches, allIngredients));
    }

    private static void addToPossibleMatches(
        String line,
        Map<Allergen, Set<Ingredient>> possibleMatches,
        List<Ingredient> allIngredients)
    {
        List<Ingredient> ingredients = getIngredients(line);
        allIngredients.addAll(ingredients);
        List<Allergen> allergens = getAllergens(line);
        for (Allergen allergen : allergens)
        {
            Set<Ingredient> possibleIngredients = possibleMatches.get(allergen);
            if (possibleIngredients == null)
            {
                possibleIngredients = new HashSet<>(ingredients);
            }
            else
            {
                possibleIngredients = possibleIngredients.stream().filter(ingredients::contains).collect(Collectors.toSet());
            }
            possibleMatches.put(allergen, possibleIngredients);
        }
    }

    private static List<Allergen> getAllergens(String line)
    {
        String allergensPart = line.substring(line.indexOf("(contains") + 10);
        allergensPart = allergensPart.replace(")", "");
        List<Allergen> allergens = new ArrayList<>();
        try (Scanner scanner = new Scanner(allergensPart).useDelimiter(","))
        {
            while (scanner.hasNext())
            {
                allergens.add(new Allergen(scanner.next().trim()));
            }
        }
        return allergens;
    }

    private static List<Ingredient> getIngredients(String line)
    {
        List<Ingredient> ingredients = new ArrayList<>();
        String ingredientsPart = line.substring(0, line.indexOf("("));
        try (Scanner scanner = new Scanner(ingredientsPart))
        {
            while (scanner.hasNext())
            {
                ingredients.add(new Ingredient(scanner.next()));
            }
        }
        return ingredients;
    }

    static class Allergen implements Comparable<Allergen>
    {
        String name;

        public Allergen(String name)
        {
            this.name = name;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Allergen))
                return false;
            Allergen allergen = (Allergen)o;
            return name.equals(allergen.name);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(name);
        }

        @Override
        public int compareTo(Allergen o)
        {
            return this.name.compareTo(o.name);
        }
    }

    static class Ingredient
    {
        String name;

        public Ingredient(String name)
        {
            this.name = name;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Ingredient))
                return false;
            Ingredient that = (Ingredient)o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(name);
        }
    }
}
