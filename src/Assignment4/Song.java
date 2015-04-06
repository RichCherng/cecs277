package Assignment4;

public class Song implements Comparable<Song>{
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

	@Override
	public int compareTo(Song s) {
		//higher value return negative cuz they go first
		if(s.mRating == mRating){
			return s.mTitle.compareTo(mTitle);
		}
		return mRating < s.mRating? 1:-1;
	}
	
	@Override
	public String toString(){
		return mTitle + " " + mArtist + " " + mAlbum + " " + mRating ;
	}
	
	
}
