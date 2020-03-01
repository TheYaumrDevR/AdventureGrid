package org.ethasia.adventuregrid.core.environment;

import org.ethasia.adventuregrid.core.input.IslandGenerator;

public class StandardIslandGenerator implements IslandGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Island result;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public Island generateIsland(int edgeLength) {
        result =  new Island(edgeLength);
   
        fillLayersFromToWithBlock(0, 126, RockBlock.getInstance());
        fillLayersFromToWithBlock(126, 127, EarthBlock.getInstance());
        fillLayersFromToWithBlock(127, 128, GrassyEarthBlock.getInstance());
        
        return result;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void fillLayersFromToWithBlock(int from, int to, Block block) {
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = from; k < to; k++) {
                    result.placeBlockAt(block, i, k, j);
                }
            }
        }         
    }
    
    //</editor-fold>
}