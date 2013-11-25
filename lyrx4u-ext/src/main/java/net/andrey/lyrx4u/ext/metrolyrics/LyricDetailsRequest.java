package net.andrey.lyrx4u.ext.metrolyrics;

import java.util.List;

import net.sf.json.JSONObject;

public class LyricDetailsRequest extends AbstractRequest {
	public LyricDetailsRequest(String artist, String songTitle, Integer lyricId) {
		super(artist, songTitle);
		this.lyricId = lyricId;
	}

	private String lyric;
	private final Integer lyricId;

	/**
	 * @return Requested lyric. Null if not found on the remote site.
	 */
	public final String getLyric() {
		return lyric;
	}

	@Override
	protected String getCommand() {
		return "lyricdetails";
	}

	@Override
	protected String getMethod() {
		return "get";
	}

	@Override
	protected List<String> getUrlPath() {
		List<String> path = super.getUrlPath();
		path.add("lyricid");
		path.add(lyricId.toString());
		path.add("deviceid");
		path.add(RequestParameters.DEVICE_ID);
		return path;
	}

	@Override
	protected String getSongTitleParameterName() {
		return "title";
	}

	@Override
	protected void processResponse(String jsonResponse) {
		JSONObject jsonObject = JSONObject.fromObject(jsonResponse);
		// Here, we use a custom-declared bean to extract the attributes that interest us, out of the JSON object. 
		LyricDetailsResponse response = (LyricDetailsResponse) JSONObject
				.toBean(jsonObject, LyricDetailsResponse.class);
		if (response.getContent_status() == 1) {
			lyric = response.getSong();
			lyric = lyric.replaceAll("\\r", "\n");
		}
	}
}
