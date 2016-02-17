// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Consumable;
import com.mygdx.game.assets.Assets;

/**
 * The item menu is part of the party menu and show information about party items.
 * 
 * NEW Class added for assessment 3 (change 19)
 */
public class UIItem extends UIComponent {

    private BitmapFont font;

    private final float LINE_HEIGHT = 25f;
    private Consumable consumable;
    private int count;

    float paddingX;
    float paddingY;

    public UIItem(float x, float y, float width, Consumable consumable, int count) {
        super(x, y, width, 50);
        this.consumable = consumable;
        this.count = count;
        paddingX = 20;
        paddingY = 20;
        font = Assets.consolas22;
    }

    /**
     * Called once per frame to render the item information.
     */
    @Override
    public void render(SpriteBatch batch, NinePatch patch) {
        patch.draw(batch, x, y, width, height + (paddingY * 2));
        renderText(batch, consumable.getName(), x, y, Color.WHITE);
        renderText(batch, consumable.getDescription(), x, y - LINE_HEIGHT, Color.LIGHT_GRAY);
        renderText(batch, "Available: " + count, x+250, y, Color.WHITE);
    }

    /**
     * Helper function for render that actually does the rendering.
     * @param batch the spritebatch to use.
     * @param message The string to add.
     * @param x The x location.
     * @param y The y location.
     * @param color The colour to render the text as.
     */
    private void renderText(SpriteBatch batch, String message, float x, float y, Color color) {
        GlyphLayout layout = new GlyphLayout(font, message,
                Color.BLACK, width - paddingX * 2, Align.left, false);

        font.draw(batch, layout, x + paddingX, y + height + paddingY - 2);
        layout.setText(font, message,
                color, width - paddingX * 2, Align.left, false);
        font.draw(batch, layout, x + paddingX, y + height + paddingY);
    }
}
