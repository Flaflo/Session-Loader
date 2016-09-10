package net.hybridhacker.sloader.utils;

/**
 * Utility to get the current operating systems and paths
 * @author Flaflo
 *
 */
public final class OS {
	
	private static final String OS = System.getProperty("os.name").toLowerCase();
	private static final boolean WINDOWS = OS.indexOf("win") >= 0;
	private static final boolean MAC = OS.indexOf("mac") >= 0;
	private static final boolean LINUX = (OS.indexOf("nix") >= 0) || (OS.indexOf("nux") >= 0)
			|| (OS.indexOf("aix") > 0);
	private static final boolean SOLARIS = OS.indexOf("sunos") >= 0;

	/**
	 * @return true if current os is windows
	 */
	public static boolean isWindows() {
		return WINDOWS;
	}

	/**
	 * @return true if current os is mac
	 */
	public static boolean isMac() {
		return MAC;
	}

	/**
	 * @return true if current os is linux
	 */
	public static boolean isLinux() {
		return LINUX;
	}

	/**
	 * @return true if current os is solaris
	 */
	public static boolean isSolaris() {
		return SOLARIS;
	}

	/**
	 * @return tje current appdata path
	 */
	public static String getAppData() {
		return isWindows() ? System.getenv("AppData") : System.getProperty("user.home");
	}
}
