public class VortexScale {

    private static final int[][] RANGES = {
            {1, 65, 80},
            {2, 81, 110},
            {3, 111, 140},
            {4, 141, 170},
            {5, 171, 200},
            {6, 201, 230},
            {7, 231, 260},
            {8, 261, 290},
            {9, 291, 320},
            {10, 321, 350},
            {11, 351, 380},
            {12, 381, 410},
            {13, 411, 440},
            {14, 441, 470},
            {15, 471, 500}
    };

    public static Integer getCategory(double wind) {
        if (wind < 0 || Double.isNaN(wind)) return null;

        for (int[] r : RANGES) {
            if (wind >= r[1] && wind <= r[2]) return r[0];
        }

        return (int) Math.floor((wind - 501) / 30.0) + 16;
    }

    public static int[] getRange(int cat) {
        if (cat < 1) return null;

        if (cat <= RANGES.length) return RANGES[cat - 1];

        int min = 501 + (cat - 16) * 30;
        int max = min + 29;
        return new int[]{cat, min, max};
    }

    public static String describe(int cat) {
        int[] r = getRange(cat);
        if (r == null) return "Invalid category.";
        return "VS-" + r[0] + ": " + r[1] + "-" + r[2] + " mph";
    }
}