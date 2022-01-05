/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: Services Static class
 */


public class Services {

    public static double[] normal(double[] v) {
        double[] arr = new double[v.length];
        double sum = 0;
        for (int i = 0; i < v.length; i++) {
            sum += v[i];
        }
        for (int i = 0; i < v.length; i++) {
            arr[i] = v[i] / sum;
        }
        return arr;
    }

}
