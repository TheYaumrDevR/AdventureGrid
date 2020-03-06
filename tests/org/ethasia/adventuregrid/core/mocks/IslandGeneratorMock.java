package org.ethasia.adventuregrid.core.mocks;

import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.core.input.IslandGenerator;

public class IslandGeneratorMock implements IslandGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Static Properties">  
    
    private static int lastGenerateIslandCallParameter;
    
    public static int getLastGenerateIslandCallParameter() {
        return lastGenerateIslandCallParameter;
    }
    
    //</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="IslandGenerator Overrides">
    
    @Override
    public Island generateIsland(int edgeLength) {
        lastGenerateIslandCallParameter = edgeLength;
        return new Island(edgeLength);
    }    
    
    //</editor-fold>    
}