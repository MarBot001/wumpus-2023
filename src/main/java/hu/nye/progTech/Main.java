package hu.nye.progTech;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();

            System.out.print("Enter your password: ");
            String playerPassword = scanner.nextLine();

            int mapSize, numBonusWalls, numPits;
            do {
                System.out.print("Enter the map size (an integer between 6 and 20): ");
                mapSize = scanner.nextInt();
            } while (mapSize < 6 || mapSize > 20);

            System.out.print("Enter the number of bonus walls: ");
            numBonusWalls = scanner.nextInt();

            System.out.print("Enter the number of pits: ");
            numPits = scanner.nextInt();

            while (true) {
                MapCreation gameMap = new MapCreation(mapSize, numBonusWalls, numPits);
                char[][] map = gameMap.getMap();

                Hero hero = new Hero(mapSize - 2, 1, numPits, mapSize);

                gameMap.placeHero(hero);

                System.out.println(playerName + ", the hero has an arrow to use against monsters.");
                System.out.println("Number of arrows: " + hero.getNumberOfArrows());

                printMap(map);

                while (true) {
                    System.out.print("Enter the movement direction (turn/exit/shoot): ");
                    String input = scanner.next();

                    if (input.equals("exit")) {
                        break;
                    } else if (input.equals("turn")) {
                        System.out.print("Enter the new direction (north/east/south/west): ");
                        String newDirection = scanner.next();
                        hero.rotate(newDirection);
                    } else if (input.equals("shoot")) {
                        hero.shootMonster(map);
                        printMap(map);
                    } else {
                        gameMap.clearHero(hero);
                        hero.move(map);
                        gameMap.placeHero(hero);
                        printMap(map);
                    }

                    // Check for the starting point
                    if (hero.isAtStartingPoint()) {
                        System.out.println("Congratulations, you won the game!");

                        ConnectToData connectToData = new ConnectToData(); // Instantiate
                        connectToData.updatePlayerScore(playerName);

                        break;
                    }
                }
                                System.out.print("Do you want to play again? (yes/no): ");
                String playAgain = scanner.next().toLowerCase();
                if (!playAgain.equals("yes")) {
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. The map size should be an integer between 6 and 20.");
        }
    }

    public static void printMap(char[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
    }
}
