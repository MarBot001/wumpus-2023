package hu.nye.progTech;

public class GameTest {

    public static void main(String[] args) {
        boolean testsPassed = true;

        // Teszt #1: Szörny megölés
        char[][] map = new char[10][10];
        MapCreation gameMap = new MapCreation(10, 0, 0);
        Hero hero = new Hero(5, 5, 3, 10);
        gameMap.placeHero(hero);

        // Szörny elhelyezése és lövés
        map[5][6] = 'M'; // Szörny elhelyezése a térképen
        hero.shootMonster(map);

        if (map[5][6] != ' ' || hero.getArrows() != 2) {
            System.out.println("Teszt #1 nem sikerült: Szörny megölése hibás.");
            testsPassed = false;
        }

        // Teszt #2: Túl sok lövés
        hero.shootMonster(map);
        hero.shootMonster(map);
        hero.shootMonster(map);

        if (hero.getArrows() != 0) {
            System.out.println("Teszt #2 nem sikerült: Túl sok lövés.");
            testsPassed = false;
        }

        if (testsPassed) {
            System.out.println("Az összes teszt sikeresen lefutott.");
        }
    }
}
