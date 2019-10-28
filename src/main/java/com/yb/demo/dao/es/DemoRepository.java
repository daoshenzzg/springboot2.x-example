package com.yb.demo.dao.es;

import com.yb.demo.pojo.document.DemoDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-07 15:55
 */
@Repository
public interface DemoRepository extends ElasticsearchRepository<DemoDocument, String> {
}
