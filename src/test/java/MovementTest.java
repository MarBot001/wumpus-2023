import hu.nye.progTech.Hero;
import hu.nye.progTech.MapCreation;

public class MovementTest {

    public static void main(String[] args) {
        boolean testsPassed = true;

        // Teszt #1: Hős mozgása északra
        char[][] map = new char[10][10];
        MapCreation gameMap = new MapCreation(10, 0, 0);
        Hero hero = new Hero(5, 5, 3, 10);
        gameMap.placeHero(hero);

        hero.rotate("észak");
        hero.move(map);

        if (hero.getX() != 4 || hero.getY() != 5) {
            System.out.println("Teszt #1 nem sikerült: Hős mozgása északra hibás.");
            testsPassed = false;
        }

        // Teszt #2: Hős mozgása délre
        hero.rotate("dél");
        hero.move(map);

        if (hero.getX() != 5 || hero.getY() != 5) {
            System.out.println("Teszt #2 nem sikerült: Hős mozgása délre hibás.");
            testsPassed = false;
        }

        // Teszt #3: Hős mozgása nyugatra falnak ütközve
        hero.rotate("nyugat");
        hero.move(map);

        if (hero.getX() != 5 || hero.getY() != 5) {
            System.out.println("Teszt #3 nem sikerült: Hős mozgása nyugatra hibás.");
            testsPassed = false;
        }

        // Teszt #4: Hős mozgása keletre
        hero.rotate("kelet");
        hero.move(map);

        if (hero.getX() != 5 || hero.getY() != 6) {
            System.out.println("Teszt #4 nem sikerült: Hős mozgása keletre hibás.");
            testsPassed = false;
        }

        if (testsPassed) {
            System.out.println("Az összes teszt sikeresen lefutott.");
        }
    }
}
