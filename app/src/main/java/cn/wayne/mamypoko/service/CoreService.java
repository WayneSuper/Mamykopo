package cn.wayne.mamypoko.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;
import java.util.List;

import cn.wayne.mamypoko.mode.found.model.Audio;

public class CoreService extends Service implements ICoreService, MediaPlayer.OnCompletionListener
        , MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener {
    private MBind mBind = new MBind();
    private MediaPlayer mMediaPlayer;
    private List<Audio> mAudioList;
    private Audio mCurrentAudio;
    private int seek;
    private int currentIndex;
    private int mListSize;
    private boolean isPlaying;


    @Override
    public void onCreate() {
        mThread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onCreate();
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        } else {
            mMediaPlayer.reset();
        }
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnSeekCompleteListener(this);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (mBind != null) return mBind;
        return null;
    }

    @Override
    public void play() {
        isPlaying = true;
        running = true;
        if (mCurrentAudio == null) {
            mCurrentAudio = mAudioList.get(0);
            if (mCurrentAudio == null) {
                return;
            }
        }
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.reset();
        }
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(mCurrentAudio.getPath());
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = new MediaPlayer();
            try {
                mMediaPlayer.setDataSource(mCurrentAudio.getPath());
                mMediaPlayer.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }

    @Override
    public void pause() {
        seek = mMediaPlayer.getCurrentPosition();
        mMediaPlayer.pause();
        isPlaying = false;
    }

    @Override
    public void next() {
        if (currentIndex <= mListSize - 2) {
            mCurrentAudio = mAudioList.get(++currentIndex);
        } else {
            mCurrentAudio = mAudioList.get(0);
        }
        seek = 0;
        play();
    }

    @Override
    public void previous() {
        if (currentIndex == 0) {
            currentIndex = mListSize - 1;
            mCurrentAudio = mAudioList.get(mListSize - 1);
        } else {
            currentIndex--;
            mCurrentAudio = mAudioList.get(currentIndex);
        }
        seek = 0;
        play();
    }

    @Override
    public void setAudioList(List<Audio> audios) {
        if (mAudioList != null) {
            mAudioList.clear();
            mAudioList.addAll(audios);
        } else {
            mAudioList = audios;
        }
        currentIndex = 0;
        seek = 0;

        mListSize = mAudioList.size();


    }

    @Override
    public void setAudio(Audio audio) {
        this.mCurrentAudio = audio;
        seek = 0;
        play();
    }

    @Override
    public Audio getAudio() {
        return mCurrentAudio;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        } else {
            return 0;
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        next();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        if (seek != 0) {
            mp.seekTo(seek);
            seek = 0;
        }
        isPlaying = true;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        this.seek = mp.getCurrentPosition();
        if(mCurrentAudio != null)
                play();
    }


    public class MBind extends Binder {
        public CoreService getService() {
            return CoreService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        running = false;
    }

    OnSeekChangeListener mSeekChangeListener;

    private boolean running = true;
    private Thread mThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (running) {
                if (mSeekChangeListener != null && mMediaPlayer.getDuration() != 0 && isPlaying)  {
                    if (mMediaPlayer != null)
                        mSeekChangeListener.onChanged(mMediaPlayer.getDuration(), mMediaPlayer.getCurrentPosition());
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });


    public void setSeekChangeListener(OnSeekChangeListener listener) {
        this.mSeekChangeListener = listener;
    }

    public interface OnSeekChangeListener {
        void onChanged(int totol, int seek);
    }

}
