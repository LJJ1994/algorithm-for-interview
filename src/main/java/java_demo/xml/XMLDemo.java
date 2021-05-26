package java_demo.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XMLDemo {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = null;
        inputStream = XMLDemo.class.getClassLoader().getResourceAsStream("demo.xml");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        NodeList list = document.getElementsByTagName("action");
        for (int i = 0; i < list.getLength(); i++) {
            System.out.println(document.getElementsByTagName("result").item(i).getFirstChild().getNodeValue());
        }
    }
}
