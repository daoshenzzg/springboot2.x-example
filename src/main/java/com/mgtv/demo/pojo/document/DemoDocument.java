package com.mgtv.demo.pojo.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-07 15:53
 */
@Data
@Document(indexName = "demo", type = "demo")
public class DemoDocument {
    @Id
    private String id;
    private String title;
}
