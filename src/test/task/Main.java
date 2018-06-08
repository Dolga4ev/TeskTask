package test.task2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scan = new Scanner(System.in);
        String input;
        Xml file = new Xml();
        ArrayList tasks = file.read();
        Operation op = new Operation();

        System.out.println("?help?");
        do {

            input = scan.nextLine();
            String[] division = input.split("\\s");

            if(input.equals("help")){
                System.out.println("list, new, done, in progress, complete i, add, edit i, remove i, exit");
            }
            if(input.equals("list")){
                op.view("all");
            }
            if(input.equals("new")){
                op.view("new");
            }
            if(input.equals("done")){
                op.view("done");
            }
            if(input.equals("in progress")){
                op.view("in progress");
            }

            //if(division[0].equals("complete")){
            if(division[0].equals("complete")){
                try {
                    op.complete(Integer.parseInt(division[1]));
                }
                catch (Exception ei) {
                    System.out.println("Вы не ввели номер элемента");
                }
            }
            if(input.equals("add")){
                op.add();
            }
            if(division[0].equals("edit")){
                try {
                op.edit(Integer.parseInt(division[1]));
                }
                catch (Exception ei) {
                    System.out.println("Вы не ввели номер элемента");
                }
            }
            if(division[0].equals("remove")){
                try {
                op.remove(Integer.parseInt(division[1]));
                }
                catch (Exception ei) {
                    System.out.println("Вы не ввели номер элемента");
                }
            }
            if(input.equals("exit")){
                System.out.println("see you soon");
            }

        } while (!input.equals("exit")); // "exit" останавливает ввод

    }
}

