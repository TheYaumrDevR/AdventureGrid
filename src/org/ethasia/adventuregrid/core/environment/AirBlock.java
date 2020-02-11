package org.ethasia.adventuregrid.core.environment;

public class AirBlock extends Block {
 
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private AirBlock() {
        super(BlockTypes.AIR);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static AirBlock instance;
    
    public static AirBlock getInstance() {
        if (null != instance) {
            return instance;
        }
        
        instance = new AirBlock();
        return instance;
    }    
    
    //</editor-fold>    
}