/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: Test Cases for the AttributeT ADT class
 */


import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class TestAttributeT
{

    private AttributeT ProbAnalysis;
    private AttributeT Know;
    private AttributeT Design;
    private AttributeT Duplicates;
    private AttributeT Empty;

    @Before
    public void setUp()
    {
        ProbAnalysis = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund});
        Know = new AttributeT("Knowledge Base for Engineering", new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow});
        Design = new AttributeT("Design", new IndicatorT[] {IndicatorT.desProcess, IndicatorT.desPrinciples,
                IndicatorT.openEnded, IndicatorT.ideaGeneration,
                IndicatorT.healthSafety, IndicatorT.standards});
        Duplicates = new AttributeT("Repeated Indicators", new IndicatorT[] {IndicatorT.math, IndicatorT.tools,
                IndicatorT.tools, IndicatorT.math, IndicatorT.tools, IndicatorT.math});
        Empty = new AttributeT("", new IndicatorT[]{});
    }

    @After
    public void tearDown()
    {
        ProbAnalysis = null;
        Know = null;
        Design = null;
        Duplicates = null;
        Empty = null;
    }

    @Test
    public void test_getName1()
    {
    	// Normal test case
        assertEquals("Problem Analysis", ProbAnalysis.getName());
    }

    @Test
    public void test_getName2()
    {
    	// Normal test case
        assertEquals("Knowledge Base for Engineering", Know.getName());
    }

    @Test
    public void test_getName3()
    {
    	// Normal test case
        assertEquals("Design", Design.getName());
    }

    @Test
    public void test_getName4()
    {
    	// Normal test case
        assertEquals("Repeated Indicators", Duplicates.getName());
    }

    @Test
    public void test_getName5()
    {
    	// Empty name test case
        assertEquals("", Empty.getName());
    }

    @Test
    public void test_getIndicators1()
    {
    	// Normal test case
        assertTrue(equalArray(ProbAnalysis.getIndicators(), new IndicatorT[] {IndicatorT.suitableFund, IndicatorT.assumpt}));
    }

    @Test
    public void test_getIndicators2()
    {
    	// Normal test case
        assertTrue(equalArray(ProbAnalysis.getIndicators(), new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund}));
    }

    @Test
    public void test_getIndicators3()
    {
    	// Normal test case
        assertTrue(equalArray(Design.getIndicators(), new IndicatorT[] {IndicatorT.standards, IndicatorT.desPrinciples,
                IndicatorT.desProcess, IndicatorT.ideaGeneration,
                IndicatorT.healthSafety, IndicatorT.openEnded}));
    }

    @Test
    public void test_getIndicators4()
    {
    	// Normal test case
        assertTrue(equalArray(Design.getIndicators(), new IndicatorT[] {IndicatorT.openEnded, IndicatorT.healthSafety,
                IndicatorT.ideaGeneration, IndicatorT.desProcess, IndicatorT.desPrinciples, IndicatorT.standards}));
    }

    @Test
    public void test_getIndicators5()
    {
    	// Empty Indicator test case
        assertTrue(equalArray(Empty.getIndicators(), new IndicatorT[] {}));
    }

    @Test
    public void test_duplicates1()
    {
    	// Duplicate Indicator Test
        assertTrue(equalArray(Duplicates.getIndicators(), new IndicatorT[] {IndicatorT.math, IndicatorT.tools}));
    }

    @Test
    public void test_duplicates2()
    {
    	// Duplicate Indicator Test
        assertTrue(equalArray(Duplicates.getIndicators(), new IndicatorT[] {IndicatorT.tools, IndicatorT.math}));
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
