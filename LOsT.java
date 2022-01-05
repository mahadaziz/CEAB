/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: LOsT ADT class
 */


import java.util.Arrays;

public class LOsT implements Measures {
    private String name;
    private int n_blw;
    private int n_mrg;
    private int n_mts;
    private int n_exc;

    public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc) {
        if ((nblw < 0) || (nmrg < 0) || (nmts < 0) || (nexc < 0)) {
            throw new IllegalArgumentException("Negative integers are not accepted as inputs");
        }
        if ((nblw == 0) && (nmrg == 0) && (nmts == 0) && (nexc == 0)) {
            throw new IllegalArgumentException("Inputs can not all be zero");
        }
        this.name = topic;
        this.n_blw = nblw;
        this.n_mrg = nmrg;
        this.n_mts = nmts;
        this.n_exc = nexc;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        LOsT O = (LOsT) o;
        if (name.equals(O.getName())) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public double[] measures() {
        double[] arr = new double[]{n_blw, n_mrg, n_mts, n_exc};
        if (!(Norm.getNLOs())) {
            return arr;
        }
        else {
            return Services.normal(arr);
        }
    }

    @Override
    public double[] measures(IndicatorT ind) {
        throw new UnsupportedOperationException("Operation is not supported");
    }

    @Override
    public double[] measures(AttributeT att) {
        throw new UnsupportedOperationException("Operation is not supported");
    }

}
