package global.genesis.infra.exception;

public class CalculateFinancialValueException extends RuntimeException {

	private static final long serialVersionUID = 3446761800983618460L;

	public CalculateFinancialValueException(Exception e) {
		super(e);
	}

}
