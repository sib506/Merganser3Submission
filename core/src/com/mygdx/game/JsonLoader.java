// Downloadable JAR: http://merganser.weebly.com/assessment-3.html or DIRECT DOWNLOAD AT https://drive.google.com/file/d/0B_xhR6pi2K8KV0FwMDRaWk1NdWM/view?usp=sharing
package com.mygdx.game;
//ASSESSMENT 3 UPDATE - Package Update (7)
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.managers.ItemManager;
import com.mygdx.game.managers.PartyManager;
import com.mygdx.game.managers.SkillManager;

import java.io.FileNotFoundException;
import java.io.IOException;



//Useful link : "http://jackyjjc.com/2013/10/07/parsing-json-in-libgdx-tutorial/"
public class JsonLoader {

    private Json json = new Json();

    public JsonLoader() {
    }

    /**
     * Returns a ItemManager by deserialising the given json file.
     * @param file The name/location of the Json file
     * @return Returns a ItemManager generated from the Json file given.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ItemManager parseItemManager(String file)throws FileNotFoundException, IOException {

        return json.fromJson(ItemManager.class, Gdx.files.internal(file));


    }

    /**
     * Returns a SkillManager by deserialising the given json file.
     * @param file The name/location of the Json file
     * @return Returns a SkillManager generated from the Json file given.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public SkillManager parseSkillManager(String file)throws FileNotFoundException, IOException {

        return json.fromJson(SkillManager.class, Gdx.files.internal(file));

    }

    /**
     * Returns a PartyManager by deserialising the given json file.
     * @param file The name/location of the Json file
     * @return Returns a PartyManager generated from the Json file given.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public PartyManager parsePartyManager(String file)throws FileNotFoundException, IOException{
        return json.fromJson(PartyManager.class, Gdx.files.internal(file));
    }


}
