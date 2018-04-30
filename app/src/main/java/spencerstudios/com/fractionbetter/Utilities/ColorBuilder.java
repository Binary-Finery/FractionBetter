package spencerstudios.com.fractionbetter.Utilities;

import android.graphics.Color;

public class ColorBuilder {

    private static final int FRACTION_COLOR = Color.parseColor("#2196F3");
    private static final int EMPTY_COLOR = Color.WHITE;

    public static int[] build(int total, int parts) {

        int[] colors = new int[total];

        for (int i = 0; i < total; i++) {
            colors[i] = i < parts ? FRACTION_COLOR : EMPTY_COLOR;
        }

        return colors;
    }
}
