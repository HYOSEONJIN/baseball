package Ver01;

public class BadMenuException extends Exception {
	
	private int select;

	public BadMenuException(int select) {
		super();
		this.select = select;
	}

	@Override
	public String toString() {
		return "BadMenuException [select=" + select + ", getMessage() ="+getMessage() +"]";
	}

}
