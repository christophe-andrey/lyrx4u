package net.andrey.lyrx4u.ext.metrolyrics;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.andrey.lyrx4u.ext.TechnicalException;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;

public class ArtistSongRequest extends AbstractRequest {
	private Integer lyricId;

	public ArtistSongRequest(String artist, String songTitle) {
		super(artist, songTitle);
	}

	@Override
	protected String getCommand() {
		return "artistsong";
	}

	@Override
	protected String getMethod() {
		return "search";
	}

	@Override
	protected String getSongTitleParameterName() {
		return "song";
	}

	@Override
	protected void processResponse(String jsonResponse) {
		JSONObject jsonObject = JSONObject.fromObject(jsonResponse);
		Object bean = JSONObject.toBean(jsonObject);
		try {
			if (PropertyUtils.isReadable(bean, "items")) {
				List<DynaBean> items = (List<DynaBean>) PropertyUtils
						.getProperty(bean, "items");
				for (int i = 0; i < items.size() && lyricId == null; i++)
					if (!items.isEmpty()) {
						DynaBean firstSong = items.get(i);
						if (PropertyUtils.isReadable(firstSong, "lyricid")
								&& PropertyUtils.isReadable(firstSong,
										"content_status")
								&& 1 == (Integer) PropertyUtils.getProperty(firstSong,
										"content_status"))
							lyricId = (Integer) PropertyUtils.getProperty(firstSong,
									"lyricid");
					}
			}
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			throw new TechnicalException(e);
		}
		// TODO Auto-generated method stub

	}

	/**
	 * @return Requested lyric. Null if not found on the remote site.
	 */
	public final Integer getLyricId() {
		return lyricId;
	}

}
