/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: Test Cases for the ProgramT ADT class
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestProgramT
{
	
	private ProgramT CS;

	@Before
    public void setUp()
    {
        IndicatorT[] ME3_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
                IndicatorT.suitableFund, IndicatorT.recogTheory, IndicatorT.modelSelect,
                IndicatorT.estOutcomes, IndicatorT.desProcess, IndicatorT.desPrinciples,
                IndicatorT.openEnded, IndicatorT.tools, IndicatorT.engInSoc,
                IndicatorT.awarePEO};
        IndicatorT[] FA3_indicators = new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund};
        IndicatorT[] XB3_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow};
        IndicatorT[] CO3_indicators = new IndicatorT[] {IndicatorT.desProcess, IndicatorT.desPrinciples,
                IndicatorT.openEnded, IndicatorT.ideaGeneration,
                IndicatorT.healthSafety, IndicatorT.standards};
        CourseT ME3 = new CourseT("Software Engineering Design 1", ME3_indicators);
        CourseT FA3 = new CourseT("Discrete Math", FA3_indicators);
        CourseT XB3 = new CourseT("Binding Theory to Practice", XB3_indicators);
        CourseT CO3 = new CourseT("Data Structures and Algorithms", CO3_indicators);
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 1", 1, 2, 3, 4));
        FA3.addLO(IndicatorT.assumpt, new LOsT("topic 2", 5, 6, 7, 8));
        XB3.addLO(IndicatorT.math, new LOsT("topic 3", 9, 10, 11, 12));
        CO3.addLO(IndicatorT.standards, new LOsT("topic 4", 0, 0, 0, 1));
        CS = new ProgramT();
        CS.add(ME3);
        CS.add(FA3);
        CS.add(XB3);
        CS.add(CO3);
    }

    @After
    public void tearDown()
    {
        CS = null;
    }

    @Test (expected = UnsupportedOperationException.class)
    public void test_measures()
    {
        CS.measures();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void test_indicator_measures()
    {
        CS.measures(IndicatorT.math);
    }

    @Test
    public void test_attribute_measures1()
    {
    	// Normal test case
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.awarePEO});
        assertEquals(Arrays.toString(new double[]{0.1,0.2,0.3,0.4}), Arrays.toString(CS.measures(Att1)));
    }

    @Test
    public void test_attribute_measures2()
    {
    	// Normal test case
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt});
        assertEquals(true, array_isclose(new double[]{0.19231, 0.23077, 0.26923, 0.30769}, CS.measures(Att1)));
    }

    @Test
    public void test_attribute_measures3()
    {
    	// Normal test case with two indicators
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.awarePEO});
        assertEquals(true, array_isclose(new double[]{0.16667, 0.22222, 0.27778, 0.33333}, CS.measures(Att1)));
    }

    @Test
    public void test_attribute_measures4()
    {
    	// Normal test case with three indicators
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.awarePEO, IndicatorT.suitableFund});
        assertEquals(true, array_isclose(new double[]{0.16667, 0.22222, 0.27778, 0.33333}, CS.measures(Att1)));
    }

    @Test
    public void test_attribute_measures5()
    {
    	// Duplicate indicator test case
        AttributeT Att1 = new AttributeT("Duplicates", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.assumpt});
        assertEquals(true, array_isclose(new double[]{0.19231, 0.23077, 0.26923, 0.30769}, CS.measures(Att1)));
    }

    @Test
    public void test_attribute_measures6()
    {
    	// Duplicate indicator test case
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.assumpt, IndicatorT.math});
        assertEquals(true, array_isclose(new double[]{0.20588, 0.23529, 0.26471, 0.29412}, CS.measures(Att1)));
    }

    @Test
    public void test_attribute_measures7()
    {
    	// Duplicate indicator and two other distinct indicators test case
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.assumpt, IndicatorT.math, IndicatorT.standards});
        assertEquals(true, array_isclose(new double[]{0.20290, 0.23188, 0.26087, 0.30435}, CS.measures(Att1)));
    }

    private static boolean array_isclose(double[] a, double[] b)
    {
        if (a.length != b.length) {
            return false;
        }
        double [] x = new double[a.length];
        for (int i = 0; i < a.length; i++)
        {
            x[i] = a[i] - b[i];
            if (!(Math.abs(x[i]) < 0.001))
            {
                return false;
            }
        }
        return true;
    }

}
