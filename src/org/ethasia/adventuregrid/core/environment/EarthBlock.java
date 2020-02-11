package org.ethasia.adventuregrid.core.environment;

public class EarthBlock extends Block {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private EarthBlock() {
        super(BlockTypes.EARTH);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static EarthBlock instance;
    
    public static EarthBlock getInstance() {
        if (null != instance) {
            return instance;
        }
        
        instance = new EarthBlock();
        return instance;
    }    
    
    //</editor-fold>    
}