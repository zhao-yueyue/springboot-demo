package com.ml.service.impl;

import com.ml.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("asyncExecutor")
    public void executeAsync(int i) {
        logger.info("[{}] start executeAsync", i);
        long begin = System.currentTimeMillis();
        long end = 0;
        try{
//            Thread.sleep(1000L);
            end = System.currentTimeMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("[{}] end executeAsync 耗时 [{}]秒", i, (end-begin)/1000);
    }

}
