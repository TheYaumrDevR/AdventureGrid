package org.ethasia.adventuregrid.interactors;

import org.ethasia.adventuregrid.interactors.output.IslandPresenter;

public abstract class IoAdaptersFactoryForInteractors {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static IoAdaptersFactoryForInteractors instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void setInstance(IoAdaptersFactoryForInteractors value) {
        instance = value;
    }
    
    public static IoAdaptersFactoryForInteractors getInstance() {
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    public abstract IslandPresenter createIslandPresenter();
    
    //</editor-fold>
}