// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.characters;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.GameWorld;
import com.mygdx.game.Level;
import com.mygdx.game.Objective;
import com.mygdx.game.UI.UIManager;

/**
 * This class represents the third npc of the game.
 * 
 * Class produced for assessment 3 (change 17)
 */
public class BobNPC extends NPC {

    private boolean doneInteraction;
    private String[] messages;

    public BobNPC(Level level, Vector2 currentTile) {
        super(level, currentTile);
        messages = new String[4];
        messages[0] = "Thank you for defeating RoboDuck!";
        messages[1] = "A new and improved RoboDuck has now appeared";
        messages[2] = "The last time I saw him, he was in Langwith.";
        messages[3] = "Here are some more resources to help you complete your quest!";
        doneInteraction = false;
    }

    @Override
    public void initializeInteraction(float delta, UIManager uiManager) {
        if (!doneInteraction) {
            uiManager.createDialogue(messages);
            this.uiManager = uiManager;
        }
    }

    @Override
    public boolean updateInteracting(float delta) {
        if (doneInteraction) {
            return false;
        }
        return uiManager.updateDialogue(delta);
    }

    @Override
    public void action(GameWorld gameWorld) {
        if (!doneInteraction) {
            uiManager.addNotification("You talked to Bob! You got 40 points!");
            level.characters.add(new EvilNPC(level, new Vector2(152, 123)));
            gameWorld.game.objectiveManager.completeObjective("Bob");
            gameWorld.game.objectiveManager.addObjective("RoboDuck2", new Objective("Defeat RoboDuck Mk2 near Langwith", 100, "100 Points", false));
            
            Game.party.addConsumable(0);
            Game.party.addConsumable(1);
            Game.party.addConsumable(2);
            doneInteraction = true;
        }
    }
}
