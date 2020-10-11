package spm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import spm.app.Service.Analysis.AnalysisObserver;
import spm.app.Service.Analysis.AnalysisService;
import spm.app.bean.AnalysisBean.AnalysisInfo;
import spm.app.bean.ResModel;
import spm.app.tools.MultipartFileToFile;

import java.io.*;

@Controller
public class AnalysisController {

    @Autowired
    private AnalysisService as;

    /**
     * 用于处理用户上传的文本分析
     */
    @RequestMapping(value = "/receiveAnalysis", method = RequestMethod.POST)
    public @ResponseBody
    ResModel
    analysis(@RequestParam("file") MultipartFile file, String token, AnalysisInfo asi, ResModel res) {
        //MultipartFile转换为File
        File f = MultipartFileToFile.execute(file);
        //主键名
        String key = token + f.getName() + System.currentTimeMillis();
        System.out.println("文件长度:" + file.getSize());
        //解析文件  设定文件 主题 过期时间(分钟) 观察者
        as.executor(f, key, 60L, new AnalysisObserver());
        return new ResModel();
    }
}
