package Ver01;

public class NullInputException extends Exception {
	
	public NullInputException() {
		super();
	}

	@Override
	public String toString() {
		return "NullInputException, getMessage() ="+getMessage() +"]";
	}

}
