package katas.regentropfen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Topologie {

    private final List<Integer> heights;
    private final List<Integer> waterHeights;

    public Topologie(List<Integer> heights) {
        this.heights = heights;
        waterHeights = new ArrayList<>(heights.size());
        heights.forEach(height -> waterHeights.add(0));
    }

    public boolean dropOfWaterKept(Integer index) {
        Integer topH = heights.get(index);
        Integer waterH = waterHeights.get(index);
        Integer newHeight = topH + waterH + 1;
        List<Integer> leftHeights  = heights.subList(0, index);
        List<Integer> rightHeights = heights.subList(index + 1, heights.size());
        boolean hasLeftBoundary = leftHeights.stream().anyMatch(th -> th >= newHeight);
        boolean hasRightBoundary = rightHeights.stream().anyMatch(th -> th >= newHeight);
        boolean kept = hasLeftBoundary && hasRightBoundary;
        if (kept) {
            waterHeights.set(index, waterHeights.get(index) + 1);
        }
        return kept;
    }

    public Integer dropsOfWaterKept(Integer index) {
        int i = 0;
        while (dropOfWaterKept(index)) i++;
        return i;
    }
    public Integer waterKept() {
        return IntStream.range(1, heights.size()-1).reduce(0, (a, b) -> a + dropsOfWaterKept(b));
    }
}
