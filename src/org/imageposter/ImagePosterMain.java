package org.imageposter;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;

import org.imageposter.imgur.ImgurImage;
import org.imageposter.imgur.ImgurInterface;
import org.xml.sax.SAXException;



public class ImagePosterMain {

	public static ImagePosterMain instance;

	public static ImagePosterMain getInstance() {
		if(instance == null) {
			instance = new ImagePosterMain();
		}
		return instance;
	}

	public static void main(final String[] args) {
		ImagePosterMain.getInstance().start();
	}

	public void start() {
		this.setup();
		try {
			Thread.sleep(1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		TrayHandler.getInstance().showTip("Awesome Sauce", "With Sausages", MessageType.INFO);
	}

	public void setLaf() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final ClassNotFoundException e) {
			PosterUtil.getInstance().logException(e);
			e.printStackTrace();
		} catch (final InstantiationException e) {
			PosterUtil.getInstance().logException(e);
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			PosterUtil.getInstance().logException(e);
			e.printStackTrace();
		} catch (final UnsupportedLookAndFeelException e) {
			PosterUtil.getInstance().logException(e);
			e.printStackTrace();
		}
	}

	public void setup() {
		this.setLaf();
		TrayHandler.getInstance().createTray();
	}

	public void captureScreen() {
		final Runnable uploader = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
				ImgurInterface.getInstance().uploadImage(
						PosterUtil.getInstance().takeScreenshot(PosterUtil.getInstance().getScreenRect()),
						false);
			}
		};
		final Thread t = new Thread(uploader);
		t.start();
	}
	public void captureWindow() {
		final Runnable uploader = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
				ImgurInterface.getInstance().uploadImage(
						PosterUtil.getInstance().takeScreenshot(NativeHandler.getInstance().getForegroundWindowRect()),
						false);
			}
		};
		final Thread t = new Thread(uploader);
		t.start();
	}

	public void imageResultRecieved(final String result) {
		try {
			final ImgurImage imgurImage = ImgurImage.fromXML(result);
			System.out.println("Use Link: " + imgurImage.getLinks().getOriginal());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			PosterUtil.getInstance().logException(e);
			e.printStackTrace();
		}
	}


	/*
	 *

<upload>
  <image>
    <name>Me at the zoo</name>
    <title></title>
    <caption></caption>
    <hash>WWs55</hash>
    <deletehash>Dte3mMaQzGoc8RF</deletehash>
    <datetime>2010-08-16 22:39:19</datetime>
    <type>image/jpeg</type>
    <animated>false</animated>
    <width>720</width>
    <height>540</height>
    <size>46174</size>
    <views>0</views>
    <bandwidth>0</bandwidth>
  </image>
  <links>
    <original>http://imgur.com/WWs55.jpg</original>
    <imgur_page>http://imgur.com/WWs55</imgur_page>
    <delete_page>http://imgur.com/delete/Dte3mMaQzGoc8RF</delete_page>
    <small_square>http://imgur.com/WWs55s.jpg</small_square>
    <large_thumbnail>http://imgur.com/WWs55l.jpg</large_thumbnail>
  </links>
</upload>


	 */
	/*

	 */

}

