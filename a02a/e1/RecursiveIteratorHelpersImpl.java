package e1;

import java.util.ArrayList;
import java.util.List;

public class RecursiveIteratorHelpersImpl implements RecursiveIteratorHelpers{

    @Override
    public <X> RecursiveIterator<X> fromList(List<X> list) {
        if (list.isEmpty()) {
            return null; // If the list is empty, return null
        }
        
        return new RecursiveIterator<>() {
            private final X element = list.get(0); // Get first element
            private final RecursiveIterator<X> nextIterator = fromList(list.subList(1, list.size())); // Recursively create next iterator
    
            @Override
            public X getElement() {
                return element;
            }
    
            @Override
            public RecursiveIterator<X> next() {
                return nextIterator; // Return the next recursive iterator
            }
        };
    }
    

    @Override
    public <X> List<X> toList(RecursiveIterator<X> input, int max) {
        List<X> result = new ArrayList<>();
        while(max > 0 && input != null){
            result.add(input.getElement());
            input = input.next();
            max--;
        }
        return result;
    }

    @Override
    public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
        if(first == null || second == null){
            return null;
        }
        return new RecursiveIterator<Pair<X,Y>>(){

            @Override
            public Pair<X, Y> getElement() {
                return new Pair<X,Y>(first.getElement(), second.getElement());

            }

            @Override
            public RecursiveIterator<Pair<X, Y>> next() {
                return zip(first.next(), second.next());
            }
            
        };

    }

    @Override
    public <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex(RecursiveIterator<X> iterator) {
            return zipWithIndexHelper(iterator, 0);
    }
    

    private <X> RecursiveIterator<Pair<X, Integer>> zipWithIndexHelper(RecursiveIterator<X> iterator, int index){
        if(iterator == null){
            return null;
        }
        return new RecursiveIterator<Pair<X,Integer>>() {

            @Override
            public Pair<X, Integer> getElement() {
                return new Pair<X,Integer>(iterator.getElement(), index);
            }

            @Override
            public RecursiveIterator<Pair<X, Integer>> next() {
                return zipWithIndexHelper(iterator.next(), index + 1);
            }
            
        };

    }

    @Override
    public <X> RecursiveIterator<X> alternate(RecursiveIterator<X> first, RecursiveIterator<X> second) {
        return alternateHelper(first, second, true);
    }

    private <X> RecursiveIterator<X> alternateHelper(RecursiveIterator<X> first, RecursiveIterator<X> second, boolean turn){
        if(first == null){
            return new RecursiveIterator<X>() {

                @Override
                public X getElement() {
                    return second.getElement();
                }

                @Override
                public RecursiveIterator<X> next() {
                    return second.next();
                }
                
            };
        }
        if (second == null){
            return new RecursiveIterator<X>() {

                @Override
                public X getElement() {
                    return first.getElement();
                }

                @Override
                public RecursiveIterator<X> next() {
                    return first.next();
                }
                
            };
        }
        if(turn){
            return new RecursiveIterator<X>() {

                @Override
                public X getElement() {
                    return first.getElement();
                }

                @Override
                public RecursiveIterator<X> next() {
                    return alternateHelper(first.next(), second, !turn);
                }
                
            };
        }
        else{
            return new RecursiveIterator<X>() {

                @Override
                public X getElement() {
                    return second.getElement();
                }

                @Override
                public RecursiveIterator<X> next() {
                    return alternateHelper(first, second.next(), !turn);
                }
                
            };
        }

    }
}

