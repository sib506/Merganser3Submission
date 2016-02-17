// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game;

//ASSESSMENT 3 change (6)
import static com.mygdx.game.Level.TILE_SIZE;



import java.util.Comparator;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.input.InputHandler;

//END ASSESSMENT 3

/**
 * This abstract class represents a character in the Level.
 * Character behaviour must be implemented before use.
 */
public abstract class Character {
    //ASSESSMENT 3 change (15)
    public float transitionSpeed = 0.25f;
    //END ASSESSMENT 3 change
    public final float WAIT_PERIOD = 0.15f;

    public static final Vector2 CHARACTER_SIZE = new Vector2(13,21);

//  Player position & orientation information
    private Vector2 currentTile;
    private Vector2 absPos;

    protected Vector2 targetTile;
    protected Vector2 targetPos;

    protected Vector2 oldPos;

    private Direction direction;

    private CharacterState state;

    private float stateTime;
    protected float waitTime;
    
    //ASSESSMENT 3 ADDED LINE (15)
    protected boolean isSwimming = false;
    //ASSESSMENT 3 END LINE
    
//  Map information for collision detection.
    protected Level level;

    /**
     * Character constructor creates a character at a particular position on the level.
     * @param level The level which contains the character.
     * @param currentTile The tile which the character should start on.
     */
    public Character(Level level, Vector2 currentTile) {
        this.level = level;

        waitTime = 0;
        this.currentTile = currentTile;
        absPos = new Vector2(currentTile.cpy().scl(TILE_SIZE));
        targetTile = new Vector2(currentTile);

        targetPos = new Vector2(targetTile.cpy().scl(TILE_SIZE));
        oldPos = new Vector2(absPos);

        setDirection(Direction.UP);

        setState(CharacterState.STATIONARY);
    }

    /**
     * Called once per frame to update character logic and current player location.
     * @param delta The time since the last frame was rendered.
     */
    public void update(float delta) {
        //ASSESSMENT 3 change (13)
    	if(this instanceof Player) {
//    		System.out.println("Before: " + Game.currentLocation);
    		Game.currentLocation = level.locationMap[(int) getCurrentTile().x][(int) getCurrentTile().y];
//        	System.out.println("After: " + Game.currentLocation);
    	}
    	//END ASSESSMENT 3 change
    	
        if (level.stopInput){

        } else if (getState() == CharacterState.STATIONARY) {
            updateStationary(delta);
        } else if (getState() == CharacterState.WAITING) {
            updateWaiting(delta);
        } else {
            updateTransitioning(delta);
        }
        
    }

    /**
     * Helper method for update if the character state is WAITING.
     * @param delta The time since the last frame was rendered.
     */
    private void updateWaiting(float delta) {
        waitTime += delta;
        if (waitTime > WAIT_PERIOD) {
            setState(CharacterState.STATIONARY);
        }
    }

    /**
     * Helper method for update if the character state is TRANSITIONING.
     * @param delta The time since the last frame was rendered.
     */
    protected abstract void updateTransitioning(float delta);

    /**
     * Helper method that determines if a character can move to a particular position in the level.
     * Function modified in assessment 3
     * @param requestedDirection The time since the last frame was rendered.
     */
    protected void updateMovement(Direction requestedDirection) {
    	// Assessment 3 Change (15)
    	isSwimming = level.waterMap[(int) getCurrentTile().x][(int) getCurrentTile().y];
    	updateSpeed();
    	if (getDirection() == requestedDirection) {
            switch (requestedDirection) {
                case UP:
                    if (!level.collisionMap[(int) getCurrentTile().x][(int) getCurrentTile().y + 1]) {
                        setState(CharacterState.TRANSITIONING);
                        targetTile.add(0, 1);
                        targetPos.set(targetTile.cpy().scl(TILE_SIZE));
                    }
                    break;
                case DOWN:
                    if (!level.collisionMap[(int) getCurrentTile().x][(int) getCurrentTile().y - 1]) {
                        setState(CharacterState.TRANSITIONING);
                        targetTile.add(0, -1);
                        targetPos.set(targetTile.cpy().scl(TILE_SIZE));
                    }
                    break;
                case LEFT:
                    if (!level.collisionMap[(int) getCurrentTile().x - 1][(int) getCurrentTile().y]) {
                        setState(CharacterState.TRANSITIONING);
                        targetTile.add(-1, 0);
                        targetPos.set(targetTile.cpy().scl(TILE_SIZE));
                    }
                    break;
                case RIGHT:
                    if (!level.collisionMap[(int) getCurrentTile().x + 1][(int) getCurrentTile().y]) {
                        setState(CharacterState.TRANSITIONING);
                        targetTile.add(1, 0);
                        targetPos.set(targetTile.cpy().scl(TILE_SIZE));
                    }
                    break;
            }
//          To stop other characters moving in the same frame
            level.collisionMap[(int) targetTile.x][(int) targetTile.y] = true;
        } else {
            setDirection(requestedDirection);
            setState(CharacterState.WAITING);
            waitTime = 0;
        }
    }

    /**
     * Helper method for update if the character state is STATIONARY.
     * @param delta The time since the last frame was rendered.
     */
    protected abstract void updateStationary(float delta);

    public Vector2 getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Vector2 currentTile) {

        this.currentTile = currentTile;
        this.targetTile = currentTile;
        absPos.set(currentTile.cpy().scl(TILE_SIZE));
        System.out.println(absPos);
    }

    public Vector2 getAbsPos() {
        return absPos;
    }

    public void setAbsPos(Vector2 absPos) {
        this.absPos = absPos;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public CharacterState getState() {
        return state;
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    /**
     * The state of the character
     */
    public enum CharacterState {
        STATIONARY, WAITING, TRANSITIONING
    }

    /**
     * Represents the direction of a character
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Used to compare the y position of characters in the level.
     * Useful for rendering correctly.
     */
    static class CharacterComparator implements Comparator<Character> {

        @Override
        public int compare(Character o1, Character o2) {
            if (o1.getCurrentTile().y > o2.getCurrentTile().y)
                return -1;
            if (o1.getCurrentTile().y == o2.getCurrentTile().y)
                return 0;
            else
                return 1;
        }
    }

    /**
     * Function ADDED for ASSESSMENT 3
     * Change 15
     */
    public boolean isSwimming(){
    	return isSwimming;
    }
    
    /**
     * Function ADDED for ASSESSMENT 3 - Change 15
     */
    public boolean isFlying(){
    	InputHandler.update();
        return InputHandler.isShiftPressed();
    }
    
    /**
     * Function ADDED for ASSESSMENT 3 - Change 15
     * Used to update speed depending on movement type
     */
    public void updateSpeed(){
    	if(isFlying()){
    		transitionSpeed = 0.05f;
    	}else if (isSwimming){
    		transitionSpeed = 0.1f;
    	}else{
    		transitionSpeed = 0.25f;
    	}
    }

}
