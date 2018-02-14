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