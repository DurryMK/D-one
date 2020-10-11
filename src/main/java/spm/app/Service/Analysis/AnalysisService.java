package spm.app.Service.Analysis;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spm.app.bean.AnalysisBean.SingleLineInfo;
import spm.app.dao.redis.RedisUtils;

import java.io.*;
import java.util.HashSet;
import java.util.List;

@Service
public class AnalysisService {

    @Qualifier("redisUtils")
    @Autowired
    private RedisUtils rut;

    /**
     * 读取文件内容并解析
     */
    public int executor(File file, String key, Long time, AnalysisObserver aos) {
        System.out.println("已开始解析  key:" + key);
        //单行数据的信息
        SingleLineInfo sli = new SingleLineInfo();

        //总词数
        int wordCount = 0;

        //解析出文件中每一行内容
        HashSet<String> hs = readFileContent(file);
        System.out.println("共" + hs.size() + "行");
        //总行数
        double totalLines = hs.size();
        //已经解析的行数
        double doneLines = 0.0;
        //解析的进度
        double process = 0.0;
        for (String line : hs) {
            //对每一行进行解析 获取行信息
            sli = doAnalysis(key, line, time, sli);
            //累加单词数
            wordCount += sli.getWordNum();
            doneLines++;
            //解析的进度
            process = doneLines / totalLines;
        }
        return wordCount;
    }

    /**
     * 读取文件内容
     *
     * @return 返回一个包含了文件中所有行的Set 每行长度大于0
     */
    private HashSet<String> readFileContent(File f) {
        //存放每一行
        HashSet<String> lineSet = new HashSet<>();
        //文件输入流
        FileInputStream fs;
        //缓存读取器
        BufferedReader br;
        try {
            fs = new FileInputStream(f);
            br = new BufferedReader(new InputStreamReader(fs));
            //读取的每一行
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    //长度大于0的行存入Set
                    lineSet.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineSet;
    }

    /**
     * 对文件内容进行分词解析 并存入Redis
     *
     * @param key  主题名
     * @param line 被解析的字符串
     * @param time 主题的过期时间
     * @return 返回一行文件的信息
     */
    private SingleLineInfo doAnalysis(String key, String line, Long time, SingleLineInfo sil) {
        //保存该行的字节数
        sil.setbLength((long) line.getBytes().length);

        //保存该行的字数
        sil.setNum((long) line.replace(" ", "").split("").length);

        //对一行内容进行分解 每个单词保存在list中
        List<Word> wordList = WordSegmenter.seg(line);

        //保存该行的单词数
        sil.setWordNum((long) wordList.size());

        //单词数大于0的  保存到redis
        if (wordList.size() > 0) {
            rut.putHashWithList(key, wordList, time);
        }
        return sil;
    }
}
