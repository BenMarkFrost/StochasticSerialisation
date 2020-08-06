package com.company.Data;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class generates a random value and is passed to the next process in chain by the network controller.
 */
public class Spot implements Serializable {

    private static final long serialVersionUID = 1L;

    public double value = ThreadLocalRandom.current().nextDouble(-1, 1);

}
