import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.*;

import java.util.Random;
import java.util.Set;

/**
 * Created by macher1 on 16/04/2018.
 */
public class FMLRandomGenerator implements FMRandomGenerator {


    private static int maxChildren;
    private int _nbFeatures;

    private FeatureNameGenerator ftNameGen = new FeatureNameGenerator();


    public FMLRandomGenerator(int nbFeatures) {
        _nbFeatures = nbFeatures;
    }


    @Override
    public FeatureModelVariable mkFM() {

           FeatureGraph<String> fg = mkFeatureDiagram();
           FeatureModel<String> fm = new FeatureModel<String>(fg);
           return new FeatureModelVariable("", fm);

    }

    private FeatureGraph<String> mkFeatureDiagram() {
        FeatureGraph<String> fg = FeatureGraphFactory.mkStringFactory().mkTop();

        int nbChangeFtParent = 4; // max depth can be an interesting parameter

        // make hierarchy
        Random random = new Random();
        FeatureNode<String> currentFtParent = _mkFeatureNode(ftNameGen.newFtName());
        fg.addVertex(currentFtParent);
        fg.addEdge(currentFtParent, fg.getTopVertex(), FeatureEdge.HIERARCHY);
        fg.addEdge(currentFtParent, fg.getTopVertex(), FeatureEdge.MANDATORY);
        int count = 1;
        while (count <= _nbFeatures) {
            FeatureNode<String> fn = _mkFeatureNode(ftNameGen.newFtName());
            fg.addVertex(fn);
            fg.addEdge(fn, currentFtParent, FeatureEdge.HIERARCHY);

            if (random.nextInt(nbChangeFtParent) == nbChangeFtParent - 1) // can be improved (we can pick an existing feature as parent)
                currentFtParent = fn;

            count++;
        }

        // make variability

        Set<FeatureNode<String>> fts = fg.vertices();

        final int MANDATORY = 0;
        final int OPTIONAL = 1;
        final int XOR = 2;
        final int OR = 3;

        Random randomVar = new Random();
        for (FeatureNode<String> ft : fts) {

            int vInformation = randomVar.nextInt(4);
            Set<FeatureNode<String>> chFts = fg.children(ft);
            if (chFts.size() > 1) {
                if (vInformation == XOR ||vInformation == OR) {
                    Set<FeatureNode<String>> srs = chFts;
                    if (srs.size() > 1) { // more than one
                      fg.addEdge(srs, ft, vInformation == XOR ? FeatureEdge.XOR : FeatureEdge.OR);
                    }

                }

            }

            if (chFts.size() != 0)
                if (vInformation == MANDATORY) {
                   fg.addEdge(chFts.iterator().next(), ft, FeatureEdge.MANDATORY);

                }
                else if (vInformation == OPTIONAL) {
                    // nothing
                }
        }

        // TODO: make constraints

        /*for (FMLFeatureEdge fe : edgesToAdd) {
            if (!fe.hasMultipleSources)
                fg.addEdge(fe._sr, fe._tg, fe._edgeType);
            else {
                System.err.println("Adding group! " + fe._srs);
                fg.removeAllEdges(fg.findEdges(fe._srs, fe._tg, FeatureEdge.HIERARCHY));

                fg.addEdge(fe._srs, fe._tg, fe._edgeType);
                // fg.addEdge(fe._srs, fe._tg, FeatureEdge.HIERARCHY);
            }
        }*/


        return fg;
    }

    private FeatureNode<String>_mkFeatureNode (String ftName) {
        return new FeatureNode<String>(ftName);
    }


    // workaround
    @Deprecated
    private class FMLFeatureEdge {

        // can be multiple sources
        private FeatureNode<String> _sr;
        private Set<FeatureNode<String>> _srs;

        private boolean hasMultipleSources = false;

        private FeatureNode<String> _tg;
        private int _edgeType;

        public FMLFeatureEdge(FeatureNode<String> sr, FeatureNode<String> tg, int edgeType) {
            _sr = sr;
            _tg = tg;
            _edgeType = edgeType;
            hasMultipleSources = false;
        }

        public FMLFeatureEdge(Set<FeatureNode<String>> srs, FeatureNode<String> tg, int edgeType) {
            _srs = srs;
            _tg = tg;
            _edgeType = edgeType;
            hasMultipleSources = true;
        }
    }
}
