package org.rcs.stream;

import java.io.IOException;
import java.io.OutputStream;

public class RcsOutputStream extends OutputStream {

	public RcsOutputStream(OutputStream outputstream) {
		sink = outputstream;
	}

	public void write(int inputByte) throws IOException {
		byte keyByte = RcsConstants.DECODE_KEY[cursor];
		cursor = (cursor + 1) % RcsConstants.DECODE_KEY.length;
		sink.write(inputByte ^ keyByte);
	}

	public void close() throws IOException {
		sink.close();
	}

	public void flush() throws IOException {
		sink.flush();
	}

	protected int cursor;
	protected OutputStream sink;
}
