package org.ethasia.adventuregrid.interactors.mocks;

import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.interactors.output.IslandPresenter;

public class IslandPresenterMock implements IslandPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Static Properties">
    
    private static Island lastPresentIslandCallParameter;
    
    public static Island getLastPresentIslandCallParameter() {
        return lastPresentIslandCallParameter;
    }
    
    public static void resetMock() {
        lastPresentIslandCallParameter = null;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mock Implementations">
    
    @Override
    public void presentIsland(Island island) {
        lastPresentIslandCallParameter = island;
    }    
    
    //</editor-fold>    
}