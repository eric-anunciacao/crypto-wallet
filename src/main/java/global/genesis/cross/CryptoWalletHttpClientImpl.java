package global.genesis.cross;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;

public class CryptoWalletHttpClientImpl implements CryptoWalletHttpClient {

	private static final int TOO_MANY_REQUESTS = 429;

	@Override
	public <T> T call(String url, Class<T> clazz) throws URISyntaxException, IOException, InterruptedException {
		var response = call(url);
		return new Gson().fromJson(response.body(), clazz);
	}

	private HttpResponse<String> call(String url) throws URISyntaxException, IOException, InterruptedException {
		var request = HttpRequest.newBuilder()
								.uri(new URI(url))
								.header("Content-Type", "application/json")
								.GET()
								.timeout(Duration.of(10, SECONDS))
								.build();

		HttpResponse<String> response = null;
		do {
			response = HttpClient.newBuilder()
								.proxy(ProxySelector.getDefault())
								.build()
								.send(request, HttpResponse.BodyHandlers.ofString());
		} while (response == null || response.statusCode() == TOO_MANY_REQUESTS);
		
		return response;
	}
}
