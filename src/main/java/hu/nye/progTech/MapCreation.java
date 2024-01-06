package hu.nye.progTech;

import hu.nye.progTech.Hero;

import java.util.Random;

public class MapCreation {
    private char[][] gameMap;
    private int mapSize;
    private int bonusWallCount;
    private int pitCount;

    public MapCreation(int size, int bonusWalls, int pits) {
        if (size < 6 || size > 20) {
            throw new IllegalArgumentException("Map size should be an integer between 6 and 20.");
        }
        this.mapSize = size;
        this.bonusWallCount = bonusWalls;
        this.pitCount = pits;
        this.gameMap = new char[size][size];
        initializeMap();
        fillBordersWithWalls();
        placePitsRandomly(pits);
        placeBonusWallsRandomly(bonusWalls);
        placeMonsters();
        placeGold();
    }

    private void initializeMap() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                gameMap[i][j] = ' ';
            }
        }
    }

    private void fillBordersWithWalls() {
        for (int i = 0; i < mapSize; i++) {
            gameMap[0][i] = 'W';
            gameMap[mapSize - 1][i] = 'W';
            gameMap[i][0] = 'W';
            gameMap[i][mapSize - 1] = 'W';
        }
    }

    private void placePitsRandomly(int numPits) {
        Random random = new Random();
        int placedPits = 0;

        while (placedPits < numPits) {
            int x = random.nextInt(mapSize);
            int y = random.nextInt(mapSize);

            if (gameMap[y][x] == ' ') {
                gameMap[y][x] = 'P';
                placedPits++;
            }
        }
    }

    private void placeBonusWallsRandomly(int numBonusWalls) {
        Random random = new Random();
        int placedBonusWalls = 0;

        while (placedBonusWalls < numBonusWalls) {
            int x = random.nextInt(mapSize);
            int y = random.nextInt(mapSize);

            if (gameMap[y][x] == ' ') {
                gameMap[y][x] = 'W';
                placedBonusWalls++;
            }
        }
    }

    private void placeMonsters() {
        gameMap[mapSize / 2][mapSize / 2] = 'M';
    }

    private void placeGold() {
        gameMap[mapSize / 2 - 1][mapSize / 2 - 1] = 'G';
    }

    public char[][] getMap() {
        return gameMap;
    }

    public void placeHero(Hero hero) {
        gameMap[hero.getPositionX()][hero.getPositionY()] = 'H';
    }

    public void clearHero(Hero hero) {
        gameMap[hero.getPositionX()][hero.getPositionY()] = ' ';
    }
}
