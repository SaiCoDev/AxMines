package com.artillexstudios.axmines.utils

import com.artillexstudios.axmines.config.impl.MineConfig
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.floor
import kotlin.math.min

object FortuneUtils {

    private val FORTUNE: Enchantment? by lazy {
        val registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT)
        registry.get(NamespacedKey.minecraft("fortune"))
    }

    fun level(player: Player, config: MineConfig): Int {
        val enchantment = FORTUNE ?: return 0
        val tool = player.inventory.itemInMainHand
        if (tool.type.isAir) return 0

        val level = tool.getEnchantmentLevel(enchantment)
        if (level <= 0) return 0

        val max = config.FORTUNE_MAX_LEVEL
        return if (max < 0) level else min(level, max)
    }

    fun apply(amount: Int, level: Int, config: MineConfig): Int {
        if (level <= 0 || amount <= 0) return amount

        return when (config.FORTUNE_TYPE.lowercase(Locale.ENGLISH)) {
            "linear" -> roll(amount * (1.0 + level * config.FORTUNE_PER_LEVEL))
            else -> amount * vanillaMultiplier(level)
        }
    }

    private fun vanillaMultiplier(level: Int): Int {
        val roll = ThreadLocalRandom.current().nextInt(level + 2) - 1
        return if (roll < 0) 1 else roll + 1
    }

    private fun roll(exact: Double): Int {
        val whole = floor(exact)
        val remainder = exact - whole
        if (remainder > 0.0 && ThreadLocalRandom.current().nextDouble() < remainder) {
            return whole.toInt() + 1
        }

        return whole.toInt()
    }

}