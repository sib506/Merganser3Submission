// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
//Imports cleaned in ASSESSMENT 3 (6)

package com.mygdx.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.game.Game;
import com.mygdx.game.GameWorld;
import com.mygdx.game.assets.Assets;

/**
 * The screen that contains the gameWorld and worldRenderer.
 * This class is responsible for calling the gameWorld update method
 * and the worldRenderer render method once per frame.
 */
public class WorldScreen extends ScreenAdapter {

    private Game game;
    private GameWorld gameWorld;
    private WorldRenderer worldRenderer;


    public WorldScreen(Game game) {
        this.game = game;
        gameWorld = new GameWorld(game);
        worldRenderer = new WorldRenderer(gameWorld);
    }

    public void show() {
        Assets.worldMusic.setVolume(Game.masterVolume+0.3f);//Start the worldMusic
        Assets.worldMusic.play();
    }

    @Override
    public void hide() {
        Assets.worldMusic.stop();
        Assets.worldMusic.dispose();
        super.hide();
    }

    public void update(float delta) {
        gameWorld.update(delta);
    }


    @Override
    public void render(float delta) {
        update(delta);
        //ASSESSMENT 3 change (11)
        game.objectiveManager.checkNumCompleteObjectives();
        //END ASSESSMENT 3 change
        worldRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
    }
    //ASSESSMENT 3 change (11)
    public GameWorld getGameWorld(){
    	return gameWorld;
    }
    //END ASSESSMENT 3 change
}

