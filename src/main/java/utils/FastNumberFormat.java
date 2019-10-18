package utils;

public class FastNumberFormat {
    public static StringBuilder format(StringBuilder sb, long value, int minimumDigits) {
        if (value < 0) {
            sb.append('-');
            value = -value;
        }

        long tmp = value;
        do {
            tmp /= 10;

        } while (--minimumDigits > 0 && tmp > 0);

        for (int i = minimumDigits; i > 0; --i) {
            sb.append('0');
        }

        sb.append(value);
        return sb;
    }


}
