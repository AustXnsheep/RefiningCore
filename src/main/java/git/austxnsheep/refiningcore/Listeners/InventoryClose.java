package git.austxnsheep.refiningcore.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClose implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("Refiner")) {
            Player player = (Player) event.getPlayer();
            int[] dropSlots = {20, 24, 40};

            for (int slot : dropSlots) {
                ItemStack item = event.getInventory().getItem(slot);
                if (item != null && item.getType() != Material.AIR && !item.getItemMeta().getDisplayName().contains("Merge items")) {
                    player.getInventory().addItem(item);
                    event.getInventory().clear(slot);
                }
            }
        }
    }
}
