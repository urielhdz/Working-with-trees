package hoffman;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class BitReader
{
	
	private InputStream instr;
	private int curr, bitsRead;
	
	/**
	 * Construct a bit reader on the specified input stream
	 * @param instr the input stream
	 * @throws IOException
	 */
	public BitReader(InputStream instr) throws IOException
	{
		this.instr = instr;
		this.curr = 0;
		this.bitsRead = 8;
	}
	
	/**
	 * readBit -- get the next bit in the input stream
	 * @return the next bit
	 * @throws IOException
	 */
	public int readBit() throws IOException
	{
		// Read next byte if necessary
		if (bitsRead == 8)
		{
			curr = instr.read();
			bitsRead = 0;
		}
		
		// If reached end of stream, stop
		if (curr == -1)
			return -1;
		
		// Interpret next bit
		int ret = (curr & 0x1);
		curr >>= 1;
		bitsRead++;
		return ret;
	}
	
	/**
	 * readByte -- read the next byte in the input stream
	 * @return the next byte
	 * @throws IOException
	 */
	public byte readByte() throws IOException
	{
		final int byteBits = 8;
		byte result = 0x0, k;
		int bit;
		
		for (k = 0; k < byteBits; k++)
		{
			bit = readBit();
			if (bit == -1)
				throw new EOFException();
			
			result += (bit << k);
		}
		
		return result;
	}
	
	/**
	 * readInt -- read an integer from the input stream
	 * @return the next integer
	 * @throws IOException
	 */
	public int readInt() throws IOException
	{
		final int intBits = 32;
		int result = 0x0, k, bit;
		
		for (k = 0; k < intBits; k++)
		{
			bit = readBit();
			if (bit == -1)
				throw new EOFException();
			
			result += (bit << k);
		}
		
		return result;
	}
	
}