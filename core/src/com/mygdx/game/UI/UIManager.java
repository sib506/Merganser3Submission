// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.UI;
//ASSESSMENT 3 updated packages (change 7)
//ASSESSMENT 3 new imports (change 14)
//ASSESSMENT 3 removed imports (change 6)
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Level;
import com.mygdx.game.assets.Assets;
//ASSESSMENT 3 change(11)
import com.mygdx.game.managers.ObjectiveManager;
//END ASSESSMENT 3 
import com.mygdx.game.managers.PartyManager;

/**
 * This class is responsible for the creation of UI elements on the screen.
 * The ui renderer uses this class to call the render function of each component.
 */
public class UIManager {
    public UIPartyMenu partyMenu;
    //ASSESSMENT 3 change (11)
    public UIObjectives objectives;
    //END ASSESSMENT 3 change
    public UIMap map;
    public List<UIMessageBox> notifications;

    private List<UIComponent> uiComponents;
    private Level level;

    public UIDialogue dialogue;
    private float notificationTimer;

    public UIManager(PartyManager party, ObjectiveManager obj, Level level) {
        notifications = new ArrayList<UIMessageBox>();
        notificationTimer = 0;
        uiComponents = new ArrayList<UIComponent>();
        this.level = level;
        partyMenu = new UIPartyMenu(40, 150, Gdx.graphics.getWidth()-80, Gdx.graphics.getHeight()-320, party);
        //ASSESSMENT 3 changes (11,14)
        objectives = new UIObjectives(40, 150, Gdx.graphics.getWidth()-80, Gdx.graphics.getHeight()-320, obj);
        map = new UIMap(85, 50, Gdx.graphics.getWidth()-170, Gdx.graphics.getHeight()-165, level);
        //END ASSESSMENT 3

    }

    /**
     * Opens the party menu.
     */
    public void openPartyMenu() {
        partyMenu.show();
    }

    /**
     * Called once per frame to update the party menu.
     * @return false if the party menu is closed.
     */
    public boolean updatePartyMenu(float delta) {
        return partyMenu.update(delta);
    }
    
    //ASSESSMENT 3 changes (11,14)
    /**
     * Opens the objectives menu.
     */
    public void openObjectiveMenu() {
        objectives.show();
    }

    /**
     * Called once per frame to update the objective menu.
     * @return false if the objective menu is closed.
     */
    public boolean updateObjectiveMenu(float delta) {
        return objectives.update(delta);
    }
    
    /**
     * Opens the map
     */
    public void openMap(){
    	map.show();
    }
    
    
    /**
     * Called once per frame to update the map.
     * @return false if the map is closed.
     */
    public boolean updateMap(float delta){
    	return map.update(delta);
    }
    //END ASSESSMENT 3 changes
    
    
    /**
     * Creates a new dialogue from an array of messages.
     */
    public void createDialogue(String[] messages) {
        dialogue = new UIDialogue(20, 20, Gdx.graphics.getWidth()/2-40, 0, messages);
    }

    /**
     * To be called if the player is in a dialogue.
     * @return false when dialogue has finished.
     */
    public boolean updateDialogue(float delta) {
        if (!dialogue.update(delta)) {
            dialogue = null;
            return false;
        }
        return true;
    }

    /**
     * Adds a notification to the current list of notifications waiting to be displayed.
     */
    public void addNotification(String message) {
        notifications.add(new UIMessageBox(message, Assets.consolas22, Color.WHITE, Align.center, 20, Gdx.graphics.getHeight()-80, Gdx.graphics.getWidth()/2, 0));
    }

    /**
     * To be called once per frame to update timing of notification components.
     */
    public void updateNotification(float delta) {
        if (notifications.isEmpty()) {
            notificationTimer = 0;
        } else {
            notificationTimer += delta;
            if (notificationTimer > 4f) {
                notificationTimer = 0;
                notifications.remove(0);
            }
        }

    }

    public void addUIComponent(UIComponent c) {
        uiComponents.add(c);
    }

    public void removeUIComponent(UIComponent c) {
        uiComponents.remove(c);
    }

    public UIComponent getUIComponent(int i) {
        return uiComponents.get(i);
    }

    public List<UIComponent> getUIComponents() {
        return uiComponents;
    }
}
