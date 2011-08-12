package org.rcs.convert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.rcs.stream.RcsInputStream;
import org.rcs.stream.RcsOutputStream;

public class RcsFileConverter {

	public RcsFileConverter() {
	}

	public void convert(File file, File file1, ConversionType conversiontype)
			throws IOException {
		InputStream in = null;
		OutputStream out = null;

		try {
			if (conversiontype == ConversionType.DECODE) {
				in = new RcsInputStream(new FileInputStream(file));
				out = new FileOutputStream(file1);
			} else if (conversiontype == ConversionType.ENCODE) {
				in = new FileInputStream(file);
				out = new RcsOutputStream(new FileOutputStream(file1));
			} else {
				throw new IllegalArgumentException((new StringBuilder())
						.append("Unknown conversion type: ")
						.append(conversiontype).toString());
			}

			pipe(in, out);
			out.flush();
		} finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		}

	}

	protected void pipe(InputStream inputstream, OutputStream outputstream)
			throws IOException {
		byte buffer[] = new byte[1024];
		int read;
		do {
			read = inputstream.read(buffer);
			if (read > 0)
				outputstream.write(buffer, 0, read);
		} while (read >= 0);
	}
}
