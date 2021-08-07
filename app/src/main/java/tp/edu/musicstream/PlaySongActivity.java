package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlaySongActivity extends AppCompatActivity {

    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private ImageButton btnPlaySong = null;
    private SongCollection songCollection = new SongCollection();
    private SongCollection originalSongCollection = new SongCollection();

    SeekBar seekbar;
    Handler handler = new Handler();

    List<Song> shuffleList = Arrays.asList(songCollection.songs);

    ImageButton btnRepeat;
    Boolean repeatFlag = false;
    ImageButton btnShuffle;
    Boolean shuffleFlag = false;

    ImageButton btnHome;
    ImageButton btnSearch;
    ImageButton btnPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        btnHome = findViewById(R.id.taskbarHome);
        btnHome.setOnClickListener(view -> openHome());

        btnSearch = findViewById(R.id.taskbarSearch);
        btnSearch.setOnClickListener(view -> openSearch());

        btnPlaylist = findViewById(R.id.taskbarPlaylist);
        btnPlaylist.setOnClickListener(view -> openPlaylists());

        btnPlaySong = findViewById(R.id.btnPlaySong);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        Log.d("temasek", "Retrieved position is: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);

        btnRepeat = findViewById(R.id.btnRepeat);
        btnShuffle = findViewById(R.id.btnShuffle);

        seekbar = findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (player != null && player.isPlaying()){
                    player.seekTo(seekBar.getProgress());
                }
            }
        });
    }

    Runnable p_bar = new Runnable() {
        @Override
        public void run() {
            if (player != null && player.isPlaying()){
                seekbar.setProgress(player.getCurrentPosition());
                seekbar.setMax(player.getDuration());
            }
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 100);
        }
    };

    public void openHome()
    {
        if(player != null) {
            handler.removeCallbacks(p_bar);
            player.stop();
            player.release();
            player = null;
        }
        Intent intent = new Intent(PlaySongActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void openSearch()
    {
        if(player != null) {
            handler.removeCallbacks(p_bar);
            player.stop();
            player.release();
            player = null;
        }
        Intent intent = new Intent(PlaySongActivity.this, SearchPage.class);
        startActivity(intent);
    }

    public void openPlaylists()
    {
        if(player != null) {
            handler.removeCallbacks(p_bar);
            player.stop();
            player.release();
            player = null;
        }
        Intent intent = new Intent(PlaySongActivity.this, PlaylistPage.class);
        startActivity(intent);
    }

    public void displaySongBasedOnIndex(int selectedIndex)
    {
        Song song = songCollection.getCurrentSong(currentIndex);
        title = song.getTitle();
        artiste = song.getArtiste();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();

        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        TextView txtArtiste = findViewById(R.id.txtArtist);
        txtArtiste.setText(artiste);

        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
    }

    public void playSong(String songUrl)
    {
        try{
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            gracefullyStopsWhenMusicEnds();

            handler.postDelayed(p_bar, 100);

            btnPlaySong.setImageResource(R.drawable.ic_pausebtn);
            setTitle(title);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void playOrPauseMusic(View view)
    {
        if(player.isPlaying())
        {
            player.pause();
            btnPlaySong.setImageResource(R.drawable.ic_playbtn);

            seekbar.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 100);
        }
        else
        {
            player.start();
            btnPlaySong.setImageResource(R.drawable.ic_pausebtn);
        }
    }

    private void gracefullyStopsWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                if (shuffleFlag)
                {
                    Collections.shuffle(shuffleList);
                    shuffleList.toArray(songCollection.songs);
                    playOrPauseMusic(null);
                    currentIndex = songCollection.getNextSong(currentIndex);
                    displaySongBasedOnIndex(currentIndex);
                    playSong(fileLink);
                }

                if (repeatFlag)
                {
                    playOrPauseMusic(null);
                    currentIndex = songCollection.getNextSong(currentIndex);
                    displaySongBasedOnIndex(currentIndex);
                    playSong(fileLink);
                }

                currentIndex = songCollection.getNextSong(currentIndex);
                displaySongBasedOnIndex(currentIndex);
                playSong(fileLink);
            }
        });
    }

    public void playNext(View view)
    {
        /*currentIndex = songCollection.getNextSong(currentIndex);
        Toast.makeText(this, "After clicking playNext,\nthe current index of this song\n" +
                "in the SongCollection array is now: " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playNext, the index is now: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);*/

        if (repeatFlag){
            currentIndex = songCollection.getNextSongRepeat(currentIndex);
            displaySongBasedOnIndex(currentIndex);
            playSong(fileLink);
        }
        else{
            currentIndex = songCollection.getNextSong(currentIndex);
            Toast.makeText(this, "After clicking playNext,\nthe current index of this song\n" +
                    "in the SongCollection array is now: " + currentIndex, Toast.LENGTH_LONG).show();
            Log.d("temasek", "After playNext, the index is now: " + currentIndex);
            displaySongBasedOnIndex(currentIndex);
            playSong(fileLink);
        }
    }

    public void playPrev(View view)
    {
        /*currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious,\nthe current index of this song\n" +
                "in the SongCollection array is now: " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playPrevious, the index is now: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);*/

        if (repeatFlag){
            currentIndex = songCollection.getPrevSongRepeat(currentIndex);
            displaySongBasedOnIndex(currentIndex);
            playSong(fileLink);
        }
        else{
            currentIndex = songCollection.getPrevSong(currentIndex);
            Toast.makeText(this, "After clicking playPrevious,\nthe current index of this song\n" +
                    "in the SongCollection array is now: " + currentIndex, Toast.LENGTH_LONG).show();
            Log.d("temasek", "After playPrevious, the index is now: " + currentIndex);
            displaySongBasedOnIndex(currentIndex);
            playSong(fileLink);
        }
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        if(player != null) {
            handler.removeCallbacks(p_bar);
            player.stop();
            player.release();
            player = null;
        }
    }

    public void repeatSong(View view) {
        if (repeatFlag)
        {
            btnRepeat.setImageResource(R.drawable.ic_repeat_off);
        }
        else
        {
            btnRepeat.setImageResource(R.drawable.ic_repeat_on);
        }
        repeatFlag = !repeatFlag;

    }

    public void shuffleSong(View view) {
        if (shuffleFlag)
        {
            btnShuffle.setImageResource(R.drawable.ic_shuffle_off);
            songCollection = new SongCollection();
        }
        else
        {
            btnShuffle.setImageResource(R.drawable.ic_shuffle_on);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.songs);
        }
        shuffleFlag = !shuffleFlag;
    }

}
