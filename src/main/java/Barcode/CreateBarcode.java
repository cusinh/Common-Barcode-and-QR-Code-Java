package Barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class CreateBarcode {
	String pathFile = "C:\\Users\\Sinh\\Desktop\\";

	public static void createImageBarcode(String image_name, String myString, String pathFile) {

		try {
			Code128Bean code128 = new Code128Bean();
			code128.setHeight(15f);
			code128.setModuleWidth(0.3);
			code128.setQuietZone(10);
			code128.doQuietZone(true);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			code128.generateBarcode(canvas, myString);
			canvas.finish();

			// write to png file
			FileOutputStream fos = new FileOutputStream(pathFile + image_name);
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String createImageToCode(String imageName, String pathFile) {
		String result1 = null;
		try {
			InputStream barCodeInputStream = new FileInputStream(pathFile + imageName);
			BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);

			LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Reader reader = new MultiFormatReader();
			Result result = reader.decode(bitmap);
			result1 = result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}
}
