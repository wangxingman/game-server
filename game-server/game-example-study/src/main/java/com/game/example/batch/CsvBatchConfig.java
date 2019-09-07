package com.game.example.batch;

import com.game.common.entity.user.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:39 2019/9/6 0006
 * @explain : 综合批处理框架
 */
@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {

    /**
     * @Author: wx
     * @Date : 上午 11:58 2019/9/6 0006
     * @params:
     * @Desc : 加载文件 赋值
     */
    @Bean
    public ItemReader<User> reader() throws Exception {
        //可以简单理解为
        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
        //加载文件
        flatFileItemReader.setResource(new ClassPathResource("person.csv"));
        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<User>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        //todo 添加字段
                        setNames(new String[]{});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                    setTargetType(User.class);
                }});
            }
        };
        flatFileItemReader.setLineMapper(defaultLineMapper);
        return flatFileItemReader;
    }


    @Bean
    public Validator<User> csvBeanValidator() {
        return new CsvBeanValidator<User>();
    }

    /**
     * @Author: wx
     * @Date : 下午 12:00 2019/9/6 0006
     * @params:
     * @Desc : 用来处理数据
     */
    @Bean
    public ItemProcessor<User, User> processor() {
        CsvItemProcessor csvItemProcessor = new CsvItemProcessor();
        Validator<User> userValidator = csvBeanValidator();
        csvItemProcessor.setValidator(userValidator);
        return csvItemProcessor;
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 2:49 2019/9/6 0006 
     * @params: 
     * @Desc  : 输出数据
     */
    @Bean
    public ItemWriter<User> writer(@Qualifier("dataSource") DataSource dataSource) {
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        //我们使用JDBC批处理的JdbcBatchItemWriter来写数据到数据库
        //参数来源提供者
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        String sql = "insert into user "+" (,,,) "
                +" values(:,:,:,:)";
        //在此设置要执行批处理的SQL语句
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

    /**
     * @Author: wx
     * @Date  : 下午 2:56 2019/9/6 0006 
     * @params: 
     * @Desc  :用来注册job容器
     */
    @Bean
    public JobRepository jobRepository(@Qualifier("dataSource") DataSource dataSource, PlatformTransactionManager transactionManager)throws Exception{
        JobRepositoryFactoryBean jobRepositoryFactoryBean =
                new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.name());
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * @Author: wx
     * @Date  : 下午 3:00 2019/9/6 0006 
     * @params: 
     * @Desc  : JobLauncher定义，用来启动Job的接口
     */
    @Bean
    public SimpleJobLauncher jobLauncher(@Qualifier("dataSource") DataSource dataSource, PlatformTransactionManager transactionManager)throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public CsvJobListener csvJobListener(){
        return new CsvJobListener();
    }

    /**
     * @Author: wx
     * @Date  : 下午 3:01 2019/9/6 0006 
     * @params: 
     * @Desc  : Job定义，我们要实际执行的任务，包含一个或多个Step
     */
    @Bean
    public Job importJob(JobBuilderFactory jobBuilderFactory, Step s1){
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                //为Job指定Step
                .flow(s1)
                .end()
                //绑定监听器csvJobListener
                .listener(csvJobListener())
                .build();
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 3:06 2019/9/6 0006 
     * @params: 
     * @Desc  : step步骤，包含ItemReader，ItemProcessor和ItemWriter
     */
    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<User> reader, ItemWriter<User> writer,
                      ItemProcessor<User,User> processor){
        return stepBuilderFactory
                .get("step1")
                //批处理每次提交65000条数据
                .<User,User>chunk(65000)
                //给step绑定reader
                .reader(reader)
                //给step绑定processor
                .processor(processor)
                //给step绑定writer
                .writer(writer)
                .build();
    }

}
