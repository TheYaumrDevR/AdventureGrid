package org.ethasia.adventuregrid.technical.niftygui;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import de.lessvoid.nifty.Nifty;
import java.util.HashMap;
import java.util.Map;
import org.ethasia.adventuregrid.core.general.NotAllPropertiesAreSetException;

import org.ethasia.adventuregrid.ioadapters.presenters.GuiScreens;

public class NiftyGuiScreens {
    
    private static Nifty nifty;
    private static final Map<GuiScreens, String> SCREEN_ID_BY_SCREEN;
    
    static {
        SCREEN_ID_BY_SCREEN = new HashMap<>();
        SCREEN_ID_BY_SCREEN.put(GuiScreens.START, "start");
        SCREEN_ID_BY_SCREEN.put(GuiScreens.MAIN_GAME, "mainGame");
    }
    
    private static void fromBuilder(With builder) {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(builder.assetManager, builder.inputManager, builder.audioRenderer, builder.guiViewPort);
        nifty = niftyDisplay.getNifty();
        builder.guiViewPort.addProcessor(niftyDisplay);
        
        nifty.addXml("Interface/Views/Definitions/StartView.xml");
        nifty.addXml("Interface/Views/Definitions/MainGameView.xml");
    }
    
    public static void gotoScreen(GuiScreens screen) {
        nifty.gotoScreen(SCREEN_ID_BY_SCREEN.get(screen));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class With {
        
        private AssetManager assetManager;
        private InputManager inputManager;
        private AudioRenderer audioRenderer;
        private ViewPort guiViewPort;
        
        public With assetManager(AssetManager value) {
            assetManager = value;
            return this;
        }
        
        public With inputManager(InputManager value) {
            inputManager = value;
            return this;
        }

        public With audioRenderer(AudioRenderer value) {
            audioRenderer = value;
            return this;
        }

        public With guiViewPort(ViewPort value) {
            guiViewPort = value;
            return this;
        }  
        
        public void initialize() {
            if (!allNecessaryPropertiesAreInitialized()) {
                throw new NotAllPropertiesAreSetException();
            }
            
            NiftyGuiScreens.fromBuilder(this);
        }
        
        private boolean allNecessaryPropertiesAreInitialized() {
            return null != assetManager 
                && null != inputManager 
                && null != audioRenderer 
                && null != guiViewPort;
        }
    }
    
    //</editor-fold>
}