package org.ethasia.adventuregrid.core;

import org.ethasia.adventuregrid.core.environment.StandardIslandGenerator;
import org.ethasia.adventuregrid.core.input.IslandGenerator;

public class RealCoreClassesFactory extends CoreClassesFactory {

    //<editor-fold defaultstate="collapsed" desc="CoreClassesFactory Overrides">
    
    @Override
    public IslandGenerator createIslandGenerator() {
        return new StandardIslandGenerator();
    }    
    
    //</editor-fold>    
}