package e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
public class FlattenerFactoryImpl implements FlattenerFactory{

    @Override
    public Flattener<Integer, Integer> sumEach() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                return list.stream().map(innerList -> innerList.stream().reduce(0, (a,b) -> a+b)).toList();

            }
            
        };

    }

    @Override
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X,X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                return list.stream().flatMap(List::stream).toList();

            }
            
        };

    }

@Override
public Flattener<String, String> concatPairs() {
    return new Flattener<String, String>() {
        @Override
        public List<String> flatten(List<List<String>> list) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < list.size(); i += 2) {
                // Join all strings in the first list
                String concatenated = list.get(i).stream().collect(Collectors.joining());

                // If there is a next list, concatenate its elements too
                if (i + 1 < list.size()) {
                    concatenated += list.get(i + 1).stream().collect(Collectors.joining());
                }
                result.add(concatenated);
            }
            return result;
        }
    };
}


    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return new Flattener<I,O>() {

            @Override
            public List<O> flatten(List<List<I>> list) {
                return list.stream().map(mapper).toList();

            }
            
        };

    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        return new Flattener<Integer,Integer>() {
            
            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                List<Integer> result = new ArrayList<>();
                for (int i = 0; i < list.get(0).size(); i++) {
                    int sum = 0;
                    for (List<Integer> innerList : list) {
                        sum += innerList.get(i);
                    }
                    result.add(sum);
                }
                return result;
            }
        };

    }

}
