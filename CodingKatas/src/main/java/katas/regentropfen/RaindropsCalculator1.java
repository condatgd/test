package katas.regentropfen;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class RaindropsCalculator1 {

    public int calculateRainDropCount(List<Integer> topologieList) {
        if(topologieList.size()>=3) {
            Topologie topologie = new Topologie(topologieList);
            return topologie.calculateVolume();
        }
        return 0;
    }


    @Data
    @AllArgsConstructor
    static class TopValue {
        private int index;
        private int height;
        private boolean maximum;
    }

    @Data
    static class Topologie {
        private List<TopValue> topValues;
        private int currentMaxIndex = 0;

        public Topologie(List<Integer> topologie) {
            topValues = topologie.stream().map(h -> new TopValue(0, h, false)).toList();
            determineMaxima();
        }

        private void determineMaxima() {
            IntStream.range(0, topValues.size()).boxed()
                    .filter(i -> getHeight(i - 1) < getHeight(i) &&  // Maximum Wendepunkt ?
                            getHeight(i) > getHeight(i + 1))
                    .forEach(i -> {
                        topValues.get(i).setMaximum(true);
                        topValues.get(i).setIndex(i);
                    });
        }

        private int getHeight(Integer index) {
            try {
                return topValues.get(index).getHeight();
            } catch (Exception e) {
                return 0;
            }
        }

        public int calculateVolume() {
            Optional<TopValue> leftMaxOpt = findFirstMax(topValues);
            if(leftMaxOpt.isEmpty()) return 0;

            Optional<TopValue> nextMaxOpt = findNextMax(leftMaxOpt.get(), topValues);
            if(nextMaxOpt.isEmpty()) return 0;

            TopValue leftMax = leftMaxOpt.get();
            TopValue nextMax = nextMaxOpt.get();
            int heightLeft = leftMax.getHeight();
            int heightRight = nextMax.getHeight();
            int height = Math.min(heightLeft, heightRight);
            int rect = (height * (nextMax.getIndex() - leftMax.getIndex() - 1));
            int sub = IntStream.range(leftMax.getIndex() + 1, nextMax.getIndex())
                        .map(this::getHeight).sum();
            currentMaxIndex = nextMax.getIndex();
            return rect - sub + calculateVolume();
        }

        private Optional<TopValue> findFirstMax(List<TopValue> topologie) {
            return topologie.stream()
                    .filter(topValue -> currentMaxIndex <= topValue.getIndex())
                    .filter(TopValue::isMaximum).findFirst();
        }

        private Optional<TopValue> findNextMax(TopValue leftMaxValue, List<TopValue> topologie) {
                int leftMaxIndex = leftMaxValue.getIndex();
                int leftHeight = leftMaxValue.getHeight();
                int idx = IntStream.range(leftMaxIndex + 1, topologie.size()).boxed()
                        .filter(mi -> leftHeight <= topologie.get(mi).getHeight()).findFirst().orElse(  // Fall 1: größeres max
                                IntStream.range(leftMaxIndex + 1, topologie.size()).boxed()             // Fall 2: größtes max von den kleineren
                                        .reduce(0, (a, b) -> topologie.get(a).getHeight() > topologie.get(b).getHeight() ? a : b)
                        );
                if(idx > 0) {
                    return topologie.stream().filter(t -> t.getIndex() == idx).findFirst();
                }
                return Optional.empty();
        }
    }

}
