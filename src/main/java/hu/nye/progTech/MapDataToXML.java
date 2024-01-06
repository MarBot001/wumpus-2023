package hu.nye.progTech;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class MapDataToXML {

    public static void saveMapDataToXML(int mapSize, int numBonusWalls, int numPiles) {
        try {
            Document document = createXMLDocument(mapSize, numBonusWalls, numPiles);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            OutputStream outputStream = new FileOutputStream(new File("map_data.xml"));
            StreamResult streamResult = new StreamResult(outputStream);
            transformer.transform(domSource, streamResult);

            System.out.println("A pálya adatai el lettek mentve a map_data.xml fájlba.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Document createXMLDocument(int mapSize, int numBonusWalls, int numPiles) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElement = document.createElement("MapData");
            document.appendChild(rootElement);

            Element mapSizeElement = document.createElement("MapSize");
            mapSizeElement.appendChild(document.createTextNode(String.valueOf(mapSize)));
            rootElement.appendChild(mapSizeElement);

            Element numBonusWallsElement = document.createElement("NumBonusWalls");
            numBonusWallsElement.appendChild(document.createTextNode(String.valueOf(numBonusWalls)));
            rootElement.appendChild(numBonusWallsElement);

            Element numPilesElement = document.createElement("NumPiles");
            numPilesElement.appendChild(document.createTextNode(String.valueOf(numPiles)));
            rootElement.appendChild(numPilesElement);

            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Példa adatokkal a fájl létrehozása
        saveMapDataToXML(10, 5, 3);
    }
}
