package org.ethasia.adventuregrid.interactors.mocks;

import org.ethasia.adventuregrid.interactors.IoAdaptersFactoryForInteractors;
import org.ethasia.adventuregrid.interactors.output.IslandPresenter;

public class IoAdaptersMockFactoryForInteractors extends IoAdaptersFactoryForInteractors {

    //<editor-fold defaultstate="collapsed" desc="Factory Overrides">
    
    @Override
    public IslandPresenter createIslandPresenter() {
        return new IslandPresenterMock();
    }    
    
    //</editor-fold>    
}