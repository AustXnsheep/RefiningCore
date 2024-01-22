package git.austxnsheep.refiningcore.nbt;

import git.austxnsheep.refiningcore.RefiningCore;
import git.austxnsheep.refiningcore.lore.AttributeEditor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public interface NBTUtil extends AttributeEditor {
    JavaPlugin plugin = RefiningCore.getInstance();
    default void addNBT(ItemStack item, String key, int value) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            String formattedKey = key.toLowerCase().replace(" ", "_");
            PersistentDataContainer container = meta.getPersistentDataContainer();
            NamespacedKey namespacedKey = new NamespacedKey(plugin, formattedKey);
            container.set(namespacedKey, PersistentDataType.INTEGER, value);
            item.setItemMeta(meta);
        }
    }
    default Integer getNBT(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
            return container.get(namespacedKey, PersistentDataType.INTEGER);
        }
        return null;
    }
    default void removeNBT(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
            container.remove(namespacedKey);
            item.setItemMeta(meta);
        }
    }
    default void applyAttributesAsNBT(ItemStack item) {
        Map<String, Integer> attributes = getAttributesFromLore(item);
        for (Map.Entry<String, Integer> attributeEntry : attributes.entrySet()) {
            addNBT(item, attributeEntry.getKey(), attributeEntry.getValue());
        }
    }
}
