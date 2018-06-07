package test.task;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.soap.Node;
import java.time.LocalDateTime;

public class Task implements Operations{
    Node task;
    Memory memory = new Memory();
    Reader openFile = new Reader();
    Writer file = new Writer();
    Document doc = openFile.getDoc();


    public Task(){}

    public void set(Node task){
        this.task = task;
    }
    public Node get(){
        return this.task;
    }

    @Override
    public void view() {

        NodeList taskList = memory.get();
        System.out.println(taskList.getLength());
        for (int je = 0; je < taskList.getLength(); je++) {
            org.w3c.dom.Node fstNode = taskList.item(je);
            if (fstNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
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
                System.out.println(
                        "Task " + taskList.item(je).getAttributes().getNamedItem("id").getNodeValue()
                                + " ("
                                + taskList.item(je).getAttributes().getNamedItem("caption").getNodeValue()
                                + ") : "
                                + ((org.w3c.dom.Node) nodeElListDes.item(0)).getNodeValue() + ", "
                                + ((org.w3c.dom.Node) nodeElListPrior.item(0)).getNodeValue() + ", "
                                + ((org.w3c.dom.Node) nodeElListDLine.item(0)).getNodeValue() + ", "
                                + ((org.w3c.dom.Node) nodeElListStatus.item(0)).getNodeValue() + ", "
                                + ((org.w3c.dom.Node) nodeElListComp.item(0)).getNodeValue()
                );

            }
        }
    }

    @Override
    public void complete(int i) {


        Element task = memory.getTask(i);
        // Element lang = (Element) taskList.item(i);
        org.w3c.dom.Node status = task.getElementsByTagName("Status").item(0).getFirstChild();
        status.setNodeValue("complete");
        org.w3c.dom.Node complete = task.getElementsByTagName("Complete").item(0).getFirstChild();
        complete.setNodeValue(LocalDateTime.now().toLocalDate().toString());
        //System.out.println(((Element) .item(i)).getElementsByTagName("Status").item(0).getTextContent());
        memory.setTask(task, i);

    }
    public void write() {
        NodeList taskList = memory.get();
        file.write(taskList);
    }

    @Override
    public void edit(int i, String title, String description, String priority, String deadline) {
        Element taskEdit = (Element) memory.getTask(i);

        taskEdit.setAttribute("caption", title);
        if(!description.equals("-")) {
            Element newDescriprion = (Element) taskEdit.getElementsByTagName("Description").item(0);
            newDescriprion.setTextContent(description);
        }
        if(!priority.equals("-")) {
            Element newPriority = (Element) taskEdit.getElementsByTagName("Priority").item(0);
            newPriority.setTextContent(priority);
        }
        if(!deadline.equals("-")) {
            Element newDeadline = (Element) taskEdit.getElementsByTagName("Deadline").item(0);
            newDeadline.setTextContent(deadline);
        }
        System.out.println("Изменения сохранены");

    }

    @Override
    public void add(String title, String description, String priority, String deadline) {

        NodeList taskList = memory.get();
        NodeList toDoList = doc.getElementsByTagName("ToDoList");
        Element task = (Element) toDoList.item(0);
        Element newTask = (Element) doc.createElement("Task");

        ((Element) newTask).setAttribute("caption", title);


        Element newDescriprion = newDescriprion = (Element) doc.createElement("Description");
        newDescriprion.setTextContent(description);
        Element newPriority = (Element) doc.createElement("Priority");
        newPriority.setTextContent(priority);
        Element newDeadline = (Element) doc.createElement("Deadline");
        newDeadline.setTextContent(deadline);
        Element newStatus = (Element) doc.createElement("Status");
        newStatus.setTextContent("new");
        Element newComplete = (Element) doc.createElement("Complete");
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
        taskList = (NodeList) task;
        memory.set(taskList);

    }

    @Override
    public void remove(int i) {
        NodeList toDoList = doc.getElementsByTagName("ToDoList");
        NodeList taskList = memory.get();
        Node removeNode = (Node) memory.getTask(i);

        //toDoList.item(0).removeChild(taskList.item(i));
        try {
            toDoList.item(0).removeChild(removeNode);
        }
        catch(Exception ei){System.out.println("error");}

        memory.set(toDoList.item(0).getChildNodes());


    }
}
