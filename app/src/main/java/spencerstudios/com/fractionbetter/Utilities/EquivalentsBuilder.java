package spencerstudios.com.fractionbetter.Utilities;


import java.util.ArrayList;
import java.util.List;

public class EquivalentsBuilder {

    public static List<String> build(int parts, int total) {

        List<String> equivalents = new ArrayList<>();

        int fractionBase = findBaseNumber(parts, total);
        int baseParts;
        int baseTotal;

        if (fractionBase == 1) {
            baseParts = parts;
            baseTotal = total;
        } else {
            baseParts = parts / fractionBase;
            baseTotal = total / fractionBase;
        }

        for (int i = 1; i < 42; i++) {
            if (!(baseParts * i == parts && baseTotal * i == total)) {
                equivalents.add(baseParts * i + "/" + baseTotal * i);
            }
        }

        return equivalents;
    }

    private static int findBaseNumber(int parts, int total) {

        int base = 1;
        int tracker = parts;
        boolean baseFound = false;

        while (!baseFound && tracker > 0) {
            if (parts % tracker == 0 && total % tracker == 0) {
                base = tracker;
                baseFound = true;
            } else {
                tracker--;
            }
        }
        return base;
    }
}
