package spm.app.bean.AnalysisBean;

/**
 * 解析的每一行数据的信息
 */
public class SingleLineInfo {
    private Long bLength;//一行的字节数
    private Long num;//一行的字数
    private Long wordNum;//一行的单词数

    public Long getbLength() {
        return bLength;
    }

    public void setbLength(Long bLength) {
        this.bLength = bLength;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        num = num;
    }

    public Long getWordNum() {
        return wordNum;
    }

    public void setWordNum(Long wordNum) {
        this.wordNum = wordNum;
    }

    @Override
    public String toString() {
        return "SingleLineInfo{" +
                "bLength=" + bLength +
                ", Num=" + num +
                ", wordNum=" + wordNum +
                '}';
    }
}
