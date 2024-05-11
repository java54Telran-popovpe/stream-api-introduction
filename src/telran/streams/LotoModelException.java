package telran.streams;

@SuppressWarnings("serial")
class LotoModelException extends Exception {
	public LotoModelException( String argName, int givenValue, String rule ) {
		super( String.format("Wrong argument <%s> = %d. %s", argName, givenValue, rule) );
	}
}
