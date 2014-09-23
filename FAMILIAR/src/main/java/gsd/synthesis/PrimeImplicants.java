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

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

/**
 * @author Wasowski
 */
public class PrimeImplicants implements Iterable<Implicant> {
    protected BDD primes = null;
    protected BDD support = null;
    protected int max_var = -1;
    protected BDDFactory B = null;
    protected Map<Pair, BDD> _cache = new HashMap<Pair, BDD>();


    public PrimeImplicants(BDD formula, BDD theory) {
        mkPrimeImplicantsWith(formula.id(), theory.id(),
                formula.support().and(theory.support()));

    }


    public PrimeImplicants(BDD formula) {
        this.B = formula.getFactory();
        mkPrimeImplicantsWith(formula.id(), B.one(), formula.support());
    }


    public PrimeImplicants(BDD formula, BDD theory, BDD support) {
        mkPrimeImplicantsWith(formula.id(), theory.id(), support.id());
    }


    public PrimeImplicants() {
        super();
    }


    /**
     * FIXME: !!! this algorithm may be potentially exponential. One should use
     * caching to avoid computing the same primes all over again. We will not see it
     * when reconstructing the feature models probably, but one can give formulae
     * with extremely many primes.
     * <p/>
     * Also we should probably be use simplify instead of restrict. Using restrict
     * we actually record the path that we come from in the bdd, eliminating a lot
     * caching possibilities.
     *
     * @param f
     * @param last_var
     * @return
     */
    protected BDD negativePrimesWith(BDD f, int last_var) {

        // Logger.getAnonymousLogger().info("last_var: " + last_var +
        // " negate: " + f);

        if (f.isOne())
            return negateFromTo(last_var + 1, max_var + 1);

        if (f.isZero())
            return f;

        int v = f.var();
        assert last_var < v;

        BDD negs = negateFromTo(last_var + 1, v);
        BDD result = B.one();

        BDD fv = f.restrict(B.ithVar(v)); // freed
        BDD fnv = f.restrict(B.nithVar(v));// freed
        BDD v2 = B.ithVar(v * 2); // freed
        BDD nv2 = v2.not();
        BDD nv2succ = B.nithVar(v * 2 + 1); // freed
        f.free();
        f = null;

        BDD without_v = negativePrimesWith(fnv.and(fv), v);
        result.andWith(without_v.and(nv2)).andWith(negs.id());
        nv2.free();
        nv2 = null;

        result.orWith(
                negativePrimesWith(fnv, v).andWith(without_v.not()).andWith(
                        v2).andWith(nv2succ)).andWith(negs);

        fv.free();
        fv = null;

        // The part below is not needed as we are only interested in implicants
        // with positive occurrences.
        // result.orWith(negativePrimesWith(fv).andWith(tempnot).andWith(B.ithVar(2
        // * v))
        // .andWith(B.ithVar(2 * v + 1)));

        return result;
    }

    public boolean equals(PrimeImplicants pi) {

        return this.primes.equals(pi.primes)
                && this.support.equals(pi.support)
                && this.max_var == pi.max_var;
    }

    public boolean equals(Object pi) {
        return this.equals((PrimeImplicants) pi);
    }

    public void free() {

        primes.free();
        support.free();

    }

    public BDD getBDD() {

        return primes;
    }

    public Iterator<Implicant> iterator() {

        assert primes != null;
        return new ImplicantsIterator(this.primes, this.support);
    }

    protected BDD negateFromTo(int from, int to) {

        BDD result = B.one();
        for (int i = from; i < to; ++i)
            result.andWith(B.nithVar(i * 2));
        return result;
    }

    public void freeCache() {
        //	System.err.println("Cache size: " + _cache.size());
        for (Map.Entry<Pair, BDD> en : _cache.entrySet()) {
            en.getKey()._f.free();
            en.getValue().free();
        }
        _cache.clear();
    }

    protected void mkPrimeImplicantsWith(BDD formula, BDD theory, BDD support) {

        this.support = support.id();

        this.B = formula.getFactory();

        int[] isup = support.scanSet();
        max_var = Util.max(isup);

        if (B.varNum() < 3 * max_var)
            B.setVarNum(3 * max_var);

        this.primes = this.negativePrimesWith(theory.imp(formula), -1);

        // why not? this.primes = negativePrimesWith (formula.and (theory), -1);
        // 2009-03-11 This is actually correct, or at least exactly the same as
        // in the SPLC09 paper. Well, essentially theory.imp(! formula) and
        // and theory.and (formula) have the same implicants. We do not do the
        // negation here, becasue the formula is already given in a negated
        // form.

        // FIXME!: we should actually compute the *implicants* of phi as a
        // metaprduct
        // and conjoin them in, to avoid this stupid enumeration and checkin
        // implication of phi. This should! be done for camera ready.

        // It seems that we do not have to remove any of the primes, because
        // in our specific application (where formula is a negated literal)
        // there will be precisely one vacous implicant, which will not be
        // generated as it is positive.

    }


    protected static class ImplicantsIterator implements Iterator<Implicant> {

        ListIterator<byte[]> curr;
        int[] support = null;


        /**
         * @param support Support of the BDD representing the formula (and theory, so
         *                the problem). Not the support of the BDD representing the
         *                metaproduct
         */
        @SuppressWarnings("unchecked")
        protected ImplicantsIterator(BDD primes, BDD support) {

            assert support != null;
            // absolutely stupid interface of javabdd
            this.curr = primes.allsat().listIterator();
            assert curr != null;
            this.support = support.scanSet();
            if (this.support == null)
                this.support = new int[0];
        }


        public boolean hasNext() {
            if (!curr.hasNext())
                return false;

            boolean isNextNull = curr.next() == null;
            curr.previous();
            return !isNextNull;
        }


        public Implicant next() {

            return new Implicant((byte[]) curr.next(), support, true);
        }


        public void remove() {

            assert false; // not implemented
        }
    }

    protected static class Pair {
        public int _last_var;
        private final BDD _f;

        public Pair(BDD f, int last) {
            _f = f;
            _last_var = last;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                Pair p = (Pair) obj;
                return _f.equals(p._f) && _last_var == p._last_var;
            }
            assert false;
            return false;
        }

        @Override
        public int hashCode() {
            return _f.hashCode() + 13 * _last_var;
        }
    }
}
