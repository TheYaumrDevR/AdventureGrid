package org.ethasia.adventuregrid.ioadapters.presenters.tests;

import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.core.environment.RockBlock;
import org.ethasia.adventuregrid.ioadapters.presenters.StandardIslandPresenter;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.VisualChunkData;
import org.ethasia.adventuregrid.ioadapters.presenters.mocks.ChunkRendererMock;
import org.ethasia.adventuregrid.ioadapters.presenters.mocks.TechnicalsMockFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

public class StandardIslandPresenterTest {
    
    @BeforeClass
    public static void initDependencies() {
        TechnicalsFactory.setInstance(new TechnicalsMockFactory());
    }
    
    @Test
    public void testInit() {
        Island toRender = new Island(64);
        toRender.placeBlockAt(RockBlock.getInstance(), 15, 0, 15);
        toRender.placeBlockAt(RockBlock.getInstance(), 16, 0, 15);
        toRender.placeBlockAt(RockBlock.getInstance(), 15, 0, 16);
        toRender.placeBlockAt(RockBlock.getInstance(), 16, 0, 16);
        
        StandardIslandPresenter testCandidate = new StandardIslandPresenter();
        testCandidate.presentIsland(toRender);
        
        VisualChunkData lastRenderedChunkData = ChunkRendererMock.getLastRenderChunkCallData();
        
        assertThat(lastRenderedChunkData.getWorldX(), is(1));
    }
}