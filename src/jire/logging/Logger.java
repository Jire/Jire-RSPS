package jire.logging;

public interface Logger {

	enum Type {
		MESSAGE, WARNING, ERROR, DEBUG
	}

	enum Format {
		DATE, TIME, DATE_TIME, RAW;
	}

	static final Type DEFAULT_LEVEL = Type.MESSAGE;

	static final Format DEFAULT_FORMAT = Format.DATE_TIME;

	Logger log(String content, Type type, Format format);

	Logger message(String content, Format format);

	Logger message(String content);

	Logger warning(String content, Format format);

	Logger warning(String content);

	Logger error(String content, Format format);

	Logger error(String content);

	Logger debug(String content, Format format);

	Logger debug(String content);

	Logger configure(Type type, boolean on);

	boolean isConfigured(Type type);

}