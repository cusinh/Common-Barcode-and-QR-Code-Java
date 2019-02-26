package Barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

	public boolean createImageBarcode(String imageName, String stringGenerateBarcode, String pathFile) {
		try {
			Code128Bean code128 = new Code128Bean();
			code128.setHeight(15f);
			code128.setModuleWidth(0.3);
			code128.setQuietZone(10);
			code128.doQuietZone(true);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			code128.generateBarcode(canvas, stringGenerateBarcode);
			canvas.finish();

			// write to png file
			FileOutputStream fos = new FileOutputStream(pathFile + "\\" + imageName);
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String createImageToCode(String imageName, String pathFile) {
		String result1 = null;
		try {
			InputStream barCodeInputStream = new FileInputStream(pathFile + "\\" + imageName);
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
