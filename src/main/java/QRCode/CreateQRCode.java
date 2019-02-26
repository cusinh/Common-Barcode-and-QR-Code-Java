package QRCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRCode {

	@SuppressWarnings("unchecked")
	public static String InputImage(String filePath) throws FileNotFoundException, NotFoundException, IOException {
		String charset = "utf-8"; // or "ISO-8859-1"
		@SuppressWarnings("rawtypes")
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		return readQRCode(filePath, charset, hintMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String readQRCode(String filePath, String charset, Map hintMap) throws FileNotFoundException, IOException, NotFoundException {

			BinaryBitmap binaryBitmap = new BinaryBitmap(
					new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
			Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
			return qrCodeResult.getText();

	}
	
	public void TextToQR(String data, String pathFile, String nameImage) throws WriterException, IOException {
		// String data = "Super Pig...";
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

		// Write to file image
		String outputFile = pathFile + nameImage + ".jpg";
		Path path = FileSystems.getDefault().getPath(outputFile);
		MatrixToImageWriter.writeToPath(matrix, "PNG", path);
		System.out.println("QR Code created!");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String QRToText(String filePath) throws FileNotFoundException, NotFoundException, IOException {
		String charset = "utf-8";
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		return readQRCode(filePath, charset, hintMap);
	}

}
