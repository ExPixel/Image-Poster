package org.imageposter;

import java.util.Scanner;

import org.imageposter.imgur.ImgurAPI;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class ImgurTest {
	private static final String PROTECTED_RESOURCE_URL = "http://api.imgur.com/2/account.xml";

	public static void main(final String[] args) {
		final String apiKey = "3739eed9926a2fd6a22a216ce0948bdc0503ea516";
		final String apiSecret = "6da282be7a4de90734d2a599a604ca90";
		final OAuthService service = new ServiceBuilder().provider(ImgurAPI.class)
				.apiKey(apiKey).apiSecret(apiSecret).build();
		final Scanner in = new Scanner(System.in);

		System.out.println("=== ImgUr's OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		final Token requestToken = service.getRequestToken();
		System.out.println("Got the Request Token!");
		System.out.println();

		System.out.println("Now go and authorize Scribe here:");
		final String authorizationUrl = service.getAuthorizationUrl(requestToken);
		System.out.println(authorizationUrl);
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		final Verifier verifier = new Verifier(in.nextLine());
		System.out.println();

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		final Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: "
				+ accessToken + " )");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		final OAuthRequest request = new OAuthRequest(Verb.GET,
				PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		final Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getBody());

		System.out.println();
		System.out
				.println("Thats it man! Go and build something awesome with Scribe! :)");

		in.close();
	}



}