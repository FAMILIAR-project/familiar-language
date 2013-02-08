/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package gsd.synthesis;

import gsd.graph.ImplicationGraph;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

/**
 * The Class IGBuilder.
 *
 * @author Steven She (shshe@uwaterloo.ca)
 * @author Andrzej Wasowski
 */
public class IGBuilder {


    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(IGBuilder.class.getName());

    public static <T extends Comparable<T>> ImplicationGraph<T> build(Formula<T> f, BDDBuilder<T> builder) {
        return build (f.getBDD(), builder, f.getDomain());
    }


    protected static <T extends Comparable<T>> ImplicationGraph<T> build (BDD problem, BDDBuilder<T> builder, Set<T> domain) {
        ImplicationGraph<T> g = new ImplicationGraph<T>();

        for (T f : domain)
            g.addVertex(f);

        int[] support = builder.vars(domain);

        HashSet<Integer> set_support = new HashSet<Integer>();
        for (int i : support)
            set_support.add (i);

        BDDFactory B = problem.getFactory ();

        for (int i : support) {

            BDD temp = problem.id ().andWith (B.nithVar (i));


            Set<Integer> falsified;
            if (temp.isZero()) { // if B is true in all assignments then it is implied by all
                falsified = new HashSet<Integer>();
                falsified.addAll(set_support);
            } else falsified = findFalsified (temp,  Util.min(support), Util.max(support));
            temp.free ();
            falsified.retainAll (set_support);
            falsified.remove(i);

            for (int fal : falsified) {
                g.addEdge(builder.feature(fal), builder.feature(i));
            }
        }

        return g;
    }


    /**
     * Find all variables in the problem that are 0 in all satisfiable assignments
     * @param problem constraint representing the problem
     * @param min_var only consider variables with identifiers min_var or larger
     * @param max_var only consider variables with
     * @return
     */
    static public Set<Integer> findFalsified(BDD problem, int min_var, int max_var) {

        Set<Integer> value = new HashSet<Integer> ();
        ValidDomains vd = new ValidDomains (problem, min_var, max_var);
        for (int var = min_var; var <= max_var; ++var)
            if (vd.canBeZero (var) && !vd.canBeOne (var))
                value.add (var);
        return value;
    }

}