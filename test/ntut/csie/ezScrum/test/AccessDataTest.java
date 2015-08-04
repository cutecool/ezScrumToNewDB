package ntut.csie.ezScrum.test;

import java.util.ArrayList;

import ntut.csie.ezScrum.issue.sql.service.core.Configuration;
import ntut.csie.ezScrum.web.dataObject.AccountObject;
import ntut.csie.ezScrum.web.dataObject.ProjectObject;
import ntut.csie.ezScrum.web.dataObject.StoryObject;
import ntut.csie.ezScrum.web.dataObject.TaskObject;
import ntut.csie.ezScrum.web.helper.ProductBacklogHelper;
import ntut.csie.ezScrum.web.helper.ProjectHelper;
import ntut.csie.ezScrum.web.helper.SprintBacklogHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccessDataTest {
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
	
	@Test
	public void testAccessProjectList() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		AccountObject account = AccountObject.get("admin");
		ProjectHelper projectHelper = new ProjectHelper();
		projectHelper.getProjectListXML(account);
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			projectHelper.getProjectListXML(account);
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access ProjectList avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessProductBacklog() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		ProjectObject project = ProjectObject.get(1);
		ProductBacklogHelper pbHelper = new ProductBacklogHelper(project);
		pbHelper.getShowProductBacklogResponseText("");
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			pbHelper.getShowProductBacklogResponseText("");
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access ProductBacklog avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessTag() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		ProjectObject project = ProjectObject.get(1);
		ProductBacklogHelper pbHelper = new ProductBacklogHelper(project);
		pbHelper.getTagListResponseText();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			pbHelper.getShowProductBacklogResponseText("");
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Tag avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessRelease() {
		
	}
	
	@Test
	public void testAccessSprint() {
		
	}
	
	@Test
	public void testAccessSprintBacklog() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		ProjectObject project = ProjectObject.get(1);
		SprintBacklogHelper sbHelper = new SprintBacklogHelper(project);
		sbHelper.getSprintBacklogListInfoText();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			sbHelper.getSprintBacklogListInfoText();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access SprintBacklog avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessExistingStory() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		ProjectObject project = ProjectObject.get(1);
		SprintBacklogHelper sbHelper = new SprintBacklogHelper(project);
		sbHelper.getExistingStories();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			sbHelper.getExistingStories();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Existing Story avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessExistingTask() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		ProjectObject project = ProjectObject.get(1);
		project.getTasksWithNoParent();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			project.getTasksWithNoParent();
			long time2 = System.currentTimeMillis();
//			long timeStamp = time2 - time1;
			timeStamps.add(time2 - time1);
//			timeStamps.add(timeStamp);
//			System.out.println(timeStamp);
		}
		
		System.out.println("Access Existing Task avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessStoryHistory() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		StoryObject story = StoryObject.get(101);
		story.getHistories();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			story.getHistories();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Story History avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessTaskHistory() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		TaskObject task = TaskObject.get(101);
		task.getHistories();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			task.getHistories();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Task History avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessAccount() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		AccountObject.getAllAccounts();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			AccountObject.getAllAccounts();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Account avg time： " + averageTime(timeStamps));
	}
	
	private double averageTime(ArrayList<Long> timeStamps) {
		double avgTime;
		long total = 0;
		for (Long timeStamp : timeStamps) {
			total += timeStamp;
		}
		avgTime = (double)total / timeStamps.size();
		return avgTime;
	}
}
