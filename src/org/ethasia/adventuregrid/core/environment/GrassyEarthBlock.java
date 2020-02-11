package org.ethasia.adventuregrid.core.environment;

public class GrassyEarthBlock extends Block {
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private GrassyEarthBlock() {
        super(BlockTypes.GRASSY_EARTH);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static GrassyEarthBlock instance;    
    
    public static GrassyEarthBlock getInstance() {
        if (null != instance) {
            return instance;
        }
        
        instance = new GrassyEarthBlock();
        return instance;
    }    
    
    //</editor-fold>  
}