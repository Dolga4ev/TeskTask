package test.task2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Operation {
    Scanner scan = new Scanner(System.in);
    String input;
    Task task = new Task();
    Xml file = new Xml();
    Xml writer = new Xml();

    public void view(String NewDoneInProgress){
        Xml file = new Xml();
        ArrayList taskList = file.read();
        for (int i = 0; i < taskList.size(); i++){
            if(NewDoneInProgress.equals("all")) {
                task = (Task) taskList.get(i);
                System.out.println("Task " + task.getId() + " (" + task.getTitle() + "): "
                        + task.getDescription() + ", "
                        + task.getPriority() + ", "
                        + task.getDeadline() + ", "
                        + task.getStatus() + ", "
                        + task.getComplete());
            }
            else{
                task = (Task) taskList.get(i);
                if (task.getStatus().equals(NewDoneInProgress)){

                    System.out.println("Task " + task.getId() + " (" + task.getTitle() + "): "
                            + task.getDescription() + ", "
                            + task.getPriority() + ", "
                            + task.getDeadline() + ", "
                            + task.getStatus() + ", "
                            + task.getComplete());
                }
            }
        }
    }
    public void complete(int index){
        Xml file = new Xml();
        ArrayList taskList = file.read();
        Task task = new Task();
        for(int i = 0; i < taskList.size(); i++){
            task = (Task) taskList.get(i);
            if(task.getId().equals(Integer.toString(index))){
                task.setStatus("done");
                task.setComplete(LocalDateTime.now().toLocalDate().toString());
            }
        }

        writer.wtite(taskList, 2);
    }
    public void add(){
        Xml file = new Xml();
        ArrayList taskList = file.read();
        task = (Task) taskList.get(taskList.size() - 1);
        int lastIndex =  Integer.parseInt(task.getId()) + 1;
        task = new Task();

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

        task.setId(Integer.toString(lastIndex));
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setDeadline(deadline);
        task.setStatus("new");
        task.setComplete("-");

        taskList.add(task);


        writer.wtite(taskList, 1);
    }
    public void edit(int index){
        Xml file = new Xml();
        ArrayList taskList = file.read();
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
        for(int i = 0; i < taskList.size(); i++){
            task = (Task) taskList.get(i);
            if(task.getId().equals(Integer.toString(index))){
                if(!title.equals("-")){
                    task.setTitle(title);
                }
                if(!description.equals("-")){
                    task.setDescription(description);
                }
                if(!priority.equals("-")){
                    task.setPriority(priority);
                }
                if(!deadline.equals("-")){
                    task.setDeadline(deadline);
                }

            }
        }

        writer.wtite(taskList, 2);
    }
    public void remove(int index){
        Xml file = new Xml();
        ArrayList taskList = file.read();
        for(int i = 0; i < taskList.size(); i++){
            task = (Task) taskList.get(i);
            if (task.getId().equals(Integer.toString(index))){
                taskList.remove(task);
            }


        }
        writer.wtite(taskList, 3);

    }
}
