package ntut.csie.ezScrum.test;

import ntut.csie.ezScrum.issue.sql.service.core.Configuration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserScenarioAccessDataTest {
	private Configuration mConfig;
	
	@Before
	public void setUp() {
		mConfig = new Configuration();
		mConfig.setTestMode(true);
		mConfig.save();
	}
	
	@After
	public void tearDown() {
		
	}
	
	/**
	 * User login and enter one project summary page
	 */
	@Test
	public void testScenario1() {
		
	}
}
