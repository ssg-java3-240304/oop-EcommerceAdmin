package exceptions;

public class InputTypeMismatchException extends RuntimeException{
    public InputTypeMismatchException() {
        super();
    }
    public InputTypeMismatchException(String msg){
        super(msg);
    }
}
