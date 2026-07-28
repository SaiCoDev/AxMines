package com.artillexstudios.axmines.config.impl;

import com.artillexstudios.axmines.AxMinesPlugin;
import com.artillexstudios.axmines.utils.FileUtils;

import java.util.List;
import java.util.Map;

public class MineConfig extends Messages {

    @Key("display-name")
    public String DISPLAY_NAME = "<color:#FF0000>Example";

    @Key("contents")
    public Map<Object, Object> CONTENTS = Map.of("gold_block", 11, "diamond_block", 10, "emerald_block", 25, "iron_block", 10);

    @Key("selection.1")
    public String SELECTION_CORNER_1 = "world;10;10;10;10;10";

    @Key("selection.2")
    public String SELECTION_CORNER_2 = "world;10;10;10;10;10";

    @Key("teleport-location")
    public String TELEPORT_LOCATION = "world;10;10;10;10;10";

    @Key("teleport-on-reset")
    public int TELEPORT_ON_RESET = 0;

    @Key("broadcast-reset")
    public int BROADCAST_RESET = -1;

    @Key("reset.ticks")
    public long RESET_TICKS = 12000;

    @Key("reset.percent")
    public double RESET_PERCENT = 10.0;

    @Key("fortune.max-level")
    @Comment({"The highest fortune level we read off the pickaxe.", "Set to -1 to use whatever level the tool actually has."})
    public int FORTUNE_MAX_LEVEL = -1;

    @Key("fortune.type")
    @Comment({"How a fortune level is turned into a multiplier.", "vanilla - The formula minecraft itself uses on ores, an even chance of 1x, 1x, 2x ... (level + 1)x", "linear - A flat multiplier of 1 + (level * per-level), so fortune 3 at a per-level of 0.5 is always 2.5x"})
    public String FORTUNE_TYPE = "vanilla";

    @Key("fortune.per-level")
    @Comment({"The bonus each fortune level is worth when type is linear.", "Fractional results are rolled, so a 2.5x on a single item gives 2 half the time and 3 the other half."})
    public double FORTUNE_PER_LEVEL = 0.5;

    @Key("random-rewards")
    public List<Map<String, Object>> RANDOM_REWARDS = List.of(Map.of("chance", 0.001, "blocks", List.of("diamond_block", "emerald_block"), "commands", List.of("eco give <player> 100000")));

    @Key("reset-commands")
    public List<String> RESET_COMMANDS = List.of("say Mine A has been reset!");

    @Key("actionbar.enabled")
    public boolean ACTION_BAR_ENABLED = false;

    @Key("actionbar.range")
    public int ACTION_BAR_RANGE = 10;

    @Key("timer-format")
    @Comment({"The format of the time placeholder", "1 -> HH:MM:SS, for example 01:25:35", "2 -> short format, for example 20m", "3 - text format, for example 01h 25m 35s"})
    public int TIMER_FORMAT = 2;

    @Key("setter")
    @Comment({"What blocksetter should we use to set the blocks?", "Options:", "parallel - Best performance, does not lag the server thread. Sometimes can be a little bit inaccurate - CURRENTLY NOT AVAILABLE", "fast - Sets the blocks on the main thread using faster methods", "bukkit - Uses the Bukkit API to set the blocks. This is the slowest out of all the setters", "", "If you are having issues with blocks not being set correctly, we suggest using bukkit."})
    public String SETTER = "fast";

    public MineConfig(String fileName) {
        super(fileName);
    }

    @Override
    public void reload() {
        this.reload(FileUtils.PLUGIN_DIRECTORY.resolve(fileName), Messages.class, this, AxMinesPlugin.MESSAGES);
        this.reload(FileUtils.PLUGIN_DIRECTORY.resolve(fileName), MineConfig.class, this, null);
    }
}