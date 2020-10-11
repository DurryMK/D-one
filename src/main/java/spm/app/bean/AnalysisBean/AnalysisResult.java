package spm.app.bean.AnalysisBean;

import org.springframework.stereotype.Component;

@Component
public class AnalysisResult {
    //文本的主键
    private String key;
    //查询的目标单词
    private String target;
    //单词的数量
    private Long wordCount;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }


}
