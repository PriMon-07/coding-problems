package general;

import java.math.BigDecimal;

public class MaximizeNumber {

    public static void main(String[] args) {
        // Sample values as strings
        String d1 = "39.13";
        String d2 = "100";
        // Using the different methods to get averages
        String sAvg = String.valueOf(getAverageMethodA(d1, d2));
        String sAvg2 = String.valueOf(getAverageMethodB(d1, d2));
        String sAvg3 = String.valueOf(getAverageMethodC(d1, d2));
        // Output the results
        System.out.println(sAvg + " " + sAvg2 + " " + sAvg3);
    }
    // Method A: Using BigDecimal for the most accurate result
    private static BigDecimal getAverageMethodA(String d1, String d2) {
        BigDecimal num1 = new BigDecimal(d1);
        BigDecimal num2 = new BigDecimal(d2);
        BigDecimal num3 = num1.divide(num2, BigDecimal.ROUND_HALF_UP);
        return num3;
    }
    // Method B: Using double
    private static double getAverageMethodB(String d1, String d2) {
        double num1 = Double.valueOf(d1);
        double num2 = Double.valueOf(d2);
        return num1 / num2;
    }
    // Method C: Using float
    private static float getAverageMethodC(String d1, String d2) {
        float num1 = Float.valueOf(d1);
        float num2 = Float.valueOf(d2);
        return num1 / num2;
    }
}
