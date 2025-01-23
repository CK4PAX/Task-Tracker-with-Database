package TaskTracker;

import java.io.File;
import java.util.List;

public class TaskTracker {
    
    public static boolean validateAmountOfArgsForTwo(String[] args){
        if(ValidateQuantityOfArguments(args, 2) != 0){
            return false;
        }
        return true;
    }
    
    public static boolean validateAmountOfArgsOnUpdate(String[] args){
        if(ValidateQuantityOfArguments(args, 3) != 0){
            return false;
        }
        return true;
    }
    
    public static boolean isIntNArgument(String[] args,int order){
        try {
            Integer.parseInt(args[order]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void showList(List<List<String>> list){
        
        for (List<String> row : list) {
            String sentence = String.format("ID:%s %s",
                    row.get(0),row.get(1));
            System.out.println(sentence);
        }
    }
    
    public static int validateSecondArgOfListTask(String[] args){
        String[] options = {"todo","done","in-progress"};
        for (String option : options) {
            if(option.equals(args[1].toLowerCase())){
                return 0;
            }
        }
        return 1;
    }
    
    public static int validateAmountOfArgsOflistTask (String[] args){
        if((ValidateQuantityOfArguments(args, 1)&
                ValidateQuantityOfArguments(args, 2)) != 1){
            return 0; //pass
        }
        return 1;//not pass
    }
    
    public static boolean validateExistId(int id){
        List<Integer> ids = Database.getAllId();
        if(ids.isEmpty()){
            return false;
        }
        if(ids.contains(id)){
            return true;
        }
        return false;
    }
    
    public static boolean isIntSecondArgument(String[] args){
        try {
            Integer.parseInt(args[1]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int ValidateQuantityOfArguments(String[] args, int n){
        if(args.length != n){
            return 1;
        }
        return 0;
    }

    public static int deleteTask(String[] args){
        // Validations
        if(ValidateQuantityOfArguments(args,2)!= 0){
            System.out.println("Follow this pattern '<delete>' + <id>");
            return 1;
        }
        if(!isIntSecondArgument(args)){
            System.out.println("The second argument(id) has to be a number");
            return 1;
        }
        if(!validateExistId(Integer.parseInt(args[1]))){
            System.out.println("This id does not exist");
            return 1;
        }
        
        Database.deleteTask(Integer.parseInt(args[1]));
        return 0;
    }
    
    public static int addTask(String[] args){
        if(ValidateQuantityOfArguments(args,2)!= 0){
                System.out.println("Follow this pattern '<add>' + <\"task\">");
                return 0;
        };
        int id = Database.addTask(args[1]);
        System.out.printf("Task added successfully. (ID: %d)%n",id);
        return id;
    }

    public static void main(String[] args) {
        
        if (args.length == 0){
            System.out.println("Enter at least one argument!");
            return;
        }
        switch (args[0].toLowerCase()){
            case "add":
                addTask(args);
                break;
            case "delete":
                deleteTask(args);
                break;
            case "list":
                if(validateAmountOfArgsOflistTask(args)!= 0){
                    System.out.println("Follow this pattern <list> or"
                    + " <list> + <\"todo\"|\"done\"|\"in-progress\">");
                    break;
                }
                if(args.length == 2){
                    if(validateSecondArgOfListTask(args)!= 0){
                        System.out.println("Second argument has to be "
                                + "<\"todo\"|\"done\"|\"in-progress\">");
                        break;
                    }
                    List<List<String>> list = Database.getListWithOption(args[1]);
                    if(list.isEmpty()){
                        System.out.println("There are no tasks");
                        break;
                    }
                    showList(list);
                    break;
                }else{
                    List<List<String>> list = Database.getList();
                    if(list.isEmpty()){
                        System.out.println("There are no tasks");
                        break;
                    }
                    showList(list);
                    break;
                }
            case "update":
                if(!validateAmountOfArgsOnUpdate(args)){
                    System.out.println("Follow this pattern <update> +"
                    + "<id> + <\"new task\">");
                    break;
                }
                if (!isIntSecondArgument(args)) {
                    System.out.println("The second argument(id) "
                            + "has to be a number");
                    break;
                }
                if(!validateExistId(Integer.parseInt(args[1]))){
                    System.out.println("This id does not exist");
                    break;
                }
                Database.updateTaskDescription(Integer.parseInt(args[1]), args[2]);
                break;
            case "mark-in-progress":
                if(!validateAmountOfArgsForTwo(args)){
                    System.out.println("Follow this pattern <mark-in-progress> +"
                    + " <id>");
                    break;
                }
                if (!isIntSecondArgument(args)) {
                    System.out.println("The second argument(id) "
                            + "has to be a number");
                    break;
                }
                if(!validateExistId(Integer.parseInt(args[1]))){
                    System.out.println("This id does not exist");
                    break;
                }
                Database.updateTaskStatus(Integer.parseInt(args[1]),"in-progress");
                break;
            case "mark-done":
                if(!validateAmountOfArgsForTwo(args)){
                    System.out.println("Follow this pattern <mark-done> +"
                    + " <id>");
                    break;
                }
                if (!isIntSecondArgument(args)) {
                    System.out.println("The second argument(id) "
                            + "has to be a number");
                    break;
                }
                if(!validateExistId(Integer.parseInt(args[1]))){
                    System.out.println("This id does not exist");
                    break;
                }
                Database.updateTaskStatus(Integer.parseInt(args[1]),"done");
                break;
            default:
                System.out.println("Enter correct arguments");;
        }
    }
}

