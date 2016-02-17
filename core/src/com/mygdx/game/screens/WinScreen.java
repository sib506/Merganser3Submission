// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Game;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.input.InputHandler;

/**
 * NEW CLASS ASSESSMENT 3 - Change 10
 * A simple screen that is used when game is won - displays score
 */
public class WinScreen extends ScreenAdapter {

    private float fadeInCounter;
    private float runningTime;
    private final String END_MESSAGE;
    private final int END_SCORE; 
    private final String QUIT_MESSAGE;
    private SpriteBatch batch = new SpriteBatch();

    public WinScreen (Game game){
        this.END_SCORE = Game.pointsScore;
        this.END_MESSAGE = "CONGRATULATIONS.HESLINGTON IS SAFE!\nYOUR SCORE: " + END_SCORE;
        this.QUIT_MESSAGE = "PRESS E TO EXIT";
    }

    public void show() {
        fadeInCounter = 1f;
        runningTime = 0;
    }

    public void render (float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        runningTime += delta;
        update();
        batch.begin();
        batch.draw(Assets.title, 0, 0);
        Pixmap black = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        black.setColor(0,0,0,fadeInCounter);
        fadeInCounter -= 0.01f;
        if (fadeInCounter < 0) {
            fadeInCounter = 0;
        }
        black.fill();
        if (runningTime %1 > 0.5f) {
            Assets.consolas22.draw(batch, new GlyphLayout(Assets.consolas22, END_MESSAGE, Color.GRAY, Gdx.graphics.getWidth(), Align.center, false), 0, 200);
            Assets.consolas22.draw(batch, new GlyphLayout(Assets.consolas22, QUIT_MESSAGE, Color.GRAY, Gdx.graphics.getWidth(), Align.center, false), 0, 100);
        } else {
            Assets.consolas22.draw(batch, new GlyphLayout(Assets.consolas22, END_MESSAGE, Color.WHITE, Gdx.graphics.getWidth(), Align.center, false), 0, 200);
            Assets.consolas22.draw(batch, new GlyphLayout(Assets.consolas22, QUIT_MESSAGE, Color.WHITE, Gdx.graphics.getWidth(), Align.center, false), 0, 100);
        }
        Texture blackTexture = new Texture(black);
        batch.draw(blackTexture,0,0);
        batch.end();
        
        black.dispose();
        black = null;
        blackTexture.dispose();
        blackTexture = null;
        
    }

    private void update() {
        InputHandler.update();
        if (InputHandler.isActJustPressed()) {
            Gdx.app.exit();
        }
    }
}
