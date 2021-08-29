package global.genesis.cross;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CryptoWalletHttpClient {

	<T> T call(String url, Class<T> clazz) throws URISyntaxException, IOException, InterruptedException;

}
