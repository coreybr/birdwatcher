
import java.util.ArrayList;

public class Database {

    private ArrayList<Bird> birds = new ArrayList<Bird>();

    public Database() {
    }

    public void addBird(Bird newBird) {
        birds.add(newBird);
    }

    public void printBirds() {
        for (Bird bird : birds) {
            System.out.println(bird);
        }
    }

    public boolean hasByName(String name) {
        ArrayList<Bird> result = new ArrayList<Bird>();
        for (Bird bird : birds) {
            if (StringUtils.included(bird.getName(), name)) {
                return true;
            }
        }
        return false;
    }

    public void observeByName(String name) {
        ArrayList<Bird> result = new ArrayList<Bird>();
        for (Bird bird : birds) {
            if (StringUtils.included(bird.getName(), name)) {
                bird.observe();
            }
        }
    }

    public String showByName(String name) {
        ArrayList<Bird> result = new ArrayList<Bird>();
        for (Bird bird : birds) {
            if (StringUtils.included(bird.getName(), name)) {
                return bird.toString();
            }
        }
        return "Is not a bird!";
    }

}
