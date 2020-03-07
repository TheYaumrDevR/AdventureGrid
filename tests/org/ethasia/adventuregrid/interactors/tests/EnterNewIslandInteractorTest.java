package org.ethasia.adventuregrid.interactors.tests;

import org.ethasia.adventuregrid.core.CoreClassesFactory;
import org.ethasia.adventuregrid.core.mocks.CoreClassesMocksFactory;
import org.ethasia.adventuregrid.core.mocks.IslandGeneratorMock;
import org.ethasia.adventuregrid.interactors.EnterNewIslandInteractor;
import org.ethasia.adventuregrid.interactors.IoAdaptersFactoryForInteractors;
import org.ethasia.adventuregrid.interactors.mocks.IoAdaptersMockFactoryForInteractors;
import org.ethasia.adventuregrid.interactors.mocks.IslandPresenterMock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

public class EnterNewIslandInteractorTest {
    
    @BeforeClass
    public static void setupDependencies() {
        CoreClassesFactory.setInstance(new CoreClassesMocksFactory());
        IoAdaptersFactoryForInteractors.setInstance(new IoAdaptersMockFactoryForInteractors());
    }
    
    @Test
    public void testSetupNewIsland_generatesRandomIslandAndPassesItToIslandPresenter() {
        EnterNewIslandInteractor testCandidate = new EnterNewIslandInteractor();
        testCandidate.setupNewIsland();
        
        assertThat(IslandGeneratorMock.getLastGenerateIslandCallParameter(), is(64));
        assertThat(IslandPresenterMock.getLastPresentIslandCallParameter().getXzDimension(), is(64));
    }
}