package net.hybridhacker.sloader.loader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.hybridhacker.sloader.utils.Directories;

/**
 * Utility to add a user profile to the launcher json
 * @author Flaflo
 *
 */
public final class AltInjector {
	
	/**
	 * Creates and injects a new user to the launcher_profiles.json
	 * @param displayName the user display name
	 * @param uuid the user id
	 * @param sessionToken the session token
	 * @param clientToken the client token
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static void inject(String displayName, String uuid, String sessionToken, String clientToken)
			throws IOException, ParseException {
		
		final JSONParser jsonParser = new JSONParser();
		final Object launcherProfile = jsonParser.parse(new FileReader(Directories.getLauncherFilePath()));

		final JSONObject launcherJson = (JSONObject) launcherProfile;
		final JSONObject profiles = (JSONObject) launcherJson.get("profiles");
		final JSONObject authenticationDatabase = (JSONObject) launcherJson.get("authenticationDatabase");

		final JSONObject launcherVersion = (JSONObject) launcherJson.get("launcherVersion");
		final JSONObject altContainer = new JSONObject();

		altContainer.put("displayName", displayName);
		altContainer.put("accessToken", sessionToken);
		altContainer.put("userid", "SessionLoader");
		altContainer.put("uuid", uuid);
		altContainer.put("username", "SessionLoader");
		authenticationDatabase.put(uuid.replace("-", ""), altContainer);

		final JSONObject launcherJsonUpdate = new JSONObject();
		launcherJsonUpdate.put("profiles", profiles);

		launcherJsonUpdate.put("clientToken", clientToken);
		launcherJsonUpdate.put("selectedUser", uuid.replace("-", ""));
		launcherJsonUpdate.put("authenticationDatabase", authenticationDatabase);
		launcherJsonUpdate.put("launcherVersion", launcherVersion);

		final FileWriter file = new FileWriter(Directories.getLauncherFilePath());
		file.write(launcherJsonUpdate.toJSONString());
		file.flush();
		file.close();
	}
}
