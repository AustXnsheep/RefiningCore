package git.austxnsheep.refiningcore.lore;

import git.austxnsheep.refiningcore.RefiningCore;
import git.austxnsheep.refiningcore.reforge.ReforgeStone;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public interface AttributeEditor {
    ArrayList list = null;
    default Map<String, Integer> getAttributesFromLore(ItemStack item) {
        Map<String, Integer> attributes = new HashMap<>();
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasLore()) {
                List<String> lore = meta.getLore();
                for (String line : lore) {
                    line = ChatColor.stripColor(line); // Remove color codes

                    if (line.startsWith(" •")) {
                        try {
                            String[] parts = line.split(": \\+");
                            if (parts.length == 2) {
                                String attributeName = parts[0].substring(parts[0].indexOf(" ")).trim().replaceAll("[^a-zA-Z0-9 ]", "");

                                String valuePart = parts[1].trim().replaceAll("[^0-9]", "");
                                Integer attributeValue = Integer.parseInt(valuePart);
                                attributeName = attributeName.length() > 2 ? attributeName.substring(2) : attributeName;
                                attributes.put(attributeName, attributeValue);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing attribute value from lore: " + e.getMessage());
                        }
                    }
                }
            }
        }
        return attributes;
    }
    default void setAttributesInLore(ItemStack item, Map<String, Integer> attributes) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasLore()) {
            List<String> newLore = new ArrayList<>();
            List<String> existingLore = meta.getLore();
            Map<String, Integer> newAttributes = mergeAttributes(getAttributesFromLore(item), attributes);
            boolean found = false;
            for (String line : existingLore) {
                String strippedLine = ChatColor.stripColor(line);
                if (isAttributeLine(strippedLine)) {
                    if (!found) {
                        addAttributesToLore(newLore, newAttributes);
                        found = true;
                    }
                } else {
                    newLore.add(line);
                }
            }
            meta.setLore(newLore);
            item.setItemMeta(meta);
        }
    }
    default boolean isAttributeLine(String line) {
        if (line == null || line.isEmpty()) {
            return false;
        }

        String strippedLine = ChatColor.stripColor(line);

        return strippedLine.startsWith(" • ") && strippedLine.contains(": +");
    }
    default Map<String, Integer> mergeAttributes(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> mergedMap = new HashMap<>();

        if (map1 != null) {
            mergedMap.putAll(map1);
        }

        if (map2 != null) {
            for (Map.Entry<String, Integer> entry : map2.entrySet()) {
                mergedMap.put(entry.getKey(), entry.getValue());
            }
        }

        return mergedMap;
    }
    default List<String> addAttributesToLore(List<String> lore, Map<String, Integer> attributes) {
        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            if (RefiningCore.percentageList.contains(entry.getKey())) {
                lore.add(ChatColor.GREEN + " • " + ChatColor.DARK_GRAY + RefiningCore.attributeEmojis.get(entry.getKey())
                        + " " + ChatColor.GRAY + entry.getKey() + ": "
                        + RefiningCore.attributeColors.get(entry.getKey())
                        + (entry.getValue() >= 0 ? "+" : "")
                        + entry.getValue() + "%");
            } else {
                lore.add(ChatColor.GREEN + " • " + ChatColor.DARK_GRAY + RefiningCore.attributeEmojis.get(entry.getKey())
                        + " " + ChatColor.GRAY + entry.getKey() + ": "
                        + RefiningCore.attributeColors.get(entry.getKey())
                        + (entry.getValue() >= 0 ? "+" : "")
                        + entry.getValue());
            }
        }
        return lore;
    }
    default Map<String, Integer> combineAttributes(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> combinedMap = new HashMap<>();

        if (map1 != null) {
            combinedMap.putAll(map1);
        }

        if (map2 != null) {
            for (Map.Entry<String, Integer> entry : map2.entrySet()) {
                combinedMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        return combinedMap;
    }
    default void addReforgeName(ItemStack item, String reforgeName) {
        if (item == null || reforgeName == null || reforgeName.isEmpty()) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {
            String displayName = meta.getDisplayName();

            if (!displayName.contains(":")) {
                meta.setDisplayName(displayName + " : " + ChatColor.LIGHT_PURPLE + reforgeName);
                item.setItemMeta(meta);
            } else {

            }
        }
    }
    default String getReforgeName(ItemStack item) {
        if (item == null) {
            return null;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {
            String displayName = meta.getDisplayName();

            String[] parts = displayName.split(":");
            if (parts.length == 2) {
                return parts[1].trim();
            }
        }

        return null;
    }
    default String removeReforgeName(ItemStack item) {
        if (item == null) {
            return null;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {
            String displayName = meta.getDisplayName();

            String[] parts = displayName.split(" : ");
            if (parts.length >= 2) {
                meta.setDisplayName(parts[0]);
                item.setItemMeta(meta);
                return parts[0];
            }
        }

        return null;
    }
    default boolean hasReforge(ItemStack item) {
        return item.getItemMeta().getDisplayName().contains(":");
    }
    default void Test(ItemStack item) {
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            List<String> lore = item.getItemMeta().getLore();
            for (int i = 0; i <= lore.size(); i++) {
                String loreString = lore.get(i).toString();
                if (loreString.toLowerCase().contains("item quality")) {
                    String[] splitLore = loreString.split("/");
                    try {
                        int itemQuality = Integer.parseInt(splitLore[0]);
                        System.out.println(itemQuality);
                    } catch (Exception e) {
                        System.out.print("Something went wrong, please report this");
                    }
                }
            }
        }
    }
}
