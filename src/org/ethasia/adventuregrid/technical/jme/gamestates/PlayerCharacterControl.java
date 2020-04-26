package org.ethasia.adventuregrid.technical.jme.gamestates;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import org.ethasia.adventuregrid.technical.jme.connectors.SoundEffectsImpl;

public class PlayerCharacterControl implements ActionListener {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final SoundEffectsImpl soundEffects;
    private final CharacterControl player;   
    
    private final Vector3f walkDirection;
    private final Vector3f camDir;
    private final Vector3f camLeft;
    
    private boolean moveDown;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveLeft;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public PlayerCharacterControl() {
        CapsuleCollisionShape playerCollisionShape = new CapsuleCollisionShape(0.25f, 0.55f);
        player = new CharacterControl(playerCollisionShape, 0.05f);
        
        walkDirection = new Vector3f();
        camDir = new Vector3f();
        camLeft = new Vector3f();
        soundEffects = new SoundEffectsImpl();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void placeOnScene(BulletAppState bulletAppState) {
        bulletAppState.getPhysicsSpace().add(player); 
        
        player.setJumpSpeed(20);
        player.setFallSpeed(30);
        player.setGravity(new Vector3f(0,-30f,0));
        player.setPhysicsLocation(new Vector3f(16.f, 65.f, 16.f));          
    }
    
    public void bindControlToKeys(InputManager inputManager) {
        inputManager.addMapping("MoveLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("MoveRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("MoveUp", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("MoveDown", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        
        inputManager.addListener(this, "MoveLeft");
        inputManager.addListener(this, "MoveRight");
        inputManager.addListener(this, "MoveUp");
        inputManager.addListener(this, "MoveDown");
        inputManager.addListener(this, "Jump");
    }    
    
    public void unbindKeys(InputManager inputManager) {
        inputManager.deleteMapping("MoveLeft");
        inputManager.deleteMapping("MoveRight");
        inputManager.deleteMapping("MoveUp");
        inputManager.deleteMapping("MoveDown");
        inputManager.deleteMapping("Jump");
        
        inputManager.removeListener(this);
    }
    
    public void update(Camera camera) {
        camDir.set(camera.getLeft()).crossLocal(Vector3f.UNIT_Y).multLocal(0.0421875f);
        camLeft.set(camera.getLeft()).multLocal(0.028125f);
        walkDirection.set(0, 0, 0);
        boolean isMovingSideWays = false;
        boolean isMovingForwardBackward = false;
        
        if (moveLeft) {
            walkDirection.addLocal(camLeft);
            isMovingSideWays = !isMovingSideWays;
        }
        if (moveRight) {
            walkDirection.addLocal(camLeft.negate());
            isMovingSideWays = !isMovingSideWays;
        }
        if (moveUp) {
            walkDirection.addLocal(camDir);
            isMovingForwardBackward = !isMovingForwardBackward;
        }
        if (moveDown) {
            walkDirection.addLocal(camDir.negate());
            isMovingForwardBackward = !isMovingForwardBackward;
        }     
        
        final boolean playWalkingSoundEffect = player.onGround() && (isMovingSideWays || isMovingForwardBackward);
        if (playWalkingSoundEffect) {
            soundEffects.playWalkOnRockSoundEffect();
        }
        
        player.setWalkDirection(walkDirection);
        camera.setLocation(player.getPhysicsLocation().addLocal(0, 0.25f, 0));
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void onAction(String actionName, boolean keyIsPressed, float tpf) {
        switch (actionName) {
            case "MoveLeft":
                moveLeft = keyIsPressed;
                break;
            case "MoveRight":
                moveRight = keyIsPressed;
                break;
            case "MoveUp":
                moveUp = keyIsPressed;
                break;
            case "MoveDown":
                moveDown = keyIsPressed;
                break;        
            case "Jump":
                if (keyIsPressed && player.onGround()) {
                    player.jump(new Vector3f(0, 7f, 0));
                }   
                break;
            default:
                break;
        }
    }    
    
    //</editor-fold>    
}