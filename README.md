# Ultra JDA (Java Discord API Ultras)
A completion of Java Discord API by DV8FromTheWorld only for Java.

### Ultra Summary

1. [Make a command](#commands)
2. [Complete your command](#do-more)

## Commands
Create Discord commands has never been so easy !

**Step 1:**
```java
import fr._3dx900.ultrajda.commands.CommandHandler;

public class UltraCommand {
    
    @CommandHandler(command = "ultra")
    public void process() {
        System.out.println("Make command with UltraJDA is so cool :D");
    }
}
```

**Step 2:**
```java
import net.dv8tion.jda.core.JDA;

import UltraJDA;

public class Main {
    
    public static void main(String[] args) {
        JDA jda = new JDABuilder("token").build();
        
        new UltraJDA(jda);
        UltraJDA.registerCommand(new UltraCommand());
    }
}
```

## Do more
You can complete your commands by adding parameters to the `process()` method.

**You have the choice between the following parameters :**
- `Command` - *Get the instance of the command.*
- `String` - *Get the first argument (the command).*
- `String[]` - *Get the arguments of the command.*
- `Guild` - *Get the guild on which the command was sent.*
- `JDA` - *Get the JDA api of the command.*
- `Message` - *Get the message instance of the command.*
- `MessageChannel` - *Get the channel message instance of the command.*
- `TextChannel` - *Get the text channel instance of the command.*
- `PrivateChannel` - *Get the private channel instance of the command.*
- `Channel[]` - *Get the mentionned channels in the arguments of the command.*
- `Member` - *Get the member instance of the author of the command.*
- `User` - *Get the user instance of the author of the command.*
- `User[]` - *Get the mentionned users in the arguments of the command.*
- `Role[]` - *Get the mentionned roles in the arguments of the command.*
- `Message.Attachment[]` - *Get the attachments of the message command.*
- `Emote[]` - *Get the emotes of the message command.*


**Examples:**
```java
import fr._3dx900.ultrajda.commands.CommandHandler;

import net.dv8tion.jda.core.entities.*;

 public class UltraCommand {
     
     @CommandHandler(command = "ultra")
     public void process(String arg, Member member, Guild guild) {
         System.out.println("The command " + arg + " was sent by " + member.getName() + " on " + guild.getName());
     }
 }
```