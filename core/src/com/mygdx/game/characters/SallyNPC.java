// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.characters;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.GameWorld;
import com.mygdx.game.Level;
import com.mygdx.game.Objective;
import com.mygdx.game.UI.UIManager;

/**
 * This class represents the first npc of the game.
 */
public class SallyNPC extends NPC {

    private boolean doneInteraction;
    private String[] messages;

    public SallyNPC(Level level, Vector2 currentTile) {
        super(level, currentTile);
        messages = new String[4];
        messages[0] = "Help! There is a robo duck on the loose!";
        messages[1] = "Please help us by finding him and defeating him!";
        messages[2] = "The last time I saw him was by the Catalyst building.";
        messages[3] = "For being such a swell guy and having a go here are some resources to help!";
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
            //ASSESSMENT 3 changes (11)
            uiManager.addNotification("You talked to Sally! You got 40 points!");
            level.characters.add(new RoboNPC(level, new Vector2(75, 98)));
            gameWorld.game.objectiveManager.completeObjective("Sally");
            gameWorld.game.objectiveManager.addObjective("RoboDuck", new Objective("Defeat RoboDuck by the Catalyst", 100, "100 Points", false));

            
            //add the new consumable type to items and the new consumable reference to party
            Game.party.addConsumable(0);
            Game.party.addConsumable(1);
            Game.party.addConsumable(2);
            Game.party.addConsumable(0);
            
            doneInteraction = true;
            level.characters.remove(this);
            //END ASSESSMENT 3 changes
        }
    }
}
