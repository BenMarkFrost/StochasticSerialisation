package com.company.Data;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is the spot object that will be instantiated and serialised.
 */
public class Spot implements Serializable {

    private static final long serialVersionUID = 1L;

    public double value = ThreadLocalRandom.current().nextDouble(-1, 1);

}
