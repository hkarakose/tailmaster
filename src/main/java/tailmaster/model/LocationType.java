package tailmaster.model;

/**
 * User: Halil KARAKOSE
 * Date: Jan 20, 2009
 * Time: 11:56:29 PM
 */
public enum LocationType {
	LOCAL(1), REMOTE(2);

	private int fileTypeId;

	LocationType(int fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	public int getLocationTypeId() {
		return fileTypeId;
	}

	public static LocationType valueOf(int locationTypeId) {
		for (LocationType type : LocationType.values()) {
			if (type.getLocationTypeId() == locationTypeId) {
				return type;
			}
		}
		return null;
	}
}
