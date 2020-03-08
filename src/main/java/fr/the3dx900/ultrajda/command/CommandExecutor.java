package fr.the3dx900.ultrajda.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.channels.Channel;

public class CommandExecutor {

    private Object classInstance;
    private Method method;
    public CommandHandler handler;

    public CommandExecutor(Object classInstance, Method method) {
        this.classInstance = classInstance;
        this.method = method;
        handler = method.getAnnotation(CommandHandler.class);
    }

    public void execute(Message message, String command) throws InvocationTargetException, IllegalAccessException {
        String[] arguments = message.getContentDisplay()
                .replace(command + " ", "")
                .split(" ");

        if(!message.getMember().hasPermission(handler.permission())) {
            message.getChannel().sendMessage("You don't have the correct permission.");
            return;
        }
        Parameter[] parameters = method.getParameters();
        Object[] objects = new Object[parameters.length];
        for(int i = 0; i < objects.length; i++) {
            if(parameters[i].getType() == CommandExecutor.class) objects[i] = this;
            if(parameters[i].getType() == String.class) objects[i] = String.join(" ", arguments);
            if(parameters[i].getType() == String[].class) objects[i] = arguments;
            if(parameters[i].getType() == Guild.class) objects[i] = message.getGuild();
            if(parameters[i].getType() == JDA.class) objects[i] = message.getJDA();
            if(parameters[i].getType() == Message.class) objects[i] = message;
            if(parameters[i].getType() == MessageChannel.class) objects[i] = message.getChannel();
            if(parameters[i].getType() == TextChannel.class) objects[i] = message.getTextChannel();
            if(parameters[i].getType() == PrivateChannel.class) objects[i] = message.getPrivateChannel();
            if(parameters[i].getType() == Member.class) objects[i] = message.getMember();
            if(parameters[i].getType() == User.class) objects[i] = message.getMember().getUser();
            if(parameters[i].getType() == Member[].class)
                objects[i] = message.getMentionedMembers().toArray(new Member[message.getMentionedMembers().size()]);
            if(parameters[i].getType() == User[].class)
                objects[i] = message.getMentionedUsers().toArray(new User[message.getMentionedUsers().size()]);
            if(parameters[i].getType() == Role[].class)
                objects[i] = message.getMentionedRoles().toArray(new Role[message.getEmotes().size()]);
            if(parameters[i].getType() == Message.Attachment[].class)
                objects[i] = message.getAttachments().toArray(new Message.Attachment[message.getAttachments().size()]);
            if(parameters[i].getType() == MessageReaction[].class)
                objects[i] = message.getReactions().toArray(new MessageReaction[message.getReactions().size()]);
            if(parameters[i].getType() == Emote[].class)
                objects[i] = message.getEmotes().toArray(new Emote[message.getEmotes().size()]);
            if(parameters[i].getType() == TextChannel[].class)
                objects[i] = message.getMentionedChannels().toArray(new TextChannel[message.getMentionedChannels().size()]);
            if(parameters[i].getType() == Channel[].class)
                objects[i] = message.getMentionedChannels().toArray(new TextChannel[message.getMentionedChannels().size()]);
        }
        if(handler.deleteCommandAfter())
            message.delete().queue();
        method.invoke(classInstance, objects);
    }
}
