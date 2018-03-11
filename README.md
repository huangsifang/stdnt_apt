1.
com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table 'performance_schema.session_variables' doesn't exist
解决方法：
MySQL版本为5.1.36及以上
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.36</version>
</dependency>

2.
org.springframework.beans.MethodInvocationException: Property 'cacheManager' threw exception; nested exception is org.apache.shiro.cache.CacheException: net.sf.ehcache.CacheException: Caches cannot be added by name when default cache config is not specified in the config. Please add a default cache config in the configuration.
解决方法：
ehcache.xml文件增加defaultCache
<defaultCache    
    maxElementsInMemory="10000"    
    maxElementsOnDisk="0"    
    eternal="true"    
    overflowToDisk="true"    
    diskPersistent="false"    
    timeToIdleSeconds="0"    
    timeToLiveSeconds="0"    
    diskSpoolBufferSizeMB="50"    
    diskExpiryThreadIntervalSeconds="120"    
    memoryStoreEvictionPolicy="LFU"    
/>

3.
mysql语句group by elt(interval(xxx, 0, 60, 80, 90), 'D', 'C', 'B', 'A')报错
原因：
MySql 5.7版本默认sql_mode=only_full_group_by问题
解决方法：
1) mysql> set @@sql_mode='NO_UNSIGNED_SUBTRACTION,NO_ENGINE_SUBSTITUTION';
2) 在mysql.ini配置文件中修改sql_mode=NO_UNSIGNED_SUBTRACTION,NO_ENGINE_SUBSTITUTION