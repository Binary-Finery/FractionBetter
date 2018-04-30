package spencerstudios.com.fractionbetter.Utilities;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EquivalentsBuilder {

    public static List<String> build(int p, int t) {

        List<String> equivalents = new ArrayList<>();
        int total = t, parts = p, upperBound;

        while (total % 2 == 0 && parts % 2 == 0) {
            total /= 2;
            parts /= 2;
            equivalents.add(parts + "/" + total);
        }

        final int MAX_FRACTIONS_TO_DISPLAY = 10;

        upperBound = MAX_FRACTIONS_TO_DISPLAY - equivalents.size();
        Collections.reverse(equivalents);

        if (equivalents.size() < MAX_FRACTIONS_TO_DISPLAY) {
            for (int i = 0; i < upperBound; i++) {
                p *= 2;
                t *= 2;
                equivalents.add(p + "/" + t);
            }
        }
        return equivalents;
    }
}
