package org.ethasia.adventuregrid.technical.jmegamestates;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import org.ethasia.adventuregrid.ioadapters.presenters.GuiScreens;
import org.ethasia.adventuregrid.technical.niftygui.NiftyGuiScreens;

public class MainGameState extends AdventureGridGameState {
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Implementations">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        NiftyGuiScreens.gotoScreen(GuiScreens.MAIN_GAME);
        flyCam.setEnabled(true);
        flyCam.setDragToRotate(false);
        mainGameState.getViewPort().setBackgroundColor(new ColorRGBA(0.56f, 0.853f, 1.f, 1.0f));
    }
    
    //</editor-fold>     

    //<editor-fold defaultstate="collapsed" desc="ScreenController Overrides">
    
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
}