package e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return new SubsequenceCombiner<Integer,Integer>() {
            private List<Integer> result = new ArrayList<>();
            private Integer sum = 0;
            private int count = 0;

            @Override
            public List<Integer> combine(List<Integer> list) {
                result.clear();
                sum = 0; count = 0;
                for(var e : list){
                    sum += e;
                    count++;
                    if(count >= 3){
                        result.add(sum);
                        sum = 0;
                        count = 0;
                    }
                }
                if(count != 0){
                    result.add(sum);
                }

                return result;

            }
            
        };

    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return new SubsequenceCombiner<X, List<X>>() {
            @Override
            public List<List<X>> combine(List<X> list) {
                List<List<X>> result = new ArrayList<>();
                List<X> sub = new ArrayList<>();
                int count = 0;
    
                for (X entry : list) {
                    sub.add(entry);
                    count++;
    
                    if (count >= 3) {
                        result.add(new ArrayList<>(sub)); // Create a copy before adding
                        sub.clear();
                        count = 0;
                    }
                }
    
                if (!sub.isEmpty()) {
                    result.add(new ArrayList<>(sub)); // Ensure the last elements are included
                }
    
                return result;
            }
        };
    }
    

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> result = new ArrayList<>();
                Integer sub = 0;
                for (Integer elem : list) {
                    if(elem.equals(0)){
                        result.add(sub);
                        sub = 0;
                    }
                    else{
                        sub++;
                    }}
                if(sub != 0){
                    result.add(sub);
                }

                return result;

            }
            
        };

    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return new SubsequenceCombiner<X,Y>() {
            @Override
            public List<Y> combine(List<X> list) {
                List<Y> result = new ArrayList<>();
                list.forEach(e -> result.add(function.apply(e)));
                return result;
            }
            
        };

    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        return new SubsequenceCombiner<Integer, List<Integer>>() {
            @Override
            public List<List<Integer>> combine(List<Integer> list) {
                List<List<Integer>> result = new ArrayList<>();
                List<Integer> sub = new ArrayList<>();
                int sum = 0;
    
                for (Integer e : list) {
                    sum += e;
                    sub.add(e);
    
                    if (sum >= threshold) {
                        result.add(new ArrayList<>(sub)); // Store a copy of `sub`
                        sub.clear();
                        sum = 0;
                    }
                }
    
                if (!sub.isEmpty()) {
                    result.add(new ArrayList<>(sub)); // Add the last batch
                }
    
                return result;
            }
        };
    }
    

}
