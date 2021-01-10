package denominator.models;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Classifier {

    private static HashMap<Double,String> coinRatios;
    private HashMap<Double,Integer> ratioCount;
    private String smallest;

    public Classifier(HashMap<Double,Integer> ratios, String smallest) {
        this.ratioCount = ratios;
        this.smallest = smallest;
        this.coinRatios = new HashMap<Double, String>();

        setRatios();
    }

    private void setRatios() {

        coinRatios.put(1.0, smallest);

        coinRatios.put(1.321, "Toonie");
        coinRatios.put(1.553, "Toonie");
        coinRatios.put(1.173, "Toonie");
        coinRatios.put(1.057, "Toonie");

        coinRatios.put(1.470, "Loonie");
        coinRatios.put(1.25, "Loonie");
        coinRatios.put(1.110, "Loonie");

        coinRatios.put(1.324, "Quarter");
        coinRatios.put(1.126, "Quarter");

        coinRatios.put(1.176, "Nickel");
    }



    public HashMap<String, Integer> classify(HashMap<Double,Integer> ratios) {

        HashMap<String, Integer> counter = new HashMap<String, Integer>();
        counter.put("Toonie", 0);
        counter.put("Loonie", 0);
        counter.put("Quarter", 0);
        counter.put("Nickel", 0);
        counter.put("Dime", 0);


        for (Map.Entry<Double, Integer> entry : ratios.entrySet()) {
            Double key = entry.getKey();
            Integer value = entry.getValue();

            String currCoin = coinRatios.get(key);
            int currCount = counter.get(currCoin);
            currCount += value;

            counter.put(currCoin, currCount);
        }
        return counter;
    }


    public int counter(HashMap<String, Integer> coinCount) {

         int rsf = 0;

        for (Map.Entry<String, Integer> entry : coinCount.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (key.equals("Toonie")) {
                rsf += (200 * value);
            } else if (key.equals("Loonie")) {
                rsf += (100 * value);
            } else if (key.equals("Quarter")) {
                rsf += (25 * value);
            } else if (key.equals("Nickel")) {
                rsf += (5 * value);
            } else {
                rsf += (10 * value);
            }
        }

        return rsf;
    }


}
