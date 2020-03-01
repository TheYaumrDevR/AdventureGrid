package org.ethasia.adventuregrid.interactors.tests;

import org.ethasia.adventuregrid.interactors.EnterNewIslandInteractor;
import org.junit.Test;

public class EnterNewIslandInteractorTest {
    
    @Test
    public void testSetupNewIsland_generatesRandomIslandAndPassesItToIslandPresenter() {
        EnterNewIslandInteractor testCandidate = new EnterNewIslandInteractor();
        testCandidate.setupNewIsland();
    }
}