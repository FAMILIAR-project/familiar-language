import fr.familiar.FMLTest;
import fr.familiar.FeatureModelLoader;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by macher1 on 16/11/2017.
 */
public class FMLGettingStarted extends FMLTest {


    @Test
    public void testHelloWorld() throws Exception {
        FeatureModelVariable fmv = FM ("fm1", "FM (A : [B] [C] ;)");
        FeatureModelLoader loader = new FeatureModelLoader(_shell, _builder);
        assertEquals(4.0, fmv.counting(), 0.0);

        FeatureModelVariable fmv2 = new FeatureModelVariable ("fm2", FMBuilder.getInternalFM("A : [B] [C] ;"));
        assertEquals(Comparison.REFACTORING, fmv2.compare(fmv));
        
        // assertEquals(201, loader.getAllSPLOTFeatureModels().size());
    }
}

