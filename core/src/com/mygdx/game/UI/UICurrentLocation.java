// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;

/**
 * NEW CLASS ASSESSMENT 3 (change 13)
 * Component for rendering the current location at the bottom right of the screen.
 */
public class UICurrentLocation extends UIComponent {

    UIMessageBox messageBox;
    String messagePrepend="Current Location: ";

    public UICurrentLocation(){
        super(0,0,0,0);
        this.width=500;
        this.height=20;
        this.x= Gdx.graphics.getWidth()-width;;
        this.y= 0;
        this.messageBox = new UIMessageBox(messagePrepend+Game.currentLocation,this.x,this.y,this.width,this.height,10,10);
    }

    /**
     * Called once per frame to render the location.
     */
    public void render(SpriteBatch batch, NinePatch patch){
        messageBox.setMessage(messagePrepend+Game.currentLocation);
        messageBox.render(batch,patch);
    }
}
