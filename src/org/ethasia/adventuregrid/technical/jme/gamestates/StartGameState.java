package org.ethasia.adventuregrid.technical.jme.gamestates;

import org.ethasia.adventuregrid.technical.jme.gamestates.MainGameState;
import org.ethasia.adventuregrid.technical.jme.gamestates.AdventureGridGameState;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import org.ethasia.adventuregrid.ioadapters.presenters.GuiScreens;
import org.ethasia.adventuregrid.technical.niftygui.NiftyGuiScreens;

public class StartGameState extends AdventureGridGameState {
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Implementations">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        NiftyGuiScreens.gotoScreen(GuiScreens.START);
        flyCam.setEnabled(false);
    }
    
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="ScreenController implementations">
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GUI Callbacks">
    
    public void onNewGameButtonClicked() {
        AdventureGridGameState.setGameState(new MainGameState());
    }
    
    public void onQuitButtonClicked() {
        System.exit(0);
    }
    
    //</editor-fold>
}