package br.com.ilegra.file_consumer.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.ilegra.file_consumer.iface.IDirectoryObserver;

/**
 *
 * @author fhc
 */
public class DirectoryObserver extends Thread implements IDirectoryObserver {

	private int waitingTime = 60000; // 1 minuto por default
	private String dirIn;
	private String dirOut;
	private String defaultSeparator;
  private String productsSeparator;
  private String productSeparator;
  private String extensionDoneFile;

	public DirectoryObserver(String dirIn, String dirOut, String defaultSeparator, String productsSeparator, String productSeparator, String extensionDoneFile, int time) {
		this.dirIn = dirIn;
		this.dirOut = dirOut;
		this.waitingTime = time;
		this.defaultSeparator = defaultSeparator;
    this.productsSeparator = productsSeparator;
    this.productSeparator = productSeparator;
    this.extensionDoneFile = extensionDoneFile;
	}

	private String getFileName(String file) {
		int pos = file.lastIndexOf(".");

		return file.substring(0, pos);
	}

	private boolean isDatFile(String file) {
		int pos = file.lastIndexOf(".");

		return file.substring(pos + 1).equalsIgnoreCase("dat");
	}

	@Override
	public Map<File, String> getDirectoryFiles() {
		Map<File, String> result = new HashMap<>();

		File file = new File(dirIn);
		File afile[] = file.listFiles();
		int i = 0;

		if (afile != null)
			for (int j = afile.length; i < j; i++) {
				File f = afile[i];

				if (isDatFile(f.getName())) {
					String output = dirOut + getFileName(f.getName()) + "."+ extensionDoneFile + ".dat";

					result.put(f, output);
				}
			}

		return result;
	}

	@Override
	public void scanDirectory() throws IOException {
		Map<File, String> files = getDirectoryFiles();

		for (File file : files.keySet()) {
			String output = files.get(file);

			File fileOut = new File(output);

			if (!fileOut.exists()) {
				FileConsumer consumer = new FileConsumer(file.getCanonicalPath(), output, defaultSeparator, productsSeparator, productSeparator);
				consumer.start();
			}
		}
	}

	@Override
	public void run() {
		while (true)
			try {
				scanDirectory();

				Thread.sleep(waitingTime);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
	}
}
