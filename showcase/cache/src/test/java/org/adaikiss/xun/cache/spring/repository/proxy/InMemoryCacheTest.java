package org.adaikiss.xun.cache.spring.repository.proxy;

import org.adaikiss.xun.cache.spring.repository.CacheTestCase;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({ "proxy", "memory" })
public class InMemoryCacheTest extends CacheTestCase {
}
