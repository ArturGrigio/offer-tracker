package org.otracker.db;

import org.junit.Test;
import org.otracker.db.Db;
import org.pmw.tinylog.Logger;

/**
 * Prefer to write your tests in scala.
 *
 * Included mainly just to exercise joint compilation.
 */
public class DbTest {

    @Test
    public void testNumAgents() {
        final int numAgents = Db.getNumAgents();
        Logger.trace("found " + numAgents + " agents in the database");
    }
}
