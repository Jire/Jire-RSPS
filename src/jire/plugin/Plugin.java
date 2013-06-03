package jire.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import jire.Environment;
import jire.logging.Logger;

public interface Plugin extends Environment {

	Logger getLogger();

	void enable();

	void disable();

	boolean isEnabled();

	InputStream getResource(String file);

	PluginManifest getManifest();

	String getResourceDirectory();

	PluginConfiguration loadConfiguration(File file);

	PluginConfiguration loadConfiguration(String file);

	void saveConfiguration(PluginConfiguration configuration, File file);

	void saveConfiguration(PluginConfiguration configuration, String file);

	List<String> readLines(File file);

	List<String> readLines(String file);

	BufferedReader openReader(File file);

	BufferedReader openReader(String file);

	BufferedWriter openWriter(File file);

	BufferedWriter oppenWriter(String file);

	InputStream openInputStream(File file);

	InputStream openInputStream(String file);

	OutputStream openOutputStream(File file);

	OutputStream openOutputStream(String file);

	File fileFrom(String file);

}