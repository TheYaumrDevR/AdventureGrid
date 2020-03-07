package org.ethasia.adventuregrid.interactors;

import org.ethasia.adventuregrid.core.CoreClassesFactory;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.core.input.IslandGenerator;
import org.ethasia.adventuregrid.interactors.output.IslandPresenter;

public class EnterNewIslandInteractor {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void setupNewIsland() {
        IslandGenerator islandGenerator = CoreClassesFactory.getInstance().createIslandGenerator();
        IslandPresenter islandPresenter = IoAdaptersFactoryForInteractors.getInstance().createIslandPresenter();
        
        Island island = islandGenerator.generateIsland(64);
        islandPresenter.presentIsland(island);
    }
    
    //</editor-fold>
}