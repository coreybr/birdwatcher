import java.util.Scanner;

public class UI {

	private Scanner reader;
	private Database db;
	private String input;

	public UI() {
		reader = new Scanner(System.in);
		db = new Database();
	}

	public void start() {
		// Print help initially
		printHelp();

		// Main input loop
		while (true) {
			System.out.print("? ");
			input = reader.nextLine();
			// Make input case-insensitive
			input = input.trim().toUpperCase();

			if (input.equals("ADD")) {
				System.out.print("Name: ");
				String name = reader.nextLine();
				System.out.print("Latin Name: ");
				String latinName = reader.nextLine();
				db.addBird(new Bird(name, latinName));

			} else if (input.equals("OBSERVE")) {
				System.out.print("What was observed:? ");
				String name = reader.nextLine();
				if (db.hasByName(name)) {
					db.observeByName(name);
				} else {
					System.out.println("Sorry, I don't recognize that bird.");
				}

			} else if (input.equals("SHOW")) {
				System.out.print("Which bird to show? ");
				input = reader.nextLine();
				System.out.println(db.showByName(input));

			} else if (input.equals("STATS")) {
				db.printBirds();

			} else if (input.equals("HELP") || input.equals("?")) {
				printHelp();

			} else if (input.equals("QUIT")) {
				break;
			} else {
				System.out.println("Sorry, that's not a recognized command.");
			}

		}
		reader.close();
	}

	public static void printHelp() {
		System.out.println("Add - adds a bird");
		System.out.println("Observe - adds an observation");
		System.out.println("Stats - prints all birds");
		System.out.println("Show - prints specified bird");
		System.out.println("Quit - terminates the program");
	}

}
