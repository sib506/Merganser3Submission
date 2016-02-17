// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An abstract class that represents a UI object on screen.
 * A UIComponent is responsible for rendering itself.
 * The properties of this object is the position and size of the component.
 */
public abstract class UIComponent {
    public float x, y, width, height;

    public UIComponent(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Called once per frame to render the UI component.
     */
    public abstract void render(SpriteBatch batch, NinePatch patch);
}
