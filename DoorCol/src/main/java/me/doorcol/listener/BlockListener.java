package me.doorcol.listener;

import me.doorcol.DoorColPlugin;
import me.doorcol.config.ConfigManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageAbortEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;

public class BlockListener implements Listener {

    private final ConfigManager config;

    public BlockListener(DoorColPlugin plugin) {
        this.config = new ConfigManager(plugin.getConfig());
    }

    @EventHandler
    public void onDamage(BlockDamageEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();

        Set<Material> allowed = config.getBlocks();
        if (!allowed.contains(b.getType())) return;

        applyFatigue(p);
    }

    @EventHandler
    public void onAbort(BlockDamageAbortEvent e) {
        e.getPlayer().removePotionEffect(PotionEffectType.MINING_FATIGUE);
    }

    private void applyFatigue(Player p) {
        int level = config.getFatigueLevel();

        ItemStack tool = p.getInventory().getItemInMainHand();

        if (config.useEfficiency() && tool != null) {
            int eff = tool.getEnchantmentLevel(Enchantment.EFFICIENCY);
            if (eff > 0) {
                level = Math.max(0, level - eff / 2);
            }
        }

        p.addPotionEffect(new PotionEffect(
                PotionEffectType.MINING_FATIGUE,
                5,
                level,
                false,
                false,
                false
        ));
    }
}
