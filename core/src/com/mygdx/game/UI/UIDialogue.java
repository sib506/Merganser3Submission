// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game.UI;
//ASSESSMENT updated packages (change 7)
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.InputHandler;

/**
 * A UIComponent that is composed of a sequence of dialogue boxes.
 */
public class UIDialogue extends UIComponent {

    private String[] messages;
    private int currentMessage;
    private float dialogueTimer;
    private float dialogueWaitTime;
    private UIDialogueBox[] dialogueBoxes;

    public UIDialogue(float x, float y, float width, float height, String[] messages) {
        super(x, y, width, height);
        this.messages = messages;
        dialogueBoxes = new UIDialogueBox[messages.length];
        for (int i=0;i<messages.length;i++) {
            dialogueBoxes[i] = new UIDialogueBox(x, y, width, height, messages[i]);
        }
        currentMessage = 0;
        this.dialogueWaitTime = 1f;
    }

    public UIDialogue(float x, float y, float width, float height, String[] messages, float dialogueWaitTime) {
        super(x, y, width, height);
        this.messages = messages;
        dialogueBoxes = new UIDialogueBox[messages.length];
        for (int i=0;i<messages.length;i++) {
            dialogueBoxes[i] = new UIDialogueBox(x, y, width, height, messages[i]);
        }
        currentMessage = 0;
        this.dialogueWaitTime = dialogueWaitTime;
    }

    /**
     * Called once per frame to render the current dialogue box.
     */
    @Override
    public void render(SpriteBatch batch, NinePatch patch) {
        dialogueBoxes[currentMessage].render(batch, patch);
    }

    /**
     * Called once per frame to handle timing of ui elements.
     * @return returns true if the dialogue box should continue to be displayed.
     */
    public boolean update(float delta) {
        dialogueTimer += delta;
        if (dialogueTimer > dialogueWaitTime) {
            dialogueBoxes[currentMessage].setArrow(dialogueTimer%1 > 0.5f);
            if (InputHandler.isActJustPressed()) {
                if (currentMessage < messages.length - 1) {
                    currentMessage++;
                    dialogueTimer = 0;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
