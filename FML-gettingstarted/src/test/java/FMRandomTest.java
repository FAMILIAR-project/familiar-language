import fr.familiar.FMLTest;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.FeatureModelVariable;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by macher1 on 16/04/2018.
 */
public class FMRandomTest extends FMLTest {

    @Test
    public void test1() throws Exception {

        int nbFeatures = 100;
        FMLRandomGenerator fmRandGen = new FMLRandomGenerator(nbFeatures);

        FeatureModelVariable fmv = fmRandGen.mkFM();
        assertEquals(fmv.features().size(), nbFeatures + 1);

        System.out.println("fmv=" + fmv);
        System.out.println("fmv=" + fmv.depth());

        FeatureModelVariable fmv2 = new FeatureModelVariable("", FMBuilder.getInternalFM(fmv.toString()));

        assertEquals(fmv.root().name(), fmv2.root().name());


    }

    @Ignore
    @Test
    public void testFeatureIDE1() {

        int nbFeatures = 100;
        FMRandomGenerator fmRandGen = new FMFeatureIDERandomGenerator(nbFeatures);

        FeatureModelVariable fmv = fmRandGen.mkFM();
        assertNotNull(fmv);
        assertEquals(fmv.features().size(), nbFeatures);

    }


}
