package global.genesis;

import java.util.logging.Level;
import java.util.logging.Logger;

import global.genesis.cross.CryptoWalletHttpClientImpl;
import global.genesis.service.CoinCapClientImpl;
import global.genesis.usecase.CalculateFinancialValueOfWalletUseCase;

public class Application {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		var cryptoWalletHttpClient = new CryptoWalletHttpClientImpl();
		var coinCapClient = new CoinCapClientImpl(cryptoWalletHttpClient);
		var performance = new CalculateFinancialValueOfWalletUseCase(coinCapClient).execute();
		LOGGER.log(Level.INFO, "{0}", performance);
	}
}
