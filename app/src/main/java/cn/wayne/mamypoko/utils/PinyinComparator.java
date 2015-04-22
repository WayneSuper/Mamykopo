package cn.wayne.mamypoko.utils;

import java.util.Comparator;

import cn.wayne.mamypoko.mode.found.model.Audio;

/**
 * Created by Pollux on 2015/4/22.
 * //
 */
public class PinyinComparator implements Comparator<Audio> {

    public int compare(Audio o1, Audio o2) {
        //������Ҫ��������ListView��������ݸ���ABCDEFG...������
        if (o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
