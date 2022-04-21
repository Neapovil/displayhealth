package com.github.neapovil.displayhealth.command;

import com.github.neapovil.displayhealth.DisplayHealth;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;

public final class ToggleCommand
{
    private static final DisplayHealth plugin = DisplayHealth.getInstance();

    public static final void register()
    {
        new CommandAPICommand("displayhealth")
                .withPermission(DisplayHealth.ADMIN_PERMISSION)
                .withArguments(new LiteralArgument("toggle"))
                .withArguments(new BooleanArgument("boolean"))
                .executes((sender, args) -> {
                    final boolean bool = (boolean) args[0];

                    if (bool && plugin.isObjectiveEnabled())
                    {
                        throw CommandAPI.fail("Nothing changed. DisplayHealth is already enabled");
                    }

                    if (!bool && !plugin.isObjectiveEnabled())
                    {
                        throw CommandAPI.fail("Nothing changed. DisplayHealth is already disabled");
                    }

                    if (bool)
                    {
                        plugin.registerObjective();
                    }
                    else
                    {
                        plugin.unregisterObjective();
                    }

                    plugin.toggleObjective(bool);

                    sender.sendMessage("DisplayHealth has been changed to: " + bool);
                })
                .register();
    }
}
