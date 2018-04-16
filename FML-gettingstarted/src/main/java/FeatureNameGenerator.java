import java.util.Random;

/**
 * Created by macher1 on 16/04/2018.
 */
public class FeatureNameGenerator {


    static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";




    public String newFtName() {

        /// size of the string (random)
        Random r = new Random();
        int min = 3;
        int max = 10;
        int len = r.nextInt(max - min + 1) + min; // between min and max

        // random char pick
        Random rCh = new Random();

        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( alphabet.charAt( rCh.nextInt(alphabet.length()) ) );
        return sb.toString();
    }
}
