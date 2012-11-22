/**
 * 
 */
package org.adaikiss.xun.serialization.msgpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.msgpack.template.Templates;
import org.msgpack.type.ArrayValue;
import org.msgpack.type.MapValue;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

/**
 * @author hlw
 *
 */
public class MessagePackUtil {
	public static Object toObject(Value value) throws IOException {
		Converter conv = new Converter(value);
		if (value.isNilValue()) { // null
			return null;
		} else if (value.isRawValue()) { // byte[] or String or maybe Date?
			// deserialize value to String object
			//RawValue v = value.asRawValue();
			return conv.read(Templates.TString);
		} else if (value.isBooleanValue()) { // boolean
			return conv.read(Templates.TBoolean);
		} else if (value.isIntegerValue()) { // int or long or BigInteger
			// deserialize value to int
			//IntegerValue v = value.asIntegerValue();
			return conv.read(Templates.TInteger);
		} else if (value.isFloatValue()) { // float or double
			// deserialize value to double
			//FloatValue v = value.asFloatValue();
			return conv.read(Templates.TDouble);
		} else if (value.isArrayValue()) { // List or Set
			// deserialize value to List object
			ArrayValue v = value.asArrayValue();
			List<Object> ret = new ArrayList<Object>(v.size());
			for (Value elementValue : v) {
				ret.add(toObject(elementValue));
			}
			return ret;
		} else if (value.isMapValue()) { // Map
			// deserialize value to Map object
			MapValue m = value.asMapValue();
			int size = m.size();
			Map<Object, Object> ret = new HashMap<Object, Object>(size);
			Value[] array = m.getKeyValueArray();
			for (int i = array.length - 2; i >= 0; i -= 2) {
				Object k = toObject(array[i]);
				Object v = toObject(array[i + 1]);
				ret.put(k, v);
			}
			return ret;
		} else {
			throw new RuntimeException("fatal error");
		}
	}
}
