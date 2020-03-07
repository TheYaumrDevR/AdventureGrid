package org.ethasia.adventuregrid;

import com.jme3.app.SimpleApplication;
import org.ethasia.adventuregrid.core.CoreClassesFactory;
import org.ethasia.adventuregrid.core.RealCoreClassesFactory;
import org.ethasia.adventuregrid.interactors.IoAdaptersFactoryForInteractors;
import org.ethasia.adventuregrid.ioadapters.presenters.RealIoAdaptersFactoryForInteractors;
import org.ethasia.adventuregrid.technical.jmegamestates.AdventureGridGameState;
import org.ethasia.adventuregrid.technical.niftygui.NiftyGuiScreens;

public class Dependencies {
    
    public static void injectEngineGlobals(SimpleApplication provider) {
        new NiftyGuiScreens.With()
            .assetManager(provider.getAssetManager())
            .audioRenderer(provider.getAudioRenderer())
            .inputManager(provider.getInputManager())
            .guiViewPort(provider.getGuiViewPort())
            .initialize();        
        
        AdventureGridGameState.setStateManager(provider.getStateManager());
    }
    
    public static void inject() {
        injectCoreDependencies();
        injectInteractorDependencies();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void injectCoreDependencies() {
        CoreClassesFactory.setInstance(new RealCoreClassesFactory());
    }
    
    private static void injectInteractorDependencies() {
        IoAdaptersFactoryForInteractors.setInstance(new RealIoAdaptersFactoryForInteractors());
    }
    
    //</editor-fold>
}