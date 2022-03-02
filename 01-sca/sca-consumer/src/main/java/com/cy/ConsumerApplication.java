package com.cy;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@EnableFeignClients
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }


    @Bean
    public RestTemplate get(){
        return new RestTemplate();
    }



    private AtomicLong aLong=new AtomicLong(1);

    @Bean
    @LoadBalanced
    public RestTemplate loadERBalanceRestTEMPLATE(){
        return new RestTemplate();
    }

    @RestController
    public class ConsumerController{



        @Autowired
        private RestTemplate get;
        @Autowired
        private RestTemplate loadERBalanceRestTEMPLATE;
        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @Value("${spring.application.name:kobe+jordan}")
        private String appName;

//        @Bean
//        public IRule loaderrule(){
//            return new RandomRule();
//        }


        @GetMapping("/consumer/echo666")
        public String getEcho(){
            ServiceInstance instance = loadBalancerClient.choose("sca-provider");
            String ip = instance.getHost();
            int port = instance.getPort();
            String url=String.format("http://%s:%s/provider/echo/%s", ip,port,appName);
            return get.getForObject(url,String.class);
        }

        @GetMapping("/consumer/echo777")
        public String getEcho02() throws InterruptedException {
            long num = aLong.getAndIncrement();
//            aLong.incrementAndGet();
            if(num%2==0){
                //系统设计时会认为相应设计超级200毫秒是慢调用
                Thread.sleep(200);//模拟耗时操作
            }
            String url=String.format("http://sca-provider/provider/echo/%s", appName);
            return loadERBalanceRestTEMPLATE.getForObject(url,String.class);
        }

        @SentinelResource(value = "res")
        @GetMapping("/consumer/findid")
        public String findbyid(Integer id,String name){
            return "现在的ID是："+id+name;
        }

    }

}
