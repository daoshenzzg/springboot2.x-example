package com.mgtv.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daoshenzzg@163.com
 * @date 2019-09-22 16:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpDownloadFileTest {

    @Autowired
    private RestTemplate httpClientTemplate;

    @Test
    public void testDownloadFile() {
        String url = "https://ugc.hitv.com/2/1907042053301DED/89730.png";
        HttpHeaders header = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        // 指定下载文件类型
        list.add(MediaType.APPLICATION_OCTET_STREAM);
        header.setAccept(list);

        HttpEntity<byte[]> request = new HttpEntity<>(header);
        ResponseEntity<byte[]> response = httpClientTemplate.exchange(url, HttpMethod.POST, request, byte[].class);
        // 取得文件字节
        byte[] result = response.getBody();
    }
}
