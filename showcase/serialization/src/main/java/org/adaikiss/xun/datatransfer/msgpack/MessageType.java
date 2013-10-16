/**
 * 
 */
package org.adaikiss.xun.datatransfer.msgpack;

import org.msgpack.annotation.MessagePackOrdinalEnum;

/**
 * @author hlw
 *
 */
@MessagePackOrdinalEnum
public enum MessageType {
	Login,
	Logout,
	Chat,
	Notice;
	
	public static MessageType get(int ordinal){
		for(MessageType type : values()){
			if(type.ordinal() == ordinal){
				return type;
			}
		}
		return null;
	}
}
