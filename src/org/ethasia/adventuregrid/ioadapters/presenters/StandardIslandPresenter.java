package org.ethasia.adventuregrid.ioadapters.presenters;

import java.util.LinkedList;
import java.util.List;
import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.interactors.output.IslandPresenter;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.VisualChunkData;
import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public class StandardIslandPresenter implements IslandPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int CHUNK_EDGE_LENGTH_IN_BLOCKS = 16;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="IslandPresenter Overrides">
    
    @Override
    public void presentIsland(Island island) {
        VisualChunkData dataToRender = new VisualChunkData();
        // StandardBlockVisualsBuilder blockVisualDataBuilder = new 
        ChunkRenderer chunkRenderer = TechnicalsFactory.getInstance().getChunkRendererInstance();
        
        int chunksPerEdge = 0;
        for (int i = 0; i * CHUNK_EDGE_LENGTH_IN_BLOCKS < island.getXzDimension(); i++) {
            chunksPerEdge++;
        }
        
        List<Block> blocksToBeRendered = new LinkedList<>();
        
        for (int i = 0; i < chunksPerEdge; i++) {
            for (int j = 0; j < chunksPerEdge; j++) {
                
                blocksToBeRendered.clear();
                
                for (int x = CHUNK_EDGE_LENGTH_IN_BLOCKS * i; x < CHUNK_EDGE_LENGTH_IN_BLOCKS * (i + 1); x++) {
                    for (int y = CHUNK_EDGE_LENGTH_IN_BLOCKS * j; y < CHUNK_EDGE_LENGTH_IN_BLOCKS * (j + 1); y++) {
                        for (int z = 0; z < Island.HEIGHT_IN_BLOCKS; z++) {
                            if (x < island.getXzDimension() && y < island.getXzDimension()) {
                                if (island.getBlockAt(x, y, z).getBlockType() != BlockTypes.AIR) {
                                    blocksToBeRendered.add(island.getBlockAt(x, y, z));
                                }
                            }    
                        }
                    }                    
                }
                
                dataToRender.setUpWithNumberOfBlocksInChunk(blocksToBeRendered.size());
                dataToRender.setWorldPosition(i, j);
            }            
        }
    }    
    
    //</editor-fold>    
}