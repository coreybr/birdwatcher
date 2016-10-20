
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Database db = new Database();
        String input;

        //Print help initially
        printHelp();
        
        //Main input loop
        while (true) {
            System.out.print("? ");
            input = reader.nextLine();
            //Make input case-insensitive
            input = input.trim().toUpperCase();

            if (input.equals("ADD")) {
                System.out.print("Name: ");
                String name = reader.nextLine();
                System.out.print("Latin Name: ");
                String latinName = reader.nextLine();
                db.addBird(new Bird(name, latinName));

            } else if (input.equals("OBSERVATION")) {
                System.out.print("What was observed:? ");
                String name = reader.nextLine();
                if (db.hasByName(name)) {
                    db.observeByName(name);
                } else {
                    System.out.println("Is not a bird!");
                }

            } else if (input.equals("SHOW")) {
                System.out.print("What? ");
                input = reader.nextLine();
                System.out.println(db.showByName(input));

            } else if (input.equals("STATISTICS")) {
                db.printBirds();

            } else if (input.equals("HELP") || input.equals("?")) {
                printHelp();

            } else if (input.equals("QUIT")) {
                break;
            } else {
                System.out.println("Sorry, I didn't quite get that.");
            }

        }
        reader.close();
    }
    
    public static void printHelp(){
    	System.out.println("Add - adds a bird");
        System.out.println("Observation - adds an observation");
        System.out.println("Statistics - prints all the birds");
        System.out.println("Show - prints one bird");
        System.out.println("Quit - terminates the program");
    }

}
