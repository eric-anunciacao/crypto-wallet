package global.genesis.cross;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import global.genesis.infra.exception.WalletException;

public class CSVUtils {

	private CSVUtils() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> load(String filename, Class<T> clazz) {
		try {
			var reader = new FileReader(filename);
			return new CsvToBeanBuilder(reader).withType(clazz).build().parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			throw new WalletException("Wallet file not found!");
		}
	}

}
