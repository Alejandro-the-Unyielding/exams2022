package e2;

import java.util.ArrayList;
import java.util.List;

public class LogicImpl implements Logic{
    private final List<Position> selected = new ArrayList<>();
    private boolean gameOver = false;

    public LogicImpl(){
    }

    @Override
    public void hit(Position p) {

            if(isSelected(p)) selected.remove(p);
            else selected.add(p);
            int stars = 0; int others = 0;
            for(int i = -1; i<= 1 ; i++){
                for(int j = -1; j<=1 ; j++){
                    if(i == 0 || j == 0) continue;
                    Position np = new Position( p.x()+i, p.y()+j);
                    if(isSelected(np)){
                        others++;
                        selected.remove(np);
                    }
                    else{
                        stars++;
                        selected.add(np);
                    }
                    gameOver = (stars == 1 && others == 3);
                }
            }
    }

    @Override
    public boolean isSelected(Position p) {
        return selected.contains(p);

    }

    @Override
    public boolean isOver() {
        return gameOver;

    }

}
