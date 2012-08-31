package org.imageposter;

import java.awt.Rectangle;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class NativeHandler {
	static NativeHandler instance;
	public static NativeHandler getInstance() {
		if(instance == null) {
			instance = new NativeHandler();
		}
		return instance;
	}

	public Rectangle getForegroundWindowRect() {
		final ImgPosterDLL imgposter_native = ImgPosterDLL.INSTANCE;
		final Pointer p = imgposter_native.getForegroundWindowRect();
		final String rect_string = new String(p.getString(0));
		Native.free(Pointer.nativeValue(p));
		final String[] rect_params = rect_string.split(",");
		final Rectangle rect = new Rectangle();

		final int top = Integer.parseInt(rect_params[0]);
		final int bottom = Integer.parseInt(rect_params[1]);
		final int left = Integer.parseInt(rect_params[2]);
		final int right = Integer.parseInt(rect_params[3]);

		final Rectangle screenRect = PosterUtil.getInstance().getScreenRect();

		rect.x = Math.max(left, 0);
		rect.y = Math.max(top, 0);
		rect.width = Math.min(right - left, screenRect.width);
		rect.height = Math.min(bottom - top, screenRect.height);
		return rect;
	}


	public interface ImgPosterDLL extends Library {
		ImgPosterDLL INSTANCE = (ImgPosterDLL) Native.loadLibrary("imgposter",
				ImgPosterDLL.class);
		Pointer getForegroundWindowRect();
	}
}
