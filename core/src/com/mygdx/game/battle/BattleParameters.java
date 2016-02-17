// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.battle;
//ASSESSMENT updated packages (change 7)
import com.mygdx.game.Agent;
import com.mygdx.game.managers.PartyManager;

/**
 * Class holds the parameters to pass to the battlescreen when switching between screens.
 */
public class BattleParameters {

    private int background;
    private PartyManager enemyParty;

    public BattleParameters(int background) {
        this.background = background;
        enemyParty = new PartyManager();
    }

    public void addEnemy(Agent enemy) {
        enemyParty.addMember(enemy);
    }

    public PartyManager getEnemyParty() {
        return enemyParty;
    }

    public int getBackground() {
        return background;
    }

}
