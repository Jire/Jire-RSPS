package jire.logging;

import jire.plugin.Plugin;
import jire.plugin.PluginManifest;

public final class PluginLogger extends UniversalLogger {

	private final PluginManifest pluginManifest;

	public PluginLogger(PluginManifest pluginManifest) {
		this.pluginManifest = pluginManifest;
	}

	public PluginLogger(Plugin plugin) {
		this(plugin.getManifest());
	}

	@Override
	public void showLog(final String content, final Type type,
			final Format format) {
		showLog("[" + pluginManifest.name() + "]" + content, type, format);
	}

}