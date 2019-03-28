package logic;

/**
 * This class is used to perform all the JUnit tests in Tank Royale
 * 
 * @author Group 7
 * @version 1.0
 * @since 2019-02-28
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        MapTest.class, //test case 1
        WallTest.class  //test case 2
})
public class TestSuite {
    //normally, this is an empty class
}