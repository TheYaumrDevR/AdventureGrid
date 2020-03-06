package org.ethasia.adventuregrid.core.mocks;

import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.core.input.IslandGenerator;

public class IslandGeneratorMock implements IslandGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Static Properties">  
    
    private static int lastGenerateIslandCallParameter;
    
    public static int getLastGenerateIslandCallParameter() {
        return lastGenerateIslandCallParameter;
    }
    
    public static void resetMock() {
        lastGenerateIslandCallParameter = 0;
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