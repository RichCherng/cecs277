package Assignment4;

import java.util.Formatter;
/**
 * Song - Representing detail of a song
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Song implements Comparable<Song>{
	/**
	 * mTitle - name of the song
	 * mArtist - name of the artist
	 * mAlbum - name of the album
	 */
	private String mTitle,mArtist,mAlbum;
	/**
	 * mRating - rating of the song
	 */
	int mRating;
	/**
	 * Constructor - construct Song
	 * @param title name of the title
	 * @param artist name of the artist
	 * @param album name of the album
	 * @param rating the rating of the song
	 */
	public Song(String title, String artist,String album,int rating){
		mTitle = title;
		mArtist = artist;
		mAlbum = album;
		mRating = rating;
	}
	/**
	 * Accessor method
	 * @return name of the title
	 */
	public String getTitle(){
		return mTitle;
	}
	/**
	 * Accessor method
	 * @return name of the artist
	 */
	public String getArtist(){
		return mArtist;
	}
	/**
	 * Accessor method
	 * @return name of the album
	 */
	public String getAlbum(){
		return mAlbum;
	}
	/**
	 * Accessor method
	 * @return rating of the song
	 */
	public int getRating(){
		return mRating;
	}

	@Override
	public int compareTo(Song s) {
		//higher value return negative cuz they go first
		if(s.mRating == mRating){
			//return mTitle.compareTo(s.mTitle);
			return mTitle.compareToIgnoreCase(s.mTitle);
		}
		return mRating < s.mRating? 1:-1;
	}
	
	@Override
	public String toString(){
		String template = "%-50s %-25s %-30s %5d ";
		StringBuilder stringBuilder = new StringBuilder();
		Formatter formatter = new Formatter(stringBuilder);
		formatter.format(template, mTitle, mArtist,mAlbum,mRating);
		formatter.close();
		return stringBuilder.toString();
		
	}
	
	
}
