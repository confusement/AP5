import org.junit.Test;
public class TestVultureBite {
    @Test(expected = VultureBiteException.class)
    public void test() throws GameWinnerException, CricketBiteException, VultureBiteException, SnakeBiteException, InfiniteLoopException, TrampolineException {
        Game gm = new Game(100,"kk");
        tile t = new tVulture();
        gm.getTrack().serTileAt(15,t);
        gm.moveToLoc(15);
    }
}
