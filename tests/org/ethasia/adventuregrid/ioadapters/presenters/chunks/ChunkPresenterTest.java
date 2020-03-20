package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.EarthBlock;
import org.ethasia.adventuregrid.core.environment.GrassyEarthBlock;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.core.environment.RockBlock;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.ioadapters.presenters.mocks.ChunkRendererMock;
import org.ethasia.adventuregrid.ioadapters.presenters.mocks.TechnicalsMockFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChunkPresenterTest {
    
    @BeforeClass
    public static void initDependencies() {
        TechnicalsFactory.setInstance(new TechnicalsMockFactory());
    }    
    
    @Test
    public void testPresentChunk_eightBlocksInFirstChunk_callsRendererWithProperChunkData() {
        Island islandToRender = new Island(16);  
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 2, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 2, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 2, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 2, 3);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 2, 1, 2);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 3, 1, 2);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 2, 1, 3);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 3, 1, 3);
        
        ChunkPresenter testCandidate = new ChunkPresenter();
        
        testCandidate.presentChunk(islandToRender, 0, 0);
        
        VisualChunkData lastRenderedChunkData = ChunkRendererMock.getLastRenderChunkCallData();     
        
        assertThat(lastRenderedChunkData, is(notNullValue()));
        assertThat(lastRenderedChunkData.getWorldX(), is(0));
        assertThat(lastRenderedChunkData.getWorldY(), is(0));
        assertThat(lastRenderedChunkData.getVertices().length, is(288));
        assertThat(lastRenderedChunkData.getIndices().length, is(144));
        assertThat(lastRenderedChunkData.getNormals().length, is(288));
        assertThat(lastRenderedChunkData.getUvCoordinates().length, is(192));
    }
    
    @Test
    public void testPresentChunk_eightBlocksInChunkTwoTwo_callsRendererWithProperChunkData() {
        Island islandToRender = new Island(31);  
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 18, 2, 18);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 19, 2, 18);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 18, 2, 19);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 19, 2, 19);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 18, 1, 18);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 19, 1, 18);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 18, 1, 19);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 19, 1, 19);
        
        ChunkPresenter testCandidate = new ChunkPresenter();
        
        testCandidate.presentChunk(islandToRender, 1, 1);
        
        VisualChunkData lastRenderedChunkData = ChunkRendererMock.getLastRenderChunkCallData();     
        
        assertThat(lastRenderedChunkData, is(notNullValue()));
        assertThat(lastRenderedChunkData.getWorldX(), is(1));
        assertThat(lastRenderedChunkData.getWorldY(), is(1));
        assertThat(lastRenderedChunkData.getVertices().length, is(288));
        assertThat(lastRenderedChunkData.getIndices().length, is(144));
        assertThat(lastRenderedChunkData.getNormals().length, is(288));
        assertThat(lastRenderedChunkData.getUvCoordinates().length, is(192));
    }

    @Test
    public void testPresentChunk_threeByThreeBlocks_middleBlockIsNotRendered() {
        Island islandToRender = new Island(46);  
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 34, 3, 18);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 35, 3, 18);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 36, 3, 18);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 34, 3, 19);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 35, 3, 19);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 36, 3, 19); 
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 34, 3, 20);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 35, 3, 20);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 36, 3, 20); 
        
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 34, 2, 18);
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 35, 2, 18);
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 36, 2, 18);
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 34, 2, 19);
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 35, 2, 19);
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 36, 2, 19); 
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 34, 2, 20);
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 35, 2, 20);
        islandToRender.placeBlockAt(EarthBlock.getInstance(), 36, 2, 20);          

        islandToRender.placeBlockAt(RockBlock.getInstance(), 34, 1, 18);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 35, 1, 18);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 36, 1, 18);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 34, 1, 19);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 35, 1, 19);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 36, 1, 19); 
        islandToRender.placeBlockAt(RockBlock.getInstance(), 34, 1, 20);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 35, 1, 20);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 36, 1, 20); 
        
        ChunkPresenter testCandidate = new ChunkPresenter();
        
        testCandidate.presentChunk(islandToRender, 2, 1);
        
        VisualChunkData lastRenderedChunkData = ChunkRendererMock.getLastRenderChunkCallData();     
        
        assertThat(lastRenderedChunkData, is(notNullValue()));
        assertThat(lastRenderedChunkData.getWorldX(), is(2));
        assertThat(lastRenderedChunkData.getWorldY(), is(1));
        assertThat(lastRenderedChunkData.getVertices().length, is(648));
        assertThat(lastRenderedChunkData.getIndices().length, is(324));
        assertThat(lastRenderedChunkData.getNormals().length, is(648));
        assertThat(lastRenderedChunkData.getUvCoordinates().length, is(432));
    }    
}