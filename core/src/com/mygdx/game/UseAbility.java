// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game;

import com.mygdx.game.battle.BattleMenu;

/**
 * The super class for the UseItem and UseSkill classes.
 */
public abstract class UseAbility {

    Agent user, target;
    int abilityID;
    BattleMenu battleMenu;

    public UseAbility(Agent user, Agent target, int abilityID, BattleMenu battleMenu){
        this.user = user;
        this.target = target;
        this.abilityID = abilityID;
        this.battleMenu = battleMenu;

    }


    public abstract void movementDone(int type);

    public abstract void update(float delta);
}
