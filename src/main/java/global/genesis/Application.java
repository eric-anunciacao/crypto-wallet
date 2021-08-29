package global.genesis;

import java.util.logging.Level;
import java.util.logging.Logger;

import global.genesis.cross.CryptoWalletHttpClient;
import global.genesis.service.CoinCapClient;
import global.genesis.usecase.CalculateFinancialValueOfWalletUseCase;

public class Application {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		var cryptoWalletHttpClient = new CryptoWalletHttpClient();
		var coinCapClient = new CoinCapClient(cryptoWalletHttpClient);
		var performance = new CalculateFinancialValueOfWalletUseCase(coinCapClient).execute();
		LOGGER.log(Level.INFO, "{0}", performance);
	}
}
