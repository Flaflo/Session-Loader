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