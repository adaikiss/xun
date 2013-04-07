/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hlw
 * 
 */
public class GameMediator implements IMediator {

	private List<Player> players = new ArrayList<Player>();

	public void addPlayer(Player...players) {
		for(Player player : players){
			this.players.add(player);
		}
	}

	@Override
	public void send(Message msg, Colleague colleague) {
		for(Player player : players){
			if(player == colleague){
				continue;
			}
			player.receive(msg);
		}
	}

}
