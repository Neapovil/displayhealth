package com.github.neapovil.displayhealth;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import net.kyori.adventure.text.Component;

public final class DisplayHealth extends JavaPlugin
{
    private static DisplayHealth instance;

    @Override
    public void onEnable()
    {
        instance = this;

        final Scoreboard scoreboard = this.getServer().getScoreboardManager().getMainScoreboard();

        if (scoreboard.getObjective(DisplaySlot.BELOW_NAME) == null)
        {
            final Objective objective = scoreboard.registerNewObjective("health", "health", Component.text("Health"));

            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }
    }

    @Override
    public void onDisable()
    {
        final Scoreboard scoreboard = this.getServer().getScoreboardManager().getMainScoreboard();

        if (scoreboard.getObjective(DisplaySlot.BELOW_NAME) != null)
        {
            scoreboard.getObjective(DisplaySlot.BELOW_NAME).unregister();
        }
    }

    public static DisplayHealth getInstance()
    {
        return instance;
    }
}
