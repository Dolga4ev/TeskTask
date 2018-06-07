package test.task;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Reader {
    File fXml = null;
    Document doc;

    public Reader(){}

    public NodeList read() {
        try {
            fXml = new File("list.xml");

        }
        catch (Exception ei) {
            System.out.println("Не удалось найти файл");
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(fXml);
            doc.getDocumentElement().normalize();
        }
        catch (Exception ei) {
            System.out.println("Не удалось прочитать файл");
        }
        NodeList taskList = doc.getElementsByTagName("Task");
        return taskList;
    }
    public Document getDoc() {
        try {
            fXml = new File("list.xml");

        }
        catch (Exception ei) {
            System.out.println("Не удалось найти файл");
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(fXml);
        }
        catch (Exception ei) {
            System.out.println("Не удалось прочитать файл");
        }

        return doc;
    }
}
