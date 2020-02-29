package org.ethasia.adventuregrid.core.environment;

public class IslandGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public Island generateIsland(int edgeLength) {
        Island result =  new Island(edgeLength);
        
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = 0; k < 126; k++) {
                    result.placeBlockAt(RockBlock.getInstance(), i, k, j);
                }
            }
        }
        
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = 126; k < 127; k++) {
                    result.placeBlockAt(EarthBlock.getInstance(), i, k, j);
                }
            }
        }

        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = 127; k < 128; k++) {
                    result.placeBlockAt(GrassyEarthBlock.getInstance(), i, k, j);
                }
            }
        }        
        
        return result;
    }
    
    //</editor-fold>
}