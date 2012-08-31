package org.imageposter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class URLUtil {

	public static String ln = System.getProperty("line.separator");

	/**
	 * Sends a post request to the specified URL using the parameters in the {postMap}.
	 * @param url
	 * @param postMap
	 * @return The String returned by the request.
	 * @throws IOException
	 */
	public static String postRequest(final URL url, final Map<String, String> postMap) throws IOException {
		OutputStreamWriter wr = null;
		BufferedReader br = null;
		final StringBuilder returnBuilder = new StringBuilder();
		try {
			final StringBuilder dataBuilder = new StringBuilder();
			boolean first = true;
			for(final String key : postMap.keySet()) {
				if(!first) {
					dataBuilder.append('&');
				}
				dataBuilder.append(URLEncoder.encode(key, "UTF-8"));
				dataBuilder.append('=');
				dataBuilder.append(URLEncoder.encode(postMap.get(key), "UTF-8"));
				if(first) {first = false;}
			}

			final String data = dataBuilder.toString();

			System.out.println("Post Request: ".concat(data));

			final URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			while((line = br.readLine()) != null) {
				returnBuilder.append(line);
				returnBuilder.append(ln);
			}
			wr.close();
			br.close();
		} catch ( final IOException e) {
			e.printStackTrace();
		} finally {
			if(wr != null) {
				wr.close();
			}
			if(br != null) {
				br.close();
			}
		}
		return returnBuilder.toString();
	}
}
