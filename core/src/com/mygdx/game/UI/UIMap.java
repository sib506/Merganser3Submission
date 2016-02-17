// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Level;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.input.InputHandler;

/**
 * The map allows the user to see a world map
 * along with pointer to current location on map
 * 
 * NEW class added for assessment 3 (change 14)
 */
public class UIMap extends UIComponent {

	private boolean show;

	private BitmapFont font;
	private Level level;

	float paddingX;
	float paddingY;
	int i;

	private UIMessageBox map = new UIMessageBox("World Map", Assets.consolas22, Color.WHITE, Align.center, x,
			(y + height + 4), width, 0, 10);

	public UIMap(float x, float y, float width, float height, Level level) {
		super(x, y, width, height);
		paddingX = 25;
		paddingY = 70;
		font = Assets.consolas22;
		show = false;
		this.level = level;
	}

	/**
	 * Called once per frame to render the map
	 */
	@Override
	public void render(SpriteBatch batch, NinePatch patch) {

		if (show) {

			new UIMessageBox("", Assets.consolas22, Color.WHITE, Align.center, x, y, width, height).render(batch,
					patch);

			map.render(batch, patch);
			batch.draw(Assets.worldMap, 95, 60);
			batch.draw(Assets.playerTexture,
					(95 + (level.player.getCurrentTile().x / level.mapWidth) * Assets.worldMap.getWidth()),
					(60 + (level.player.getCurrentTile().y / level.mapHeight) * Assets.worldMap.getHeight()));
		}
	}

	/**
	 * Makes the UI component visible on screen.
	 */
	public void show() {
		show = true;
	}

	/**
	 * Called once per frame to handle closing of map
	 * 
	 * @return returns true if the dialogue box should continue to be displayed.
	 */
	public boolean update(float delta) {
		if (InputHandler.isEscJustPressed()) {
			show = false;
			return false;
		} else {
			return true;
		}

	}

	/**
     * Helper function for render that actually does the text rendering.
     * @param batch the spritebatch to use.
     * @param message The string to add.
     * @param x The x location.
     * @param y The y location.
     * @param color The colour to render the text as.
     */
	public void renderText(SpriteBatch batch, String message, float x, float y, Color color) {
		GlyphLayout layout = new GlyphLayout(font, message, Color.BLACK, width - paddingX * 2, Align.left, false);

		font.draw(batch, layout, x + paddingX, y + height + paddingY - 2);
		layout.setText(font, message, color, width - paddingX * 2, Align.left, false);
		font.draw(batch, layout, x + paddingX, y + height + paddingY);
	}
}
