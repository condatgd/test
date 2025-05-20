package katas.regentropfen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class RaindropsCalculator1Test {
    private RaindropsCalculator1 raindropsCalculator = new RaindropsCalculator1();

    private static Stream<Arguments> raindropTestArguments() {
        return Stream.of(
                arguments(List.of(), 0),
                arguments(List.of(1), 0),
                arguments(List.of(2), 0),
                arguments(List.of(1,1), 0),
                arguments(List.of(2,1), 0),
                arguments(List.of(2,1,1), 0),
                arguments(List.of(2,1,2), 1),
                arguments(List.of(1, 4, 2, 5, 2, 3), 3),
                arguments(List.of(1, 6, 2, 5, 2, 2, 4, 3, 10), 36-18),  // 6*6-(2+5+2+2+4+3)=36-18
                arguments(List.of(0,1,0,4,0,3,0,1,0,2,0,2,0,3,0,2), 22),
                arguments(List.of(1_000_000_001,1_000_000_004,1_000_000_002,1_000_000_005,1_000_000_002,1_000_000_003), 3)

        );
    }

    @ParameterizedTest
    @MethodSource("raindropTestArguments")
    void calculateRainDropCountTest(List<Integer> topologie, int expected) {
        // WHEN
        int result = raindropsCalculator.calculateRainDropCount(topologie);

        // THEN
        Assertions.assertEquals(expected, result);
    }
}