package Assignment4;

public class Song {
	private String mTitle,mArtist,mAlbum;
	int mRating;
	
	public Song(String title, String artist,String album,int rating){
		mTitle = title;
		mArtist = artist;
		mAlbum = album;
		mRating = rating;
	}
	
	public String getTitle(){
		return mTitle;
	}
	
	public String getArtist(){
		return mArtist;
	}
	
	public String getAlbum(){
		return mAlbum;
	}
	
	public int getRating(){
		return mRating;
	}
}
