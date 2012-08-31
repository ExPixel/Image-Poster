package org.imageposter.imgur;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.imageposter.ImagePosterMain;
import org.imageposter.PosterUtil;
import org.imageposter.URLUtil;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class ImgurInterface {//90798039e6c413e35fa80bb273a09b2e
	public static final String addr_ACCOUNT = "http://api.imgur.com/2/account";
	public static final String addr_UPLOAD = "http://api.imgur.com/2/upload ";
	ImgurAnonymousInterface anonInstance;
	ImgurAuthenticationInterface authInstance;
	static ImgurInterface instance;

	public static ImgurInterface getInstance() {
		if(instance == null) {
			instance = new ImgurInterface();
		}
		return instance;
	}

	public ImgurInterface() {
		this.createInterfaces();
	}

	public void createInterfaces() {
		this.anonInstance = new ImgurAnonymousInterface();
		this.authInstance = new ImgurAuthenticationInterface();
	}

	public void uploadImage(final BufferedImage img, final boolean auth) {
		try {
			//final File imgFile = PosterUtil.getInstance().saveImage(img);
			if(!auth) {
				this.anonInstance.uploadImage(img);
			}
			//if(imgFile.exists()) {imgFile.delete();}
		} catch (final IOException e) {
			PosterUtil.getInstance().logException(e);
			e.printStackTrace();
		}
	}

	class ImgurAnonymousInterface {
		private static final String API_KEY = "90798039e6c413e35fa80bb273a09b2e";

		public void uploadImage(final BufferedImage bi) throws IOException {
			final ByteArrayOutputStream os = new ByteArrayOutputStream();
			final org.imageposter.base64.Base64.OutputStream b64 = new org.imageposter.base64.Base64.OutputStream(os);
			ImageIO.write(bi, "png", b64);
			final String b64_string = os.toString("UTF-8");
			final Map<String, String> req = new HashMap<String, String>();

			final String ntime = Long.toString(System.nanoTime());

			final String d = (new Date()).toString();

			req.put("key", ImgurAnonymousInterface.API_KEY);
			req.put("image", b64_string);
			req.put("type", "base64");
			req.put("name", ntime.concat(".png"));
			req.put("title", d);
			req.put("caption", "Created on: " + d);
			final String result = URLUtil.postRequest(new URL(ImgurInterface.addr_UPLOAD), req);

			ImagePosterMain.getInstance().imageResultRecieved(result);
		}
		/*
try {
    // Construct data
    String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
    data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");

    // Send data
    URL url = new URL("http://hostname:80/cgi");
    URLConnection conn = url.openConnection();
    conn.setDoOutput(true);
    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    wr.write(data);
    wr.flush();

    // Get the response
    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    while ((line = rd.readLine()) != null) {
        // Process line...
    }
    wr.close();
    rd.close();
} catch (Exception e) {
}
		 */
		/*
FAILED PUT:
			final URL url = new URL(addr_UPLOAD);
			final HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("PUT");
			OutputStreamWriter out = null;
			FileInputStream fis = null;
			try {
				out = new OutputStreamWriter(http.getOutputStream());
				fis = new FileInputStream(imgFile);
				int b;
				while((b = fis.read()) >= 0) {
					out.write(b);
				}
				out.close();

				System.out.println("File Uploaded, Reading...");
				final BufferedReader r = new BufferedReader(new InputStreamReader(http.getInputStream()));
				final StringBuilder sb = new StringBuilder();
				String line;
				while((line = r.readLine()) != null) {
					sb.append(line);
					sb.append(PosterUtil.ln);
				}
				System.out.println("Output: " + sb.toString());
				r.close();
			} finally {
				if(out != null) { out.close(); }
				if(fis != null) { fis.close(); }
			}

		 */
	}

	class ImgurAuthenticationInterface {
		private static final String API_KEY = "3739eed9926a2fd6a22a216ce0948bdc0503ea516";
		private static final String API_SECRET = "6da282be7a4de90734d2a599a604ca90";


		OAuthService service;
		Token requestToken;
		Token accessToken;

		public void buildService() {
			this.service = new ServiceBuilder().provider(ImgurAPI.class)
					.apiKey(API_KEY).apiSecret(API_SECRET).build();
		}

		public String getAuthURL() {
			this.requestToken = this.service.getRequestToken();
			final String authorizationUrl = this.service.getAuthorizationUrl(this.requestToken);
			return authorizationUrl;
		}

		public void createAccessToken(final String verifier) {
			this.createAccessToken(new Verifier(verifier));
		}

		public void createAccessToken(final Verifier verifier) {
			this.accessToken = this.service.getAccessToken(this.requestToken, verifier);
		}
	}
}

/*
 * SECRET API STUFF:
 *
 * --Authentication API
 * Consumer Key: 3739eed9926a2fd6a22a216ce0948bdc0503ea516
 * Consumer Secret: 6da282be7a4de90734d2a599a604ca90
 */

