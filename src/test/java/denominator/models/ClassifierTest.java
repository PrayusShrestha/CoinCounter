package denominator.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassifierTest {

    HashMap<Double, Integer> input;
    Classifier classifier;

    @BeforeEach
    void setUp() {
        input = new HashMap<Double, Integer>();
        input.put(1.0, 1);
    }

    @Test
    void classifyTest() {
        input.put(1.176, 2);
        input.put(1.470, 1);

        classifier = new Classifier(input, "Dime");

        HashMap<String, Integer> output = classifier.classify(input);

        System.out.println(output.toString());
    }
    @Test
    void classifyTestRealInput() {
        input.put(1.176, 2);
        input.put(1.0, 2);
        input.put(1.324, 3);
        input.put(1.553, 1);
        input.put(1.47, 1);

        classifier = new Classifier(input, "Dime");

        HashMap<String, Integer> output = classifier.classify(input);

        System.out.println(output.toString());
    }

    @Test
    void counterTest() {
        input.put(1.176, 2);
        input.put(1.470, 1);

        classifier = new Classifier(input, "Dime");

        HashMap<String, Integer> output = classifier.classify(input);

        assertEquals(120, classifier.counter(output));

    }

}