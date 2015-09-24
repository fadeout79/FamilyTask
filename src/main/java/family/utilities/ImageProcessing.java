package family.utilities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang.RandomStringUtils;


public class ImageProcessing {

	public static final int IMAGE_WIDTH = 80;
	public static final int IMAGE_HEIGHT = 80;
	private String filePath;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	private static final List<String> imageExtensions = Arrays.asList("jpg", "gif", "png");

	private static final Logger logger = LoggerFactory.getLogger(ImageProcessing.class);

	public String getFilePath() {
		return filePath;
	}

	private void setFilePath(String fileName, String imagePath) {
		String rootPath = imagePath;
		File dir = new File(rootPath);
		if (!dir.exists())
			dir.mkdirs();

		this.filePath = dir.getAbsolutePath() + File.separator + fileName;;
	}


	private String generateUniqueFileName() {
		Date date = new Date();
		String name = String.format("%s", RandomStringUtils.randomAlphanumeric(8)) + ".jpg";
		return name;
	}
	
	public void createImage(MultipartFile file, String fileName, String imagePath) {
		try {
			if (isValidImage(fileName)) {
				setFileName(generateUniqueFileName());
				setFilePath(this.fileName, imagePath);
				BufferedImage tempImage = convertMultipartToBufferedImage(file, fileName);
				BufferedImage resizedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = resizedImage.createGraphics();
				g.drawImage(tempImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
				g.dispose();		
				logger.info("Writing file " + this.filePath);
				ImageIO.write(resizedImage, "jpg", new File(this.filePath));
			} else {
				logger.info("Invalid filename : " + getExtension(fileName));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
	}

	private boolean isValidImage(String fileName) {
		return imageExtensions.contains(getExtension(fileName));
	}


	private BufferedImage convertMultipartToBufferedImage(MultipartFile file, String fileName) {
		BufferedImage bImageFromConvert = null;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				InputStream in = new ByteArrayInputStream(bytes);
				bImageFromConvert = ImageIO.read(in);
			} catch (Exception e) {
				logger.error("You failed to upload " + fileName + " => " + e.getMessage());
			}
		} else {
			logger.info("File is empty");
		}
		return bImageFromConvert;
	}

}
