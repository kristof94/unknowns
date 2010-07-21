package hld.coins.intent;

import java.util.ArrayList;
import java.util.HashMap;

import hld.coins.util.LogUnit;

public class GameIntent {
	HashMap<String, Object> mMap;

	public GameIntent() {
		mMap = new HashMap<String, Object>();
	}

	public void putBoolean(String key, boolean value) {
		mMap.put(key, value);
	}

	public void putByte(String key, byte value) {
		mMap.put(key, value);
	}

	public void putChar(String key, char value) {
		mMap.put(key, value);
	}

	public void putShort(String key, short value) {
		mMap.put(key, value);
	}

	public void putInt(String key, int value) {
		mMap.put(key, value);
	}

	public void putLong(String key, long value) {
		mMap.put(key, value);
	}

	public void putFloat(String key, float value) {

		mMap.put(key, value);
	}

	public void putDouble(String key, double value) {
		mMap.put(key, value);
	}

	public void putString(String key, String value) {
		mMap.put(key, value);
	}

	public void putCharSequence(String key, CharSequence value) {
		mMap.put(key, value);
	}

	public void putIntegerArrayList(String key, ArrayList<Integer> value) {
		mMap.put(key, value);
	}

	public void putStringArrayList(String key, ArrayList<String> value) {
		mMap.put(key, value);
	}

	public void putBooleanArray(String key, boolean[] value) {
		mMap.put(key, value);
	}

	public void putByteArray(String key, byte[] value) {
		mMap.put(key, value);
	}

	public void putShortArray(String key, short[] value) {
		mMap.put(key, value);
	}

	public void putCharArray(String key, char[] value) {
		mMap.put(key, value);
	}

	public void putIntArray(String key, int[] value) {
		mMap.put(key, value);
	}

	public void putLongArray(String key, long[] value) {
		mMap.put(key, value);
	}

	public void putFloatArray(String key, float[] value) {
		mMap.put(key, value);
	}

	public void putDoubleArray(String key, double[] value) {
		mMap.put(key, value);
	}

	public void putStringArray(String key, String[] value) {
		mMap.put(key, value);
	}

	// ////////////////////////////////////////////////////////////
	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		Object o = mMap.get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			return (Boolean) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "Boolean", defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * Returns the value associated with the given key, or (byte) 0 if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a byte value
	 */
	public byte getByte(String key) {
		return getByte(key, (byte) 0);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a byte value
	 */
	public Byte getByte(String key, byte defaultValue) {
		Object o = mMap.get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			return (Byte) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "Byte", defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * Returns the value associated with the given key, or false if no mapping
	 * of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a char value
	 */
	public char getChar(String key) {
		return getChar(key, (char) 0);
	}

	/**
	 * Returns the value associated with the given key, or (char) 0 if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a char value
	 */
	public char getChar(String key, char defaultValue) {
		Object o = mMap.get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			return (Character) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "Character", defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * Returns the value associated with the given key, or (short) 0 if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a short value
	 */
	public short getShort(String key) {
		return getShort(key, (short) 0);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a short value
	 */
	public short getShort(String key, short defaultValue) {
		Object o = mMap.get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			return (Short) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "Short", defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * Returns the value associated with the given key, or 0 if no mapping of
	 * the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return an int value
	 */
	public int getInt(String key) {
		return getInt(key, 0);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return an int value
	 */
	public int getInt(String key, int defaultValue) {
		Object o = mMap.get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			return (Integer) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "Integer", defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * Returns the value associated with the given key, or 0L if no mapping of
	 * the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a long value
	 */
	public long getLong(String key) {
		return getLong(key, 0L);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a long value
	 */
	public long getLong(String key, long defaultValue) {
		Object o = mMap.get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			return (Long) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "Long", defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * Returns the value associated with the given key, or 0.0f if no mapping of
	 * the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a float value
	 */
	public float getFloat(String key) {
		return getFloat(key, 0.0f);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a float value
	 */
	public float getFloat(String key, float defaultValue) {
		Object o = mMap.get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			return (Float) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "Float", defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * Returns the value associated with the given key, or 0.0 if no mapping of
	 * the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a double value
	 */
	public double getDouble(String key) {
		return getDouble(key, 0.0);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 *            a String
	 * @return a double value
	 */
	public double getDouble(String key, double defaultValue) {
		Object o = mMap.get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			return (Double) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "Double", defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 *            a String, or null
	 * @return a String value, or null
	 */
	public String getString(String key) {
		Object o = mMap.get(key);
		if (o == null) {
			return null;
		}
		try {
			return (String) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "String", e);
			return null;
		}
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 *            a String, or null
	 * @return a CharSequence value, or null
	 */
	public CharSequence getCharSequence(String key) {
		Object o = mMap.get(key);
		if (o == null) {
			return null;
		}
		try {
			return (CharSequence) o;
		} catch (ClassCastException e) {
			typeWarning(key, o, "CharSequence", e);
			return null;
		}
	}

	private void typeWarning(String key, Object value, String className,
			Object defaultValue, ClassCastException e) {
		StringBuilder sb = new StringBuilder();
		sb.append("Key ");
		sb.append(key);
		sb.append(" expected ");
		sb.append(className);
		sb.append(" but value was a ");
		sb.append(value.getClass().getName());
		sb.append(".  The default value ");
		sb.append(defaultValue);
		sb.append(" was returned.");
		LogUnit.w(sb.toString());
		LogUnit.w("Attempt to cast generated internal exception:", e);
	}

	private void typeWarning(String key, Object value, String className,
			ClassCastException e) {
		typeWarning(key, value, className, "<null>", e);
	}
}
