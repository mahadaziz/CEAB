/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: Test Cases for the CourseT ADT class
 */


import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class TestCourseT
{

	private CourseT ME3;
    private CourseT FA3;
    private CourseT XB3;
    private CourseT CO3;

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
        ME3 = new CourseT("Software Engineering Design 1", ME3_indicators);
        FA3 = new CourseT("Discrete Math", FA3_indicators);
        XB3 = new CourseT("Binding Theory to Practice", XB3_indicators);
        CO3 = new CourseT("Data Structures and Algorithms", CO3_indicators);
    }

    @After
    public void tearDown()
    {
        ME3 = null;
        FA3 = null;
        XB3 = null;
        CO3 = null;
    }

    @Test
    public void test_getName1()
    {
    	// Normal test case
        assertEquals("Software Engineering Design 1", ME3.getName());
    }

    @Test
    public void test_getName2()
    {
    	// Normal test case
        assertEquals("Discrete Math", FA3.getName());
    }

    @Test
    public void test_getName3()
    {
    	// Normal test case
        assertEquals("Binding Theory to Practice", XB3.getName());
    }

    @Test
    public void test_getName4()
    {
    	// Normal test case
        assertEquals("Data Structures and Algorithms", CO3.getName());
    }

    @Test
    public void test_getIndicator1()
    {
    	// Normal test case
        assertTrue(equalArray(new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
                IndicatorT.suitableFund, IndicatorT.recogTheory, IndicatorT.modelSelect,
                IndicatorT.estOutcomes, IndicatorT.desProcess, IndicatorT.desPrinciples,
                IndicatorT.openEnded, IndicatorT.tools, IndicatorT.engInSoc,
                IndicatorT.awarePEO}, (ME3.getIndicators())));
    }

    @Test
    public void test_getIndicator2()
    {
    	// Normal test case
        assertTrue(equalArray(new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund}, (FA3.getIndicators())));
    }

    @Test
    public void test_getIndicators3()
    {
    	// Normal test case
        assertTrue(equalArray(new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow}, (XB3.getIndicators())));
    }

    @Test
    public void test_getIndicators4()
    {
    	// Normal test case
        assertTrue(equalArray(new IndicatorT[] {IndicatorT.desProcess, IndicatorT.desPrinciples,
                IndicatorT.openEnded, IndicatorT.ideaGeneration,
                IndicatorT.healthSafety, IndicatorT.standards}, (CO3.getIndicators())));
    }

    @Test
    public void test_addLO1()
    {
    	// Normal test case
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 1", 1, 2, 3, 4));
        for (LOsT outcome : ME3.getLOs(IndicatorT.awarePEO)) {
            assertEquals("topic 1", outcome.getName());
        }
    }

    @Test
    public void test_addLO2()
    {
    	// Two LO with the same name test case
        XB3.addLO(IndicatorT.specEngKnow, new LOsT("topic 5", 1, 2, 3, 4));
        XB3.addLO(IndicatorT.specEngKnow, new LOsT("topic 5", 5, 6, 7, 8));
        for (LOsT outcome : ME3.getLOs(IndicatorT.awarePEO)) {
            assertEquals("topic 5", outcome.getName());
        }
    }

    @Test
    public void test_addLO3()
    {
    	// Looking for wrong Indicator test case
        CO3.addLO(IndicatorT.assumpt, new LOsT("topic 1", 402, 324, 324, 32));
        for (LOsT outcome : ME3.getLOs(IndicatorT.awarePEO)) {
            assertEquals("", outcome.getName());
        }
    }

    @Test
    public void test_delLO1()
    {
    	// Deleting from empty indicator test case
        ME3.delLO(IndicatorT.estOutcomes, new LOsT("topic 1", 1, 2, 3, 4));
        for (LOsT outcome : ME3.getLOs(IndicatorT.estOutcomes)) {
            assertEquals("", outcome.getName());
        }
    }

    @Test
    public void test_delLO2()
    {
    	// Normal test case
        FA3.addLO(IndicatorT.assumpt, new LOsT("topic 2", 45, 548, 32, 593));
        FA3.addLO(IndicatorT.assumpt, new LOsT("topic 3", 423,543,654,6));
        FA3.delLO(IndicatorT.assumpt, new LOsT("topic 2", 45, 548, 32, 593));
        for (LOsT outcome : FA3.getLOs(IndicatorT.assumpt)) {
            assertEquals("topic 3", outcome.getName());

        }
    }

    @Test
    public void test_delLO3()
    {
    	// Adding two LO with the same name and then deleting name test case
        XB3.addLO(IndicatorT.standards, new LOsT("topic 5", 1, 2, 3, 4));
        XB3.addLO(IndicatorT.standards, new LOsT("topic 5", 5, 6, 7, 8));
        XB3.delLO(IndicatorT.standards, new LOsT("topic 5", 5, 6, 7, 8));
        for (LOsT outcome : ME3.getLOs(IndicatorT.standards)) {
            assertEquals("topic 5", outcome.getName());
        }
    }

    @Test
    public void test_delLO4()
    {
    	// Looking for wrong Indicator test case
        CO3.delLO(IndicatorT.assumpt, new LOsT("topic 1", 402, 324, 324, 32));
        for (LOsT outcome : ME3.getLOs(IndicatorT.awarePEO)) {
            assertEquals("", outcome.getName());
        }
    }

    @Test
    public void test_member1()
    {
    	// Normal test case
        LOsT L01 = new LOsT("topic 1", 1, 2, 3, 4);
        ME3.addLO(IndicatorT.awarePEO, L01);
        assertEquals(true, ME3.member(IndicatorT.awarePEO, new LOsT[]{L01}));
    }

    @Test
    public void test_member2()
    {
    	// Normal test case
        LOsT L01 = new LOsT("topic 1", 1, 2, 3, 4);
        LOsT LO2 = new LOsT("topic 2", 5, 6, 7, 8);
        ME3.addLO(IndicatorT.awarePEO, L01);
        assertEquals(false, ME3.member(IndicatorT.awarePEO, new LOsT[]{L01, LO2}));
    }

    @Test
    public void test_member3()
    {
    	// Normal test case
        LOsT L01 = new LOsT("topic 5", 1, 2, 3, 4);
        LOsT LO2 = new LOsT("topic 5", 5, 6, 7, 8);
        FA3.addLO(IndicatorT.suitableFund,L01);
        FA3.addLO(IndicatorT.suitableFund,LO2);
        assertEquals(false, FA3.member(IndicatorT.suitableFund, new LOsT[]{L01, LO2}));

    }

    @Test
    public void test_member4()
    {
    	// Member of wrong Indicator test case
        LOsT L01 = new LOsT("topic 1", 402, 324, 324, 32);
        CO3.addLO(IndicatorT.assumpt, L01);
        assertEquals(false, CO3.member(IndicatorT.healthSafety, new LOsT[]{L01}));
    }

    @Test
    public void test_member5()
    {
    	// Normal test case
        LOsT LO1 = new LOsT("topic 1", 1, 2, 3, 4);
        ME3.delLO(IndicatorT.estOutcomes, LO1);
        assertEquals(false, ME3.member(IndicatorT.estOutcomes, new LOsT[]{LO1}));
    }

    @Test
    public void test_member6()
    {
    	// Normal test case
        LOsT LO1 = new LOsT("topic 2", 45, 548, 32, 593);
        LOsT LO2 = new LOsT("topic 3", 423,543,654,6);
        FA3.addLO(IndicatorT.assumpt, LO1);
        FA3.addLO(IndicatorT.assumpt, LO2);
        FA3.delLO(IndicatorT.assumpt, LO1);
        assertEquals(true, FA3.member(IndicatorT.assumpt, new LOsT[]{LO2}));
    }

    @Test
    public void test_member7()
    {
    	// Normal test case
        LOsT LO1 = new LOsT("topic 2", 45, 548, 32, 593);
        LOsT LO2 = new LOsT("topic 3", 423,543,654,6);
        FA3.addLO(IndicatorT.assumpt, LO1);
        FA3.addLO(IndicatorT.assumpt, LO2);
        FA3.delLO(IndicatorT.assumpt, LO1);
        assertEquals(false, FA3.member(IndicatorT.assumpt, new LOsT[]{LO1, LO2}));
    }

    @Test
    public void test_member8()
    {
    	// Deleting an LO and then checking if it is a member test case
        LOsT LO1 = new LOsT("topic 5", 1, 2, 3, 4);
        LOsT LO2 = new LOsT("topic 5", 5, 6, 7, 8);
        XB3.addLO(IndicatorT.standards, LO1);
        XB3.addLO(IndicatorT.standards, LO2);
        XB3.delLO(IndicatorT.standards, LO2);
        assertEquals(false, XB3.member(IndicatorT.standards, new LOsT[]{LO1, LO2}));
    }

    @Test
    public void test_member9()
    {
    	// Member of wrong Indicator test case
        LOsT LO1 = new LOsT("topic 1", 402, 324, 324, 32);
        CO3.delLO(IndicatorT.assumpt, LO1);
        assertEquals(false, CO3.member(IndicatorT.awarePEO, new LOsT[]{LO1}));
    }


    @Test (expected = UnsupportedOperationException.class)
    public void test_measures()
    {
    	// Exception test case
        ME3.measures();
    }

    @Test
    public void test_indicator_measures1()
    {
    	// Indicator with no LO test case
        assertEquals(Arrays.toString(new double[]{0,0,0,0}), Arrays.toString(ME3.measures(IndicatorT.standards)));
    }

    @Test
    public void test_indicator_measures2()
    {
    	// Normal test case
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 1", 1, 2, 3, 4));
        assertEquals(Arrays.toString(new double[]{1,2,3,4}), Arrays.toString(ME3.measures(IndicatorT.awarePEO)));
    }

    @Test
    public void test_indicator_measures3()
    {
    	// Normal test case
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 1", 1, 2, 3, 4));
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 2", 5, 6, 7 , 8));
        assertEquals(Arrays.toString(new double[]{6,8,10,12}), Arrays.toString(ME3.measures(IndicatorT.awarePEO)));
    }

    @Test
    public void test_attribute_measures1()
    {
    	// Attribute with Indicator with no LO test case
        AttributeT Att1 = new AttributeT("", new IndicatorT[] {});
        assertEquals(Arrays.toString(new double[]{0,0,0,0}), Arrays.toString(ME3.measures(Att1)));
    }

    @Test
    public void test_attribute_measures2()
    {
    	// Normal test case
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 1", 1, 2, 3, 4));
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.awarePEO});
        assertEquals(Arrays.toString(new double[]{1,2,3,4}), Arrays.toString(ME3.measures(Att1)));
    }

    @Test
    public void test_attribute_measures3()
    {
    	// Normal test case
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 1", 1, 2, 3, 4));
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 2", 5, 6, 7 , 8));
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.awarePEO});
        assertEquals(Arrays.toString(new double[]{6,8,10,12}), Arrays.toString(ME3.measures(Att1)));
    }

    @Test
    public void test_attribute_measures4()
    {
    	// Attribute with two distinct indicators test case
        ME3.addLO(IndicatorT.awarePEO, new LOsT("topic 1", 1, 2, 3, 4));
        ME3.addLO(IndicatorT.math, new LOsT("topic 2", 5, 6, 7 , 8));
        AttributeT Att1 = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.awarePEO, IndicatorT.math});
        assertEquals(Arrays.toString(new double[]{6,8,10,12}), Arrays.toString(ME3.measures(Att1)));
    }

    public static boolean equalArray(Object[] a1, Object[] a2)
    {
        if (a1.length != a2.length)
        {
            return false;
        }
        List<Object> list1 = Arrays.asList(a1);
        List<Object> list2 = Arrays.asList(a2);
        for (Object item : list1)
        {
            if (!list2.contains(item))
                return false;
        }
        return true;
    }

}
