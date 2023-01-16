package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.directory.DirContext;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */

    public static void main(String[] args) {

        String dirPath = "\\data2";
        String fileName = "";

        File newDirectory = new File(dirPath);
        if (newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        }

        System.out.println("Welcome to my shopping cart");

        List<String> cartItems = new ArrayList<>();

        Console con = System.console();
        String input = "";

        while (!input.equals("quit")) {

            input = con.readLine("What do you want to perform? (Type 'quit' to exit program)\n");

            switch (input) {
                case "help": {
                    displayMessage("'list' to show a list of items in shopping cart");
                    displayMessage("'login <name>' to access your shopping cart");
                    displayMessage("'add <item>' to add items into your shopping cart");
                    displayMessage("'delete <item #>' to delete an item from shopping cart");
                    displayMessage("'quit' to exit this program");
                    break;
                }

                case "list":
                    if(cartItems.size() > 0){
                        for(String item: cartItems){
                            System.out.printf(item + "\n");
                        }
                    }else{
                        displayMessage("Your cart is empty");
                    }
                    break;

                case "users":
                    break;

                default:
                    break;
            }

            if (input.startsWith("add")) {

                input = input.replace(",", " ");
                String strValue = "";
                Scanner scanner = new Scanner(input.substring(4));
                while(scanner.hasNext()){
                    strValue = scanner.next();
                    cartItems.add(strValue);
                }
            }


        }

    }


    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
