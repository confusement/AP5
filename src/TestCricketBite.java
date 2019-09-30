import org.junit.Test;
public class TestCricketBite {
    @Test(expected = CricketBiteException.class)
    public void test() throws GameWinnerException, CricketBiteException, VultureBiteException, SnakeBiteException, InfiniteLoopException, TrampolineException {
        Game gm = new Game(100,"kk");
        tile t = new tCricket();
        gm.getTrack().serTileAt(9,t);
        gm.moveToLoc(9);
    }
}
