package jire.logging;

import java.util.BitSet;

public abstract class AbstractLogger implements Logger {

	private final BitSet bitSet = new BitSet(Type.values().length);

	protected AbstractLogger() {
		configure(Type.MESSAGE, true);
		configure(Type.WARNING, true);
		configure(Type.ERROR, true);
	}

	@Override
	public final Logger log(String content, Type type, Format format) {
		if (isConfigured(type))
			showLog(content, type, format);
		return this;
	}

	protected abstract void showLog(String content, Type type, Format format);

	@Override
	public final Logger message(String content, Format format) {
		return log(content, Type.MESSAGE, format);
	}

	@Override
	public final Logger message(String content) {
		return message(content, DEFAULT_FORMAT);
	}

	@Override
	public final Logger warning(String content, Format format) {
		return log(content, Type.WARNING, format);
	}

	@Override
	public final Logger warning(String content) {
		return warning(content, DEFAULT_FORMAT);
	}

	@Override
	public final Logger error(String content, Format format) {
		return log(content, Type.ERROR, format);
	}

	@Override
	public final Logger error(String content) {
		return error(content, DEFAULT_FORMAT);
	}

	@Override
	public final Logger debug(String content, Format format) {
		return log(content, Type.DEBUG, format);
	}

	@Override
	public final Logger debug(String content) {
		return debug(content, DEFAULT_FORMAT);
	}

	@Override
	public final Logger configure(Type type, boolean on) {
		bitSet.set(type.ordinal(), on);
		return this;
	}

	@Override
	public final boolean isConfigured(Type type) {
		return bitSet.get(type.ordinal());
	}

}