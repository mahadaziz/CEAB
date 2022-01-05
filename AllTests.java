/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: AllTests file that runs the test cases files
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestAttributeT.class,	
   TestLOsT.class,
   TestCourseT.class,
   TestProgramT.class
})

public class AllTests
{
}
