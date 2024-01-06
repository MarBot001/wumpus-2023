import hu.nye.progTech.MapDataToXML;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapDataToXMLTest {

    @Test
    public void testSaveMapDataToXML() {
        // Tesztadatok
        int mapSize = 10;
        int numBonusWalls = 5;
        int numPiles = 3;

        // Teszteset végrehajtása
        MapDataToXML.saveMapDataToXML(mapSize, numBonusWalls, numPiles);

        // Ellenőrzés, hogy a fájl létrejött-e
        java.io.File file = new java.io.File("map_data.xml");
        assertTrue(file.exists());

        // Egyéb ellenőrzések az XML tartalmára vonatkozóan is végezhetők
        // Pl. XML struktúra, adatok stb. ellenőrzése
    }
}
