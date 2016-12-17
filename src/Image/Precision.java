package Image;

/**
 * Created by kobako on 2016/12/17.
 * Just a game
 */
public enum Precision {
    Highest,Half,One_Third,Quarter,One_Fifth,One_Sixth;

    public int getValue(){
        return this.ordinal()+1;
    }
}
