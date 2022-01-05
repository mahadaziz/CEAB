/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: Measures interface
 */


interface Measures {

    double[] measures() throws UnsupportedOperationException;

    double[] measures(IndicatorT ind) throws UnsupportedOperationException;

    double[] measures(AttributeT att) throws UnsupportedOperationException;
}
