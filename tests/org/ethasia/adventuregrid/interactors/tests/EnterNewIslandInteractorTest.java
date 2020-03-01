package org.ethasia.adventuregrid.interactors.tests;

import org.ethasia.adventuregrid.interactors.EnterNewIslandInteractor;
import org.ethasia.adventuregrid.interactors.IoAdaptersFactoryForInteractors;
import org.ethasia.adventuregrid.interactors.mocks.IoAdaptersMockFactoryForInteractors;

import org.junit.BeforeClass;
import org.junit.Test;

public class EnterNewIslandInteractorTest {
    
    @BeforeClass
    public static void setupDependencies() {
        IoAdaptersFactoryForInteractors.setInstance(new IoAdaptersMockFactoryForInteractors());
    }
    
    @Test
    public void testSetupNewIsland_generatesRandomIslandAndPassesItToIslandPresenter() {
        EnterNewIslandInteractor testCandidate = new EnterNewIslandInteractor();
        testCandidate.setupNewIsland();
    }
}