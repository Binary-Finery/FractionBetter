package spencerstudios.com.fractionbetter.Utilities;

import java.util.Locale;

public class LabelBuilder {

    public static String[] build(int total, int parts) {

        String[] labels = new String[total];

        final String fraction = String.format(Locale.getDefault(), "1/%d", total);

        for (int i = 0; i < total; i++) {
            labels[i] = i < parts ? fraction : "";
        }

        return labels;
    }
}
