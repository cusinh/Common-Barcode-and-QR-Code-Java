package Barcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestBarcode {
	private static CreateBarcode createBarcode;

	@BeforeClass
	public static void prepareForAllTest() {
		createBarcode = new CreateBarcode();
	}

	String pathFile = "E:\\FPT-JAVA";
	String imageName = "demo.png";
	String stringGenerateBarcode = "123123123";
	String stringGenerateBarcode1 = "1231231231";

	@Test
	public void testCreateImageBarcodeTrue() {
		String test = createBarcode.createImageToCode(imageName, pathFile);
		assertEquals(test, stringGenerateBarcode);
	}
	
	@Test
	public void testCreateImageBarcodeFalse() {
		String test = createBarcode.createImageToCode(imageName, pathFile);
		assertNotEquals(test, stringGenerateBarcode1);
	}

	@Test
	public void barCodeCreateImage() {
		boolean a = createBarcode.createImageBarcode(imageName, stringGenerateBarcode, pathFile);
		assertEquals(a, true);
	}
	@Test
	public void barCodeCreateImage1() {
		boolean a = createBarcode.createImageBarcode(imageName, stringGenerateBarcode, pathFile);
		assertNotEquals(a, false);
	}
}
