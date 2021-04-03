package exceptions;

public class MissAuthenticationException extends Exception {
	public MissAuthenticationException(){
		super("You have to complete the authentication before sending message");
	}
}
