package denominator;

import denominator.Exceptions.RatioNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoinRadiusMatrix {

    // from http://www.saskatooncoinclub.ca/articles/02a_coin_specs.html
    private static final double NICKEL_RADIUS = 21.2;
    private static final double DIME_RADIUS = 18.03;
    private static final double QUARTER_RADIUS = 23.88;
    private static final double LOONIE_RADIUS = 26.5;
    private static final double TOONIE_RADIUS = 28;
    private static final double ERROR = 0.1;

    private static String smallest;

    public CoinRadiusMatrix(String smallest) {
        this.smallest = smallest;
    }


    // group ratios using smallest radius as reference (hashmap is <ratio, count>)
    public static HashMap<Double,Integer> countRatio(ArrayList<Integer> radiusList) throws RatioNotFoundException {
        double min = findMin(radiusList);
        HashMap<Double, Integer> rsf = new HashMap<Double, Integer>();

        for (Integer radius : radiusList) {
            double ratio = radius / min;
            System.out.println(min + ", " + radius + ". " + radius/min);
            // TODO: Section off the checks based on what the samllest coin value is
            if (Math.abs(ratio - 1) < ERROR) {
                putter(rsf, 1.0);
            } else if (Math.abs(ratio - 1.176) < ERROR) {
                putter(rsf, 1.176);
            } else if (Math.abs(ratio - 1.324) < ERROR) {
                putter(rsf, 1.324);
            } else if (Math.abs(ratio - 1.470) < ERROR) {
                putter(rsf, 1.470);
            } else if (Math.abs(ratio - 1.553) < ERROR) {
                putter(rsf, 1.553);
            } else if (Math.abs(ratio - 1.126) < ERROR) {
                putter(rsf, 1.126);
            } else if (Math.abs(ratio - 1.25) < ERROR) {
                putter(rsf, 1.25);
            } else if (Math.abs(ratio - 1.321) < ERROR) {
                putter(rsf, 1.321);
            } else if (Math.abs(ratio - 1.110) < ERROR) {
                putter(rsf, 1.110);
            } else if (Math.abs(ratio - 1.173) < ERROR) {
                putter(rsf, 1.173);
            } else if (Math.abs(ratio - 1.057) < ERROR) {
                putter(rsf, 1.057);
            } else {
                throw new RatioNotFoundException();
            }
        }
        return rsf;
    }

    private static void putter(HashMap<Double, Integer> rsf, double v) {
        if (!rsf.containsKey(v)) {
            rsf.put(v, 1);
        } else {
            int curr = rsf.get(v);
            curr++;
            rsf.put(v, curr);
        }
    }

    // gets min radius from list of radius
    private static double findMin(List<Integer> radiusList) {
        int minimumRadius = Integer.MAX_VALUE;

        for (Integer radius : radiusList) {
            if (radius < minimumRadius) {
                minimumRadius = radius;
            }
        }

        return (double) minimumRadius;
    }
}
