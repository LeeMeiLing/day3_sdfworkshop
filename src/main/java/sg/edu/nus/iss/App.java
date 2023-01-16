package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

    public static void main(String[] args) throws IOException{

        String dirPath = ".\\data2";
        String fileName = "";

        // instantiating a file/directory object
        File newDirectory = new File (dirPath);

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
                    cartItems = readCartItemsFromFile(dirPath, fileName);
                    listCart(cartItems);
                    break;

                case "users":
                    listUsers(dirPath);
                    break;

                default:
                    break;
            }

            String strValue = "";

            if (input.startsWith("add")) {

                input = input.replace(",", " ");

                Scanner scanner = new Scanner(input.substring(4));
                FileWriter fw = new FileWriter(dirPath + File.separator + fileName,true);
                PrintWriter pw = new PrintWriter(fw);

                while (scanner.hasNext()) {
                    strValue = scanner.next();
                    cartItems.add(strValue);

                    pw.printf("%s\n",strValue);
                    pw.flush();
                    fw.flush();
                }

                pw.close();
                fw.close();

            }

            if (input.startsWith("delete")) {
                cartItems = deleteCartItem(cartItems, input);
                updateCartItemToFIle(cartItems, dirPath, fileName);
            }

            if(input.startsWith("login")){
                input = input.replace(",", " ");
                Scanner scanner = new Scanner(input.substring(6));
                while(scanner.hasNext()){
                    fileName = scanner.next();
                }

                File loginFile = new File (dirPath + File.separator + fileName);

                boolean isCreated = loginFile.createNewFile();

                if (isCreated){
                    displayMessage("New file created successfully" + loginFile.getCanonicalFile());

                }else{
                    displayMessage("File already created");
                }

                fileName = loginFile.getName();  // try get Path if does work


            }

        }

    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void listCart(List<String> cartItems) {
        if (cartItems.size() > 0) {
            // for(String item: cartItems){
            // System.out.printf(item + "\n");
            // }
            for (int i = 0; i < cartItems.size(); i++) {
                System.out.printf("%d: %s\n", i + 1, cartItems.get(i));
            }

        } else {
            displayMessage("Your cart is empty");
        }
    }

    public static List<String> readCartItemsFromFile(String dirPath, String fileName) throws IOException{

        List<String> items = new ArrayList<>();
        File file = new File (dirPath + File.separator + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sr;
        while((sr = br.readLine())!= null){
            items.add(sr);
        }
        br.close();

        return items;
    }

    public static List<String> deleteCartItem(List<String> cartItems, String input) {

        String strValue = "";
        Scanner scanner = new Scanner(input.substring(6));
        while (scanner.hasNext()) {
            strValue = scanner.next();
            int listItemIndex = Integer.parseInt(strValue);

            if (listItemIndex < cartItems.size()) {
                cartItems.remove(listItemIndex - 1);
            } else {
                displayMessage("Incorrect item index");
            }
        }
        return cartItems;
    }

    public static void updateCartItemToFIle(List<String> cartItems, String dirPath, String fileName) throws IOException{
        FileWriter fw = new FileWriter(dirPath + File.separator + fileName, false);
        BufferedWriter bw = new BufferedWriter(fw);

        int listCount = 0;
        while(listCount < cartItems.size()){
            bw.write(cartItems.get(listCount));
            bw.newLine();
            listCount++;
        }

        bw.flush();
        fw.flush();
        bw.close();
        fw.close();
    }

    public static void listUsers(String dirPath) {
        File directoryPath = new File(dirPath);
        String[] contents = directoryPath.list();
        for(String file:contents){
            displayMessage(file);
        }
    }

}
