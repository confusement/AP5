import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
class SnakeBiteException extends Exception{
    public SnakeBiteException()
    {
        super("SnakeBiteException");
    }
}
class CricketBiteException extends Exception{
    public CricketBiteException(){super("CricketBiteException");}
}
class VultureBiteException extends Exception{
    public VultureBiteException()
    {
        super("VultureBiteException");
    }
}
class TrampolineException extends Exception{
    public TrampolineException()
    {
        super("TrampolineException");
    }
}
class GameWinnerException extends Exception{
    public GameWinnerException()
    {
        super("GameWinnerException");
    }
}
class InfiniteLoopException extends Exception{
    public InfiniteLoopException()
    {
        super("InfiniteLoopException");
    }
}
class Track implements Serializable{
    private tile tilesAt[];
    private int trackSize;
    private final int X,Y,Z,T;
    public int getTrackSize() {
        return trackSize;
    }
    public tile[] getTileArr()
    {
        return this.tilesAt;
    }
    @Override
    public boolean equals(Object other)
    {
        if(other.getClass() == this.getClass())
        {
            Track o = (Track)other;
            if(this.X==o.X && this.Y==o.Y && this.Z==o.Z && this.T==o.T)
            {
                if(this.getTileArr().length==o.getTileArr().length)
                {
                    System.out.println("same");
                    boolean ans = true;
                    for(int pt=0;pt<this.getTileArr().length;pt++)
                    {
                        if(!this.getTileArr()[pt].equals(o.getTileArr()[pt]))
                            ans=false;
                    }
                    return ans;
                }
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }
    public tile getTileAt(int loc)
    {
        if(loc>this.trackSize)
            return null;
        else
            return this.tilesAt[loc-1];
    }
    public void serTileAt(int loc,tile t1)
    {
        if(loc<=this.trackSize)
            this.tilesAt[loc-1]=t1;
    }
    private void addNRandomTiles() throws Exception {
        int cnt=0;
        if(this.X+this.Y+this.Z+this.T<=this.trackSize)
        {
            Random rb = new Random();
            int x=this.X,y=this.Y,z=this.Z,t=this.T;
            while(x>0)
            {
                cnt++;
                int h = rb.nextInt(this.trackSize);
                if(this.tilesAt[h]==null)
                {
                    this.tilesAt[h]=new tSnake();
                    x--;
                }
            }
            while(y>0)
            {
                cnt++;
                int h = rb.nextInt(this.trackSize);
                if(this.tilesAt[h]==null)
                {
                    this.tilesAt[h]=new tCricket();
                    y--;
                }
            }
            while(z>0)
            {
                cnt++;
                int h = rb.nextInt(this.trackSize);
                if(this.tilesAt[h]==null)
                {
                    this.tilesAt[h]=new tVulture();
                    z--;
                }
            }
            while(t>0)
            {
                cnt++;
                int h = rb.nextInt(this.trackSize);
                if(this.tilesAt[h]==null)
                {
                    this.tilesAt[h]=new tTrampoline();
                    t--;
                }
            }
            for(int l=0;l<this.trackSize;l++)
            {
                if(this.tilesAt[l]==null){this.tilesAt[l]=new tWhite();}
            }
            System.out.println("Taken: "+Integer.toString(cnt));
        }
        else{
            throw new Exception("noSpaceonTrackException");
        }
    }
    public Track(int n)
    {
        this.trackSize = n;
        tilesAt = new tile[n];
        Random rd = new Random();
        int InvfreqXYZ=10;
        int InvfreqT=4;
        this.X = Math.abs((int)((rd.nextGaussian()*3)+this.trackSize/InvfreqXYZ));
        this.Y = Math.abs((int)((rd.nextGaussian()*3)+this.trackSize/InvfreqXYZ));
        this.Z = Math.abs((int)((rd.nextGaussian()*3)+this.trackSize/InvfreqXYZ));

        tile.RandomiseABCD(this.trackSize);
        String formatXYZ = Integer.toString(this.X)+", "+Integer.toString(this.Y)+", "+Integer.toString(this.Z);
        String formatABC = Integer.toString(tile.A)+", "+Integer.toString(tile.B)+", "+Integer.toString(tile.C);
        System.out.println("Danger: There are "+formatXYZ+" numbers of Snakes, Cricket, and Vultures respectively on your track!");
        System.out.println("Danger: Each Snake, Cricket, and Vultures can throw you back by "+formatABC+" number of Tiles respectively!");

        this.T = (int)((rd.nextGaussian()*3)+this.trackSize/InvfreqT);
        System.out.println("Good News: There are "+Integer.toString(this.T)+" number of Trampolines on your track!");
        System.out.println("Good News: Each Trampoline can help you advance by "+Integer.toString(tile.D)+" number of Tiles");
        try{
            addNRandomTiles();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
//        for(int l=0;l<this.trackSize;l++)
//        {
//            System.out.println(this.tilesAt[l].getType());
//        }
    }
}
class Game implements Serializable{
    private Track track;
    private user player;
    private int checkPoint;
    private int nextCheckPoint;
    public Track getTrack() {
        return track;
    }
    public void setTrack(Track track) {
        this.track = track;
    }
    public user getPlayer() {
        return player;
    }
    public void setPlayer(user player) {
        this.player = player;
    }
    public int getCheckPoint() {
        return checkPoint;
    }
    public void setCheckPoint(int checkPoint) {
        this.checkPoint = checkPoint;
    }
    public int getCurRoll() {
        return curRoll;
    }
    public void setCurRoll(int curRoll) {
        this.curRoll = curRoll;
    }
    private int TSB,TCB,TVB,TTB;
    private int curRoll;

    public int getNextCheckPoint() {
        return nextCheckPoint;
    }

    @Override
    public boolean equals(Object other)
    {
        if(other.getClass() == this.getClass())
        {
            Game o = (Game)other;
            if(o.getTrack().equals(this.track) && this.player.equals(o.getPlayer()) && this.checkPoint==o.getCheckPoint() && this.nextCheckPoint==o.getNextCheckPoint())
                return true;
            else
                return false;
        }
        else
            return false;
    }
    public void serialize() throws IOException
    {
        try{
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(this.player.getName()));
            o.writeObject(this);
            o.close();
        }
        catch (Exception e)
        {
            System.out.println("File Error");
        }
    }
    public static Game deserialize(String username)
    {
        Game curGame = null;
        try{
            ObjectInputStream i = new ObjectInputStream(new FileInputStream(username));
            curGame = (Game)i.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("File Error");
        }
        return curGame;
    }
    public Game(int tSize,String Username)
    {
        System.out.println("Setting up the race track...");
        this.track = new Track(tSize);
        this.player = new user(Username);
        this.checkPoint=0;
        this.nextCheckPoint=this.track.getTrackSize()/4;
        this.TSB=0;
        this.TCB=0;
        this.TVB=0;
        this.TTB=0;
        this.curRoll = 1;
        System.out.println("Game created");
    }
    public void checkGameWinning(int destination) throws GameWinnerException
    {
        if(destination>=this.track.getTrackSize())
            throw new GameWinnerException();
    }
    public void moveToLoc(int destination)throws GameWinnerException,
            TrampolineException,SnakeBiteException,CricketBiteException,VultureBiteException
    {
            tile destTile;
            destTile = this.track.getTileAt(destination);
            if(destTile==null)
            {
                destTile=this.track.getTileAt(this.track.getTrackSize());
            }
            int shakeAm = destTile.shake();
            int finalLoc = this.player.getLocation()+shakeAm;
            this.checkGameWinning(finalLoc);
            if(finalLoc<1)
            {
                finalLoc=1;
                System.out.println(this.player.getName()+" moved to Tile 1 as it can’t go back further");
            }
            else{
                System.out.println(this.player.getName()+" moved to Tile "+finalLoc);
            }
            this.player.setLocation(finalLoc);
            if(destTile instanceof tSnake)
                throw new SnakeBiteException();
            if(destTile instanceof tCricket)
                throw new CricketBiteException();
            if(destTile instanceof tVulture)
                throw new VultureBiteException();
            if(destTile instanceof tTrampoline)
                throw new TrampolineException();
    }
    public void playGame() throws GameWinnerException,InfiniteLoopException,
            TrampolineException,SnakeBiteException,CricketBiteException,VultureBiteException
    {
        System.out.println("Starting the game with"+this.player.getName()+"at Tile-"+Integer.toString(this.player.getLocation()));
        System.out.println("Control transferred to Computer for rolling the Dice for "+this.player.getName());
        System.out.println("Hit enter to start the game");
        Main.sc.nextLine();
        String temp=Main.sc.nextLine();
        System.out.println("Game Started ======================>");
        String rollPrefix = this.player.getName()+" rolled ";
        boolean flag=true;
        int roll;
        Random rb = new Random();
        boolean isInCage=true;
        while(flag)
        {
//            if(curRoll>100)
//                flag=false;
            if(curRoll>4*this.track.getTrackSize() && this.track.getTrackSize()>10)
            {
                throw new InfiniteLoopException();
            }
            int curTile = this.player.getLocation();
            roll = rb.nextInt(6)+1;
            curRoll++;
            System.out.print("[Roll-"+Integer.toString(curRoll)+"]: ");
            System.out.print(rollPrefix+roll+" at Tile-"+curTile+", ");
            if(roll!=6 && isInCage)
            {
                System.out.println("OOPs you need 6 to start");
            }
            else if(roll==6 && isInCage)
            {
                System.out.println("You are out of the cage! You get a free roll");
                isInCage=false;
            }
            else{
                try{
                    int destLoc = curTile+roll;
                    System.out.println("landed on Tile-"+destLoc);
                    System.out.println("Trying to shake the Tile-"+destLoc);
                    if(destLoc>=this.track.getTrackSize())
                        throw new GameWinnerException();
                    this.player.setLocation(destLoc);
                    this.moveToLoc(destLoc);
                    if(destLoc>=this.nextCheckPoint)
                    {
                        this.checkPoint++;
                        if(checkPoint==2)
                            this.nextCheckPoint=this.track.getTrackSize()/2;
                        else if(checkPoint==3)
                            this.nextCheckPoint=this.track.getTrackSize()/4*3;
                        else if(checkPoint==4)
                            this.nextCheckPoint=this.track.getTrackSize();
                        System.out.println(this.checkPoint);
                        System.out.println(this.nextCheckPoint);
                        System.out.println("Do you want to save game or continue");
                        System.out.println("1.Save Game and Exit");
                        System.out.println("2.Continue Playing game");
                        int ansQues = Main.sc.nextInt();
                        if(ansQues==1)
                        {
                            this.serialize();
                            flag=false;
                            break;
                        }
                        else{
                            System.out.println("Continue Playing");
                        }
                    }
                }
                catch (GameWinnerException g)
                {
                    System.err.println(g.getMessage());
                    System.out.println(this.player.getName()+" wins the race in "+(curRoll-1)+" rolls");
                    System.out.println("Total Snake Bites = "+TSB);
                    System.out.println("Total Vulture Bites = "+TVB);
                    System.out.println("Total Cricket Bites = "+TCB);
                    System.out.println("Total Trampolines = "+TTB);
                    break;
                }
                catch (Exception o)
                {
                    System.err.println(o.getMessage());
                    if(o instanceof SnakeBiteException)
                        TSB++;
                    else if(o instanceof CricketBiteException)
                        TCB++;
                    else if(o instanceof VultureBiteException)
                        TVB++;
                    else if(o instanceof TrampolineException)
                        TTB++;
                }
            }
//            if(this.player.getLocation()==1)
//                isInCage=true;
        }
    }
}
class user implements Serializable {
    private final String name;
    private int location;
    @Override
    public boolean equals(Object other)
    {
        if(other.getClass() == this.getClass())
        {
            user o = (user)other;
            if(o.name.equals(this.name) && o.getLocation()==this.getLocation())
                return true;
            else
                return false;
        }
        else
            return false;
    }
    public int getLocation() {
        return location;
    }
    public void setLocation(int location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public user(String nm)
    {
        this.name=nm;
        this.location=1;
    }
}
abstract class tile implements Serializable{
    protected String type;
    static int A,B,C,D;
    @Override
    public boolean equals(Object other)
    {
        if(other.getClass() == this.getClass())
        {
            tile o = (tile)other;
            if(o.getType().equals(this.getType()))
                return true;
            else
                return false;
        }
        else{
            return false;
        }
    }
    public static void RandomiseABCD(int TrackSize)
    {
        Random rd = new Random();
        tile.A=1+rd.nextInt(10);
        tile.B=1+rd.nextInt(10);
        tile.C=1+rd.nextInt(10);
        tile.D=1+rd.nextInt(10);
    }
    public String getType()
    {
        return this.type;
    }
    abstract int shake();
}
class tWhite extends tile{
    {
        this.type="White";
    }
    public int shake()
    {
        System.out.println("I am a White tile!");
        return 0;
    }
}
class tSnake extends tile{
    {
        this.type="Snake";
    }
    public int shake()
    {
        System.out.println("Hiss...! I am a Snake, you go back "+ Integer.toString(tile.A) +" tiles!");
        return -tile.A;
    }
}
class tCricket extends tile{
    {
        this.type="Cricket";
    }
    public int shake()
    {
        System.out.println("Chirp...! I am a Cricket, you go back "+Integer.toString(tile.B)+" tiles!");
        return -tile.B;
    }
}
class tVulture extends tile{
    {
        this.type="Vulture";
    }
    public int shake()
    {
        System.out.println("Yapping...! I am a Vulture, you go back "+Integer.toString(tile.C)+" tiles!");
        return -tile.C;
    }
}
class tTrampoline extends tile{
    {
        this.type="Trampoline";
    }
    public int shake()
    {
        System.out.println("PingPong! I am a Trampoline, you advance "+Integer.toString(tile.D)+" tiles!");
        return tile.D;
    }
}
public class Main {
    public static Scanner sc = new Scanner(System.in);;
    public static void main(String[] args) throws GameWinnerException, InfiniteLoopException,
            SnakeBiteException, CricketBiteException, VultureBiteException, TrampolineException, FileNotFoundException {
        while(true)
        {
            Game curGame = null;
            System.out.println("1.Start New Game");
            System.out.println("2.Load Game");
            int ansSel=0;
            try{
                ansSel = sc.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Wrong input");
                Main.sc.nextLine();
                continue;
            }
            if(ansSel==1)
            {
                try
                {
                    System.out.println("Enter total number of tiles on the race track (length)");
                    int ansN = sc.nextInt();
                    System.out.println("Enter the Player Name");
                    String ansName = Main.sc.next();
                    curGame = new Game(ansN,ansName);
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Wrong Input");
                    Main.sc.nextLine();
                    continue;
                }
            }
            else if(ansSel==2)
            {
                System.out.println("Enter your name");
                String nm = sc.next();
                curGame = Game.deserialize(nm);
            }
            else{
                System.out.println("Wrong Input Entered");
                continue;
            }
            System.out.println("Now Playing Game");
            try{
                curGame.playGame();
            }
            catch (InfiniteLoopException e)
            {
                System.out.println("Infinite loop Occurred, Terminating Game");
            }
            catch (GameWinnerException e)
            {
                System.out.println("Game Won");
            }
            catch (NullPointerException e)
            {
                System.out.println("Game not Loaded Error");
            }
        }
    }
}
