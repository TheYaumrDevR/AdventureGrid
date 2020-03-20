package org.ethasia.adventuregrid.technical.jmegamestates;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import org.ethasia.adventuregrid.interactors.EnterNewIslandInteractor;
import org.ethasia.adventuregrid.ioadapters.presenters.GuiScreens;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.technical.niftygui.NiftyGuiScreens;

public class MainGameState extends AdventureGridGameState {
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Implementations">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        NiftyGuiScreens.gotoScreen(GuiScreens.MAIN_GAME);
        flyCam.setEnabled(true);
        flyCam.setDragToRotate(false);
        flyCam.setMoveSpeed(12);
        app.getCamera().setLocation(new Vector3f(16.f, 66.f, 16.f));
        mainGameState.getViewPort().setBackgroundColor(new ColorRGBA(0.56f, 0.853f, 1.f, 1.0f));
        
        Node chunksRootNode = TechnicalsFactory.getInstance().getChunkRendererInstance().getRootNode();
        mainGameState.getRootNode().attachChild(chunksRootNode);        
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Overrides">
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        EnterNewIslandInteractor enterStartingIslandInteractor = new EnterNewIslandInteractor();
        enterStartingIslandInteractor.setupNewIsland();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager) {
        mainGameState.getRootNode().detachAllChildren();
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