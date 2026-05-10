package game.io;

public class InvalidMapException extends Exception {
    public InvalidMapException(){
        super("The map data is invalid or incorrect");
    }
}
