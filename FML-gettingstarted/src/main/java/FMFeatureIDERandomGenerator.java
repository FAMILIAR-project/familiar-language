import de.ovgu.featureide.fm.core.Feature;
import fr.familiar.fm.featureide.FeatureIDEtoFML;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.FeatureModelVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by macher1 on 16/04/2018.
 */
public class FMFeatureIDERandomGenerator implements FMRandomGenerator {


    private static int maxChildren = 10;
    private int _numberOfFeatures;

    public FMFeatureIDERandomGenerator(int numberOfFeatures) {

        _numberOfFeatures = numberOfFeatures;
    }


    public de.ovgu.featureide.fm.core.FeatureModel genFeatureModel() {

        Random random = new Random();
        de.ovgu.featureide.fm.core.FeatureModel fm = new de.ovgu.featureide.fm.core.FeatureModel();
        List<Feature> leaves = new LinkedList<Feature>();
        leaves.add(fm.getFeature("C1"));
        int count = 1;
        while (count < _numberOfFeatures) {
            int parentIndex = random.nextInt(leaves.size());
            Feature parent = leaves.remove(parentIndex);
//            fm.renameFeature(parent.getName(), "A"
  //                  + parent.getName().substring(1));
            int childrenCount = random.nextInt(maxChildren) + 1;
            childrenCount = Math.min(childrenCount, _numberOfFeatures - count);
            for (int i = 1; i <= childrenCount; i++) {
                Feature child = new Feature(fm, "C" + (count + i));
                fm.addFeature(child);
                parent.addChild(child);
                leaves.add(child);
            }
            if (random.nextBoolean()) {
                parent.changeToAnd();
                for (Feature child : parent.getChildren())
                    child.setMandatory(random.nextBoolean());
            } else if (random.nextBoolean())
                parent.changeToOr();
            count += childrenCount;
        }
        fm.performRenamings();
        return fm;

    }

    @Override
    public FeatureModelVariable mkFM() {
        de.ovgu.featureide.fm.core.FeatureModel fmFeatureIDE = genFeatureModel();
        FeatureIDEtoFML toFML = new FeatureIDEtoFML(fmFeatureIDE);
        String strFmIDE = toFML.writeToString();
        gsd.synthesis.FeatureModel<String> fm = FMBuilder.getInternalFM(strFmIDE);
        return new FeatureModelVariable("", fm);
    }
}
