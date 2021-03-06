package com.bebehp.bousaistation.deploader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.LinkedList;

import com.bebehp.bousaistation.BousaiStation;
import com.bebehp.bousaistation.Reference;
import com.bebehp.bousaistation.log.Log;

public class DepLoader {
	public static DepLoader INSTANCE = new DepLoader();
	public File libDir;

	private LinkedList<Dependencies> deps;
	private final LinkedList<Dependencies> dlDeps;

	private DepLoader() {
		this.deps = new LinkedList<>();
		this.dlDeps = new LinkedList<>();
		this.libDir = new File(BousaiStation.DATA_DIR, "lib");
		if (!this.libDir.exists())
			this.libDir.mkdir();
	}

	public LinkedList<Dependencies> getDeps() {
		return this.deps;
	}

	public void setDeps(final LinkedList<Dependencies> deps) {
		this.deps = deps;
	}

	public DepLoader readCSV() {
		final InputStream is = this.getClass().getClassLoader().getResourceAsStream("dep.info");
		if (is != null)
			try (final BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
				String line;
				while((line = br.readLine()) != null) {
					final String[] depLine = line.split(",");
					final Dependencies dep = new Dependencies(depLine[0], depLine[1]);
					this.deps.add(dep);
					this.dlDeps.add(dep);
				}
			} catch (final IOException e) {
				Log.warn(e);
			}
		return this;
	}

	public DepLoader checkDep() {
		final Iterator<Dependencies> it = this.dlDeps.iterator();
		while (it.hasNext()) {
			final Dependencies dep = it.next();
			final File file = new File(this.libDir, dep.getLocal());
			if (file.exists())
				it.remove();
			else
				System.out.println("Not Found: " + dep.getLocal());
		}
		return this;
	}

	public boolean depListIsEmpty() {
		return this.deps.isEmpty();
	}

	public boolean depDLTaskListIsEmpty() {
		return this.dlDeps.isEmpty();
	}

	public void download() {
		final long startTime = System.currentTimeMillis();
		File downloadingFile = null;
		HttpURLConnection connection = null;
		for (final Dependencies dep : this.dlDeps) {
			try {
				System.out.println("Downloading: " + dep.getRemote());
				downloadingFile = new File(this.libDir, dep.getLocal());
				final URL remote = new URL(dep.getRemote());
				connection = (HttpURLConnection) remote.openConnection();
				connection.setConnectTimeout(10000);
				connection.setReadTimeout(10000);
				connection.setRequestProperty("User-Agent", Reference.ID);

				if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
					throw new RuntimeException();
			} catch (final IOException e) {
				Log.warn(e);
				System.out.println("A connection error occured: " + dep.getRemote());
			}

			if (downloadingFile != null && connection != null)
				try (FileOutputStream fos = new FileOutputStream(downloadingFile); InputStream is = connection.getInputStream()) {
					final byte[] buffer = new byte[4096];
					int n = 0;
					while (-1 != (n = is.read(buffer)))
						fos.write(buffer, 0, n);
				} catch (final IOException e) {
					System.out.println("A download error occured: " + dep.getLocal());
					downloadingFile.delete();
				}
		}
		final long endTime = System.currentTimeMillis()-startTime;
		System.out.println("All of the download is complete");
		System.out.println("Elapsed time: " + endTime + "ms");
	}

	public void addClassPath() {
		try {
			final Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			if (method == null) {
				Log.fatal("URLClassLoader.addURL() is null!");;
				return;
			}
			method.setAccessible(true);

			for (final Dependencies dep : this.deps) {
				final File file = new File(this.libDir, dep.getLocal());
				if (file.exists()) {
					System.out.println("AddClassPath: " + dep.getLocal());
					method.invoke(getClass().getClassLoader(), file.toURI().toURL());
				}
			}
		} catch (final Exception e) {
			Log.fatal(e);
			System.out.println("Adding to the class path of Dependencies failed!");
		}
	}
}
