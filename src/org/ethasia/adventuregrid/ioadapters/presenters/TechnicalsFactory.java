package org.ethasia.adventuregrid.ioadapters.presenters;

import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public abstract class TechnicalsFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static TechnicalsFactory instance;
    
    //</editor-fold>   
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void setInstance(TechnicalsFactory value) {
        instance = value;
    }
    
    public static TechnicalsFactory getInstance() {
        return instance;
    }
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    public abstract ChunkRenderer getChunkRendererInstance();
    
    //</editor-fold>    
}