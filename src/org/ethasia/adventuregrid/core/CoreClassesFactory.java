package org.ethasia.adventuregrid.core;

import org.ethasia.adventuregrid.core.input.IslandGenerator;

public abstract class CoreClassesFactory {

    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static CoreClassesFactory instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void setInstance(CoreClassesFactory value) {
        instance = value;
    }
    
    public static CoreClassesFactory getInstance() {
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    public abstract IslandGenerator createIslandGenerator();
    
    //</editor-fold>    
}