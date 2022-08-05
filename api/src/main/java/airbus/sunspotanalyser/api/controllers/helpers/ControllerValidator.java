package airbus.sunspotanalyser.api.controllers.helpers;

import airbus.sunspotanalyser.api.exceptions.ConflictIdException;

public class ControllerValidator {

	/**
	 * Checks the path id is the same than the dto id.
	 * @param pathId - the path id
	 * @param dtoId - the dto id
	 */
	public static void validateId(Long pathId, Long dtoId) {
		if (!pathId.equals(dtoId)) {
			throw new ConflictIdException("path id and dto id are not equal");
		}
	}

	/**
	 * Checks the path id is the same than the dto id.
	 * @param pathId - the path id
	 * @param dtoId - the dto id
	 */
	public static void validateId(String pathId, String dtoId) {
		if (!pathId.equals(dtoId)) {
			throw new ConflictIdException("path id and dto id are not equal");
		}
	}

}
