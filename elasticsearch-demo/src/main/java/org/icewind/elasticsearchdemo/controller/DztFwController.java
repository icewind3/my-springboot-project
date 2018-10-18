package org.icewind.elasticsearchdemo.controller;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.icewind.elasticsearchdemo.entity.DztFw;
import org.icewind.elasticsearchdemo.service.DztFwServiceImpl;
import org.icewind.elasticsearchdemo.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ye Jianyu
 * @date 2018/9/30
 */
@Controller
@RequestMapping(value = "dz/DztFw")
public class DztFwController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DztFwServiceImpl dztFwService;

    @RequestMapping(value = "createDoc")
    public HttpEntity<List<DztFw>> createDoc(){
        return new ResponseEntity<>(dztFwService.createDzDoc(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "search.json", produces = "application/json")
    public HttpEntity<ResponseResult> search(@RequestParam String keyword,
                                             @RequestParam String ssxq,
                                             @RequestParam(defaultValue = "1") Integer pageNumber,
                                             @RequestParam(defaultValue = "20") Integer pageSize,
                                             @RequestParam(required = false) Boolean sort) {

        ResponseResult responseResult;
        try {
            responseResult = new ResponseResult();
            List<DztFw> dztFwList = Lists.newArrayList();
            if (StringUtils.isNotBlank(keyword)) {
                dztFwList = dztFwService.query2(keyword, ssxq, pageNumber, pageSize);
            }
            responseResult.setResultCode(ResponseResult.OK);
            responseResult.setData(dztFwList);
        } catch (Exception e) {
            String message = "查询地址发生错误";
            responseResult = new ResponseResult(ResponseResult.ERROR, message);
            logger.error(message, e);
        }
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }
}
