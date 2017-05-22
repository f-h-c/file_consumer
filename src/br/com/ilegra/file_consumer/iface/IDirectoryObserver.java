package br.com.ilegra.file_consumer.iface;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface IDirectoryObserver {

	void scanDirectory() throws IOException;

	Map<File, String> getDirectoryFiles();
	
	void run();

}