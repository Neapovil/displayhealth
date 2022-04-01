package com.github.neapovil.displayhealth;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import net.kyori.adventure.text.Component;

public final class DisplayHealth extends JavaPlugin implements Listener
{
    private static DisplayHealth instance;

    @Override
    public void onEnable()
    {
        instance = this;

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable()
    {
    }

    public static DisplayHealth getInstance()
    {
        return instance;
    }

    @EventHandler
    private void join(PlayerJoinEvent event)
    {
        final Scoreboard scoreboard = this.getServer().getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective(event.getPlayer().getName(), "health", Component.text("Health"));

        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        event.getPlayer().setScoreboard(scoreboard);
    }

    @EventHandler
    private void quit(PlayerQuitEvent event)
    {
        final Objective objective = event.getPlayer().getScoreboard().getObjective(event.getPlayer().getName());

        if (objective != null)
        {
            objective.unregister();
        }
    }
}
