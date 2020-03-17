package org.ethasia.adventuregrid;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import org.ethasia.adventuregrid.technical.jmegamestates.AdventureGridGameState;
import org.ethasia.adventuregrid.technical.jmegamestates.StartGameState;

public class AdventureGrid extends SimpleApplication {

    public static void main(String[] args) {
        AdventureGrid app = new AdventureGrid();
        
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Dependencies.inject(assetManager);
        Dependencies.injectEngineGlobals(this);
        AdventureGridGameState.setGameState(new StartGameState());
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}
