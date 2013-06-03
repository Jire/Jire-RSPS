package jire.plugin;

import java.io.File;

public abstract class AbstractPluginLoader implements PluginLoader {

	@Override
	public Plugin load(String file) {
		return load(new File(file));
	}

}