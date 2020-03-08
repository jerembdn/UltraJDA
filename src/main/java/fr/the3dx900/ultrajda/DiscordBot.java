package fr.the3dx900.ultrajda;

import fr.the3dx900.ultrajda.listener.Listeners;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public abstract class DiscordBot extends UltraJDA {

    public static JDA JDA;

    public DiscordBot(String token) throws LoginException {
        this(AccountType.BOT, token);
    }

    public DiscordBot(AccountType accountType, String token) throws LoginException {
        this(new JDABuilder(accountType)
                .setToken(token)
                .addEventListeners(new Listeners())
                .build()
        );
    }

    private DiscordBot(JDA jda) {
        JDA = jda;
    }
}
