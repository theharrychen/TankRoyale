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
        MapTest.class, //TestCase #1
        WallTest.class,  //TestCase #2
        KinematicEntityTest.class, //TestCase #3
        TankTest.class, //TestCase #4
        GameEntityTest.class, //TestCase #5
        TextBasedTest.class //TestCase #6

})
public class TestSuite {
    //normally, this is an empty class
}
