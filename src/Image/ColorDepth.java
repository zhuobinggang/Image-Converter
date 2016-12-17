package Image;

/**
 * Created by kobako on 2016/12/17.
 * Just a game
 */
public enum ColorDepth {
    Super_Light,Hight_Light,Light,Slight_Light,Grey,Drak;

    public int getValue(){
        return 90+this.ordinal()*20;
    }
}
