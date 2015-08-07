package ntut.csie.ezScrum.test;

import ntut.csie.ezScrum.issue.sql.service.core.Configuration;
import ntut.csie.ezScrum.test.CreateData.AddSprintToRelease;
import ntut.csie.ezScrum.test.CreateData.AddStoryToSprint;
import ntut.csie.ezScrum.test.CreateData.AddTaskToStory;
import ntut.csie.ezScrum.test.CreateData.CreateAccount;
import ntut.csie.ezScrum.test.CreateData.CreateProductBacklog;
import ntut.csie.ezScrum.test.CreateData.CreateProject;
import ntut.csie.ezScrum.test.CreateData.CreateRelease;
import ntut.csie.ezScrum.test.CreateData.CreateTag;
import ntut.csie.ezScrum.test.CreateData.CreateTask;
import ntut.csie.ezScrum.test.CreateData.InitialSQL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddTestDataTest {
	private CreateProject CP;
	private CreateRelease CR;
	private AddSprintToRelease ASTR;
	private AddStoryToSprint ASTS;
	private AddTaskToStory ATTS;
	private CreateProductBacklog CPB;
	private CreateTask CT;
	private CreateTag CG;
	private CreateAccount CA;
	private Configuration config;
	
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		config.setTestMode(true);
		config.save();
		
		InitialSQL ini = new InitialSQL(config);
		ini.exe();
		
		CP = new CreateProject(1);
		CP.exeCreate();
		
		CR = new CreateRelease(1, CP);
		CR.exe();
		
		CPB = new CreateProductBacklog(100, CP);
		CPB.exe();
		
		CT = new CreateTask(100, 2, -1, CP);
		CT.exe();
		
		CG = new CreateTag(100, CP);
		CG.exe();
		
		CA = new CreateAccount(100);
		CA.exe();
		
		createSprint();
		createStory();
		createTask();
		
		createProject();
		createRelease();
		
		ini = null;
	}
	
	@After
	public void tearDown() {
		
	}
	
	public void createProject() {
		System.out.println("******* Project Start ********");
		
		CP = new CreateProject(100);
		CP.exeCreate();
		
		System.out.println("******************************");
		System.out.println("******** Project End *********");
		System.out.println("******************************");
	}
	
	public void createRelease() {
		System.out.println("******** Release Start *******");
		
		CR = new CreateRelease(100, CP);
		CR.exe();
		
		System.out.println("******************************");
		System.out.println("******** Release End *********");
		System.out.println("******************************");
	}
	
	public void createSprint() throws Exception {
		System.out.println("******** Sprint Start ********");
		
		ASTR = new AddSprintToRelease(100, CR, CP);
		ASTR.exe();
		
		System.out.println("******************************");
		System.out.println("******** Sprint End **********");
		System.out.println("******************************");
	}
	
	public void createStory() throws Exception {
		System.out.println("********* Story Start *********");
		
		ASTS = new AddStoryToSprint(5, 5, 100, CP, "EST");
		ASTS.exe();
		
		System.out.println("******************************");
		System.out.println("********* Story End **********");
		System.out.println("******************************");
	}
	
	public void createTask() throws Exception {
		System.out.println("********* Task Start *********");
		
		ATTS = new AddTaskToStory(5, 5, ASTS, CP);
		ATTS.exe();
		
		System.out.println("******************************");
		System.out.println("********** Task End **********");
		System.out.println("******************************");
	}
	
	@Test
	public void test1() {
		
	}
}
