package e2;

import java.util.ArrayList;
import java.util.List;

public class LogicImpl implements Logic {
    private int count = 1;
    private List<Position> diagnoals = new ArrayList<>();
    private boolean isOver = false;
    private List<Position> selected = new ArrayList<>();

    public LogicImpl(){
    }

    @Override
    public void hit(Position p) {

        if(selected.contains(p)){
            selected.remove(p);
        }
        else{
            switch (count) {
                case 1:
                    diagnoals.add(p);
                    count ++;
                    break;
            
                case 2:
                    diagnoals.add(p);
                    count ++;
                    break;

                case 3:
                    diagnoals.add(p);
                    this.isOver = isDiagonal();
                    diagnoals.clear();
                    count = 1;
                    break;
            }
            selected.add(p);
        }

    }

    @Override
    public boolean isSelected(Position p) {
        return selected.contains(p);

    }

    @Override
    public boolean isDiagonal(){
        if(
            Math.abs(diagnoals.get(0).x() - diagnoals.get(1).x()) == 1 &&
            Math.abs(diagnoals.get(0).y() - diagnoals.get(1).y()) == 1){
            if(
                (Math.abs(diagnoals.get(1).x() - diagnoals.get(2).x()) == 1 &&
                Math.abs(diagnoals.get(1).y() - diagnoals.get(2).y()) == 1) &&
                (Math.abs(diagnoals.get(0).x() - diagnoals.get(2).x()) == 2 &&
                Math.abs(diagnoals.get(0).y() - diagnoals.get(2).y()) == 2))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOver() {
        return isOver;
    }

}
