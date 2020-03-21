package org.ethasia.adventuregrid;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import org.ethasia.adventuregrid.core.CoreClassesFactory;
import org.ethasia.adventuregrid.core.RealCoreClassesFactory;
import org.ethasia.adventuregrid.interactors.IoAdaptersFactoryForInteractors;
import org.ethasia.adventuregrid.ioadapters.presenters.RealIoAdaptersFactoryForInteractors;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.technical.RealTechnicalsFactory;
import org.ethasia.adventuregrid.technical.jme.gamestates.AdventureGridGameState;
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
    
    public static void inject(AssetManager assetManager) {
        injectCoreDependencies();
        injectInteractorDependencies();
        injectTechnicalDependencies(assetManager);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void injectCoreDependencies() {
        CoreClassesFactory.setInstance(new RealCoreClassesFactory());
    }
    
    private static void injectInteractorDependencies() {
        IoAdaptersFactoryForInteractors.setInstance(new RealIoAdaptersFactoryForInteractors());
    }
    
    private static void injectTechnicalDependencies(AssetManager assetManager) {
        TechnicalsFactory.setInstance(new RealTechnicalsFactory(assetManager));
    }    
    
    //</editor-fold>
}