package org.ethasia.adventuregrid.ioadapters.presenters.mocks;

import com.jme3.scene.Node;
import java.util.LinkedList;
import java.util.List;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.VisualChunkData;
import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public class ChunkRendererMock implements ChunkRenderer {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static final List<VisualChunkData> renderedChunkData;
    private static VisualChunkData lastRenderChunkCallData;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    static {
        renderedChunkData = new LinkedList<VisualChunkData>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mock Methods">
    
    public static VisualChunkData getLastRenderChunkCallData() {
        return lastRenderChunkCallData;
    }
    
    public static List<VisualChunkData> getRenderedChunkData() {
        return renderedChunkData;
    }
    
    public static void resetMock() {
        lastRenderChunkCallData = null;
        renderedChunkData.clear();
    }    
   
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void renderChunk(VisualChunkData chunkData) {
        lastRenderChunkCallData = chunkData;
        renderedChunkData.add(chunkData);
    }

    @Override
    public Node getRootNode() {
        return null;
    }    
    
    //</editor-fold>
}