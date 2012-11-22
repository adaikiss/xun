/**
 * 
 */
package org.adaikiss.xun.serialization.msgpack;

import static org.adaikiss.xun.serialization.msgpack.MessagePackUtil.toObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.session.mgt.SimpleSession;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.Unpacker;

/**
 * @author hlw
 * 
 */
public class SimpleSessionTemplate extends AbstractTemplate<SimpleSession> {

	@Override
	public void write(Packer pk, SimpleSession v, boolean required)
			throws IOException {
		if (v == null) {
			if (required) {
				throw new NullPointerException();
			}
			pk.writeNil();
			return;
		}
		// serialize entity object
		pk.writeArrayBegin(8);
		{
			// 1
			pk.write(v.getId());
			// 2
			pk.write(v.getStartTimestamp());
			// 3
			pk.write(v.getStopTimestamp());
			// 4
			pk.write(v.getLastAccessTime());
			// 5
			pk.write(v.getTimeout());
			// 6
			pk.write(v.isExpired());
			// 7
			pk.write(v.getHost());
			// 8
			Map<Object, Object> attributes = v.getAttributes();
			if(null == attributes){
				pk.writeNil();
			} else {
				pk.writeMapBegin(v.getAttributes().size());
				{
					// serialize each pair of key and value
					for (Map.Entry<Object, Object> e : v.getAttributes().entrySet()) {
						Object key = e.getKey();
						pk.write(key);
						Object val = e.getValue();
						pk.write(val);
					}
				}
				pk.writeMapEnd();
			}
		}
		pk.writeArrayEnd();
	}

	@Override
	public SimpleSession read(Unpacker u, SimpleSession to, boolean required)
			throws IOException {
		if (!required && u.trySkipNil()) {
			return null;
		}
		if (to == null) {
			to = new SimpleSession();
		}
		// deserialize entity object
		u.readArrayBegin();
		{
			// 1
			to.setId((Serializable)toObject(u.readValue()));
			// 2
			to.setStartTimestamp(u.read(Templates.TDate));
			// 3
			to.setStopTimestamp(u.read(Templates.TDate));
			// 4
			to.setLastAccessTime(u.read(Templates.TDate));
			// 5
			to.setTimeout(u.read(Templates.TLong));
			// 6
			to.setExpired(u.read(Templates.TBoolean));
			// 7
			to.setHost(u.read(Templates.TString));
			// 8
			try {
				int size = u.readMapBegin();
				Map<Object, Object> attributes = new HashMap<Object, Object>(size);
				to.setAttributes(attributes);
				{
					// deserialize each pair of key and value
					for (int i = 0; i < size; i++) {
						// deserialize key
						Object key = toObject(u.readValue());
						// deserialize value
						Object val = toObject(u.readValue());
						attributes.put(key, val);
					}
				}
				u.readMapEnd();
			} catch (Exception e) {
				to.setAttributes(null);
			}
		}
		u.readArrayEnd();
		return to;
	}
}
