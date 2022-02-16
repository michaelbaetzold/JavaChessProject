package chess;
import java.util.ArrayList;

public class King extends ChessPiece {

    private boolean hasMoved = true;

    public King(ColorEnum color, ChessBoard board) {
        super(color, board);
    }

    public void setCastlingRights() {
        this.hasMoved = false;
    }

    @Override
    public ArrayList<Position> getAllPossibleMoves() throws InvalidMoveException, GameStateException{
        ArrayList<Position> result = new ArrayList<>();

        Position tempPosition = new Position(this.position);
        tempPosition.file++;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file++;
        tempPosition.rank++;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.rank++;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file++;
        tempPosition.rank--;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file--;
        tempPosition.rank++;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file--;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file--;
        tempPosition.rank--;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.rank--;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        return result;
    }

    @Override
    protected boolean isLegalMove(Position newPosition) throws InvalidMoveException, GameStateException {
        
        if (!board.isInBounds(newPosition)) {
            return false;
        }
        
        ChessPiece newPositionFigure = board.getChessPiece(newPosition);
        
        if (newPositionFigure.getColor() == this.getColor()) {
            return false;
        }

        if (!board.wayIsClear(this.position, newPosition)) {
            throw new InvalidMoveException("You can't move through pieces");
        } 

        if (isInDanger(newPosition)) {
            return false;
        }
        
        return true;
    }

    @Override
    public char getName() {
        if (this.getColor() == ColorEnum.Black) {
			return 'k';
		}
		return 'K';
    }

    public boolean canCastle() {
        return !hasMoved;
    }

    protected boolean isInDanger( Position newPosition ) throws InvalidMoveException, GameStateException{
        ColorEnum enemyColor;
        if (this.getColor() == ColorEnum.White) {
            enemyColor = ColorEnum.Black;
        } 
        else {
            enemyColor = ColorEnum.White;
        } 
        ArrayList<ChessPiece> enemyFigures = this.board.getAllChessPiecesFromColor(enemyColor);

        for (ChessPiece figure : enemyFigures) {
            if (figure.isLegalMove(newPosition)) {
                return true;
            }
        }

        return false;
    }
    
}