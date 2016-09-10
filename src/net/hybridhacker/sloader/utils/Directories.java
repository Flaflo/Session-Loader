package net.hybridhacker.sloader.utils;

/**
 * Utility to get needed directories
 * @author Flaflo
 *
 */
public final class Directories {
	
	/**
	 * @return the directory containing all minecraft files
	 */
	public static String getMCDataDir() {
		if ((OS.isWindows()) || (OS.isLinux()))
			return OS.getAppData() + "\\.minecraft";
		else if (OS.isMac())
			return OS.getAppData() + "/Library/Application Support/minecraft";
		
		return "";
	}

	/**
	 * @return the path to the launcher_profiles.json
	 */
	public static String getLauncherFilePath() {
		if ((OS.isMac()) || (OS.isLinux()))
			return getMCDataDir() + "/launcher_profiles.json";
		else if (OS.isWindows())
			return getMCDataDir() + "\\launcher_profiles.json";
		
		return "";
	}
}