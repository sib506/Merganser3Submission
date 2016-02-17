// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game;
// IMPORTS - ASSESSMENT 3 DELETION (6)
import java.util.Arrays;

/**
 * Class represents an individual equipable item, ID's are assigned by the order of the equipment in the ItemManager's list.
 * The Modifiers array stores the statistic modifiers this equipment piece gives in the order: Speed, Strength, Dexterity, Intelligence, ArmourValue.
 */
public class Equipable {

    private int ID = -1;
    private String name, description;
    private equipType type;
    private int[] modifiers = new int[5];//Ordered: speed, strength, dexterity, intelligence, armourVal
    private int levelRequirement;

    @Override
    public String toString() {
        return "Equipable{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", modifiers=" + Arrays.toString(modifiers) +
                ", levelRequirement=" + levelRequirement +
                '}';
    }

    /**
     * Constructor for a new Equipable.
     * @param name The name of the Equipable.
     * @param description The description of the Equipable.
     * @param type The type of the Equipable.
     * @param speedModifier The speedModifier.
     * @param strengthModifier The strengthModifier.
     * @param dexterityModifier The dexterityModifier.
     * @param intelligenceModifier The intelligenceModifier.
     * @param armourValModifier The armourValue Modifier.
     * @param levelRequirement The level requirement of the Equipable.
     */
    public Equipable(String name, String description, equipType type, int speedModifier, int strengthModifier, int dexterityModifier, int intelligenceModifier, int armourValModifier, int levelRequirement) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.modifiers[0] = speedModifier;
        this.modifiers[1] = strengthModifier;
        this.modifiers[2] = dexterityModifier;
        this.modifiers[3] = intelligenceModifier;
        this.modifiers[4] = armourValModifier;
        this.levelRequirement = levelRequirement;


    }

    /**
     * Used for generating classes from json.
     */
    public Equipable() {
    }


    //Used in the CurrentEquipment method, totalStatModifiers. Simply returns modifiers array
    public int[] getModifiers() {
        return modifiers;
    }

    public void updateID(int id) {
        this.ID = id;
    }


    public int getID() {
        return ID;
    }

    public equipType getType() {
        return type;
    }

    //Only 5 different types of equipable
    public enum equipType {
        HEAD, CHEST, FEET, ACCESSORY, WEAPON
    }
}
