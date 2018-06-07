package test.task;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Writer {

    File fXml;
    Document doc;

    public Writer() {
        try {
            fXml = new File("list.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fXml);
        }
        catch (Exception ei) {
            System.out.println("Не удалось найти файл");
        }
    }


    public void write(NodeList taskList){

        int n = 0;
        NodeList tl = null;
        try {

            tl = doc.getElementsByTagName("ToDoList");
            System.out.println(tl.getLength());
        }
        catch (Exception ei) {
            System.out.println("Не удалось");
        }
        //tl = doc.getElementsByTagName("ToDoList");
        n = taskList.getLength();
        for(int i = 0; i < n; i++) {
            System.out.println("d");
            Node newTask = doc.createElement("Task");
            tl.item(0).appendChild(newTask);
            ((Element) newTask).setAttribute("id", Integer.toString(i));
            Node paradigmElement1 = doc.createElement("Description");
            paradigmElement1.setTextContent(((Element) taskList.item(i)).getElementsByTagName("Description").item(0).getTextContent());
            Node paradigmElement2 = doc.createElement("Priority");
            paradigmElement2.setTextContent(((Element) taskList.item(i)).getElementsByTagName("Priority").item(0).getTextContent());
            Node paradigmElement3 = doc.createElement("Deadline");
            paradigmElement3.setTextContent(((Element) taskList.item(i)).getElementsByTagName("Deadline").item(0).getTextContent());
            Node paradigmElement4 = doc.createElement("Status");
            paradigmElement4.setTextContent(((Element) taskList.item(i)).getElementsByTagName("Status").item(0).getTextContent());
            Node paradigmElement5 = doc.createElement("Complete");
            paradigmElement5.setTextContent(((Element) taskList.item(i)).getElementsByTagName("Complete").item(0).getTextContent());
            newTask.appendChild(paradigmElement1);
            newTask.appendChild(paradigmElement2);
            newTask.appendChild(paradigmElement3);
            newTask.appendChild(paradigmElement4);
            newTask.appendChild(paradigmElement5);

        }
        try{
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("list.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML успешно изменен!");
        }
        catch(Exception ei){System.out.println("Не удалось изменить файл");}
    }
}