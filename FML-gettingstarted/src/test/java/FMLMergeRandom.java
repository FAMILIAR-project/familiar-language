import fr.familiar.FMLTest;
import fr.familiar.operations.FMLMerger;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.Mode;
import fr.familiar.variable.FeatureModelVariable;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by macher1 on 16/04/2018.
 */
public class FMLMergeRandom extends FMLTest {



    @Test
    public void testRandUnion() throws Exception {


        FeatureModelVariable fmA = new FMLRandomGenerator(10).mkFM();
        FeatureModelVariable fmR = fmA.merge(fmA, Mode.StrictUnion);

        assertEquals(fr.familiar.variable.Comparison.REFACTORING, fmR.compare(fmA));
        FMLMerger mergeOperator = new FMLMergerBDD(fmA, fmA, fmA, fmA);

        FeatureModelVariable fmR2 = mergeOperator.union();
        assertEquals(fr.familiar.variable.Comparison.REFACTORING, fmR2.compare(fmA));
    }

    @Test
    public void testRandUnion2() throws Exception {


        // inputs
        FeatureModelVariable fmA = new FMLRandomGenerator(10).mkFM();
        FeatureModelVariable fmB = new FMLRandomGenerator(10).mkFM(); // ideally speaking I would like to have a mutation operator
        // we assume that root features "match" (name-based matching)
        fmA.renameFeature(fmA.root().name(), "COMMON_ROOT");
        fmB.renameFeature(fmB.root().name(), "COMMON_ROOT");
        assertEquals(fmA.root().name(), fmB.root().name());

        FeatureModelVariable fmR = fmA.merge(fmB, Mode.StrictUnion);
        FeatureModelVariable fmR2 = fmB.merge(fmA, Mode.StrictUnion);
        assertEquals(fr.familiar.variable.Comparison.REFACTORING, fmR.compare(fmR2));
        FMLMerger mergeOperator = new FMLMergerBDD(fmA, fmB);

        FeatureModelVariable fmR3 = mergeOperator.union();
        assertEquals(fr.familiar.variable.Comparison.REFACTORING, fmR2.compare(fmR3));
    }

    @Test
    public void testRandIntersection() throws Exception {


        FeatureModelVariable fmA = new FMLRandomGenerator(10).mkFM();
        FeatureModelVariable fmR = fmA.merge(fmA, Mode.Intersection);
        assertEquals(fr.familiar.variable.Comparison.REFACTORING, fmR.compare(fmA));
        FMLMerger mergeOperator = new FMLMergerBDD(fmA, fmA, fmA, fmA);

        FeatureModelVariable fmR2 = mergeOperator.intersection();
        assertEquals(fr.familiar.variable.Comparison.REFACTORING, fmR2.compare(fmA));
    }

    @Test
    public void testRandDiff() throws Exception {
        FeatureModelVariable fmA = new FMLRandomGenerator(10).mkFM();
        FeatureModelVariable fmR = fmA.mergeDiff(fmA);
        assertFalse(fmR.isValid());
    }
}
