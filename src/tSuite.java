import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({TestSnakeBite.class,TestCricketBite.class,TestVultureBite.class,TestGameWinner.class,TestTrampoline.class,TestSerialization.class})
public class tSuite {
    public static void main(String [] args)
    {
        Result res = JUnitCore.runClasses(tSuite.class);
        for(Failure f:res.getFailures())
            System.out.println(f.getMessage());
        System.out.println("All Test Case Passed");
    }
}
