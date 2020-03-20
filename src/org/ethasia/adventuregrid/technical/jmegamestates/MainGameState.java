package org.ethasia.adventuregrid.technical.jmegamestates;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import org.ethasia.adventuregrid.interactors.EnterNewIslandInteractor;
import org.ethasia.adventuregrid.ioadapters.presenters.GuiScreens;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.technical.niftygui.NiftyGuiScreens;

public class MainGameState extends AdventureGridGameState {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BulletAppState bulletAppState;  
    private CharacterControl player;
    private Camera camera;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Implementations">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        NiftyGuiScreens.gotoScreen(GuiScreens.MAIN_GAME);
        flyCam.setEnabled(true);
        flyCam.setDragToRotate(false);
        flyCam.setMoveSpeed(12);
        camera = app.getCamera();
        camera.setFrustumNear(camera.getFrustumNear() / 2.f);
        mainGameState.getViewPort().setBackgroundColor(new ColorRGBA(0.56f, 0.853f, 1.f, 1.0f));
        
        Node chunksRootNode = TechnicalsFactory.getInstance().getChunkRendererInstance().getRootNode();
        mainGameState.getRootNode().attachChild(chunksRootNode);   
        
        CapsuleCollisionShape playerCollisionShape = new CapsuleCollisionShape(0.25f, 1.f);
        player = new CharacterControl(playerCollisionShape, 0.05f);      
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.getPhysicsSpace().addAll(chunksRootNode);
        bulletAppState.getPhysicsSpace().add(player);
        
        player.setJumpSpeed(20);
        player.setFallSpeed(30);
        player.setGravity(new Vector3f(0,-30f,0));
        player.setPhysicsLocation(new Vector3f(16.f, 66.f, 16.f));        
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
        stateManager.detach(bulletAppState);
    }
    
    @Override
    public void update(float tpf) {
        camera.setLocation(player.getPhysicsLocation());
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