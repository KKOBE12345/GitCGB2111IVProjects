package com.cy.service.factory;

import com.cy.service.RemoteProviderService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProviderCallbackFactory implements FallbackFactory<RemoteProviderService> {
    @Override
    public RemoteProviderService create(Throwable throwable) {
//        return new RemoteProviderService() {
//            @Override
//            public String acceptEcho(String kobe) {
//                return "哈哈  第一次尝试调用服务熔断方法  么么哒";
//            }
//        };
//        return msg->"ahahha";
        return msg->{
            log.error("服务调用出错了：{}","我只是在测试");
            return "啊哈哈哈哈";
        };
    }
}
