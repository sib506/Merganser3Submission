// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.UI;
//ASSESSMENT remove imports (change 6)
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.*;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.input.InputHandler;
import com.mygdx.game.managers.PartyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * The party menu allows the user to see information about each party member.
 * It contains a party member's skills and statistics.
 */
public class UIPartyMenu extends UIComponent {

    private PartyManager party;
    private boolean show;

    private int playerSelected, menuSelected;

    private List<UIPlayer> playerList;
    
    //ASSESSMENT 3 change (5)
    private UIMessageBox statsDisplay = new UIMessageBox("STATS", Assets.consolas22, Color.LIGHT_GRAY, Align.center, x+width/2, (y + height + 4), width/6, 0, 10);
    private UIMessageBox skillsDisplay = new UIMessageBox("SKILLS", Assets.consolas22, Color.LIGHT_GRAY, Align.center, x+width/2+width/6, (y + height +4), width/6, 0, 10);
    private UIMessageBox itemDisplay = new UIMessageBox("ITEMS", Assets.consolas22, Color.LIGHT_GRAY, Align.center, x+width/2+width/3, (y + height+4), width/6, 0, 10);
    //END ASSESSMENT 3 change

    public UIPartyMenu(float x, float y, float width, float height, PartyManager party) {
        super(x, y, width, height);
        this.party = party;
        show = false;
        playerSelected = 0;
        menuSelected = 1;
        playerList = new ArrayList<UIPlayer>();
        for (int i=0;i<party.size();i++) {
            playerList.add(new UIPlayer(x,(y + height - 70)-(110*i), width/2, party.getMember(i)));
        }
    }

    /**
     * Called once per frame to render the party menu.
     */
    @Override
    public void render(SpriteBatch batch, NinePatch patch) {

        if (show) {
            //ASSESSMENT 3 change (5)
        	statsDisplay.setColour(Color.LIGHT_GRAY);
        	skillsDisplay.setColour(Color.LIGHT_GRAY);
        	itemDisplay.setColour(Color.LIGHT_GRAY);

        	
            new UIMessageBox("", Assets.consolas22, Color.WHITE, Align.center, x, y, width, height).render(batch, patch);
            for (int i=0;i<playerList.size();i++) {
                playerList.get(i).render(batch, patch);
            }
            if (menuSelected == 0) {
            	statsDisplay.setColour(Color.WHITE);
                new UIStats(x + width/2, (y + height - 266), width/2, party.getMember(playerSelected)).render(batch, patch);
            }
            if (menuSelected == 1) {
            	skillsDisplay.setColour(Color.WHITE);
                for (int i=0;i<party.getMember(playerSelected).getSkills().size();i++) {
                    new UISkill(x + width/2, (y + height - 86)-(90*i), width/2, Game.skills.getSkill(party.getMember(playerSelected).getSkills().get(i))).render(batch, patch);
                }
            }
            if (menuSelected == 2) {
            	itemDisplay.setColour(Color.WHITE);
            	for(int i =0; i < Game.items.getConsumables().size(); i++){
            		Consumable currentConsumable = Game.items.getConsumable(i);
            		int count = 0;
            		for(int j = 0; j < Game.party.getConsumables().size(); j++){
            			if(currentConsumable.getID() == Game.party.getConsumables().get(j)){
            				count++;
            			}
            		}
            		new UIItem(x + width/2, (y + height - 86)-(90*i), width/2, currentConsumable, count).render(batch, patch);
            	}
            }
            
            statsDisplay.render(batch, patch);
            skillsDisplay.render(batch, patch);
            itemDisplay.render(batch, patch);
        	//END ASSESSMENT 3 change (5)
        }
    }

    /**
     * Makes the UI component visible on screen.
     */
    public void show() {
        playerSelected = 0;
        menuSelected = 1;
        show = true;
    }

    /**
     * Called once per frame to handle input logic for selecting a player and exiting the menu.
     * @return returns true if the dialogue box should continue to be displayed.
     */
    public boolean update(float delta) {
        if (InputHandler.isEscJustPressed()) {
            show = false;
            return false;
        } else {
            playerList.get(playerSelected).selected = false;
            optionUpdate();
            playerList.get(playerSelected).selected = true;
            return true;
        }

    }

    private void optionUpdate() {
        if (InputHandler.isUpJustPressed()) {
            playerSelected--;
        } else if (InputHandler.isDownJustPressed()) {
            playerSelected++;
        }
        if (InputHandler.isLeftJustPressed()) {
            menuSelected--;
        } else if (InputHandler.isRightJustPressed()) {
            menuSelected++;
        }
        if (menuSelected < 0)
            menuSelected = 0;
        if (menuSelected > 2)
            menuSelected = 2;
        if (playerSelected < 0)
            playerSelected = 0;
        if (playerSelected >= party.size())
            playerSelected = party.size()-1;
    }
}
