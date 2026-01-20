package coffee.cIM;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class JSONReader {

    private final Main plugin;
    private final File parentFolder;
    private final Gson gson = new Gson();

    public JSONReader(Main plugin) { this.plugin = plugin; this.parentFolder = plugin.getDataFolder(); }

    public class CustomItemData {
        String name;
        int amount;
        List<String> custom_tags;
    }

    public void createFolders() {

        //Each folder here is created inside CIT folder
        String[] folders = {"items", "recipes", "loot_tables"};

        for (String folderNames : folders) {
            File childFolders = new File(parentFolder, folderNames);
            if(childFolders.mkdirs()) {
                plugin.getLogger().warning("Created: " + childFolders);
            }
        }
    }

    public void loadCustomItems() {
        if (parentFolder.exists() && parentFolder.isDirectory()) {

            //Check for the items folder
            File[] directories = parentFolder.listFiles(File::isDirectory);
            if (directories != null) {
                for (File dir : directories) {
                    if (dir.getName().equals("items")) {

                        //Check if files do exist and then lists them
                        File[] filesItems = dir.listFiles();
                        if (filesItems != null) {
                            for (File fileItem : filesItems) {
                                if (fileItem.getName().endsWith(".json")) {

                                    try(Reader reader = new FileReader(fileItem)) {
                                        CustomItemData data = gson.fromJson(reader, CustomItemData.class);
                                        plugin.getLogger().warning("Loaded: " + data.name);

                                        //Checks for a recipe with the same name of created item
                                        File recipesFolder = new File(parentFolder, "recipes");
                                        File matchingRecipe = new File(recipesFolder, fileItem.getName());

                                        if (matchingRecipe.exists()) {

                                            //since the file is already married you just need to
                                            //check if there's updates to the item file
                                                //there is: get its "couple" and alter it based on what's inside
                                                //there isn't: don't change anything
                                            //update on the datapack folder (if there are) the changes

                                        } else {
                                            plugin.getLogger().warning("No recipe found for: " + fileItem.getName() + ". Creating default.");

                                            //gets the "single" file name
                                            //creates a recipe folder with the same name
                                            //after marrying these folder send them to the datapack folder

                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            System.out.println("No files found!");
                        }
                    }
                }
            } else {
                plugin.getLogger().warning("No directories found!");
            }
        }
    }
}