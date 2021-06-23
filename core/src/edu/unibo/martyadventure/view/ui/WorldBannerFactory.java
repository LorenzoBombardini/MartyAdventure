package edu.unibo.martyadventure.view.ui;

import java.util.EnumMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

import edu.unibo.martyadventure.view.MapManager.Maps;
import edu.unibo.martyadventure.view.Toolbox;

public class WorldBannerFactory {

    private static String PATH_1 = "Level/Title/MartyAdventureTitle1.png";
    private static String PATH_2 = "Level/Title/MartyAdventureTitle2.png";
    private static String PATH_3 = "Level/Title/MartyAdventureTitle3.png";

    private Map<Maps, String> mapPath;


    public WorldBannerFactory() {
        mapPath = new EnumMap<>(Maps.class);
        mapPath.put(Maps.MAP1, PATH_1);
        mapPath.put(Maps.MAP2, PATH_2);
        mapPath.put(Maps.MAP3, PATH_3);
    }

    public Texture createBanner(Maps map) {
        return Toolbox.getTexture(mapPath.get(map));
    }
}
