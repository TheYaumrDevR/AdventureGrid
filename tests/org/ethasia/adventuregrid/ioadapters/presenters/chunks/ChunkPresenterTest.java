package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import java.util.List;
import org.ethasia.adventuregrid.core.environment.EarthBlock;
import org.ethasia.adventuregrid.core.environment.GrassyEarthBlock;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.core.environment.PortalBlock;
import org.ethasia.adventuregrid.core.environment.RockBlock;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.ioadapters.presenters.mocks.ChunkRendererMock;
import org.ethasia.adventuregrid.ioadapters.presenters.mocks.TechnicalsMockFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChunkPresenterTest {
    
    @BeforeClass
    public static void initDependencies() {
        TechnicalsFactory.setInstance(new TechnicalsMockFactory());
    }    
    
    @Before
    public void resetMocks() {
        ChunkRendererMock.resetMock();
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
    
    @Test
    public void testPresentChunk_normalAndTransparentBlocksPresent_rendersTransparentBlocksInSeparateChunk() {
        Island islandToRender = new Island(31);  
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 18, 2, 18);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 19, 2, 18);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 18, 2, 19);
        islandToRender.placeBlockAt(PortalBlock.getInstance(), 20, 2, 19);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 18, 1, 18);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 19, 1, 18);
        islandToRender.placeBlockAt(RockBlock.getInstance(), 18, 1, 19);
        islandToRender.placeBlockAt(PortalBlock.getInstance(), 20, 1, 19);    
        
        ChunkPresenter testCandidate = new ChunkPresenter();
        
        testCandidate.presentChunk(islandToRender, 1, 1);        
        
        List<VisualChunkData> presentedChunkData = ChunkRendererMock.getRenderedChunkData();
        
        assertThat(presentedChunkData.size(), is(2));
        assertThat(presentedChunkData.get(0).getVertices().length, is(120));
        assertThat(presentedChunkData.get(0).getIndices().length, is(60));
        assertThat(presentedChunkData.get(0).getNormals().length, is(120));
        assertThat(presentedChunkData.get(0).getUvCoordinates().length, is(80));
    }
    
    @Test
    public void testPresentChunk_portalBlockIsAdded_addsParticleEffect() {
        Island islandToRender = new Island(31);  
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 15, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 15, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 15, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 15, 2);
        islandToRender.placeBlockAt(PortalBlock.getInstance(), 2, 15, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 15, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 15, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 15, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 15, 3);    
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 14, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 14, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 14, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 14, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 14, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 14, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 14, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 14, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 14, 3);   
        
        ChunkPresenter testCandidate = new ChunkPresenter();
        
        testCandidate.presentChunk(islandToRender, 0, 0);        
        
        List<VisualChunkData> presentedChunkData = ChunkRendererMock.getRenderedChunkData(); 
        
        assertThat(presentedChunkData.get(0).getParticleEffects().size(), is(1));
        assertThat(presentedChunkData.get(0).getParticleEffects().get(0).getPosX(), is(2));
        assertThat(presentedChunkData.get(0).getParticleEffects().get(0).getPosY(), is(15));
        assertThat(presentedChunkData.get(0).getParticleEffects().get(0).getPosZ(), is(2));
        assertThat(presentedChunkData.get(0).getParticleEffects().get(0).getParticleEffect(), is(ParticleEffects.PORTAL));
    }
    
    @Test
    public void testPresentChunk_portalBlockIsCovered_noParticleEffect() {
        Island islandToRender = new Island(31);  
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 15, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 15, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 15, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 15, 2);
        islandToRender.placeBlockAt(PortalBlock.getInstance(), 2, 15, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 15, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 15, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 15, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 15, 3);    
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 14, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 14, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 14, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 14, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 14, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 14, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 14, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 14, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 14, 3); 
        
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 16, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 16, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 16, 1);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 16, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 16, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 16, 2);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 1, 16, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 2, 16, 3);
        islandToRender.placeBlockAt(GrassyEarthBlock.getInstance(), 3, 16, 3);         
        
        ChunkPresenter testCandidate = new ChunkPresenter();
        
        testCandidate.presentChunk(islandToRender, 0, 0);        
        
        List<VisualChunkData> presentedChunkData = ChunkRendererMock.getRenderedChunkData(); 
        
        assertThat(presentedChunkData.get(0).getParticleEffects().size(), is(0));
    }    
}