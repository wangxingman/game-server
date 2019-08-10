package com.game.see.service.es.impl;

import com.game.common.constant.Const;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityNotFoundException;
import com.game.see.model.Customer;
import com.game.see.repository.es.CustomerRepository;
import com.game.see.service.es.CustomerService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:45 2019/8/2 0002
 * @explain :
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

   @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Customer saveByCustomer(Customer customer) {
        Customer save = customerRepository.save(new Customer(customer.getFirstName(), customer.getLastName()));
        if (Objects.isNull(save)) {
            throw new EntityNotFoundException(Customer.class, "customer", customer.getFirstName(), customer.getLastName());
        }
        return save;
    }

    @Override
    public void deleteByCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Customer updateByCustomer(Customer customer) {

        return null;
    }

    @Override
    public Object findByCustomer() {
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        Iterable<Customer> customers = customerRepository.findAll();
        if (Objects.isNull(customers)) {
            throw new EntityNotFoundException(Customer.class, "集合数据");
        }
        return customers;
    }

    @Override
    public Object findByCustomerName(String... field) {
        int length = field.length;
        Map<String, Object> map = new HashMap();
        if (length > Const.number.ZERO) {
            for (int i = 0; i < field.length; i++) {
                switch (i) {
                    case Const.number.ONE:
                        List<Customer> byFirstName = customerRepository.findByFirstName(field[Const.number.ONE]);
                        if (Objects.nonNull(byFirstName)) {
                            map.put(field[Const.number.ONE], byFirstName);
                        }
                        break;
                    case Const.number.TWO:
                        List<Customer> byLastName = customerRepository.findByLastName(field[Const.number.ONE]);
                        if (Objects.nonNull(byLastName)) {
                            map.put(field[Const.number.TWO], byLastName);
                        }
                        break;
                    default:
                        throw new BadRequestException("您传输的参数有问题!");
                }
            }
        }
        return map;
    }

    /**
     * @Author: wx
     * @Date  : 下午 5:45 2019/8/6 0006 
     * @params: 
     * @Desc  : 1、term代表完全匹配
     *          2、Bucket: 满足某个条件(聚合)的文档集合
     *
     *
     *          filter比query快
     *          query的时候，会先比较查询条件，然后计算分值，最后返回文档结果；
     *          而filter则是先判断是否满足查询条件，如果不满足，会缓存查询过程（记录该文档不满足结果）；
     */
    @Override
    public void RawByCustomer() {
        String[] strings = {"name", "price"};
        //连接条件 和聚合函数组合
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                //匹配对应的字段    短语搜索
                .withQuery(matchPhraseQuery("name","heiren"))
                //分页
                .withPageable(new PageRequest(1,1))
                //排序
                .withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC))
                //指定查询的字段
                .withSourceFilter(new FetchSourceFilter(strings,null))
                //高亮搜索结果
                .withHighlightFields(new HighlightBuilder.Field("name"))
                .withFilter(boolQuery().must(matchQuery("name", "heiren").filter(matchQuery("name", "heiren"))))
                .build();
        Stream<Customer> customerStream = elasticsearchTemplate.queryForPage(nativeSearchQuery, Customer.class).get();
    }

    private BoolQueryBuilder matchQuery(String name, String heiren) {
        return null;
    }

    private QueryBuilder boolFilter() {
        return null;
    }


}
