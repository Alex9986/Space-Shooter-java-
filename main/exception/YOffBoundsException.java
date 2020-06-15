package exception;

public class YOffBoundsException extends OutOfBoundsException {
    @Override
    public void showMessage() {
        System.out.println("The ship is out of bound on y-axis");
    }
}
