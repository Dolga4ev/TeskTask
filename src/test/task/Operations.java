package test.task;

import org.w3c.dom.NodeList;

import javax.xml.soap.Node;

public interface Operations {
    void view();
    void complete(int i);
    void edit(int i, String title, String description, String priority, String deadline);
    void add(String title, String description, String priority, String deadline);
    void remove(int i);

}
