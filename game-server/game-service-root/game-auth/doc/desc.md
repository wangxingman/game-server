1、feign的解释
    就是在当前模块上 调用 对应服务的controller方法
    
    参数说明！
    
    value
    空字符串
    调用服务名称，和name属性相同
    
    
    
    serviceId
    空字符串
    服务id，作用和name属性相同
    已过期
    
    
    name
    空字符串
    调用服务名称，和value属性相同
    
    
    
    url
    空字符串
    全路径地址或hostname，http或https可选
    
    
    
    decode404
    false
    配置响应状态码为404时是否应该抛出FeignExceptions
    
    
    
    configuration
    {}
    自定义当前feign client的一些配置
    参考FeignClientsConfiguration
    
    
    fallback
    void.class
    熔断机制，调用失败时，走的一些回退方法，可以用来抛出异常或给出默认返回数据。
    底层依赖hystrix，启动类要加上@EnableHystrix
    
    
    path
    空字符串
    自动给所有方法的requestMapping前加上前缀，类似与controller类上的requestMapping
    
    
    
    primary
    true
    
    作者：wangxiaowu241
    链接：https://www.jianshu.com/p/8bca50cb11d8
    来源：简书
    简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。