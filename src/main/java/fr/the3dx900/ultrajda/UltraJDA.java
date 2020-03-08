package fr.the3dx900.ultrajda;

import fr.the3dx900.ultrajda.command.CommandExecutor;
import fr.the3dx900.ultrajda.command.CommandHandler;
import fr.the3dx900.ultrajda.command.Listener;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class UltraJDA {

    private static List<CommandExecutor> COMMANDS = new ArrayList<>();
    public static Map<Guild, String> PREFIX = new HashMap<>();

    public static boolean isCommand(Message command) {
        AtomicBoolean result = new AtomicBoolean(false);
        String prefix = getPrefix(command.getGuild());

        for(CommandExecutor executor : COMMANDS) {
            result.set(command.getContentDisplay().startsWith(prefix + executor.handler.command()));
            for(String aliase : executor.handler.aliases()) {
                if(command.getContentDisplay().startsWith(prefix + aliase))
                    result.set(true);
            }
            if(result.get()) break;
        }
        return result.get();
    }

    public static void registerCommand(Listener command) {
        for(Method method : command.getClass().getMethods()) {
            if(method.isAnnotationPresent(CommandHandler.class))
                COMMANDS.add(new CommandExecutor(command, method));
        }
    }

    public static void executeCommand(Message command) throws Exception {
        if(!isCommand(command)) return;
        for(CommandExecutor executor : COMMANDS) {
            boolean result = false;
            if(command.getContentDisplay().startsWith(getPrefix(command.getGuild()) + executor.handler.command()))
                result = true;
            for(String aliase : executor.handler.aliases()) {
                if(command.getContentDisplay().startsWith(getPrefix(command.getGuild()) + aliase))
                    result = true;
            }
            if(result) {
                executor.execute(command, command.getContentDisplay().split(" ")[0]);
                return;
            }
        }
        throw new Exception("Unknown command.");
    }

    public static String getPrefix(Guild guild) {
        return PREFIX.getOrDefault(guild, getPrefix());
    }

    public static String getPrefix() {
        return "!";
    }
}
