package br.com.ilegra.file_consumer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import br.com.ilegra.file_consumer.iface.IDirectoryObserver;
import br.com.ilegra.file_consumer.iface.IFileConsumer;

public class FileConsumerTest {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	private File createFile(String fileName) throws IOException {
		File file = temp.newFile(fileName);
		temp.newFolder("data", "out");

		PrintWriter fileRecorder = new PrintWriter(file);

		fileRecorder.print("001ç1234567891234çDiegoç50000\n");
		fileRecorder.print("001ç3245678865434çRenatoç40000.99\n");
		fileRecorder.print("002ç2345675434544345çJose da SilvaçRural\n");
		fileRecorder.print("002ç2345676533444345çEduardo PereiraçRural\n");
		fileRecorder.print("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego\n");
		fileRecorder.print("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato\n");

		fileRecorder.close();

		return file;
	}

	private boolean processFileLine(int num, String line) {
		boolean result = false;

		switch (num) {
			case 0:
				result = line.trim().equalsIgnoreCase("2");
				break;
			case 1:
				result = line.trim().equalsIgnoreCase("2");
				break;
			case 2:
				result = line.trim().equalsIgnoreCase("10");
				break;
			case 3:
				result = line.trim().equalsIgnoreCase("Renato");
				break;
		}

		return result;
	}

	private boolean processedFileOk(File file) throws IOException {
		boolean result = true;
		int line = 0;

		BufferedReader in = new BufferedReader(new FileReader(file));

		while (in.ready() && result) {
			if (!processFileLine(line++, in.readLine()))
				result = false;
		}

		in.close();

		return result;
	}

	@Test
	public void runValidation() {
		String dir = temp.getRoot().getAbsolutePath();
		String dirOut = dir + "/data/out/";

		try {
			File file = createFile("exemplo.dat");

			File diretorio = temp.getRoot();
			File afile[] = diretorio.listFiles();

			assertEquals(2, afile.length);

			IDirectoryObserver obs = new DirectoryObserver(dir, dirOut, "ç", ",", "-", "done", 1);

			Map<File, String> files = obs.getDirectoryFiles();

			assertEquals(1, files.size());

			assertEquals(file, files.keySet().toArray()[0]);
			assertEquals(dirOut + "exemplo.done.dat", files.get(files.keySet().toArray()[0]));

			IFileConsumer consumer = new FileConsumer(file.getAbsolutePath(), files.get(files.keySet().toArray()[0]), "ç", ",", "-");

			consumer.run();

			File outDir = new File(dirOut);
			afile = outDir.listFiles();

			assertEquals(1, afile.length);

			assertEquals(dirOut + "exemplo.done.dat", afile[0].getAbsolutePath());
			
			assertTrue(processedFileOk(afile[0]));
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
