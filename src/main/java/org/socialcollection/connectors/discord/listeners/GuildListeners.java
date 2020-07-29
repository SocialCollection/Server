package org.socialcollection.connectors.discord.listeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.socialcollection.util.WriteFile;

import javax.annotation.Nonnull;

public class GuildListeners extends ListenerAdapter {
    private void writeToFile(String content){
        WriteFile.writer("data", "guild", "txt", content);
    }
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        writeToFile(String.format("USER JOIN %s - %s\n", event.getGuild().getId(), event.getUser().getAsTag()));
    }
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        writeToFile(String.format("USER LEAVE %s - %s\n", event.getGuild().getId(), event.getUser().getAsTag()));
    }
}
