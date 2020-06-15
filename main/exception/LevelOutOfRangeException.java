package exception;

public class LevelOutOfRangeException extends InvalidInputException {

    @Override
    public void showMessage() {
        System.out.println("The level you just entered is not valid.");
    }
}
