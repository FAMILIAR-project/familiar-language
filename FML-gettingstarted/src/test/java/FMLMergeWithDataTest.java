import fr.familiar.FMLTest;
import fr.familiar.operations.Mode;
import fr.familiar.variable.FeatureModelVariable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by macher1 on 16/04/2018.
 */
@RunWith(Parameterized.class)
public class FMLMergeWithDataTest extends FMLTest {


    /**
     * feature model (string specification with fml syntax)
     */
    protected String _strFm ;

    private static int fmID = 0;

    public FMLMergeWithDataTest(String strFm)
            throws Exception {
        _strFm = strFm;
    }



    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays
                .asList(new Object[][] {
                        { "FM (A : (B|C|D)+; ) " } ,
                        { "FM (A : (B|D|E) ; )" } ,
                        { "FM (A : (B|D)+; )" },
                        { "FM (A : (B|D|E|C) ; )" } ,
                        { "FM (A : [B] [D] [E] ; )" }
                });


    }

    @Test
    public void testMerge1() throws Exception {
        fmID++;
        FeatureModelVariable fmA = FM ("fm" + fmID, _strFm);

        FeatureModelVariable fmR = fmA.merge(fmA, Mode.StrictUnion);
        assertEquals(fr.familiar.variable.Comparison.REFACTORING, fmR.compare(fmA));
        //_shell.parse(fmID + " = " + _strFm + " \n");
    }









}
