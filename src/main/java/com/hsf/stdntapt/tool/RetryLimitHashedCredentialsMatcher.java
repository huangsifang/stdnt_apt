package com.hsf.stdntapt.tool;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	// private Ehcache passwordRetryCache;
	//
	// public RetryLimitHashedCredentialsMatcher() {
	// CacheManager cacheManager = CacheManager
	// .newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
	// passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	// }
	//
	// @Override
	// public boolean doCredentialsMatch(AuthenticationToken token,
	// AuthenticationInfo info) {
	// String username = (String) token.getPrincipal();
	// // retry count + 1
	// Element element = passwordRetryCache.get(username);
	// if (element == null) {
	// element = new Element(username, new AtomicInteger(0));
	// passwordRetryCache.put(element);
	// }
	// AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
	// if (retryCount.incrementAndGet() > 5) {
	// // if retry count > 5 throw
	// throw new ExcessiveAttemptsException();
	// }
	//
	// boolean matches = super.doCredentialsMatch(token, info);
	// if (matches) {
	// // clear retry count
	// passwordRetryCache.remove(username);
	// }
	// return matches;
	// }

	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}