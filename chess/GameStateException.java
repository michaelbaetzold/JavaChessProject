package chess;
public class GameStateException extends Exception{

    public GameStateException(String message) {
        super("Invalid Gamestate: "+ message);
    }
    
}
