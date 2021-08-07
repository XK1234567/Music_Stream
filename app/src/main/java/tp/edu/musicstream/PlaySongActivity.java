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

    private ImageButton buttonHome;
    private ImageButton buttonSearch;
    private ImageButton buttonPlaylist;

    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private ImageButton btnPlaySong = null;
    SeekBar seekBar;
    Handler handler = new Handler();
    private SongCollection songCollection = new SongCollection();
    private SongCollection originalSongCollection = new SongCollection();

    List<Song> shuffleList = Arrays.asList(songCollection.songs);

    ImageButton btnRepeat;
    Boolean repeatFlag = false;
    ImageButton btnShuffle;
    Boolean shuffleFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        /*buttonHome = (ImageButton) findViewById(R.id.TaskbarHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
        buttonSearch = (ImageButton) findViewById(R.id.TaskbarSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });
        buttonPlaylist = (ImageButton) findViewById(R.id.TaskbarPlaylist);
        buttonPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlaylist();
            }
        });*/

        btnPlaySong = findViewById(R.id.btnPlaySong);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        Log.d("temasek", "Retrieved position is: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(player.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    player.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnRepeat = findViewById(R.id.btnRepeat);
        btnShuffle = findViewById(R.id.btnShuffle);
    }

    public void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openSearch() {
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }
    public void openPlaylist() {
        Intent intent = new Intent(this, PlaylistPage.class);
        startActivity(intent);
    }


    public class UpdateSeekBar implements Runnable {
        @Override
        public void run() {
            seekBar.setProgress(player.getCurrentPosition());

            handler.postDelayed(this, 100);
        }
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

            btnPlaySong.setImageResource(R.drawable.ic_pausebtn);
            setTitle(title);
            UpdateSeekBar updateSeekBar = new UpdateSeekBar();
            handler.post(updateSeekBar);
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
                }/*else{
                    btnPlaySong.setImageResource(R.drawable.ic_playbtn);
                }*/

                if (repeatFlag)
                {
                    playOrPauseMusic(null);
                    currentIndex = songCollection.getNextSong(currentIndex);
                    displaySongBasedOnIndex(currentIndex);
                    playSong(fileLink);
                }/*else{
                    Toast.makeText(getBaseContext(), "The song had ended and the onCompleteListener is activated\n" +
                            "The button text is changed to 'PLAY", Toast.LENGTH_LONG).show();
                    btnPlaySong.setImageResource(R.drawable.ic_playbtn);
                }*/

                currentIndex = songCollection.getNextSong(currentIndex);
                displaySongBasedOnIndex(currentIndex);
                playSong(fileLink);
            }
        });
    }

    public void playNext(View view)
    {
        currentIndex = songCollection.getNextSong(currentIndex);
        Toast.makeText(this, "After clicking playNext,\nthe current index of this song\n" +
                "in the SongCollection array is now: " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playNext, the index is now: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void playPrev(View view)
    {
        currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious,\nthe current index of this song\n" +
                "in the SongCollection array is now: " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playPrevious, the index is now: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        player.release();
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
