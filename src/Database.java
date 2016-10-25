
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Database {

	ArrayList<Bird> birds;
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

	/*
	 * Deletes existing serialization file.
	 */
	public void deleteBirds() {
		birds.clear();
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
	}

	public void addBird(Bird newBird) {
		birds.add(newBird);
		saveBirds();
	}

	public void removeBirdByName(String name) {
		birds.remove(findByName(name));
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

	public Bird findByName(String name) {
		for (Bird bird : birds) {
			if (StringUtils.included(bird.getName(), name)) {
				return bird;
			}
		}
		return null;
	}

	public boolean hasByName(String name) {
		return findByName(name) != null;
	}

	public void observeByName(String name) {
		findByName(name).observe();
		saveBirds();
	}

	public String showByName(String name) {
		Bird bird = findByName(name);
		if (bird != null) {
			return bird.toString();
		} else {
			return "Sorry, I don't recognize that bird.";
		}
	}

}
