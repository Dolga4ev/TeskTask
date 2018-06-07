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
        Task t = new Task();



        do {

            input = scan.nextLine();
            String[] division = input.split("\\s");

            if(input.equals("help")){
                System.out.println("list, new, done, in progress, complete i, add, edit i, remove i, exit");
            }
            if(input.equals("list")){
                t.view();
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
                t.complete(Integer.parseInt(division[1]));
            }
            if(division[0].equals("add")){
                System.out.println("enter the new title");
                input = scan.nextLine();
                String title = input;
                System.out.println("enter the new description");
                input = scan.nextLine();
                String description = input;
                System.out.println("enter the new priority");
                input = scan.nextLine();
                String priority = input;
                System.out.println("enter the new deadline");
                input = scan.nextLine();
                String deadline = input;
                t.add(title, description, priority, deadline);
            }
            if(division[0].equals("edit")){
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
                t.edit(Integer.parseInt(division[1]), title, description, priority, deadline);
            }
            if(division[0].equals("remove")){
                t.remove(Integer.parseInt(division[1]));
            }
            if(input.equals("exit")){
                System.out.println("see you soon");
            }

        } while (!input.equals("exit")); // "exit" останавливает ввод

    }
}
