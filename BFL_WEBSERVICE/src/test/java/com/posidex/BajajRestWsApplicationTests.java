package com.posidex;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BajajRestWsApplicationTests {
	private static Logger logger = Logger.getLogger(BajajRestWsApplicationTests.class.getName());
	@Test
	public void contextLoads() {
		logger.info("Testing the BajajRestWsApplication");
		logger.info("Testing the after code commited to git.");
	}

}
