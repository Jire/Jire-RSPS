package jire.player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import jire.world.ChatMessage;
import jire.world.Entity;
import jire.world.UpdateFlagSet;

public class Player extends Entity {
	
	private final transient List<LocalPlayerListEntry> localPlayers = new ArrayList<LocalPlayerListEntry>();
	private final transient UpdateFlagSet updateFlags = new UpdateFlagSet();
	private final transient Queue<ChatMessage> chatQueue = new LinkedList<ChatMessage>();

	private transient UUID uuid;
	private transient int index;

	private final String username;
	private final String password;
	private final int rights;

	public Player(UUID uuid, int index, String username, String password, int rights) {
		this.uuid = uuid;
		this.index = index;
		this.username = username;
		this.password = password;
		this.rights = rights;
	}

	public UpdateFlagSet getUpdateFlags() {
		return updateFlags;
	}

	public List<LocalPlayerListEntry> getLocalPlayerEntries() {
		return localPlayers;
	}

	public Queue<ChatMessage> getChatQueue() {
		return chatQueue;
	}

	public UUID getUUID() {
		return uuid;
	}

	public int getIndex() {
		return index;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getRights() {
		return rights;
	}

}