package denominator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinRadiusMatrixTest {


    @Test
    void simpleInput() {
        List<Integer> testList = new ArrayList<Integer>();
        CoinRadiusMatrix matrix = new CoinRadiusMatrix("Dime");

        for (int i = 0; i < 3; i++) {
            testList.add(1000);
        }

        HashMap<Double, Integer> output = null;
        try {
            output = matrix.countRatio(testList);
        } catch (Exception e) {
            fail("OOPS");
        }

        assertEquals(1, output.size());
        assertEquals(3, output.get(1.0));

    }

    @Test
    void standardInput() {
        List<Integer> testList = new ArrayList<Integer>();
        CoinRadiusMatrix matrix = new CoinRadiusMatrix("Dime");

        testList.add(211);
        testList.add(280);
        testList.add(265);
        testList.add(212);
        testList.add(267);
        testList.add(180);

        HashMap<Double, Integer> output = null;
        try {
            output = matrix.countRatio(testList);
        } catch (Exception e) {
            fail("OOPS");
        }

       assertEquals(3, output.size());
       assertEquals(1, output.get(1.0));

        System.out.println(output.toString());

    }

    @Test
    void realInput() {
        List<Integer> testList = new ArrayList<Integer>();
        CoinRadiusMatrix matrix = new CoinRadiusMatrix("Dime");
// [255, 276, 229, 233, 227, 201, 168, 200, 177]
        testList.add(255);
        testList.add(276);
        testList.add(229);
        testList.add(233);
        testList.add(227);
        testList.add(201);
        testList.add(168);
        testList.add(200);
        testList.add(177);

        HashMap<Double, Integer> output = null;

        try {
            output = matrix.countRatio(testList);
        } catch (Exception e) {
            fail("OOPS");
        }

        assertEquals(5, output.size());
        assertEquals(2, output.get(1.0));

        System.out.println(output.toString());
        // {1.0=2, 1.176=2, 1.324=3, 1.47=1}

    }




}