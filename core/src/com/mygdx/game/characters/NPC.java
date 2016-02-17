// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.characters;
//ASSESSMENT updated packages (change 7)
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Character;
import com.mygdx.game.GameWorld;
import com.mygdx.game.Level;
import com.mygdx.game.UI.UIManager;

/**
 * NPC extends the character class to provide functionality that is unique to NPC's.
 * An npc will move randomly around the level.
 */
public abstract class NPC extends Character {
    private final int MOVEMENT_PROBABILITY = 480;
    private float runningTime;
    protected UIManager uiManager;

    public NPC(Level level, Vector2 currentTile) {
        super(level, currentTile);
    }

    /**
     * Generates a random integer and moves to a new tile.
     * @param delta The time since the last frame was rendered.
     */
    protected void updateStationary(float delta) {
        int randomInt = MathUtils.random(MOVEMENT_PROBABILITY);
        if (randomInt == 0) {
            updateMovement(Direction.UP);
        } else if (randomInt == 1) {
            updateMovement(Direction.DOWN);
        } else if (randomInt == 2) {
            updateMovement(Direction.LEFT);
        } else if (randomInt == 3) {
            updateMovement(Direction.RIGHT);
        }
    }

    @Override
    protected void updateTransitioning(float delta) {
        runningTime += delta;
        // ASSESSMENT 3 change (15)
        float t = runningTime / transitionSpeed;
        // ASSESSMENT 3 END
        getAbsPos().set(oldPos.x + (targetPos.x - oldPos.x) * t, oldPos.y + (targetPos.y - oldPos.y) * t);
        if (t >= 1) {
            setState(CharacterState.STATIONARY);
            runningTime = 0;
            getCurrentTile().set(targetTile);
            oldPos.set(getAbsPos());
        }
    }

    /**
     * This abstract method is called when a player first interacts with the NPC.
     * @param delta The time since the last frame was rendered.
     */
    public abstract void initializeInteraction(float delta, UIManager uiManager);

    /**
     * This abstract method is called every frame while a player interacts with the NPC.
     * @param delta The time since the last frame was rendered.
     * @return Returns true if update should continue.
     */
    public abstract boolean updateInteracting(float delta);

    /**
     * This abstract method is called when a player has finished interacting with the NPC.
     */
    public abstract void action(GameWorld gameWorld);
}
