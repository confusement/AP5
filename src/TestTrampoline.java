import org.junit.Test;
public class TestTrampoline {
    @Test(expected = TrampolineException.class)
    public void test() throws GameWinnerException, CricketBiteException, VultureBiteException, SnakeBiteException, InfiniteLoopException, TrampolineException {
        Game gm = new Game(100,"kk");
        tile t = new tTrampoline();
        gm.getTrack().serTileAt(1,t);
        gm.moveToLoc(1);
    }
}
