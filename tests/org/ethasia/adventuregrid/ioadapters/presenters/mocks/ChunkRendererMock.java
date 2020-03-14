package org.ethasia.adventuregrid.ioadapters.presenters.mocks;

import com.jme3.scene.Node;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.VisualChunkData;
import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public class ChunkRendererMock implements ChunkRenderer {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static VisualChunkData lastRenderChunkCallData;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mock Methods">
    
    public static VisualChunkData getLastRenderChunkCallData() {
        return lastRenderChunkCallData;
    }
    
    public static void resetMock() {
        lastRenderChunkCallData = null;
    }    
   
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void renderChunk(VisualChunkData chunkData) {
        lastRenderChunkCallData = chunkData;
    }

    @Override
    public Node getRootNode() {
        return null;
    }    
    
    //</editor-fold>
}