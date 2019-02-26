package QRCode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

public class TestQRCodeConverter {
	String filePath = "E:\\FPT-JAVA";
	String data = "YEAH";
	String name = "phon";
	String name2 = "phon2";

	@Test
	public void testQRToTextTrue() throws FileNotFoundException, NotFoundException, IOException {
		CreateQRCode converter = new CreateQRCode();
		String outPut = "Data read from QR Code: YEAH";
		String test = converter.QRToText(filePath + "\\phon.jpg");
		assertEquals(outPut, test);
	}

	@Test
	public void testQRToTextFalse() throws FileNotFoundException, NotFoundException, IOException {
		CreateQRCode converter = new CreateQRCode();
		String outPut = "Data read from QR Code: YEA";
		String test = converter.QRToText(filePath + "\\phon.jpg");
		assertNotEquals(outPut, test);
	}

	@Test
	public void testTextToQRTrue() throws FileNotFoundException, NotFoundException, IOException, WriterException {
		CreateQRCode converter = new CreateQRCode();
		String outPut = "QR Code 'phon.jpg' created!";
		String test = converter.TextToQR(data, filePath, name);
		assertEquals(outPut, test);
	}

	@Test
	public void testTextToQRFalse() throws FileNotFoundException, NotFoundException, IOException, WriterException {
		CreateQRCode converter = new CreateQRCode();
		String outPut = "QR Code 'phon.jpg' create!";
		String test = converter.TextToQR(data, filePath, name);
		assertNotEquals(outPut, test);
	}
}
