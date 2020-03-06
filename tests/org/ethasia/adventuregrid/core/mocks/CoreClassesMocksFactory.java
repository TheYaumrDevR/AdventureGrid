package org.ethasia.adventuregrid.core.mocks;

import org.ethasia.adventuregrid.core.CoreClassesFactory;
import org.ethasia.adventuregrid.core.input.IslandGenerator;

public class CoreClassesMocksFactory extends CoreClassesFactory {

    //<editor-fold defaultstate="collapsed" desc="CoreClassesFactory Overrides">
    
    @Override
    public IslandGenerator createIslandGenerator() {
        return new IslandGeneratorMock();
    }    
    
    //</editor-fold>
}