// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game;

import java.util.List;

/**
 * Represents either a party member or an enemy dependant on AgentType.
 */

//Agent is either an enemy or a party member
public class Agent implements Comparable<Agent>{

    private String name;
    public  AgentType type;//Type of agent this is, either ENEMY or FRIENDLY

    private Statistics stats; //Uses Statistics class to store statistics

    private List<Integer> skills; //Stores IDs of skills this agent can use
    private CurrentEquipment equipment;    //Stores IDs of equipment the agent currently has equipped

    private float x,y;

    private int texture;


    /**
     * Implements the compareTo function required when implementing the Comparable abstract class.
    * Compares the total speeds of two agents, using base stats and equipment modifiers.
    * Orders in descending order.
    */
    public int compareTo(Agent agent){

        int thisSpeed = this.stats.getSpeed()+this.getCurrentEquipment().getTotalSpeedModifiers();
        int otherSpeed = agent.getStats().getSpeed() + agent.getCurrentEquipment().getTotalSpeedModifiers();
        return otherSpeed-thisSpeed;
    }

    /**
     * @param name the name of the Agent.
     * @param type defines whether a friendly or enemy.
     * @param stats an instance of {@link Statistics}.
     * @param skills a list of skill ID's that the agent can use.
     * @param equipment an instance of {@link CurrentEquipment}.
     */
    public Agent(String name, AgentType type, Statistics stats, List<Integer> skills, CurrentEquipment equipment, int texture){

        this.name = name;
        this.type = type;
        this.stats = stats;
        this.skills = skills;
        this.equipment = equipment;
        this.x=0;
        this.y=0;
        this.texture = texture;
    }
    /**
     * Empty constructor required for class to be generated by deserialising json.
     */
    public Agent(){};

    public void dealDamage(int power){
        //Needs to take into account User armourval
        stats.reduceHP(power);
    }
    public void dealHealth(int amount){
        stats.increaseHP(amount);
    }

    public void giveMana(int amount){stats.increaseMP(amount);}

    public void takeMana(int amount){stats.reduceMP(amount);}

    public Statistics getStats() {
        return this.stats;
    }

    public enum AgentType{
        ENEMY,FRIENDLY
    }

    public void equipEquipment(int ID){

        equipment.equip(ID);

    }

    public CurrentEquipment getCurrentEquipment() {
        return equipment;
    }
    //////////////
    //Need to work out how adding equipment when creating agent will work with the armour value statistics of the agent
    /////////////

    /////////
    //Skills
    /////////
    public void addSkill(int skillID){
        if(skills.size()>0){
            if(!skills.contains(skillID)){
                skills.add(skillID);
            }
        }
        else{
            skills.add(skillID);
        }

    }

    public List<Integer> getSkills() {
        return skills;
    }


    public String getName() {
        return name;
    }

    public boolean isDead(){
        if(stats.getCurrentHP()<=0)
            return true;
        else
            return false;
    }

    public AgentType getType() {
        return type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", stats=" + stats +
                ", skills=" + skills +
                ", equipment=" + equipment +
                '}';
    }

    public int getTexture() {
        return texture;
    }

}