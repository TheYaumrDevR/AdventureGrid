package org.ethasia.adventuregrid.ioadapters.presenters.mocks;

import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public class TechnicalsMockFactory extends TechnicalsFactory {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public ChunkRenderer getChunkRendererInstance() {
        return new ChunkRendererMock();
    }    
    
    //</editor-fold>    
}