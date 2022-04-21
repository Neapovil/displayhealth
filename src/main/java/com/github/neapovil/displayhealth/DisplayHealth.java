package com.github.neapovil.displayhealth;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.github.neapovil.displayhealth.command.ToggleCommand;

import net.kyori.adventure.text.Component;

public final class DisplayHealth extends JavaPlugin
{
    private static DisplayHealth instance;
    private FileConfig config;
    public static final String ADMIN_PERMISSION = "displayhealth.command";

    @Override
    public void onEnable()
    {
        instance = this;

        this.saveResource("config.json", false);

        this.config = FileConfig.builder(new File(this.getDataFolder(), "config.json"))
                .autoreload()
                .autosave()
                .build();
        this.config.load();

        if (this.isObjectiveEnabled())
        {
            this.registerObjective();
        }

        ToggleCommand.register();
    }

    @Override
    public void onDisable()
    {
        this.unregisterObjective();
    }

    public static DisplayHealth getInstance()
    {
        return instance;
    }

    public boolean isObjectiveEnabled()
    {
        return this.config.get("enabled");
    }

    public void toggleObjective(boolean bool)
    {
        this.config.set("enabled", bool);
    }

    public void registerObjective()
    {
        final Scoreboard scoreboard = this.getServer().getScoreboardManager().getMainScoreboard();

        if (scoreboard.getObjective(DisplaySlot.BELOW_NAME) == null)
        {
            final Objective objective = scoreboard.registerNewObjective("health", "health", Component.text("Health"));

            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }
    }

    public void unregisterObjective()
    {
        final Scoreboard scoreboard = this.getServer().getScoreboardManager().getMainScoreboard();

        if (scoreboard.getObjective(DisplaySlot.BELOW_NAME) != null)
        {
            scoreboard.getObjective(DisplaySlot.BELOW_NAME).unregister();
        }
    }
}
