package tp.edu.musicstream;

public class SongCollection {
    public Song songs[] = new Song[7];

    public SongCollection()
    {
        Song theWayYouLookTonight = new Song("S1001",
                "The Way You Look Tonight",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.michael_buble_collection);

        Song billieJean = new Song("S1002",
                "Billie Jean",
                "Michael Jackson",
                "https://p.scdn.co/mp3-preview/14a1ddedf05a15ad0ac11ce28b40ea1a15fabd20?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.billie_jean);

        Song one = new Song("S1003",
                "One",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/daa8679253ba20620db6e1db9c88edfcf1f4069f?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.one);

        Song butter = new Song("S1004",
                "Butter",
                "BTS",
                "https://p.scdn.co/mp3-preview/edf24f427483d886b640c5ed9944f9291e0976fc?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.butter);

        Song liveWhileWereYoung = new Song("S1005",
                "Live While We're Young",
                "One Direction",
                "https://p.scdn.co/mp3-preview/2d7e502cfbccd748e5c4b0d032e775ecca4ed721?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.live_while_were_young);

        Song shapeOfYou = new Song("S1006",
                "Shape Of You",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/84462d8e1e4d0f9e5ccd06f0da390f65843774a2?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.shape_of_you);

        Song onTheGround = new Song("S1007",
                "On The Ground",
                "ROSÉ",
                "https://p.scdn.co/mp3-preview/b34f2ba3258da59b4a205cdd0e413a4481aeacc3?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.on_the_ground);

        songs[0] = theWayYouLookTonight;
        songs[1] = billieJean;
        songs[2] = one;
        songs[3] = butter;
        songs[4] = liveWhileWereYoung;
        songs[5] = shapeOfYou;
        songs[6] = onTheGround;
    }

    public Song getCurrentSong(int currentSongId)
    {

        return songs[currentSongId];
    }

    public int searchSongById(String id)
    {
        for(int index = 0; index < songs.length; index++)
        {
            Song tempSong = songs[index];
            if(tempSong.getId().equals(id))
            {
                return index;
            }
        }
        return -1;
    }

    public int getNextSong(int currentSongIndex)
    {
        if(currentSongIndex >= songs.length-1)
        {
            return currentSongIndex;
        }
        else
        {
            return currentSongIndex + 1;
        }
    }

    public int getPrevSong(int currentSongIndex)
    {
        if(currentSongIndex <= 0)
        {
            return currentSongIndex;
        }
        else
        {
            return currentSongIndex - 1;
        }
    }
}
