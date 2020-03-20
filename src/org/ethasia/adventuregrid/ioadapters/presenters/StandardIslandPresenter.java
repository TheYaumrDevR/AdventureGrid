package org.ethasia.adventuregrid.ioadapters.presenters;

import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.interactors.output.IslandPresenter;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.ChunkPresenter;

public class StandardIslandPresenter implements IslandPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int CHUNK_EDGE_LENGTH_IN_BLOCKS = 16;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final ChunkPresenter chunkPresenter;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public StandardIslandPresenter() {
        chunkPresenter = new ChunkPresenter();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="IslandPresenter Overrides">
    
    @Override
    public void presentIsland(Island island) {        
        int chunkCountPerIslandEdge = calculateChunkCountPerIslandEdge(island);
        
        for (int i = 0; i < chunkCountPerIslandEdge; i++) {
            for (int j = 0; j < chunkCountPerIslandEdge; j++) {
                chunkPresenter.presentChunk(island, i, j);
            }            
        }
    }  
    
    private int calculateChunkCountPerIslandEdge(Island island) {
        int result = 0;
        for (int i = 0; i * CHUNK_EDGE_LENGTH_IN_BLOCKS < island.getXzDimension(); i++) {
            result++;
        }  
        
        return result;
    }
    
    //</editor-fold>    
}