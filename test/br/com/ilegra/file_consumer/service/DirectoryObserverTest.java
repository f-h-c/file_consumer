package br.com.ilegra.file_consumer.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import br.com.ilegra.file_consumer.iface.IDirectoryObserver;

public class DirectoryObserverTest {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	private File createFile(String fileName) throws IOException {
		File file = temp.newFile(fileName);

		PrintWriter fileRecorder = new PrintWriter(file);

		fileRecorder.print("001ç1234567891234çDiegoç50000\n");
		fileRecorder.print("001ç3245678865434çRenatoç40000.99\n");
		fileRecorder.print("002ç2345675434544345çJose da SilvaçRural\n");
		fileRecorder.print("002ç2345676533444345çEduardo PereiraçRural\n");
		fileRecorder.print("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego\n");
		fileRecorder.print("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çrenato\n");

		fileRecorder.close();

		return file;
	}

	@Test
	public void scanDirectoryValidation() {
		String dir = temp.getRoot().getAbsolutePath();
		String dirOut = dir + "/data/out/";

		try {
			File file = createFile("exemplo.dat");
			createFile("exemplo.data");

			File diretorio = temp.getRoot();
			File afile[] = diretorio.listFiles();

			assertEquals(2, afile.length);

			IDirectoryObserver obs = new DirectoryObserver(dir, dirOut, "ç", ",", "-", "done", 1);
			
			Map<File, String> files = obs.getDirectoryFiles();
			
			assertEquals(1, files.size());
			
			assertEquals(file, files.keySet().toArray()[0]);
			assertEquals(dirOut + "exemplo.done.dat", files.get(files.keySet().toArray()[0]));
			
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
