package com.game.see.repository.es;

import com.game.see.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:42 2019/8/2 0002
 * @explain :
 */
@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
    
}
