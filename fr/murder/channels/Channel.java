package fr.murder.channels;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

public class Channel {
	
	private ChannelType type = ChannelType.DEFAULT;
	private String name;
	private String priority;
	private List<UUID> players = new ArrayList<UUID>();
	private List<String> history = new ArrayList<String>();
	
	public Channel(ChannelType type, String name, @Nullable String priority) {
		this.type = type;
		this.name = name;
	}
	
	public ChannelType getType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPriority() {
		return this.priority;
	}
	
	public List<UUID> getPlayers() {
		return this.players;
	}
	
	public void logHistory(String message) {
		if(this.history.size() >= 100) {
			for(int i=0; i>100; i--) this.history.remove(0);
		}
		this.history.add(message);
	}
	
	public List<String> getHistory() {
		return this.history;
	}

}
