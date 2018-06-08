package test.task2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class Xml {
    File fXml = null;
    Document doc;

    public Xml() {
        try {
            fXml = new File("list.xml");

        }
        catch (Exception ei) {
            System.out.println("Не удалось найти файл");
            System.exit(0);
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(fXml);
            doc.getDocumentElement().normalize();
        }
        catch (Exception ei) {
            System.out.println("Не удалось прочитать файл");
            System.exit(0);
        }
    }

    public ArrayList read(){
        ArrayList<Task> taskList = new ArrayList<>();
        NodeList tasks = null;
        try {
        tasks = doc.getElementsByTagName("Task");
        }
        catch (Exception ei) {
            System.out.println("Не удалось найти теги 'Task'");
            System.exit(0);
        }

        for(int i = 0; i < tasks.getLength(); i++){
            //System.out.println(i);

            Element currTask = (Element) tasks.item(i);

            Task newTask = new Task();
            //if (currTask.getNodeType() == Node.ELEMENT_NODE) {

                //String id = Integer.toString(i);
                //String title = "qwerty";

                String id = currTask.getAttributes().getNamedItem("id").getNodeValue();
                String title = currTask.getAttributes().getNamedItem("caption").getNodeValue();
                String description = currTask.getElementsByTagName("Description").item(0).getTextContent();
                String priority = currTask.getElementsByTagName("Priority").item(0).getTextContent();
                String deadline = currTask.getElementsByTagName("Deadline").item(0).getTextContent();
                String status = currTask.getElementsByTagName("Status").item(0).getTextContent();
                String complete = currTask.getElementsByTagName("Complete").item(0).getTextContent();
                newTask.setId(id);
                newTask.setTitle(title);
                newTask.setDescription(description);
                newTask.setPriority(priority);
                newTask.setDeadline(deadline);
                newTask.setStatus(status);
                newTask.setComplete(complete);
                taskList.add(newTask);
        //}
        }
        return taskList;

    }
    public void wtite(ArrayList taskList, int flag){
        NodeList tasks = doc.getElementsByTagName("ToDoList");
        NodeList removeTasks = doc.getElementsByTagName("Task");
        int n = removeTasks.getLength() - 1;

        for (int i = n; i >= 0; i--){
            tasks.item(0).removeChild(removeTasks.item(i));

        }
        for(int i = 0; i < taskList.size(); i++){
            Element newTask = doc.createElement("Task");
            Task task = (Task) taskList.get(i);
            newTask.setAttribute("caption", task.getTitle());
            newTask.setAttribute("id", task.getId());
            Element newDescriprion = (Element) doc.createElement("Description");
            newDescriprion.setTextContent(task.getDescription());
            Element newPriority = doc.createElement("Priority");
            newPriority.setTextContent(task.getPriority());
            Element newDeadline =  doc.createElement("Deadline");
            newDeadline.setTextContent(task.getDeadline());
            Element newStatus = doc.createElement("Status");
            newStatus.setTextContent(task.getStatus());
            Element newComplete = doc.createElement("Complete");
            newComplete.setTextContent(task.getComplete());
            newTask.appendChild(newDescriprion);
            newTask.appendChild(newPriority);
            newTask.appendChild(newDeadline);
            newTask.appendChild(newStatus);
            newTask.appendChild(newComplete);
            tasks.item(0).appendChild(newTask);


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
