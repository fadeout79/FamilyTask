package utilities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import family.controller.ImageMvcController;

public class ImageProcessing {

		public static final int IMAGE_WIDTH = 80;
		public static final int IMAGE_HEIGHT = 80;
		private String filePath;
		
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


		private static final List<String> imageExtensions = Arrays.asList("jpg", "gif", "png");
		
		private static final Logger logger = LoggerFactory.getLogger(ImageProcessing.class);

		public void createImage(MultipartFile file, String fileName, String imagePath) {
			try {
				if (isValidImage(fileName)) {
					setFilePath(fileName, imagePath);
					BufferedImage tempImage = convertMultipartToBufferedImage(file, fileName, imagePath);
					BufferedImage resizedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
					Graphics2D g = resizedImage.createGraphics();
					g.drawImage(tempImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
					g.dispose();		
					
						ImageIO.write(resizedImage, "jpg", new File(this.filePath));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private String getExtension(String fileName) {
			return fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
		}
		
		private boolean isValidImage(String fileName) {
			return imageExtensions.contains(getExtension(fileName));
		}
		
		
		private BufferedImage convertMultipartToBufferedImage(MultipartFile file, String fileName, String imagePath) {
			BufferedImage bImageFromConvert = null;
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                InputStream in = new ByteArrayInputStream(bytes);
	    			bImageFromConvert = ImageIO.read(in);

	    			
//	                // Creating the directory to store file
//	                String rootPath = imagePath;
//	                File dir = new File(rootPath);
//	                if (!dir.exists())
//	                    dir.mkdirs();
//	                fullPath = dir.getAbsolutePath() + File.separator + fileName;
//	 
//	                // Create the file on server
//	                File serverFile = new File(fullPath);
//	                BufferedOutputStream stream = new BufferedOutputStream(
//	                        new FileOutputStream(serverFile));
//	                stream.write(bytes);
//	                stream.close();
//	 
//	                logger.info("Server File Location="
//	                        + serverFile.getAbsolutePath());
	            } catch (Exception e) {
	                logger.error("You failed to upload " + fileName + " => " + e.getMessage());
	            }
	        } 
	        return bImageFromConvert;
		}
		
}
