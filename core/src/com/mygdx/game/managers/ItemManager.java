// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.managers;
//ASSESSMENT updated packages (change 7)
import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.Consumable;
import com.mygdx.game.Equipable;

/**
 * Stores and manages every item in the game.
 */
public class ItemManager {

    private List<Equipable> equipables = new ArrayList<Equipable>();
    private List<Consumable> consumables = new ArrayList<Consumable>();


    public ItemManager() {

        //Add default Empty equipments to first index of each list
        equipables.add(new Equipable("EmptyEquipment", "EmptyEquipment", Equipable.equipType.WEAPON, 0, 0, 0, 0, 0, 0));
        consumables.add(new Consumable("EmptyConsumable","EmptyConsumable", Consumable.consumeType.HEAL,0));
    }

    /**
     * Adds a new equipable item.
     * @param equipable The Equipable to add.
     */
    public void addEquipable(Equipable equipable) {

        equipable.updateID(equipables.size());
        equipables.add(equipable);//Check size function
    }

    /**
     * Returns the Equipable stored at the given index.
     * @param itemID The index of the Equipable to retrieve.
     * @return An Equipable
     */
    public Equipable getEquipable(int itemID) {
        return equipables.get(itemID);
    }

    /**
     * Adds a new consumable item.
     */
    public void addConsumable(Consumable consumable) {

        consumable.updateID(consumables.size());
        consumables.add(consumable);//Check size function
    }

    /**
     * Returns the Consumable stored at the given index.
     * @param itemID The index of the Consumable to retrieve.
     * @return A Consumable
     */
    public Consumable getConsumable(int itemID) {
        return consumables.get(itemID);
    }

    @Override
    public String toString() {
        return "ItemManager{" +
                "equipables=" + equipables +
                ", consumables=" + consumables +
                '}';
    }
	
	
	/**
	 * Getter for list of consumables
	 * Added for assessment 3 - change 19
	 */ 
	public List<Consumable> getConsumables() {
		
		return consumables;
	}
}
