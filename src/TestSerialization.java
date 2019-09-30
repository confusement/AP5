import org.junit.Test;
import java.io.IOException;
import static junit.framework.TestCase.assertEquals;
public class TestSerialization {
    @Test
    public void test() throws GameWinnerException, CricketBiteException, VultureBiteException, SnakeBiteException, InfiniteLoopException, TrampolineException, IOException {
        try{
            Game saved = new Game(100,"kk");
            saved.serialize();
            Game loaded = Game.deserialize("kk");
            assertEquals(saved.equals(loaded),true);
        }
        catch (Exception e)
        {

        }
    }
}
