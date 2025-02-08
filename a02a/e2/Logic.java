package e2;

public interface Logic {

    void hit(Position p);

    boolean isOver();

    void reset();

    boolean isBishop(Position p);

    boolean isDisabled(Position p);

    void disableDiagonals(Position p);

    void disableInDirection(Position p, int dx, int dy);

    

}
