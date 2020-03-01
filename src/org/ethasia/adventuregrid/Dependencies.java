package org.ethasia.adventuregrid;

import com.jme3.app.SimpleApplication;
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
        injectInteractorDependencies();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void injectInteractorDependencies() {
        
    }
    
    //</editor-fold>
}