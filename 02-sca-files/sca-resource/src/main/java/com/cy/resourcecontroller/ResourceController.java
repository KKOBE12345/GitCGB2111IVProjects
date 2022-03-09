package com.cy.resourcecontroller;


import com.cy.resource.aspect.RequiredLog;
import com.cy.resource.aspect.WorkLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/resource")
@RefreshScope  //配置中心的配置要即时生效     需要加上这个注解
//@CrossOrigin
public class ResourceController {
    @Value("${cy.resource.path:f:/uppp}")
    private String resourcePath;
    @Value("${cy.resource.host:http://localhost:8888/}")
    private String resourceHost;

    //基于ID去删除
    @WorkLog("记录操作时间")
    @PreAuthorize("hasAnyAuthority('sys:res:delete')")
    @DeleteMapping("/upload/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        return "恭喜你  删除成功！！！";
    }


    @RequiredLog("文件上传切面注解")
    @PostMapping("/upload")
    @PreAuthorize("hasAnyAuthority('sys:res:create')")
    public String uploadfile(MultipartFile uploadFile) throws IOException {
        //这个方法实现文件的上传
        /**uploadFile接受要上传的文件
         * 参数名要与客户名提交的一致
         * return文件上传后在服务器的实际存储路径
         * 可以基于HTTP协议访问这个文件*/
        //1.创建文件的存储目录（按照年月日的结构进行存储）
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String dateStr = sdf.format(new Date());   这种方法不推荐使用
        //1.1.2  方式2   jdk8以后推出的新工具
        String dateStr = DateTimeFormatter.ofPattern("yyyy/MM/dd/")
                .format(LocalDate.now());
        File uploadDir=new File(resourcePath+"/"+dateStr);
        if(!uploadDir.exists()){uploadDir.mkdirs();}
        //2.给文件一个新的名字（文件的前缀随机产生   文件后缀不能改变）
        String originalFilename = uploadFile.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String fileSubFix = originalFilename.substring(i, originalFilename.length());
        String filePreFix = UUID.randomUUID().toString().replace("-", "");
        String newFileName=filePreFix+fileSubFix;
        log.info(newFileName);
        //3.上传文件到指定目录
        uploadFile.transferTo(new File(uploadDir,newFileName));
        //4.返回通过http协议可以访问这个文件的路径   其实就是图片的url地址
        String newAllFilePath=resourceHost+dateStr+newFileName;
        log.info("访问路径是 {}",newAllFilePath);
        return newAllFilePath;
    }
}
