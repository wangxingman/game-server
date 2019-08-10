package com.game.see.repository.es;

import com.game.see.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:42 2019/8/2 0002
 * @explain :
 */
@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

    /**
     * @Author: wx
     * @Date  : 下午 6:03 2019/8/5 0005 
     * @params: 
     * @Desc  :
     */
     List<Customer> findByFirstName(String firstName);

     /**
      * @Author: wx
      * @Date  : 下午 6:03 2019/8/5 0005 
      * @params: 
      * @Desc  :查询 
      */
     List<Customer> findByLastName(String lastName);

}
