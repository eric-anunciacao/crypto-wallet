package global.genesis.infra.exception;

public class WalletException extends RuntimeException {

	private static final long serialVersionUID = 649808498981664197L;

	public WalletException(String message) {
		super(message);
	}

}
