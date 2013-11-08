package org.adaikiss.xun.cache.spring.repository.aspectj;

import org.adaikiss.xun.cache.spring.repository.CacheTestCase;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({ "aspectj", "memory"})
public class InMemoryCacheTest extends CacheTestCase {
}
