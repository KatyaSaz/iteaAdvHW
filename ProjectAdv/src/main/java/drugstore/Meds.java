package drugstore;

public class Meds {
	private String name;
	private int time;
	
	public Meds() {
		super();
	}

	public Meds(String name, int time) {
		super();
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Meds [name=" + name + ", time=" + time + "]";
	}
	
}
