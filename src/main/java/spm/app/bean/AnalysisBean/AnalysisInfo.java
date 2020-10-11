package spm.app.bean.AnalysisBean;

import org.springframework.stereotype.Component;

@Component
public class AnalysisInfo {
    //文本分析的主键
    private String key;
    //文本分析的开始时间
    private String time;
    //文本分析的时长
    private String duration;
    //文本的单词总数
    private Long wordCount;
    //文本的字数
    private Long total;
    //文本内容的情感基调
    private String emotion;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
}
