// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.managers;
//ASSESSMENT updated packages (change 7)
import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.Skill;

/**
 * Stores and manages every skill.
 */

public class SkillManager {

    private List<Skill> skills = new ArrayList<Skill>();


    public SkillManager() {

    }

    /**
     * Add new Skill.
     * @param skill The Skill to add
     */
    public void addSkill(Skill skill) {

        skill.updateID(skills.size()); //Gives the skill the ID of it's index
        skills.add(skill);//Check size function
    }

    /**
     * Returns the Skill stored at the given index.
     * @param skillID The index of the Skill to retrieve
     * @return Skill
     */
    public Skill getSkill(int skillID) {
        return skills.get(skillID);
    }


    @Override
    public String toString() {
        return "SkillManager{" +
                "skills=" + skills +
                '}';
    }


}
