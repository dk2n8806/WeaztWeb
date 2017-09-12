package com;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.admin.config.RootContextConfig;
import com.admin.config.ServletContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
							classes={RootContextConfig.class, ServletContextConfig.class, RootContextConfig.class})
@WebAppConfiguration
public class BaseTest{

}
