package org.ethasia.adventuregrid.ioadapters.presenters;

import org.ethasia.adventuregrid.interactors.IoAdaptersFactoryForInteractors;
import org.ethasia.adventuregrid.interactors.output.IslandPresenter;

public class RealIoAdaptersFactoryForInteractors extends IoAdaptersFactoryForInteractors {

    //<editor-fold defaultstate="collapsed" desc="IoAdaptersFactoryForInteractors Overrides">
    
    @Override
    public IslandPresenter createIslandPresenter() {
        return new StandardIslandPresenter();
    }    
    
    //</editor-fold>
}