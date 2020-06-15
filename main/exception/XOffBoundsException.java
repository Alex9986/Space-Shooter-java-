package exception;

public class XOffBoundsException extends OutOfBoundsException {
    @Override
    public void showMessage() {
        System.out.println("The ship is out of bound on x-axis");
    }
}
