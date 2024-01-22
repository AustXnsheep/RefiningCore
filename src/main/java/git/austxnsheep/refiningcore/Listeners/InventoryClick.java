package git.austxnsheep.refiningcore.Listeners;

import git.austxnsheep.refiningcore.lore.AttributeEditor;
import git.austxnsheep.refiningcore.lore.EmojiExtractor;
import git.austxnsheep.refiningcore.nbt.NBTUtil;
import git.austxnsheep.refiningcore.reforge.ReforgeStoneConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class InventoryClick implements Listener, AttributeEditor, NBTUtil, ReforgeStoneConstructor, EmojiExtractor {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Refiner")) {
            Inventory clickedInventory = event.getClickedInventory();
            int slot = event.getRawSlot();
            Inventory inv = event.getClickedInventory();
            if (clickedInventory == event.getView().getTopInventory()) {
                ItemStack weapon = inv.getItem(20);
                ItemStack ReforgeStone = inv.getItem(24);
                ItemStack placeholder= inv.getItem(40);
                //Merge items
                switch (slot) {
                    case 20:
                    case 24:
                        break;
                    case 40:
                        if (placeholder.getItemMeta().getDisplayName().contains("Merge items")) {
                            event.getWhoClicked().closeInventory();
                            return;
                        }
                        break;
                    case 22:
                        event.setCancelled(true);
                        if (weapon.getType() != Material.AIR && ReforgeStone.getType() != Material.AIR && canCombine(weapon, ReforgeStone)) {
                            if (hasReforge(weapon)) {
                                if (weapon.getAmount() > 1 || ReforgeStone.getAmount() > 1) {
                                    event.getWhoClicked().closeInventory();
                                    return;
                                }
                                Player player = (Player) event.getWhoClicked();
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                                //Removing old reforge
                                String oldReforge = getReforgeName(weapon);
                                removeReforgeName(weapon);
                                ItemStack fakeItem = new ItemStack(Material.STICK);
                                ItemMeta fakeMeta = fakeItem.getItemMeta();
                                fakeMeta.setDisplayName(oldReforge);
                                fakeItem.setItemMeta(fakeMeta);
                                git.austxnsheep.refiningcore.reforge.ReforgeStone oldstone = constructStone(fakeItem, true);
                                Map<String, Integer> itemAttributes = getAttributesFromLore(weapon);
                                applyAttributesAsNBT(weapon);
                                setAttributesInLore(weapon, combineAttributes(oldstone.getAttributes(), itemAttributes));
                                //Applying new reforge
                                git.austxnsheep.refiningcore.reforge.ReforgeStone stone = constructStone(inv.getItem(24), false);
                                itemAttributes = getAttributesFromLore(weapon);
                                applyAttributesAsNBT(weapon);
                                setAttributesInLore(weapon, combineAttributes(stone.getAttributes(), itemAttributes));
                                addReforgeName(weapon, stone.getReforgeName());
                                inv.setItem(20, new ItemStack(Material.AIR));
                                inv.setItem(24, new ItemStack(Material.AIR));
                                inv.setItem(40, weapon);
                            } else {
                                git.austxnsheep.refiningcore.reforge.ReforgeStone stone = constructStone(inv.getItem(24), false);
                                Map<String, Integer> itemAttributes = getAttributesFromLore(weapon);
                                applyAttributesAsNBT(weapon);
                                setAttributesInLore(weapon, combineAttributes(stone.getAttributes(), itemAttributes));
                                addReforgeName(weapon, stone.getReforgeName());
                                inv.setItem(20, new ItemStack(Material.AIR));
                                inv.setItem(24, new ItemStack(Material.AIR));
                                inv.setItem(40, weapon);
                            }
                        }
                    default:
                        event.setCancelled(true);
                        break;
                }
            }
        }
    }
    public boolean canCombine(ItemStack item1, ItemStack item2) {
        List<String> list1 = getEmojisFromItem(item1);
        List<String> list2 = getEmojisFromItem(item2);
        return haveCommonEmojis(list1, list2) && isCompatable(item1, item2) && containsWord(item2, "Refinement");
    }
    public static boolean containsWord(ItemStack item, String word) {
        if (item == null || !item.hasItemMeta() || word == null || word.isEmpty()) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }

        String lowerCaseWord = word.toLowerCase();

        if (meta.hasDisplayName() && meta.getDisplayName().toLowerCase().contains(lowerCaseWord)) {
            return true;
        }

        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            for (String line : lore) {
                if (line.toLowerCase().contains(lowerCaseWord)) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean isCompatable(ItemStack item1, ItemStack item2) {
        if (isArmor(item1) && isArmor(item2)) {
            return true;
        }
        if (isWeapon(item1) && isWeapon(item2)) {
            return true;
        }
        return false;
    }
    public boolean isArmor(ItemStack item) {
        return containsWord(item, "Armor") || containsWord(item, "Armour");
    }
    public boolean isWeapon(ItemStack item) {
        return containsWord(item, "Weapon");
    }
}
