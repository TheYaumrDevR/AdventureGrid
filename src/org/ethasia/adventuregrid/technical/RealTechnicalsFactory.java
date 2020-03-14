package org.ethasia.adventuregrid.technical;

import com.jme3.asset.AssetManager;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;
import org.ethasia.adventuregrid.technical.rendering.ChunkRendererImpl;

public class RealTechnicalsFactory extends TechnicalsFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Chunk Renderer">
    
    private final AssetManager assetManager;
    private ChunkRenderer chunkRenderer;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RealTechnicalsFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TechnicalsFactory Overrides">
    
    @Override
    public ChunkRenderer getChunkRendererInstance() {
        if (null == chunkRenderer) {
            chunkRenderer = new ChunkRendererImpl(assetManager);
        }
        
        return chunkRenderer;
    }    
    
    //</editor-fold>    
}