package hu.nye.progTech;

public class Hero {
    private int posX;
    private int posY;
    private int numberOfArrows;
    private String currentDirection;
    private int mapDimension;
    private int collectedGold;

    public Hero(int initialX, int initialY, int arrowCount, int mapSize) {
        this.posX = initialX;
        this.posY = initialY;
        this.numberOfArrows = arrowCount;
        this.currentDirection = "north";
        this.mapDimension = mapSize;
        this.collectedGold = 0;
    }

    public int getPositionX() {
        return posX;
    }

    public int getPositionY() {
        return posY;
    }

    public int getNumberOfArrows() {
        return numberOfArrows;
    }

    public void move(char[][] map) {
        int newX = posX;
        int newY = posY;

        if (currentDirection.equals("north")) {
            newX = posX - 1;
        } else if (currentDirection.equals("south")) {
            newX = posX + 1;
        } else if (currentDirection.equals("west")) {
            newY = posY - 1;
        } else if (currentDirection.equals("east")) {
            newY = posY + 1;
        }

        char nextCell = map[posX][posY];

        if (nextCell == 'P') {
            numberOfArrows--;
            System.out.println("The hero stepped on a pit. Remaining arrows: " + numberOfArrows);
        }

        if (newX >= 0 && newX < map.length && newY >= 0 && newY < map[0].length && map[newX][newY] != 'W') {
            posX = newX;
            posY = newY;

            if (map[posX][posY] == 'G') {
                collectedGold++;
                System.out.println("Congratulations, you've found gold!");
                map[posX][posY] = ' ';
            }
        }

        checkForGold(map);
    }

    public void shootMonster(char[][] map) {
        if (numberOfArrows > 0) {
            int shotX = posX;
            int shotY = posY;

            if (currentDirection.equals("north")) {
                for (int i = shotX - 1; i >= 0; i--) {
                    if (map[i][shotY] == 'M') {
                        map[i][shotY] = ' ';
                        numberOfArrows--;
                        break;
                    }
                }
            } else if (currentDirection.equals("south")) {
                for (int i = shotX + 1; i < mapDimension; i++) {
                    if (map[i][shotY] == 'M') {
                        map[i][shotY] = ' ';
                        numberOfArrows--;
                        break;
                    }
                }
            } else if (currentDirection.equals("west")) {
                for (int i = shotY - 1; i >= 0; i--) {
                    if (map[shotX][i] == 'M') {
                        map[shotX][i] = ' ';
                        numberOfArrows--;
                        break;
                    }
                }
            } else if (currentDirection.equals("east")) {
                for (int i = shotY + 1; i < mapDimension; i++) {
                    if (map[shotX][i] == 'M') {
                        map[shotX][i] = ' ';
                        numberOfArrows--;
                        break;
                    }
                }
            }
        }
    }

    public void rotate(String newDirection) {
        this.currentDirection = newDirection;
    }

    private void checkForGold(char[][] map) {
        int northX = posX - 1;
        int southX = posX + 1;
        int westY = posY - 1;
        int eastY = posY + 1;

        if (northX >= 0 && map[northX][posY] == 'G') {
            collectedGold++;
            System.out.println("Congratulations, you've found gold!");
            map[northX][posY] = ' ';
        }
        if (southX < mapDimension && map[southX][posY] == 'G') {
            collectedGold++;
            System.out.println("Congratulations, you've found gold!");
            map[southX][posY] = ' ';
        }
        if (westY >= 0 && map[posX][westY] == 'G') {
            collectedGold++;
            System.out.println("Congratulations, you've found gold!");
            map[posX][westY] = ' ';
        }

        if (eastY < mapDimension && map[posX][eastY] == 'G') {
            collectedGold++;
            System.out.println("Congratulations, you've found gold!");
            map[posX][eastY] = ' ';
        }
    }

    public boolean isAtStartingPoint() {
        return (posX == mapDimension - 2) && (posY == 1);
    }
}
