package org.socialcollection.connectors.discord.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.socialcollection.util.WriteFile;

import javax.annotation.Nonnull;

public class MessageListeners extends ListenerAdapter {

    private void writeToFile(String content){
        WriteFile.writer("data", "msg", "txt", content);
    }
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String authorTag = event.getMessage().getAuthor().getAsTag();
        String guildId = event.getMessage().getGuild().getId();
        String channelId = event.getMessage().getChannel().getId();
        String msgContent = event.getMessage().getContentDisplay();
        String msgID = event.getMessageId();

        writeToFile(String.format("%s : %s - %s - %s - %s -- %s", "CREATED " + event.getMessage().getTimeCreated().toString(), authorTag, guildId, channelId, msgID, msgContent + "\n"));
    }
    public void onGuildMessageUpdate(@Nonnull GuildMessageUpdateEvent event) {
        String authorTag = event.getMessage().getAuthor().getAsTag();
        String guildId = event.getMessage().getGuild().getId();
        String channelId = event.getMessage().getChannel().getId();
        String msgContent = event.getMessage().getContentDisplay();
        String msgID = event.getMessageId();
        writeToFile(String.format("%s : %s - %s - %s - %s -- %s", "EDITED " + event.getMessage().getTimeCreated().toString(), authorTag, guildId, channelId, msgID, msgContent + "\n"));
    }
    public void onGuildMessageDelete(@Nonnull GuildMessageDeleteEvent event) {
        String msgID = event.getMessageId();
        writeToFile(String.format("DELETED %s - %s --%s \n", event.getGuild().getId(), event.getChannel().getId(), msgID));
    }
/* TODO: do it better

    public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event) {
        System.out.println(event.getReaction().retrieveUsers().complete().get(event.getReaction().retrieveUsers().complete().size()-1).getAsTag());
        Config.writeConfig("data", "reactionUser", event.getGuild().getId() + event.getChannel().getId() + event.getMessageId() + event.getReaction().getReactionEmote().getName(), Arrays.toString(event.getReaction().retrieveUsers().complete().toArray()));
        writeToFile(String.format("REACTION ADDED %s - %s - %s\n", Objects.requireNonNull(event.getReaction().getGuild()).getId(), event.getReaction().getChannel().getId(), event.getReaction().getReactionEmote().getName()));
    }

    public void onGuildMessageReactionRemove(@Nonnull GuildMessageReactionRemoveEvent event) {
        System.out.println(event.getReaction().getReactionEmote().getName());
        if(Config.readConfig("data", "reactionUser", event.getGuild().getId() + event.getChannel().getId() + event.getMessageId() + event.getReaction().getReactionEmote().getName()) == Arrays.toString(event.getReaction().retrieveUsers().complete().toArray())){
            System.out.println(Arrays.toString(event.getReaction().retrieveUsers().complete().toArray()));
        }
        writeToFile(String.format("REACTION removed %s - %s - %s\n", Objects.requireNonNull(event.getReaction().getGuild()).getId(), event.getReaction().getChannel().getId(), event.getReaction().getReactionEmote().getName()));

    }
 */

}
