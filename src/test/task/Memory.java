package test.task;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.soap.Node;

public class Memory {

    NodeList taskList;
    Writer file = new Writer();
    Reader fileOpen = new Reader();


    public Memory(){
        this.taskList = fileOpen.read();
    }

    public void set(NodeList taskList){
        this.taskList = taskList;
    }

    public NodeList get(){
        return this.taskList;
    }

    public void setTask(Element task, int i){
        int pointOfEditTask = findTask(i);
        Element taskI = (Element) taskList.item(pointOfEditTask);
        taskI.setAttribute("id", task.getAttributes().getNamedItem("id").getNodeValue());
        taskI.getElementsByTagName("Description").item(0).setNodeValue(task.getElementsByTagName("Description").item(0).getTextContent());
        taskI.getElementsByTagName("Priority").item(0).setNodeValue(task.getElementsByTagName("Priority").item(0).getTextContent());
        taskI.getElementsByTagName("Deadline").item(0).setNodeValue(task.getElementsByTagName("Deadline").item(0).getTextContent());
        taskI.getElementsByTagName("Status").item(0).setNodeValue(task.getElementsByTagName("Status").item(0).getTextContent());
        taskI.getElementsByTagName("Complete").item(0).setNodeValue(task.getElementsByTagName("Complete").item(0).getTextContent());
        //file.write(taskList);

    }
    public Element getTask(int i) {
        int pointOfEditTask = findTask(i);
        //System.out.println("Tasks");
        Element taskI = (Element) taskList.item(pointOfEditTask);
        return taskI;
    }

    int findTask(int i){
        int pointOfEditTask = 0;
        for (int iter = 0; iter < taskList.getLength(); iter++) {
            if (taskList.item(iter).getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(i))) {
                pointOfEditTask = iter;
            }
        }
        return pointOfEditTask;
    }

}
