package org.imageposter;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class TrayHandler {
	static TrayHandler instance;
	TrayIcon trayIcon;
	PopupMenu popup;

	public static TrayHandler getInstance() {
		if(instance == null) {
			instance = new TrayHandler();
		}
		return instance;
	}

	public TrayIcon getTrayIcon() {
		return this.trayIcon;
	}

	public PopupMenu getPopup() {
		return this.popup;
	}

	public void showTip(final String caption, final String text, final TrayIcon.MessageType messageType) {
		this.trayIcon.displayMessage(caption, text, messageType);
	}

	public void addListeners() {
		this.trayIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				System.out.println("ACTION!");
			}
		});
	}

	public boolean createTray() {
		if(!SystemTray.isSupported()) {
			PosterUtil.getInstance().log("System tray is not supported.");
			return false;
		}
		try {
			this.trayIcon = new TrayIcon(
					PosterUtil.getInstance().getImage("/resources/image--arrow.png"),
					"Image Poster");
			this.trayIcon.setPopupMenu(this.getPopupMenu());
			final SystemTray tray = SystemTray.getSystemTray();
			this.addListeners();
			tray.add(this.trayIcon);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (final AWTException e) {
			PosterUtil.getInstance().logException(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	PopupMenu getPopupMenu() {
		this.popup = new PopupMenu();
		this.popup.add(this.getWindowCaptureMenuItem());
		this.popup.add(this.getScreenshotMenuItem());
		this.popup.add(this.getExitMenuItem());
		return this.popup;
	}

	MenuItem getExitMenuItem() {
		final MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				SystemTray.getSystemTray().remove(TrayHandler.getInstance().getTrayIcon());
				System.exit(0);
			}});
		return exitItem;
	}

	MenuItem getScreenshotMenuItem() {
		final MenuItem exitItem = new MenuItem("Capture Screen");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				ImagePosterMain.getInstance().captureScreen();
			}});
		return exitItem;
	}

	MenuItem getWindowCaptureMenuItem() {
		final MenuItem exitItem = new MenuItem("Capture Current Window");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				ImagePosterMain.getInstance().captureWindow();
			}});
		return exitItem;
	}
}
