// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.managers;

import java.util.LinkedHashMap;

import com.mygdx.game.Game;
import com.mygdx.game.Objective;
// ASSESSMENT 3 START - Change 11//
/**
 * NEW CLASS - ASSESMENT 3 Change 11
 * Stores and manages objectives within the game.
 */
public class ObjectiveManager {
	
	/**
	 * Data structure to store objectives within to allow easy access
	 */
	public LinkedHashMap<String, Objective> gameObjectives = new LinkedHashMap<String, Objective>(); 
	
	public static int objectivesComplete = 0;
	public static int battlesWon = 0;
	public Game game;

	private boolean complete3 = false;
	private boolean complete6 = false;
	
	/**
	 * Creates initial objectives when started with game
	 * @param game Game
	 */
	public ObjectiveManager(Game game) {
		this.game = game;
		// Add objectives that are defined by the game e.g. score
		addObjective("Sally", new Objective("Talk to Sally", 40, "40 Points", false));
		addObjective("10Batt", new Objective("Win 10 battles", 50, "50 Points", true));
		addObjective("3Obj", new Objective("Complete 3 objectives", 0 , "Mystery reward", true));
		addObjective("200Points", new Objective("Reach 200 points", 0, "Mystery reward", true));
		addObjective("AllObj", new Objective("Complete All objectives", 0, "Completes the game", true));
	}
	
	/**
	 * Add a new Objective to game
	 * @param key The Key of the Objective (name)
	 * @param e Objective
	 * @return Number of Objectives
	 */
	public int addObjective(String key, Objective e){
		gameObjectives.put(key, e);
		return gameObjectives.size()-1;
	}

	/**
	 * Checks whether can complete battle objectives
	 * Called each time a battle is won
	 */
	public void battleWon() {
		battlesWon += 1;
		if (battlesWon == 10 && gameObjectives.containsKey("10Batt")){
			completeObjective("10Batt");
			ObjectiveNotification("You won 10 battles! You got 50 points!");
			Game.party.addConsumable(0);
            Game.party.addConsumable(1);
            Game.party.addConsumable(2);
		} 
	}
	
	/**
	 * Sets objective as complete, removes from game objectives
	 * Increases game score by objective. Increases objectivesComplete
	 * @param key
	 */
	public void completeObjective(String key){
		gameObjectives.get(key).setComplete(true);
		Game.pointsScore += gameObjectives.get(key).getAddScore();
		gameObjectives.remove(key);
		objectivesComplete++;
	}

	/**
	 * Displays message on world game screen
	 * @param message String to display on screen
	 */
	public void ObjectiveNotification(String message){
		game.getWorldScreen().getGameWorld().uiManager.addNotification(message);
	}

	/**
	 * Checks points objectives. Called each frame.
	 */
	public void checkPoints() {
		if (Game.pointsScore >= 200 && gameObjectives.containsKey("200Points")) {
			addObjective("400Points", new Objective("Reach 400 points", 0, "Mystery reward", true));
			completeObjective("200Points");
			ObjectiveNotification("You have over 200 points. Objective completed.");
		}
		else if (Game.pointsScore >= 400 && gameObjectives.containsKey("400Points")){
			completeObjective("400Points");
			ObjectiveNotification("You have over 400 points. Objective completed.");
		}
	}

	/**
	 * Check number of objectives complete. Called each frame.
	 */
	public void checkNumCompleteObjectives(){
		if (!complete3){
			check3Objective();
		}
		else if (!complete6){
			check6Objective();
		}
		else {
			checkAllComplete();
		}
		checkPoints();
	}
	
	/**
	 * Checks if 3 objectives have been completed. Adds reward skills when completed.
	 * Add next 'complete number of objectives' objective 
	 */
	public void check3Objective() {
		if (objectivesComplete == 3 && !complete3) {
			ObjectiveNotification("You have completed 3 objectives. Have some new skills!");
			addObjective("6Obj", new Objective("Complete 6 objectives", 0, "Mystery reward", true));
			completeObjective("3Obj");
			Game.party.getMember(0).addSkill(9);
			Game.party.getMember(1).addSkill(10);
			Game.party.getMember(2).addSkill(11);
			Game.party.getMember(3).addSkill(12);
			complete3 = true;
		}
	}

	/**
	 * Checks if 6 objectives complete. Adds reward skills when completed.
	 */
	public void check6Objective() {
		if (objectivesComplete == 6 && !complete6) {
			ObjectiveNotification("You have completed 6 objectives. Have some new skills!");
			completeObjective("6Obj");
			Game.party.getMember(0).addSkill(13);
			Game.party.getMember(1).addSkill(14);
			Game.party.getMember(2).addSkill(15);
			Game.party.getMember(3).addSkill(16);
			complete6 = true;
		}
	}

	/**
	 * Checks if all objectives completed. Calls gameWinScreen to set to win screen + ends game.
	 */
	public void checkAllComplete() {
		if (gameObjectives.size() == 1 && gameObjectives.containsKey("AllObj")) {
			game.newWinScreen();
		}
	}

}
// ASSESSMENT 3 END //
