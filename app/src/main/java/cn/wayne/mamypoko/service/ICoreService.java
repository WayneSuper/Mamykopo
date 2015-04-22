package cn.wayne.mamypoko.service;

import java.util.List;

import cn.wayne.mamypoko.mode.found.model.Audio;

/**
 * Created by Pollux on 2015/4/22.
 * //
 */
public interface ICoreService {

    void play();

    void pause();

    void next();

    void previous();

    void setAudioList(List<Audio> audios);

    void setAudio(Audio audio);

    Audio getAudio();

    boolean isPlaying();

    int getDuration();
}
