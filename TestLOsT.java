/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: Test Cases for the LOsT ADT class
 */


import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestLOsT
{

    private LOsT L01;
    private LOsT L02;
    private LOsT L03;
    private LOsT L04;
    private LOsT L05;

    @Before
    public void setUp()
    {
        L01 = new LOsT("topic 1", 1, 2, 3, 4);
        L02 = new LOsT("topic 2", 45, 548, 32, 593);
        L03 = new LOsT("topic 2", 593, 32, 548, 45);
    }

    @After
    public void tearDown()
    {
        L01 = null;
        L02 = null;
        L03 = null;
        L04 = null;
        L05 = null;
    }

    @Test
    public void test_getName1()
    {
    	// Normal test case
        assertEquals("topic 1", L01.getName());
    }

    @Test
    public void test_getName2()
    {
    	// Normal test case
        assertEquals("topic 2", L02.getName());
    }

    @Test
    public void test_getName3()
    {
    	// Normal test case
        assertEquals("topic 2", L03.getName());
    }

    @Test
    public void test_equals1()
    {
    	// Normal test case
        assertEquals(false, L01.equals(L02));
    }

    @Test
    public void test_equals2()
    {
    	// Normal test case
        assertEquals(true, L02.equals(L03));
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_negative()
    {
    	// Exception with negative number test case
        L04 = new LOsT("topic 4", -1, 0, 543, 99);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_all_zeros()
    {
    	// Exception with all zeros test case
        L05 = new LOsT("topic 5", 0, 0, 0, 0);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void test_indicator_measures()
    {
    	// Exception test case
        L01.measures(IndicatorT.ideaGeneration);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void test_attribute_measures()
    {
    	// Exception test case
        L01.measures(new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund}));
    }

    @Test
    public void test_measures1()
    {
    	// Normal test case
        assertEquals(Arrays.toString(new double[]{1.0, 2.0, 3.0, 4.0}), Arrays.toString(L01.measures()));
    }

    @Test
    public void test_measures2()
    {
    	// Normal test case
        assertEquals(Arrays.toString(new double[]{45.0, 548.0, 32.0, 593.0}), Arrays.toString(L02.measures()));
    }

}
