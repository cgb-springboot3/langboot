package com.hkh.ai.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import com.hkh.ai.chain.llm.capabilities.generation.function.ChatFunctionObject;
import com.hkh.ai.chain.llm.capabilities.generation.function.FunctionChatService;
import com.hkh.ai.chain.llm.capabilities.generation.function.FunctionCompletionResult;
import com.hkh.ai.chain.llm.capabilities.generation.text.TextChatService;
import com.hkh.ai.domain.SysUser;
import com.hkh.ai.request.*;
import com.hkh.ai.service.CompletionService;
import com.hkh.ai.util.FunctionReflect;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CompletionServiceImpl implements CompletionService {

    private final TextChatService textChatService;
    private final FunctionChatService functionChatService;

    @Override
    public String summary(SysUser sysUser, CompletionSummaryRequest request) {
        String fullContent = "请将文本内容生成对应摘要： " + request.getContent() + "\n\n";
        if (StrUtil.isNotBlank(request.getPrompt())){
            fullContent += "具体要求如下：" + request.getPrompt();
        }
        String completionResult = textChatService.blockCompletion(fullContent);
        return completionResult;
    }

    @Override
    public String keyword(SysUser sysUser, CompletionKeywordRequest request) {
        String fullContent = "从下面文本中提取" +request.getKeywordNum() +"个"+request.getKeywordArea()+"领域关键词： " + request.getContent() + "\n\n每个关键词以英文分号;分隔";
        String completionResult = textChatService.blockCompletion(fullContent);
        return completionResult;
    }

    @Override
    public String translate(SysUser sysUser, CompletionTranslateRequest request) {
        String fullContent = "将下面文本翻译成" +request.getTargetLanguage() + ":" + request.getContent() ;
        String completionResult = textChatService.blockCompletion(fullContent);
        return completionResult;
    }

    @Override
    public String classic(SysUser sysUser, CompletionClassicRequest request) {
        String fullContent = "按照括号里的分类(" + request.getCategoryList() +" )，将以下文本进行分类：" +request.getContent() +  "\n\n依据相关性高低返回2~3个分类结果，并以;进行分隔";
        String completionResult = textChatService.blockCompletion(fullContent);
        return completionResult;
    }

    @Override
    public String security(SysUser sysUser, CompletionSecurityRequest request) {
        String fullContent = "判断以下文本是否包含政治、暴力、色情、毒品、恐怖、歧视等敏感内容：" + request.getContent() +  "\n\n，你只能回答2个字：安全或者危险";
        String completionResult = textChatService.blockCompletion(fullContent);
        return completionResult;
    }

    @Override
    public String function(SysUser sysUser, String content, List<ChatFunctionObject> functionObjectList) {
        List<FunctionCompletionResult> functions = functionChatService.functionCompletion(content, functionObjectList);
        String result = "";
        if (functions!=null && functions.size()>0){
            for (int i = 0; i < functions.size(); i++) {
                FunctionCompletionResult function = functions.get(i);
                String functionName = function.getName();
                JSONObject arguments = function.getArguments();
                String reflect = (String) FunctionReflect.reflect(functionName, arguments);
                result = result + reflect  + " ";
            }
        }
        return result;
    }

    @Override
    public String functionWeather(SysUser sysUser, CompletionFunctionWeatherRequest request) {
        // 必填参数
        String[] required = {"location","datePeriod"};

        ChatFunctionObject weatherFunctionBuilder = new ChatFunctionObject();
        ChatFunctionObject weatherFunction = weatherFunctionBuilder
                .build("get_location_weather",
                        "the weather of a location",
                        Lists.newArrayList("location","datePeriod"),
                        Lists.newArrayList("string","string"),
                        Lists.newArrayList("city, for example: 常州,苏州,上海","日期,格式yyyy-MM-dd"),
                        required);

        List<ChatFunctionObject> functionObjectList = new ArrayList<>();
        functionObjectList.add(weatherFunction);

        String completionResult = function(sysUser,request.getContent(), functionObjectList);
        log.info("functionWeather completionResult = {}",completionResult);
        return completionResult;
    }

}
