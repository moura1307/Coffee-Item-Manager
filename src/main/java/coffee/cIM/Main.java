package coffee.cIM;

import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public final class Main extends JavaPlugin {

    private JSONReader reader;

    @Override
    public void onEnable() {
        this.reader = new JSONReader(this);
        reader.createFolders();
        reader.loadCustomItems();
    }

    @Override
    public void onDisable() {
        getLogger().warning( "CIM disabled successfully.");
    }

}

/* TODO
*   1. Create the file of the constructor for the file if ITEM exists and RECIPE doesn't
*   2. Add this to the minecraft datapack folder automatically
*   3. Start working on the actual constructor (making the .json file easier to read))
*   4. Create custom tags to some items
* */