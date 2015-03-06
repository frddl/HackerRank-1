package Algorithms;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.InputMismatchException;

public class Consecutive_Subsequences {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		ConsecutiveSubsequences solver = new ConsecutiveSubsequences();
		int testCount = Integer.parseInt(in.next());

		for (int i = 1; i <= testCount; i++) {
			solver.solve(i, in, out);
		}

		out.close();
	}
}

class ConsecutiveSubsequences {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int modulo = in.readInt();
		int[] sequence = IOUtils.readIntArray(in, count);
		int[] answer = new int[modulo];
		answer[0] = 1;
		long result = 0;
		int sum = 0;

		for (int i : sequence) {
			sum += i;
			sum %= modulo;
			result += answer[sum]++;
		}

		out.printLine(result);
	}
}

class InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1) {
			throw new InputMismatchException();
		}

		if (curChar >= numChars) {
			curChar = 0;

			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}

			if (numChars <= 0) {
				return -1;
			}
		}

		return buf[curChar++];
	}

	public int readInt() {
		int c = read();

		while (isSpaceChar(c)) {
			c = read();
		}

		int sgn = 1;

		if (c == '-') {
			sgn = -1;
			c = read();
		}

		int res = 0;

		do {
			if (c < '0' || c > '9') {
				throw new InputMismatchException();
			}

			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));

		return res * sgn;
	}

	public String readString() {
		int c = read();

		while (isSpaceChar(c)) {
			c = read();
		}

		StringBuilder res = new StringBuilder();

		do {
			if (Character.isValidCodePoint(c)) {
				res.appendCodePoint(c);
			}

			c = read();
		} while (!isSpaceChar(c));

		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		if (filter != null) {
			return filter.isSpaceChar(c);
		}

		return isWhitespace(c);
	}

	public static boolean isWhitespace(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				outputStream)));
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void close() {
		writer.close();
	}

	public void printLine(long i) {
		writer.println(i);
	}
}

class IOUtils {
	public static int[] readIntArray(InputReader in, int size) {
		int[] array = new int[size];

		for (int i = 0; i < size; i++) {
			array[i] = in.readInt();
		}

		return array;
	}
}
