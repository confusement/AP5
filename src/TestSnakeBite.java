import org.junit.Test;
public class TestSnakeBite {
    @Test(expected = SnakeBiteException.class)
    public void test() throws GameWinnerException, CricketBiteException, VultureBiteException, SnakeBiteException, InfiniteLoopException, TrampolineException {
        Game gm = new Game(100,"kk");
        tile t = new tSnake();
        gm.getTrack().serTileAt(5,t);
        gm.moveToLoc(5);
    }
}
