package org.imageposter;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.w3c.dom.Element;

public class PosterUtil {
	static PosterUtil instance;
	Robot robot;
	public static String ln = System.getProperty("line.separator");

	public static PosterUtil getInstance() {
		if(instance == null) {
			instance = new PosterUtil();
		}
		return instance;
	}

	public Robot getRobot() {
		if(this.robot == null) {
			try {
				this.robot = new Robot();
			} catch (final AWTException e) {
				this.logException(e);
				e.printStackTrace();
			}
		}
		return this.robot;
	}

	public Image getImage(final String path) throws FileNotFoundException {
		final URL url = this.getClass().getResource(path);
		if(url == null) {
			throw(this.logException(new FileNotFoundException(String.format("Failed to locate file: %s", path))));
		} else {
			return (new ImageIcon(url)).getImage();
		}
	}

	public <T extends Exception> T logException(final T e) {
		return e;
	}

	public String log(final String s) {
		return s;
	}

	public BufferedImage takeScreenshot(final Rectangle rect) {
		return this.getRobot().createScreenCapture(rect);
	}

	/**
	 * Saves the buffered image in a random file.
	 * @return The file created.
	 * @throws IOException
	 */
	public File saveImage(final BufferedImage bufimg) throws IOException {
		final File outputFile = new File("temp", Long.toString(System.nanoTime()).concat(".png"));
		outputFile.getParentFile().mkdirs();
		outputFile.createNewFile();
		ImageIO.write(bufimg, "png", outputFile);
		return outputFile;
	}

	public Rectangle getScreenRect() {
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		return new Rectangle(dim);
	}

	public static Element XMLFirstElem(final Element elem, final String n) {
		return (Element) elem.getElementsByTagName(n).item(0);
	}
}
