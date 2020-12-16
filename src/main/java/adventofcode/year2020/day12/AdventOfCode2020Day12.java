package adventofcode.year2020.day12;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day12
{
    public static void main(String[] args)
    {
        System.out.println(getDistanceAfterReplacements("inputDay12.txt"));
        System.out.println(getDistanceAfterReplacementsPart2("inputDay12.txt"));// 1481649944 = too high

    }
    static class State
    {
        long x;
        long y;
        char direction; // e, w, n, s

        public State(long x, long y, char direction)
        {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public static class Ship
        {
            State state = new State(0,0, 'e');
            Waypoint waypoint = new Waypoint();

            public Ship(long shipx, long shipy, char shipDirection,long waypointx, long waypointy, char waypointDirection)
            {
                state.x = shipx;
                state.y = shipy;
                state.direction = shipDirection;
                waypoint.state.x = waypointx;
                waypoint.state.y = waypointy;
                waypoint.state.direction = waypointDirection;
            }

            public Ship getNewState(Instruction instruction)
            {
                long distance = getDistance();
                Ship newShip = this;

                switch (instruction.action)
                {
                    case 'N':
                    case 'S':
                    case 'E':
                    case 'W':
                        newShip.waypoint.state = newShip.waypoint.state.getNewStatePart1(instruction);
                        break;
                    case 'L':
                    case 'R':
                    case 'F':
                        newShip = handlePart2Instruction(instruction);
                        if (distance != newShip.getDistance())
                        {
                            throw new IllegalArgumentException("Unexpected change in distance between ship and waypoint through action : " + instruction.action);
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid instruction: " + instruction);
                }
                return newShip;
            }

            private long getDistance()
            {
                return Math.abs(state.x - waypoint.state.x) + Math.abs(state.y - waypoint.state.y);
            }

            private Ship handlePart2Instruction(Instruction instruction)
            {
                Ship newShip = this;
                long xDistance = waypoint.state.x - state.x;
                long yDistance = waypoint.state.y - state.y;
                switch (instruction.action)
                {
                    case 'L':
                        if (instruction.inputValue == 90)
                        {
                            rotateWaypointMin90Degrees(newShip, xDistance, yDistance);
                        }
                        else if (instruction.inputValue == 180)
                        {
                            rotateWaypoint180Degrees(newShip, xDistance, yDistance);
                        }
                        else if (instruction.inputValue == 270)
                        {
                            rotateWaypointPlus90Degrees(newShip, xDistance, yDistance);
                        }
                        else
                        {
                            throw new IllegalArgumentException("Invalid action: " + instruction.action+instruction.inputValue);
                        }
                        break;
                    case 'R':
                        if (instruction.inputValue == 90)
                        {
                            rotateWaypointPlus90Degrees(newShip, xDistance, yDistance);
                        }
                        else if (instruction.inputValue == 180)
                        {
                            rotateWaypoint180Degrees(newShip, xDistance, yDistance);
                        }
                        else if (instruction.inputValue == 270)
                        {
                            rotateWaypointMin90Degrees(newShip, xDistance, yDistance);
                        }
                        else
                        {
                            throw new IllegalArgumentException("Invalid instruction: " + instruction.action+instruction.inputValue);
                        }
                        break;
                    case 'F':
                        moveShipInWaypointDirection(instruction, newShip, xDistance, yDistance);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid action: " + instruction.action);
                }
                return newShip;

            }

            private void moveShipInWaypointDirection(Instruction instruction, Ship newShip, long xDistance, long yDistance)
            {
                newShip.state.y = newShip.state.y + yDistance * instruction.inputValue;
                newShip.state.x = newShip.state.x + xDistance * instruction.inputValue;
                newShip.waypoint.state.y = newShip.state.y + yDistance;
                newShip.waypoint.state.x = newShip.state.x + xDistance;
            }

            private void rotateWaypoint180Degrees(Ship newShip, long xDistance, long yDistance)
            {
                newShip.waypoint.state.y -= yDistance * 2;
                newShip.waypoint.state.x -= xDistance * 2;
            }

            private void rotateWaypointPlus90Degrees(Ship newShip, long xDistance, long yDistance)
            {
                newShip.waypoint.state.y = newShip.state.y-xDistance;
                newShip.waypoint.state.x = newShip.state.x+yDistance;
            }

            private void rotateWaypointMin90Degrees(Ship newShip, long xDistance, long yDistance)
            {
                newShip.waypoint.state.y = newShip.state.y+xDistance;
                newShip.waypoint.state.x = newShip.state.x-yDistance;
            }

            private Ship generateNewShip()
            {
                return new Ship(state.x, state.y, state.direction, waypoint.state.x, waypoint.state.y, waypoint.state.direction);
            }
        }

         static class Waypoint
        {
            State state = new State(0,0,'e');
        }

        State getNewStatePart1(Instruction instruction)
        {
            State newState = new State(x, y, direction);
            switch (instruction.action)
            {
                case 'N':
                    newState.y += instruction.inputValue;
                    break;
                case 'S':
                    newState.y -= instruction.inputValue;
                    break;
                case 'E':
                    newState.x += instruction.inputValue;
                    break;
                case 'W':
                    newState.x -= instruction.inputValue;
                    break;
                case 'L':
                    newState.direction = getNewDirectionToTheLeft(direction, instruction.inputValue);
                    break;
                case 'R':
                    newState.direction = getNewDirectionToTheRight(direction, instruction.inputValue);
                    break;
                case 'F':
                    newState = moveInCurrentDirection(newState, instruction.inputValue);
                    break;
                default:
                    throw new IllegalArgumentException("Illegal instruction: " + instruction.action);
            }
            return newState;
        }

        private State moveInCurrentDirection(State state, long inputValue)
        {
            State newState = new State(state.x, state.y, state.direction);
            switch (state.direction)
            {
                case 'n':
                    newState.y += inputValue;
                    break;
                case 's':
                    newState.y -= inputValue;
                    break;
                case 'e':
                    newState.x += inputValue;
                    break;
                case 'w':
                    newState.x -= inputValue;
                    break;
                default:
                    throw new IllegalArgumentException("Illegal direction: " + state.direction);
            }
            return newState;
        }

        private char getNewDirectionToTheRight(char direction, long inputValue)
        {
            long nofQuarters = inputValue/90;
            long currentIndex =  ArrayUtils.indexOf(directions, direction);
            long newIndex = (currentIndex+nofQuarters+4)%4;
            return directions[(int)newIndex];
        }

        private char getNewDirectionToTheLeft(char direction, long inputValue)
        {
            long nofQuarters = inputValue/90;
            long currentIndex =  ArrayUtils.indexOf(directions, direction);
            long newIndex = (currentIndex-nofQuarters+4)%4;
            return directions[(int)newIndex];
        }
    }

    static class Instruction
    {
        char action;
        long inputValue;

        public Instruction(String inputString)
        {
            action = inputString.charAt(0);
            inputValue = Integer.parseInt(inputString.substring(1));
        }

        @Override
        public String toString()
        {
            return "Instruction{" + "action=" + action +
                ", inputValue=" + inputValue +
                '}';
        }
    }

    static char[] directions = new char[]{'e','s', 'w', 'n'};

    static long getDistanceAfterReplacements(String filename)
    {
        List<String> lines = Utils.readLines(filename);
        State state = new State( 0,0, 'e');
        for (String line : lines)
        {
            Instruction instruction = new Instruction(line);
            state = state.getNewStatePart1(instruction);
        }
        return Math.abs(state.x) + Math.abs(state.y);
    }
    static long getDistanceAfterReplacementsPart2(String filename)
    {
        List<String> lines = Utils.readLines(filename);
        State.Ship ship = new State.Ship(0,0,'e', 10, 1, 'e');
        for (String line : lines)
        {
            Instruction instruction = new Instruction(line);
            ship = ship.getNewState(instruction);
        }
        return Math.abs(ship.state.x) + Math.abs(ship.state.y);
    }
}
