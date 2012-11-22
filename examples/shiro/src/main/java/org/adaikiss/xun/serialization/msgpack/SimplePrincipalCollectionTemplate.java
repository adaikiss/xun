/**
 * 
 */
package org.adaikiss.xun.serialization.msgpack;

import static org.adaikiss.xun.serialization.msgpack.MessagePackUtil.toObject;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.apache.shiro.subject.SimplePrincipalCollection;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.Unpacker;

/**
 * @author hlw
 *
 */
public class SimplePrincipalCollectionTemplate extends AbstractTemplate<SimplePrincipalCollection>{

	@Override
	@SuppressWarnings("rawtypes")
	public void write(Packer pk, SimplePrincipalCollection v, boolean required)
			throws IOException {
		if (v == null) {
			if (required) {
				throw new NullPointerException();
			}
			pk.writeNil();
			return;
		}
		// serialize entity object
		pk.writeArrayBegin(1);
		{
			// 1
			Set<String> realmNames = v.getRealmNames();
			if(null == realmNames){
				pk.writeNil();
			}else{
				pk.writeMapBegin(realmNames.size());
				{
					for(String realmName : realmNames){
						pk.write(realmName);
						Collection principals = v.fromRealm(realmName);
						pk.writeArrayBegin(principals.size());
						for(Object principal : principals){
							pk.write(principal);
						}
						pk.writeArrayEnd();
					}
				}
				pk.writeMapEnd();
			}
		}
		pk.writeArrayEnd();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public SimplePrincipalCollection read(Unpacker u,
			SimplePrincipalCollection to, boolean required) throws IOException {
		if (!required && u.trySkipNil()) {
			return null;
		}
		if (to == null) {
			to = new SimplePrincipalCollection();
		}
		// deserialize entity object
		u.readArrayBegin();
		{
			// 1
			try {
				int size = u.readMapBegin();
				{
					// deserialize each pair of key and value
					for (int i = 0; i < size; i++) {
						// deserialize key
						String key = u.read(Templates.TString);
						// deserialize value
						Object val = toObject(u.readValue());
						to.addAll((Collection)val, key);
					}
				}
				u.readMapEnd();
			} catch (Exception e) {
			}
		}
		u.readArrayEnd();
		return to;
	}

}
