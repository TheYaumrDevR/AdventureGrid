package org.ethasia.adventuregrid.technical.jmegamestates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.FlyByCamera;
import de.lessvoid.nifty.screen.ScreenController;

public abstract class AdventureGridGameState extends AbstractAppState implements ScreenController {

    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static AppStateManager stateManager;    
    private static AdventureGridGameState currentGameState;
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    protected FlyByCamera flyCam;    
    protected SimpleApplication mainGameState;
    
    //</editor-fold>      
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void setStateManager(AppStateManager value) {
        stateManager = value;
    }
    
    public static void setGameState(AdventureGridGameState value) {
        if (null != currentGameState) {
            stateManager.detach(currentGameState);
        }
        
        stateManager.attach(value);
        currentGameState = value;
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Overrides">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        mainGameState = (SimpleApplication)app;
        
        flyCam = mainGameState.getFlyByCamera();
    }    
    
    //</editor-fold>   
}