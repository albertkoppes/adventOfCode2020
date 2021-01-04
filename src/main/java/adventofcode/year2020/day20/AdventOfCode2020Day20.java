package adventofcode.year2020.day20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day20
{
    public static void main(String[] args)
    {
        List<Tile> allTiles = readTiles("day20/inputDay20.txt");
        System.out.println(getProductOfIdsOfCornerTiles(allTiles)); // 111936085519519
    }

    enum Edge
    {
        UPPER,
        RIGHT,
        LOWER,
        LEFT
    }

    static class Tile
    {
        int id;
        Character[][] chars = new Character[10][10];

        public Tile(int id)
        {
            this.id = id;
        }

        public void addLine(String line)
        {
            for (int i = 0; i < chars.length; i++)
            {
                if (chars[i][0] == null)
                {
                    chars[i] = ArrayUtils.toObject(line.toCharArray());
                    return;
                }
            }
        }

        private List<Character> getUpperEdge()
        {
            return Arrays.asList(chars[0]);
        }

        private List<Character> getLowerEdge()
        {
            List<Character> characters = new ArrayList<>(Arrays.asList(chars[9])); // take a copy otherwise original array will be reversed
            Collections.reverse(characters);
            return characters;
        }

        List<Character> getEdge(Edge edge, boolean flipped)
        {
            List<Character> theEdge;
            switch (edge)
            {
                case UPPER:
                    theEdge = getUpperEdge();
                    break;
                case RIGHT:
                    theEdge = getRightEdge();
                    break;
                case LOWER:
                    theEdge = getLowerEdge();
                    break;
                case LEFT:
                    theEdge = getLeftEdge();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown edge: " + edge);
            }
            List<Character> copyEdge = new ArrayList<>(theEdge);
            if (flipped)
            {
                Collections.reverse(copyEdge);
            }
            return copyEdge;
        }

        private List<Character> getLeftEdge()
        {
            List<Character> edge = new ArrayList<>();
            for (Character[] aChar : chars)
            {
                edge.add(aChar[0]);
            }
            Collections.reverse(edge);
            return edge;
        }

        private List<Character> getRightEdge()
        {
            List<Character> edge = new ArrayList<>();
            for (Character[] aChar : chars)
            {
                edge.add(aChar[9]);
            }
            return edge;
        }

        List<List<Character>> getAllEdges()
        {
            List<List<Character>> edges = new ArrayList<>();
            for (Edge edge : Edge.values())
            {
                edges.add(getEdge(edge, false));
                edges.add(getEdge(edge, true));
            }
            return edges;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Tile))
                return false;
            Tile tile = (Tile)o;
            return id == tile.id;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(id);
        }

        public int getNumberOfMatchingEdges(List<Tile> allTiles)
        {
            List<List<Character>> edges = getAllEdges();
            List<List<Character>> allEdgesExceptThis = getAllEdgesExceptThis(allTiles);
            int matchingEdges = 0;
            for (List<Character> edge : edges)
            {
                if (allEdgesExceptThis.contains(edge))
                {
                    matchingEdges++;
                }
            }
            return matchingEdges;

        }

        private List<List<Character>> getAllEdgesExceptThis(List<Tile> allTiles)
        {
            List<List<Character>> allEdgesExceptThis = new ArrayList<>();
            for (Tile aTile : allTiles)
            {
                if (!this.equals(aTile))
                {
                    allEdgesExceptThis.addAll(aTile.getAllEdges());
                }
            }
            return allEdgesExceptThis;
        }
    }

    static List<Tile> readTiles(String filename)
    {
        List<String> lines = Utils.readLines(filename);
        List<Tile> tiles = new ArrayList<>();
        Tile tile = null;
        for (String line : lines)
        {
            if (!line.isEmpty())
            {
                if (line.startsWith("Tile"))
                {
                    tile = addTileId(tiles, tile, line);
                }
                else
                {
                    addTileLine(filename, tile, line);
                }
            }

        }
        // add the last one
        if (tile != null)
        {
            tiles.add(tile);
        }
        return tiles;
    }

    private static void addTileLine(String filename, Tile tile, String line)
    {
        if (tile == null)
        {
            throw new IllegalArgumentException("Illegal input for file: " + filename);
        }
        tile.addLine(line);
    }

    private static Tile addTileId(List<Tile> tiles, Tile tile, String line)
    {
        if (tile != null)
        {
            tiles.add(tile);
        }
        int id = Integer.parseInt(line.substring(5, line.indexOf(":")));
        tile = new Tile(id);
        return tile;
    }

    static long getProductOfIdsOfCornerTiles(List<Tile> allTiles)
    {
        List<Tile> centerTiles = getTileWithNeighborsToAllSides(allTiles);
        List<List<Character>> borders = getOuterBorders(centerTiles);
        List<Tile> cornerTiles = getCornerTiles(allTiles, centerTiles, borders);
        long product = 1;
        for (Tile cornerTile : cornerTiles)
        {
            product *= cornerTile.id;
        }
        return product;
    }

    private static List<Tile> getCornerTiles(List<Tile> allTiles, List<Tile> centerTiles, List<List<Character>> borders)
    {
        List<Tile> theTiles = new ArrayList<>(allTiles);
        theTiles.removeAll(centerTiles);
        List<List<Character>> theBorders = new ArrayList<>(borders);
        for (List<Character> theBorder : theBorders)
        {
            for (Tile theTile : theTiles)
            {
                if (theTile.getAllEdges().contains(theBorder))
                {
                    theTiles.remove(theTile);
                    break;
                }
            }
        }
        return theTiles;

    }

    private static List<List<Character>> getOuterBorders(List<Tile> centerTiles)
    {
        List<List<Character>> outerBorders = new ArrayList<>();
        for (Tile centerTile : centerTiles)
        {
            outerBorders.addAll(centerTile.getAllEdges());
        }
        Set<List<Character>> toBeDeleted = new HashSet<>();
        for (List<Character> outerBorder : outerBorders)
        {
            if (Collections.frequency(outerBorders, outerBorder) > 1)
            {
                toBeDeleted.add(outerBorder);
            }
        }
        outerBorders.removeAll(toBeDeleted);
        return outerBorders;
    }

    private static List<Tile> getTileWithNeighborsToAllSides(List<Tile> allTiles)
    {
        return allTiles.stream().filter(tile -> tile.getNumberOfMatchingEdges(allTiles) == 8).collect(Collectors.toList());
    }

}
