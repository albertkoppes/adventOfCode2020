package adventofcode.year2020.day17;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day17Part1
{
    public static void main(String[] args)
    {
        List<AdventOfCode2020Day17Part1.Cell> map = AdventOfCode2020Day17Part1.getMap("day17/inputDay17.txt");
        map = getMapAfterXCycles(map, 6);
        System.out.println(AdventOfCode2020Day17Part1.getNumberOfActiveStates(map)); // 426

    }
    public static long getNumberOfActiveStates(List<Cell> map)
    {
        return map.stream().filter(c->c.state=='#').count();
    }

    public static List<Cell> getMapAfterXCycles(List<Cell> map, int nofCycles)
    {
        for (int i = 0; i <nofCycles ; i++)
        {
            map = getNextCycle(map);
        }
        return map;
    }

    static class Position implements Comparable<Position>
    {
        int x;
        int y;
        int z;

        public Position(Position position)
        {
            this.x = position.x;
            this.y = position.y;
            this.z = position.z;
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public int getZ()
        {
            return z;
        }

        @Override
        public int compareTo(Position other)
        {
            return Comparator.comparing(Position::getX)
                .thenComparing(Position::getY)
                .thenComparingInt(Position::getZ).compare(this, other);
        }

        boolean isNeighBor(Position other)
        {
            return !this.equals(other)
                && Math.abs(this.x - other.x) <= 1
                && Math.abs(this.y - other.y) <= 1
                && Math.abs(this.z - other.z) <= 1;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Position))
                return false;
            Position position = (Position)o;
            return x == position.x && y == position.y && z == position.z;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(x, y, z);
        }

        public Position(int x, int y, int z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }

    static class Cell
    {
        Position position;
        char state;

        public Cell(int x, int y, int z, char state)
        {
            this.position = new Position(x,y,z);
            this.state = state;
        }

        public Cell(Cell oldCell)
        {
            this.position = new Position(oldCell.position);
            this.state = oldCell.state;
        }

        public boolean isPosition(int x, int y, int z)
        {
            return position.x == x && position.y == y && position.z == z;
        }

        public void calcNewState(List<Cell> input)
        {
            List<Cell> neighbors = getNeighbors(input);
            long numberOfActiveNeighborStates = neighbors.stream().filter(c->c.state=='#').count();
            if (state == '#')
            {
                state = (numberOfActiveNeighborStates == 2 || numberOfActiveNeighborStates == 3) ? '#' : '.';
            }
            else
            {
                state = (numberOfActiveNeighborStates == 3) ? '#' : '.';
            }

        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Cell))
                return false;
            Cell cell = (Cell)o;
            return position.equals(cell.position);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(position);
        }

        boolean isNeighBor(Cell other)
        {
            return this.position.isNeighBor(other.position);
        }

        private List<Cell> getNeighbors(List<Cell> input)
        {
            return input.stream().filter(this::isNeighBor).collect(Collectors.toList());
        }
    }

    static List<Cell> getMap(String fileName)
    {
        List<String> lines = Utils.readLines(fileName);
        List<Cell> cells = new ArrayList<>();
        int y=0;
        int z=0;
        for (String line : lines)
        {
            int x=0;
            char[] chars = line.toCharArray();
            for (char aChar : chars)
            {
                Cell cell = new Cell(x++, y, z, aChar);
                cells.add(cell);
            }
            y--;

        }
        return cells;
    }

    static int getMinX(List<Cell> map)
    {
        return map.stream().map(cell->cell.position.x).mapToInt(x->x).min().orElseThrow(()->new IllegalArgumentException(map.toString()));
    }
    static int getMinY(List<Cell> map)
    {
        return map.stream().map(cell->cell.position.y).mapToInt(x->x).min().orElseThrow(()->new IllegalArgumentException(map.toString()));
    }
    static int getMinZ(List<Cell> map)
    {
        return map.stream().map(cell->cell.position.z).mapToInt(x->x).min().orElseThrow(()->new IllegalArgumentException(map.toString()));
    }

    static int getMaxX(List<Cell> map)
    {
        return map.stream().map(cell->cell.position.x).mapToInt(x->x).max().orElseThrow(()->new IllegalArgumentException(map.toString()));
    }
    static int getMaxY(List<Cell> map)
    {
        return map.stream().map(cell->cell.position.y).mapToInt(x->x).max().orElseThrow(()->new IllegalArgumentException(map.toString()));
    }
    static int getMaxZ(List<Cell> map)
    {
        return map.stream().map(cell->cell.position.z).mapToInt(x->x).max().orElseThrow(()->new IllegalArgumentException(map.toString()));
    }

    static List<Cell> getNextCycle(List<Cell> input)
    {
        List<Cell> output = new ArrayList<>();
        for (int x = getMinX(input)-1; x <= getMaxX(input)+1; x++)
        {
            for (int y = getMinY(input)-1; y <= getMaxY(input)+1; y++)
            {
                for (int z = getMinZ(input)-1; z <= getMaxZ(input)+1; z++)
                {
                    Cell oldCell = getCell(input, x,y,z).orElse(null);
                    Cell newCell;
                    if (oldCell == null)
                    {
                        newCell = new Cell(x, y, z, '.');
                    }
                    else
                    {
                        newCell = new Cell(oldCell);
                    }
                    newCell.calcNewState(input);
                    output.add(newCell);
                }

            }

        }
        return output;
    }


    private static Optional<Cell> getCell(List<Cell> output, int x, int y, int z)
    {
        return output.stream().filter((c->c.isPosition(x,y,z))).findFirst();
    }

    static void printMap(List<Cell> map)
    {
        for (int z = getMinZ(map) ; z<=getMaxZ(map); z++)
        {
            for (int y = getMaxY(map); y>= getMinY(map); y--)
            {
                for (int x = getMinX(map); x <= getMaxX(map); x++)
                {
                    Cell cell = getCell(map, x, y, z).orElseThrow(() -> new IllegalArgumentException("no cell found for input"));
                    System.out.print(cell.state);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }

}
