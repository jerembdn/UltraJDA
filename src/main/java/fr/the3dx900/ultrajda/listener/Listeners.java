package fr.the3dx900.ultrajda.listener;

import fr.the3dx900.ultrajda.UltraJDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

/**
 * This file is a part of UltraJDA, located on fr._3dx900.ultrajda.listener
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 18/09/2019 at 14:21.
 */
public class Listeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if(event.getMessage().getChannelType() == ChannelType.TEXT && UltraJDA.isCommand(event.getMessage())) {
            try {
                UltraJDA.executeCommand(event.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
