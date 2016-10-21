
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Database {

	private ArrayList<Bird> birds;
	private String filePath = "birds.ser";

	public Database() {
		loadBirds();
	}

	/*
	 * Load List of birds from file. Create new list if file not found.
	 */
	private void loadBirds() {
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			try {
				FileInputStream fis = new FileInputStream(filePath);
				ObjectInputStream in = new ObjectInputStream(fis);
				this.birds = (ArrayList<Bird>) in.readObject();
				in.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			birds = new ArrayList<Bird>();
		}
	}

	/*
	 * Persist list of birds to file.
	 */
	private void saveBirds() {
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(filePath, false);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(birds);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void addBird(Bird newBird) {
		birds.add(newBird);
		saveBirds();
	}

	public void printBirds() {
		for (Bird bird : birds) {
			System.out.println(bird);
		}
		if (birds.isEmpty()) {
			System.out.println("No birds recorded.");
		}
	}

	public boolean hasByName(String name) {
		for (Bird bird : birds) {
			if (StringUtils.included(bird.getName(), name)) {
				return true;
			}
		}
		return false;
	}

	public void observeByName(String name) {
		for (Bird bird : birds) {
			if (StringUtils.included(bird.getName(), name)) {
				bird.observe();
				saveBirds();
			}
		}
	}

	public String showByName(String name) {
		for (Bird bird : birds) {
			if (StringUtils.included(bird.getName(), name)) {
				return bird.toString();
			}
		}
		return "Is not a bird!";
	}

}
