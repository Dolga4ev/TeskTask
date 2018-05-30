package test.task;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ViewFile {
    public ViewFile(){}

    public static void viewList(String NewDoneInProgress){
        try {
            File fXml = new File("list.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fXml);

            doc.getDocumentElement().normalize();
            System.out.println(doc.getDocumentElement().getNodeName());

            NodeList taskList = doc.getElementsByTagName("Task");

            //System.out.println("Tasks");
            for (int je = 0; je < taskList.getLength(); je++) {
                Node fstNode = taskList.item(je);
                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element des = (Element) fstNode;

                    NodeList listDes = des.getElementsByTagName("Description");
                    Element elListDes = (Element) listDes.item(0);
                    NodeList nodeElListDes = elListDes.getChildNodes();

                    NodeList listPrior = des.getElementsByTagName("Priority");
                    Element elListPrior = (Element) listPrior.item(0);
                    NodeList nodeElListPrior = elListPrior.getChildNodes();

                    NodeList listDLine = des.getElementsByTagName("Deadline");
                    Element elListDLine = (Element) listDLine.item(0);
                    NodeList nodeElListDLine = elListDLine.getChildNodes();

                    NodeList listStatus = des.getElementsByTagName("Status");
                    Element elListStatus = (Element) listStatus.item(0);
                    NodeList nodeElListStatus = elListStatus.getChildNodes();

                    NodeList listComp = des.getElementsByTagName("Complete");
                    Element elListComp = (Element) listComp.item(0);
                    NodeList nodeElListComp = elListComp.getChildNodes();

                    if(!NewDoneInProgress.equals("all")){
                        if(nodeElListStatus.item(0).getNodeValue().equals(NewDoneInProgress)) {
                            System.out.println(
                                    "Task " + taskList.item(je).getAttributes().getNamedItem("id").getNodeValue()
                                            + " ("
                                            + taskList.item(je).getAttributes().getNamedItem("caption").getNodeValue()
                                            + ") : "
                                            + ((Node) nodeElListDes.item(0)).getNodeValue() + ", "
                                            + ((Node) nodeElListPrior.item(0)).getNodeValue() + ", "
                                            + ((Node) nodeElListDLine.item(0)).getNodeValue() + ", "
                                            + ((Node) nodeElListStatus.item(0)).getNodeValue() + ", "
                                            + ((Node) nodeElListComp.item(0)).getNodeValue()
                            );
                        }
                    }
                    else{
                        System.out.println(
                                "Task " + taskList.item(je).getAttributes().getNamedItem("id").getNodeValue()
                                        + " ("
                                        + taskList.item(je).getAttributes().getNamedItem("caption").getNodeValue()
                                        + ") : "
                                        + ((Node) nodeElListDes.item(0)).getNodeValue() + ", "
                                        + ((Node) nodeElListPrior.item(0)).getNodeValue() + ", "
                                        + ((Node) nodeElListDLine.item(0)).getNodeValue() + ", "
                                        + ((Node) nodeElListStatus.item(0)).getNodeValue() + ", "
                                        + ((Node) nodeElListComp.item(0)).getNodeValue()
                        );
                    }
                }
            }
        }
        catch(Exception ei){}
    }


    public static void complete(int i){
        try {
            File fXml = new File("list.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fXml);

            doc.getDocumentElement().normalize();

            int pointOfEditTask = 0;
            NodeList tasks = doc.getElementsByTagName("Task");
            for(int iter = 0; iter < tasks.getLength(); iter++){
                if(tasks.item(iter).getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(i))){
                    pointOfEditTask = iter;
                }
            }
            NodeList taskList = doc.getElementsByTagName("Task");

            //System.out.println("Tasks");
            Element lang = (Element) taskList.item(pointOfEditTask);
            Node status = lang.getElementsByTagName("Status").item(0).getFirstChild();
            status.setNodeValue("complete");
            Node complete = lang.getElementsByTagName("Complete").item(0).getFirstChild();
            complete.setNodeValue(LocalDateTime.now().toLocalDate().toString());

            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("list.xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("Task " + i + " is complete");
        }
        catch(Exception ei){}
    }

    public static void New(){
        try {
            Scanner scan = new Scanner(System.in);
            String input;
            System.out.println("enter the title");
            input = scan.nextLine();
            String title = input;
            System.out.println("enter the description");
            input = scan.nextLine();
            String description = input;
            System.out.println("enter the priority");
            input = scan.nextLine();
            String priority = input;
            System.out.println("enter the deadline");
            input = scan.nextLine();
            String deadline = input;

            File fXml = new File("list.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fXml);

            NodeList toDoList = doc.getElementsByTagName("ToDoList");
            Element task = (Element) toDoList.item(0);
            Node newTask = doc.createElement("Task");
            ((Element) newTask).setAttribute("caption", title);

            Node newDescriprion = doc.createElement("Description");
            newDescriprion.setTextContent(description);
            Node newPriority = doc.createElement("Priority");
            newPriority.setTextContent(priority);
            Node newDeadline = doc.createElement("Deadline");
            newDeadline.setTextContent(deadline);
            Node newStatus = doc.createElement("Status");
            newStatus.setTextContent("new");
            Node newComplete = doc.createElement("Complete");
            newComplete.setTextContent("-");

            newTask.appendChild(newDescriprion);
            newTask.appendChild(newPriority);
            newTask.appendChild(newDeadline);
            newTask.appendChild(newStatus);
            newTask.appendChild(newComplete);

            NodeList list = doc.getElementsByTagName("Task");
            int index = Integer.parseInt(list.item(list.getLength() - 1).getAttributes().getNamedItem("id").getNodeValue());
            ((Element) newTask).setAttribute("id", String.valueOf(index + 1));
            task.appendChild(newTask);



            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("list.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("New task successfully added");

        }
        catch(Exception ei){}

    }
    public static void Edit(int i){
        try {
            Scanner scan = new Scanner(System.in);
            String input;
            System.out.println("enter the title (if you don't want to change, write '-')");
            input = scan.nextLine();
            String title = input;

            System.out.println("enter the description (if you don't want to change, write '-')");
            input = scan.nextLine();
            String description = input;
            System.out.println("enter the priority (if you don't want to change, write '-')");
            input = scan.nextLine();
            String priority = input;
            System.out.println("enter the deadline (if you don't want to change, write '-')");
            input = scan.nextLine();
            String deadline = input;

            File fXml = new File("list.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fXml);
            int pointOfEditTask = 0;
            NodeList tasks = doc.getElementsByTagName("Task");
            for(int iter = 0; iter < tasks.getLength(); iter++){
                if(tasks.item(iter).getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(i))){
                    pointOfEditTask = iter;
                }
            }
            NodeList toDoList = doc.getElementsByTagName("ToDoList");
            Node task = doc.getElementsByTagName("Task").item(pointOfEditTask);
            NamedNodeMap attributes = task.getAttributes();
            if(!title.equals("-")) {
                Node id = attributes.getNamedItem("caption");
                id.setTextContent(title);
            }
            if(!description.equals("-")) {
                Node newDescriprion = doc.getElementsByTagName("Description").item(pointOfEditTask);
                newDescriprion.setTextContent(description);
            }
            if(!priority.equals("-")) {
                Node newPriority = doc.getElementsByTagName("Priority").item(pointOfEditTask);
                newPriority.setTextContent(priority);
            }
            if(!deadline.equals("-")) {
                Node newDeadline = doc.getElementsByTagName("Deadline").item(pointOfEditTask);
                newDeadline.setTextContent(deadline);
            }

            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("list.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("New task successfully added");

        }
        catch(Exception ei){}

    }
    public static void Remove(int i){
        try {
            File fXml = new File("list.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fXml);

            doc.getDocumentElement().normalize();

            NodeList taskList = doc.getElementsByTagName("ToDoList");
            NodeList tasks = doc.getElementsByTagName("Task");

            //int iter = 0;

            for(int iter = 0; iter < tasks.getLength(); iter++){
                if(tasks.item(iter).getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(i))){
                    taskList.item(0).removeChild(tasks.item(iter));
                }
            }

            //System.out.println(task);
            //taskList.item(0).removeChild(tasks.item(iter));

            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("list.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("Task " + i + " is deleted successfully");
        }
        catch(Exception ei){}
    }
}
