import org.junit.Test;
public class TestGameWinner {
    @Test(expected = GameWinnerException.class)
    public void test() throws GameWinnerException, CricketBiteException, VultureBiteException, SnakeBiteException, InfiniteLoopException, TrampolineException {
        Game gm = new Game(100,"kk");
        gm.checkGameWinning(105);
    }
}
