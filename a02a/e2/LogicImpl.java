package e2;

import java.util.ArrayList;
import java.util.List;

public class LogicImpl implements Logic {

    private int size;
    private List<Position> bishops = new ArrayList<>();
    private List<Position> disabled = new ArrayList<>();

    public LogicImpl(final int size){
        this.size = size;
    }

    @Override
    public void hit(Position p) {
        if (!isBishop(p)) {
            bishops.add(p);
            disableDiagonals(p);
        }
    }
    
    public void disableDiagonals(Position p) {
        disableInDirection(p, 1, 1);  // Down-right
        disableInDirection(p, -1, -1); // Up-left
        disableInDirection(p, 1, -1); // Down-left
        disableInDirection(p, -1, 1); // Up-right
    }
    
    public void disableInDirection(Position p, int dx, int dy) {
        int x = p.x();
        int y = p.y();
        
        while (true) {
            x += dx;
            y += dy;
            if (x < 0 || x >= size || y < 0 || y >= size) {
                break;  // Stop at board boundaries
            }
            Position pos = new Position(x, y);
            if(!isDisabled(pos)){
            disabled.add(pos);}  // Disable diagonal position
        }
    }
    

    @Override
    public boolean isOver() {
        if((size * size) - bishops.size() == disabled.size())return true;
        return false;
    }


    @Override
    public void reset() {
        bishops.clear();
        disabled.clear();
    }

    @Override
    public boolean isBishop(Position p) {
        return bishops.contains(p);
    }

    @Override
    public boolean isDisabled(Position p) {
        return disabled.contains(p);
    }

}
