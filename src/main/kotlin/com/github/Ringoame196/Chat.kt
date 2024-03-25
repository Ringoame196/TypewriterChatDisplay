package com.github.Ringoame196

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

class Chat {
    fun typewriterDisplay(plugin: Plugin, message: String, sender: Player) {
        var i = 1
        val config = plugin.config
        val size = message.length
        val maxSize = try {
            config.getInt("maxSize")
        } catch (e: Exception) {
            15
        }
        val interval = try {
            config.getInt("interval")
        } catch (e: Exception) {
            3
        }
        if (size >= maxSize) { return }
        val senderName = "<${sender.displayName}>"
        object : BukkitRunnable() {
            override fun run() {
                if (i == size) { this.cancel() }
                val displayCharacter = message.substring(0, i)
                sendSubTitle("${ChatColor.GOLD}$senderName $displayCharacter")
                i++
            }
        }.runTaskTimer(plugin, 0L, interval.toLong())
    }
    private fun sendSubTitle(message: String) {
        Bukkit.getOnlinePlayers().forEach { player ->
            player.sendTitle("", message, 0, 60, 0)
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1f)
        }
    }
}
