package com.multicurrency.domain;

import java.util.Hashtable;

public class Bank {

    private Hashtable<Pair, Integer> rates = new Hashtable<Pair, Integer>();

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public void addRate(String from, String to, int rate) {

        rates.put(new Pair(from, to), new Integer(rate));

    }

    public int rate(String from, String to) {
        if (from.equals(to)) {
            return 1;
        }
        return rates.get(new Pair(from, to));
    }

    private class Pair {

        private String from;
        private String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }

            Pair pair = (Pair) other;

            return from.equals(pair.from)
                    && to.equals(pair.to);

        }

        @Override
        public int hashCode() {
            final int fromHashCode = from == null ? 0 : from.hashCode();
            final int toHashCode = to == null ? 0 : to.hashCode();
            return 31 * fromHashCode + toHashCode;

        }
    }
}
