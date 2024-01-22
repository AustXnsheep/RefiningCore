package git.austxnsheep.refiningcore.reforge;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public interface ReforgeStoneConstructor {
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
    default ReforgeStone constructStone(ItemStack item, boolean negative) {
        ReforgeStone stone;
        Map<String, Integer> newAttributes = new HashMap<>();
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta.getDisplayName().contains("Blitzfang") || itemMeta.getDisplayName().contains("Honed")) {
            newAttributes.put("Attack Damage", 20);
            newAttributes.put("Crit Damage", 16);
            newAttributes.put("Crit Rate", 8);
            stone = new ReforgeStone(newAttributes, "Honed");
        } else if (itemMeta.getDisplayName().contains("Organic Onion") || itemMeta.getDisplayName().contains("Pungent")) {
            newAttributes.put("Attack Damage", 15);
            newAttributes.put("Defense", -2);
            stone = new ReforgeStone(newAttributes, "Pungent");
        } else if (itemMeta.getDisplayName().contains("Razor Leaf") || itemMeta.getDisplayName().contains("Serrated")) {
            newAttributes.put("Attack Damage", 10);
            newAttributes.put("Crit Damage", 10);
            newAttributes.put("Defense", -15);
            stone = new ReforgeStone(newAttributes, "Serrated");
        } else if (itemMeta.getDisplayName().contains("Storm Battery") || itemMeta.getDisplayName().contains("Stormy")) {
            newAttributes.put("Magic Damage", 5);
            newAttributes.put("Starfury", 60);
            stone = new ReforgeStone(newAttributes, "Stormy");
        } else if (itemMeta.getDisplayName().contains("Ether Particle") || itemMeta.getDisplayName().contains("Charged")) {
            newAttributes.put("Starfury", 100);
            newAttributes.put("Magic Damage", 5);
            stone = new ReforgeStone(newAttributes, "Charged");
        } else if (itemMeta.getDisplayName().contains("Stonecut Tablet") || itemMeta.getDisplayName().contains("Hardened")) {
            newAttributes.put("Defense", 10);
            newAttributes.put("Health", 10);
            newAttributes.put("Crit Rate", 1);
            stone = new ReforgeStone(newAttributes, "Hardened");
        } else if (itemMeta.getDisplayName().contains("Torched Tulip") || itemMeta.getDisplayName().contains("Torched")) {
            newAttributes.put("Attack Damage", 12);
            newAttributes.put("Starfury", -25);
            newAttributes.put("Crit Damage", 15);
            newAttributes.put("Crit Rate", 10);
            stone = new ReforgeStone(newAttributes, "Torched");
        } else if (itemMeta.getDisplayName().contains("Ancient Bowstring") || itemMeta.getDisplayName().contains("Forgotten")) {
            newAttributes.put("Attack Damage", 10);
            newAttributes.put("Crit Damage", 30);
            newAttributes.put("Crit Rate", 15);
            stone = new ReforgeStone(newAttributes, "Forgotten");
        } else if (itemMeta.getDisplayName().contains("Wither Orb") || itemMeta.getDisplayName().contains("Withering")) {
            newAttributes.put("Magic Damage", 10);
            newAttributes.put("Starfury", 15);
            stone = new ReforgeStone(newAttributes, "Withering");
        } else if (itemMeta.getDisplayName().contains("Raw Uranium") || itemMeta.getDisplayName().contains("Radioactive")) {
            newAttributes.put("Health", 15);
            newAttributes.put("Crit Damage", 8);
            stone = new ReforgeStone(newAttributes, "Radioactive");
        } else if (itemMeta.getDisplayName().contains("Electrical Generator") || itemMeta.getDisplayName().contains("Electrified")) {
            newAttributes.put("Defense", 10);
            newAttributes.put("Attack Damage", 5);
            stone = new ReforgeStone(newAttributes, "Radioactive");
        }
        else if (itemMeta.getDisplayName().contains("Waste Water") || itemMeta.getDisplayName().contains("Polluted")) {
            newAttributes.put("Defense", 12);
            newAttributes.put("Starfury", 60);
            stone = new ReforgeStone(newAttributes, "Polluted");
        } else if (itemMeta.getDisplayName().contains("Thunder Apple") || itemMeta.getDisplayName().contains("Healthy")) {
            newAttributes.put("Health", 40);
            stone = new ReforgeStone(newAttributes, "Healthy");
        } else if (itemMeta.getDisplayName().contains("Frozen Metal Scale") || itemMeta.getDisplayName().contains("Freeze-Dried")) {
            newAttributes.put("Health", 15);
            newAttributes.put("Crit Damage", 15);
            newAttributes.put("Damage", 9);
            stone = new ReforgeStone(newAttributes, "Freeze-Dried");
        } else if (itemMeta.getDisplayName().contains("Diamond Gear") || itemMeta.getDisplayName().contains("Shiny")) {
            newAttributes.put("Health", 10);
            newAttributes.put("Defense", 8);
            newAttributes.put("Starfury", 10);
            stone = new ReforgeStone(newAttributes, "Shiny");
        } else if (itemMeta.getDisplayName().contains("Sharpening Stone") || itemMeta.getDisplayName().contains("Sharp")) {
            newAttributes.put("Crit Rate", 6);
            newAttributes.put("Crit Damage", 25);
            newAttributes.put("Damage", 25);
            stone = new ReforgeStone(newAttributes, "Sharp");
        } else if (itemMeta.getDisplayName().contains("Electrified Scope") || itemMeta.getDisplayName().contains("Calibrated")) {
            newAttributes.put("Crit Rate", 3);
            newAttributes.put("Crit Damage", 20);
            newAttributes.put("Damage", 40);
            stone = new ReforgeStone(newAttributes, "Calibrated");
        } else {
            stone = new ReforgeStone(newAttributes, "FAILED (pls tell admin if you see this)");
            Bukkit.broadcastMessage("Reforge Failed");
        }

        if (negative) {
            newAttributes.forEach((key, value) -> newAttributes.put(key, -value));
        }

        return stone;
    }
}
