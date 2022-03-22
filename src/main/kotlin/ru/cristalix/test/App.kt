package ru.cristalix.test

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class App : JavaPlugin(), Listener {



    override fun onEnable() {
        Bukkit.getConsoleSender().sendMessage("enabled")
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onJoin(e : PlayerJoinEvent){
        var armorStand = e.player.world.spawnEntity(e.player.location, EntityType.ARMOR_STAND)
        armorStand.apply {
            this as ArmorStand
            helmet.type = Material.GOLD_ORE
        }
        var location : Location
        location = e.player.location
        location.y = location.y - (0..10).random()
        location.x = location.x - (0..10).random()
        location.block.type = Material.DIAMOND_BLOCK
        e.player.sendMessage(location.y.toString())
    }

    @EventHandler
    fun onMove(e : PlayerMoveEvent){
        if(e.player.location.subtract(0.0, 1.0, 0.0).block.type == Material.DIAMOND_BLOCK && e.player.inventory.contains(Material.GOLD_ORE)){
            /* победа */
            e.player.sendMessage("123")
        }
    }




    @EventHandler
    fun onClick(e : EntityDamageByEntityEvent){
        if(e.entity.type == EntityType.ARMOR_STAND && (e.entity as ArmorStand).helmet.type == Material.GOLD_ORE){
            if(e.damager is Player){
                (e.entity as ArmorStand).helmet.type = Material.AIR
                (e.damager as Player).inventory.addItem(ItemStack(Material.GOLD_ORE, 1))
            }
        }
    }

}