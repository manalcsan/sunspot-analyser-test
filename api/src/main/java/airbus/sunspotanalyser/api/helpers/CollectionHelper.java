package airbus.sunspotanalyser.api.helpers;

import java.util.Collection;
import java.util.stream.Collectors;

public class CollectionHelper {

	/**
	 * Returns a list of values as a string.
	 * @param values - the values
	 * @return the values as a concatenated string
	 */
	public static String join(Collection<String> values) {
		return values.stream().collect(Collectors.joining("|"));
	}

}
