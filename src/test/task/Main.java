package test.task;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

    public static void main(String[] args) {
        // write your code here


        Scanner scan = new Scanner(System.in);
        String input;
        System.out.println("?help?");

        do {
            input = scan.nextLine();
            String[] division = input.split("\\s");

            if(input.equals("help")){
                System.out.println("list, new, done, in progress, complete i, add, edit i, remove i, exit");
            }
            if(input.equals("list")){
                ViewFile.viewList("all");
            }
            if(input.equals("new")){
                ViewFile.viewList(input);
            }
            if(input.equals("done")){
                ViewFile.viewList(input);
            }
            if(input.equals("in progress")){
                ViewFile.viewList("in_progress");
            }
            if(division[0].equals("complete")){
                ViewFile.complete(Integer.parseInt(division[1]));
            }
            if(division[0].equals("add")){
                ViewFile.New();
            }
            if(division[0].equals("edit")){
                ViewFile.Edit(Integer.parseInt(division[1]));
            }
            if(division[0].equals("remove")){
                ViewFile.Remove(Integer.parseInt(division[1]));
            }
            if(input.equals("exit")){
                System.out.println("see you soon");
            }

        } while (!input.equals("exit")); // "exit" останавливает ввод

    }
}
