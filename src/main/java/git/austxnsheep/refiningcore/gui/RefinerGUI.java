package git.austxnsheep.refiningcore.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RefinerGUI {
    public RefinerGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, "Refiner");
        //PINK_STAINED_GLASS_PANE
        ItemStack darkPinkPanel = createGuiItem(Material.PINK_STAINED_GLASS_PANE, ChatColor.DARK_PURPLE + "Path", " ");
        ItemStack air = new ItemStack(Material.AIR, 1);
        ItemStack anvil = createGuiItem(Material.ANVIL, ChatColor.WHITE + "Combine Items", ChatColor.YELLOW + "<Reforged Item | Reforge>");

        ItemStack pinkPane = createGuiItem(Material.MAGENTA_STAINED_GLASS_PANE, " ", " ");
        ItemStack grayPane = createGuiItem(Material.GRAY_STAINED_GLASS_PANE, " ", " ");

        ItemStack sign = new ItemStack(Material.BAMBOO_HANGING_SIGN);
        ItemMeta meta = sign.getItemMeta();
        List<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.GRAY + "Put a reforge stone and the item");
        lore1.add(ChatColor.GRAY + "of your choice to reforge the item!");
        lore1.add(ChatColor.GRAY + "Reforges add stats to the item!");
        lore1.add(ChatColor.GRAY + " ");
        lore1.add(ChatColor.RED + "TIP: Reforge stone gets consumed on use.");
        lore1.add(ChatColor.RED + "TIP: This cannot be undone.");
        meta.setLore(lore1);
        meta.setDisplayName(ChatColor.GREEN + "Reforge Station");
        sign.setItemMeta(meta);

        for (int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, grayPane);
        }

        int[] availableSlots = {29, 33, 38, 42, 39, 41};
        for (int slot : availableSlots) {
            gui.setItem(slot, darkPinkPanel);
        }
        for (int i = 0; i <= 8; i++) {
            gui.setItem(i, pinkPane);
        }
        int[] redSlots = {9, 17, 18, 26, 27, 35, 36, 44, 45, 53};
        for (int slot : redSlots) {
            gui.setItem(slot, pinkPane);
        }
        int[] openSlots = {20, 24, 40};
        for (int slot : openSlots) {
            gui.setItem(slot, air);
        }
        gui.setItem(40, new ItemStack(createGuiItem(Material.MAGENTA_GLAZED_TERRACOTTA, ChatColor.LIGHT_PURPLE + "Merge items", ChatColor.RED + "Merging items cannot be undone")));
        gui.setItem(22, anvil);
        gui.setItem(4, sign);

        player.openInventory(gui);
    }
    private ItemStack createGuiItem(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Collections.singletonList(lore));
        item.setItemMeta(meta);
        return item;
    }
}
