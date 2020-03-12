package org.ethasia.adventuregrid.ioadapters.presenters.output;

import com.jme3.scene.Node;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.VisualChunkData;

public interface ChunkRenderer {
    
    public void renderChunk(VisualChunkData chunkData);
    public Node getRootNode();
}