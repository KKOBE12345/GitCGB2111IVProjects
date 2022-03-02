package com.cy.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在此Controller中读取配置中心的一个开关值
 * (useLocalCache-是否使用本地缓存)
 */
@RefreshScope
@RestController
public class ProviderCacheController {
    /**
     * 从配置文件或配置中心读取useLocalCache的值,
     * 并将这个值赋值给ProviderCacheController类中的
     * useLocalCache属性.
     */
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @GetMapping("/provider/cache01")
    public String doUseLocalCache01(){
        /*
          无论配置中心配置的useLocalCache的值是什么,
          假如我们这里useLocalCache的值始终是默认值false,
          说明没有从配置中心读取到useLocalCache的值,读取
          不到的原因可能是这样的:
          1)项目中添加的配置依赖有问题
          2)配置文件的名字不是bootstrap.yml
          3)配置文件对配置中心数据获取的配置有问题
          4)useLocalCache这个单词与配置中心配置的不一致.
         */
        return "useLocalCache's value is "+useLocalCache;
    }

    /*构建一个本地cache对象*/
    private CopyOnWriteArrayList<String> localCache=
            new CopyOnWriteArrayList<>();
    /**
     * 通过这个方法,模拟从本地缓存取数据,本地缓存没有则从数据库取数据.
     * @return
     */
    @GetMapping("/provider/cache02")
    public List<String> doUseLocalCache02(){
        //1.检查本地缓存是否开启,假如没有开启
        //则从数据库去取数据并返回
        if(!useLocalCache){
            System.out.println("Get Data from Database");
            return Arrays.asList("A","B","C");//假设这是从DB中取的
        }
        //2.假设本地缓存已开启,则从缓存取数据,缓存没有则从数据库取,
        //并将数据在缓存放一份(暂时先不考虑一致性问题)
        if(localCache.isEmpty()){
            System.out.println("Get Data from Database");
            List<String> dbData = Arrays.asList("A", "B", "C");
            localCache.addAll(dbData);
        }
        return localCache;
    }

}

