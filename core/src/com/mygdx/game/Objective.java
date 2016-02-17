// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game;
/**
 * Class to handle data for each objective
 * ASSESSMENT 3 addition (11)
 */
public class Objective {
	private String description;
	private boolean complete = false;
	private int addScore;
	private String textReward;
	private boolean valueObjective;
	
	public Objective(String description, int score, String textReward, boolean valueObjective ){
		this.description = description;
		this.addScore = score;
		this.textReward = textReward;
		this.valueObjective = valueObjective;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public int getAddScore() {
		return addScore;
	}

	public void setAddScore(int addScore) {
		this.addScore = addScore;
	}

	public String getTextReward() {
		return textReward;
	}

	public void setTextReward(String textReward) {
		this.textReward = textReward;
	}

	public boolean isValueObjective() {
		return valueObjective;
	}

	public void setValueObjective(boolean valueObjective) {
		this.valueObjective = valueObjective;
	}
	
}
