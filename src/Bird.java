import java.io.Serializable;

public class Bird implements Serializable {

	private static final long serialVersionUID = 1L;
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
		this.observations++;
		if (observations == 1) {
			System.out.println("Observed " + this.name + " " + this.observations + " time.");
		} else {
			System.out.println("Observed " + this.name + " " + this.observations + " times.");
		}
	}

	@Override
	public String toString() {
		if (observations == 1) {
			return this.name + " (" + latinName + "): " + this.observations + " observation";
		} else {
			return this.name + " (" + latinName + "): " + this.observations + " observations";
		}
	}

}
