
public class Bird {

    private String name;
    private String latinName;
    private int observations;

    public Bird(String name, String latinName) {
        this.name = name;
        this.latinName = latinName;
        this.observations = 0;
    }

    public String getName() {
        return name;
    }

    public void observe() {
        observations++;
    }

    @Override
    public String toString() {
        if (observations == 1) {
            return name + " (" + latinName + "): " + observations + " observation";
        } else {
            return name + " (" + latinName + "): " + observations + " observations";
        }
    }

}
