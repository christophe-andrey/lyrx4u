package net.andrey.lyrx4u.ext.metrolyrics;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.andrey.lyrx4u.ext.TechnicalException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public abstract class AbstractRequest {

	private final String artist;
	private final String songTitle;

	protected AbstractRequest(String artist, String songTitle) {
		this.artist = artist;
		this.songTitle = songTitle;
	}

	protected abstract String getCommand();

	protected abstract String getMethod();

	protected abstract void processResponse(String jsonResponse);

	protected List<String> getUrlPath() {
		List<String> pathElements = new ArrayList<>();
		pathElements.add(RequestParameters.VERSION);
		pathElements.add(getMethod());
		pathElements.add(getCommand());
		pathElements.add("artist");
		pathElements.add(getUriEncoded(artist));
		pathElements.add(getSongTitleParameterName());
		pathElements.add(getUriEncoded(songTitle));

		pathElements.add("X-API-KEY");
		pathElements.add(getUriEncoded(RequestParameters.API_KEY));
		return pathElements;
	}

	protected abstract String getSongTitleParameterName();

	/**
	 * A hack, that leaves untouched all other characters that shoul be replaced
	 * as well. Experience will show whether this method is sufficient or needs
	 * to be generalized to other characters.
	 * 
	 * @param s
	 * @return
	 */
	private String getUriEncoded(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("/", "%2F");
		} catch (UnsupportedEncodingException uee) {
			throw new TechnicalException(uee);
		}
	}

	protected final URL getURL() {

		String urlPathString = "";
		for (String elt : getUrlPath())
			urlPathString += "/" + elt;
		try {
			return new URL(RequestParameters.PROTOCOL, RequestParameters.HOST,
					urlPathString);
		} catch (MalformedURLException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * Implementation inspired from
	 * http://svn.apache.org/viewvc/httpcomponents/oac
	 * .hc3x/trunk/src/examples/TrivialApp.java?view=markup
	 * 
	 * @param url
	 * @return
	 */
	public final static String executeHttpGetRequest(URL url) {
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url.toString());
		get.setFollowRedirects(true);
		// execute method and handle any error responses.
		// We currently don't handle such error responses.
		try {
			client.executeMethod(get);
			String responseBody = get.getResponseBodyAsString();
			return responseBody;
		} catch (IOException e) {
			throw new TechnicalException(e);
		} finally {
			get.releaseConnection();
		}
	}

	public final void execute() {
		String response = executeHttpGetRequest(getURL());
		processResponse(response);
	}
}
