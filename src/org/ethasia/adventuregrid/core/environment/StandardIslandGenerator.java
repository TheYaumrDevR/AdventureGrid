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
        addPortals();
        
        return result;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void fillLayersFromToWithBlock(int from, int to, Block block) {
        for (int i = 0; i < result.getXzDimension(); i++) {
            for (int j = 0; j < result.getXzDimension(); j++) {
                for (int k = from; k < to; k++) {
                    result.placeBlockAt(block, i, k, j);
                }
            }
        }         
    }
    
    private void addPortals() {
        Block blockToPlace = PortalBlock.getInstance();
        
        result.placeBlockAt(blockToPlace, 31, 128, 2);
        result.placeBlockAt(blockToPlace, 31, 129, 2);
        result.placeBlockAt(blockToPlace, 32, 128, 2);
        result.placeBlockAt(blockToPlace, 32, 129, 2);
        
        result.placeBlockAt(blockToPlace, 2, 128, 31);
        result.placeBlockAt(blockToPlace, 2, 129, 31);
        result.placeBlockAt(blockToPlace, 2, 128, 32);
        result.placeBlockAt(blockToPlace, 2, 129, 32);  
        
        result.placeBlockAt(blockToPlace, 31, 128, 61);
        result.placeBlockAt(blockToPlace, 31, 129, 61);
        result.placeBlockAt(blockToPlace, 32, 128, 61);
        result.placeBlockAt(blockToPlace, 32, 129, 61);       
        
        result.placeBlockAt(blockToPlace, 61, 128, 31);
        result.placeBlockAt(blockToPlace, 61, 129, 31);
        result.placeBlockAt(blockToPlace, 61, 128, 32);
        result.placeBlockAt(blockToPlace, 61, 129, 32);        
    }
    
    //</editor-fold>
}