/*******************************************************************************
* Copyright 2016 HybridHacker.net
*
* Licensed under the GNU Public License, Version 3.0 (the "License");
* you may not use this file except in compliance with the License.				
* You may obtain a copy of the License at
*
*     https://www.gnu.org/licenses/gpl.html
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.		
********************************************************************************/

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
