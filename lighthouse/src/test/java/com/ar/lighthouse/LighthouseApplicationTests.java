package com.ar.lighthouse;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LighthouseApplicationTests {
	
	@Autowired
	StringEncryptor jasyptStringEncryptor;
	
	@Test
	void contextLoads() {
		String[] datas = {
				"net.sf.log4jdbc.sql.jdbcapi.DriverSpy"
				, "jdbc:log4jdbc:oracle:thin:@3.39.124.186/xe"
				, "ar"
				, "ar"
				
		};
		
		for(String data : datas) {
			String enyData = jasyptStringEncryptor.encrypt(data);
			System.out.println(enyData);
		}
	}

}
