package hu.nye.progTech;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class PlayerXMLHandler {

    private static final String FILE_NAME = "players.xml";

    public void addPlayer(String playerName, String playerPassword, int score) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;

            File file = new File(FILE_NAME);

            if (file.exists()) {
                doc = builder.parse(file);
            } else {
                doc = builder.newDocument();
                Element rootElement = doc.createElement("players");
                doc.appendChild(rootElement);
            }

            Element player = doc.createElement("player");

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(playerName));
            player.appendChild(name);

            Element password = doc.createElement("password");
            password.appendChild(doc.createTextNode(playerPassword));
            player.appendChild(password);

            Element playerScore = doc.createElement("score");
            playerScore.appendChild(doc.createTextNode(String.valueOf(score)));
            player.appendChild(playerScore);

            doc.getDocumentElement().appendChild(player);

            saveXML(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveXML(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILE_NAME));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
