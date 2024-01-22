package git.austxnsheep.refiningcore;

import git.austxnsheep.refiningcore.Listeners.InventoryClick;
import git.austxnsheep.refiningcore.Listeners.InventoryClose;
import git.austxnsheep.refiningcore.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RefiningCore extends JavaPlugin {
    /*
Attack Damage: 69
Evasion Chance: 69
Starfury: 69
Crit Damage: 69
Magic Damage: 69
Health: 69
Defense: 69
Crit Rate: 69
🗡️ 🛡️ 🔥 🏹🧪
     */
    public static JavaPlugin plugin;
    public static Map<String, String> attributeEmojis = new HashMap<>();
    public static Map<String, ChatColor> attributeColors = new HashMap<>();
    public static List<String> percentageList = new ArrayList<>();
    //Blitzfang

    @Override
    public void onEnable() {
        plugin = this;
        createAttributeEmojiMap();
        createAttributeColorMap();
        createPercentList();
        //Commands
        this.getCommand("refiner").setExecutor(new Refiner());
        //Listeners
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
    }

    @Override
    public void onDisable() {
    }
    public static JavaPlugin getInstance() {
        return plugin;
    }

    public static void createAttributeEmojiMap() {
        attributeEmojis.put("Health", "❤");
        attributeEmojis.put("Defense", "🛡");
        attributeEmojis.put("Attack Damage", "🗡");
        attributeEmojis.put("Magic Damage", "🔥");
        attributeEmojis.put("Crit Damage", "☠");
        attributeEmojis.put("Crit Rate", "✴");
        attributeEmojis.put("Evasion Chance", "➠");
        attributeEmojis.put("Starfury", "✨");

    }
    public static void createAttributeColorMap() {
        attributeColors.put("Health", ChatColor.RED);
        attributeColors.put("Defense", ChatColor.GREEN);
        attributeColors.put("Attack Damage", ChatColor.RED);
        attributeColors.put("Magic Damage", ChatColor.LIGHT_PURPLE);
        attributeColors.put("Crit Damage", ChatColor.BLUE);
        attributeColors.put("Crit Rate", ChatColor.BLUE);
        attributeColors.put("Evasion Chance", ChatColor.DARK_PURPLE);
        attributeColors.put("Starfury", ChatColor.AQUA);

    }
    public static void createPercentList() {
        percentageList.add("Crit Damage");
        percentageList.add("Evasion Chance");
        percentageList.add("Crit Rate");
    }
}
