/**
 * @file CourseT.java
 * @author Mahad Aziz - azizm17
 * @brief CourseT abstract data type class that allows the creation of a course
 * @date March 29, 2021
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;

/**
 * @brief An ADT of CourseT that represents a Course
 * @details The course takes a course name and indicators as parameters
 */
public class CourseT implements Measures {

    private String name;
    private Map<IndicatorT, HashSet<LOsT>> m = new HashMap<IndicatorT, HashSet<LOsT>>();

    /**
     * @brief Initializes the CourseT object
     * @param courseName A string that represents the name of the course
     * @param indicators An array that holds the indicators of the course
     */
    public CourseT(String courseName, IndicatorT[] indicators) {
        this.name = courseName;
        for (int i = 0; i < indicators.length; i++) {
            this.m.put(indicators[i], new HashSet<LOsT>());
        }
    }

    /**
     * @brief Returns the course name
     * @return The String that holds the course name of the CourseT object
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Returns the course indicators
     * @return An array that holds the indicators of the CourseT object
     */
    public IndicatorT[] getIndicators() {
        IndicatorT[] ind = new IndicatorT[this.m.keySet().size()];
        int i = 0;
        for (IndicatorT s : m.keySet()) {
            ind[i] = s;
            i++;
        }
        return ind;
    }

    /**
     * @brief Returns all the learning outcomes of a course associated with a given indicator
     * @param indicator An IndicatorT object
     * @return An array of learning outcomes for a given indicator in the CourseT object
     */
    public LOsT[] getLOs(IndicatorT indicator) {
        ArrayList<LOsT> a = new ArrayList<LOsT>();
        for (IndicatorT ind : m.keySet()) {
            if (ind == indicator) {
                for (LOsT lo : m.get(ind)) {
                    a.add(lo);
                }
                return a.toArray(new LOsT[0]);
            }
        }
        LOsT[] empty = {};
        return empty;
    }

    /**
     * @brief Adds a learning outcome to the set of existing learning outcomes for a given indicator
     * @details If the indicator sent as a parameter is not a valid indicator then nothing is added.
     * Also if there is a learning outcome is already present with the same name then the learning 
     * outcome is not added.
     * @param indicator An IndicatorT object
     * @param outcome The LOsT object that will be added 
     */
    public void addLO(IndicatorT ind, LOsT outcome) {
        if (m.containsKey(ind)) {
            HashSet<LOsT> set = m.get(ind);
            for (LOsT lo : set) {
                if (lo.equals(outcome)) {
                    return;
                }
            }
            set.add(outcome);
        }
    }

    /**
     * @brief Deletes a learning outcome from the set of existing learning outcomes for a given indicator
     * @details If the indicator sent as a parameter is not a valid indicator then nothing is deleted.
     * @param indicator An IndicatorT object
     * @param outcome The LOsT object that will be removed
     */
    public void delLO(IndicatorT ind, LOsT outcome) {
        if (m.containsKey(ind)) {
            HashSet<LOsT> set = m.get(ind);
            for (LOsT lo : set) {
                if (lo.equals(outcome)) {
                    set.remove(lo);
                    m.put(ind, set);
                    return;
                }
            }
        }
    }

    /**
     * @brief Searches and returns whether the given indicator has the given array of learning outcomes
     * @param indicator An IndicatorT object
     * @param outcomes The array of LOsT objects that need to be searched
     * @return true if the array of learning outcomes provided for a given indicator are equal, otherwise
     * returns false
     */
    public boolean member(IndicatorT indicator, LOsT[] outcomes) {
        for (IndicatorT x : m.keySet()) {
            if (x == indicator) {
                Set<String> temp = new HashSet<String>();
                Set<String> original = new HashSet<String>();
                for (LOsT lo : outcomes) {
                    temp.add(lo.getName());
                } for (LOsT lo : m.get(x)) {
                    original.add(lo.getName());
                }
                if (temp.size() != outcomes.length) {return false;}
                if (temp.equals(original)) {return true;}
            }
        } return false;
    }

    /**
     * @brief Override the measure() method from the Measures interface
     * @throws UnsupportedOperationException because this method is not implemented in CourseT
     */
    @Override
    public double[] measures() {
        throw new UnsupportedOperationException("Operation is not supported");
    }

    /**
     * @brief Override the measure(IndicatorT) method from the Measures interface
     * @details Measures the results of the learning outcome for the number of students who were below, 
     * marginal, meeting, and exceeding standards, given an indicator
     * @param ind An IndicatorT object that provides access to an array of LOsT objects
     * @return Array that holds 4 double values that show the measures of the indicator. Exact values 
     * are based on the boolean values of the Norm  
     */
    @Override
    public double[] measures(IndicatorT ind) {
        if (this.getLOs(ind).length == 0) {
            return new double[] {0,0,0,0};
        }
        double[] measureInd = new double[]{0,0,0,0};
        for (LOsT o : this.getLOs(ind)) {
            measureInd = sumMeas(measureInd, o.measures());
        }
        if (Norm.getNInd()) {
            return Services.normal(measureInd);
        } else {
            return measureInd;
        }
    }

    /**
     * @brief Override the measure(AttributeT) method from the Measures interface
     * @details Measures the results of the learning outcome for the number of students who were below, 
     * marginal, meeting, and exceeding standards, given an attribute
     * @param att An AttributeT object that provides access to an set of Indicator Objects that each 
     * have access to an array of LOsT objects
     * @return Array that holds 4 double values that show the measures of the attribute. Exact values are 
     * based on the boolean values of the Norm  
     */
    @Override
    public double[] measures(AttributeT att) {
        if (att.getIndicators().length == 0) {
            return new double[] {0,0,0,0};
        }

        double[] measureInd = new double[]{0,0,0,0};
        for (IndicatorT i : att.getIndicators()) {
            measureInd = sumMeas(measureInd, this.measures(i));
        }
        if (Norm.getNAtt()) {
            return Services.normal(measureInd);
        } else {
            return measureInd;
        }
    }

    private double[] sumMeas(double[] a, double[] b) {
        return new double[]{a[0] + b[0], a[1] + b[1], a[2] + b[2], a[3] + b[3]};
    }
}
