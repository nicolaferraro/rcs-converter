package org.rcs.stream;

import java.io.IOException;
import java.io.InputStream;

public class RcsInputStream extends InputStream {

	public RcsInputStream(InputStream inputstream) {
		source = inputstream;
	}

	public int available() throws IOException {
		return source.available();
	}

	public void close() throws IOException {
		source.close();
	}

	public int read() throws IOException {
		int readByte = source.read();
		if (readByte < 0) {
			return readByte;
		} else {
			byte keyByte = RcsConstants.DECODE_KEY[cursor];
			cursor = (cursor + 1) % RcsConstants.DECODE_KEY.length;
			return readByte ^ keyByte;
		}
	}

	protected int cursor;
	protected InputStream source;
}
