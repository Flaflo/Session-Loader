package net.hybridhacker.sloader.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Utility to resolve an username to an uuid
 * @author Flaflo
 *
 */
public final class UUIDResolver {
	/**
	 * Resolves a Name to UUID
	 * @param username the username to resolve
	 * @return the resolved uuid
	 * @throws IOException
	 */
	public static final String resolveUUID(String username) throws IOException {
		return addUUIDDashes(new BufferedReader(new InputStreamReader(new URL("https://us.mc-api.net/v3/uuid/" + username).openStream())).readLine());
	}

	/**
	 * Adds dashes to a uuid without them
	 * returns the original string when it already contains one or more dashes
	 * @param idNoDashes the id without dashes
	 * @return the id with dashes
	 */
	public static String addUUIDDashes(String idNoDashes) {
		if (idNoDashes.contains("-"))
			return idNoDashes;
		
		return new StringBuffer(idNoDashes).insert(20, '-').insert(16, '-').insert(12, '-').insert(8, '-').toString();
	}
}