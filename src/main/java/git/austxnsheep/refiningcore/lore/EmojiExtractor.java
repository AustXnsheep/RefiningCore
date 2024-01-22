package git.austxnsheep.refiningcore.lore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public interface EmojiExtractor {
    Set<String> possibleEmojis = new HashSet<>(Arrays.asList("🗡", "🛡", "🔥", "🏹", "🧪"));

    default List<String> getEmojisFromItem(ItemStack item) {
        List<String> foundEmojis = new ArrayList<>();

        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();

            if (meta.hasDisplayName()) {
                String displayName = ChatColor.stripColor(meta.getDisplayName());
                for (String emoji : possibleEmojis) {
                    if (displayName.contains(emoji)) {
                        foundEmojis.add(emoji);
                    }
                }
            }

            if (meta.hasLore()) {
                List<String> lore = meta.getLore();
                for (String line : lore) {
                    for (String emoji : possibleEmojis) {
                        if (line.contains(emoji)) {
                            foundEmojis.add(emoji);
                        }
                    }
                }
            }
        }

        return foundEmojis;
    }
    default boolean haveCommonEmojis(List<String> listOne, List<String> listTwo) {
        for (String emoji : listOne) {
            if (listTwo.contains(emoji)) {
                return true;
            }
        }
        return false;
    }
}
