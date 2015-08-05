package ntut.csie.ezScrum.test;

import java.io.File;

import ntut.csie.ezScrum.issue.sql.service.core.Configuration;
import ntut.csie.ezScrum.web.dataObject.ProjectObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import servletunit.struts.MockStrutsTestCase;

public class UserScenarioAccessDataTest extends MockStrutsTestCase {
	private Configuration mConfig;
	private ProjectObject mProject;
	
	@Before
	public void setUp() throws Exception {
		mConfig = new Configuration();
		mConfig.setTestMode(true);
		mConfig.save();
		
		mProject = ProjectObject.get(1);
		
		super.setUp();
	}
	
	@After
	public void tearDown() {
		
	}
	
	/**
	 * User login and enter one project summary page
	 * viewProjectList.
	 * AjaxGetTagList.
	 * AjaxGetHandlerList.
	 * AjaxGetSprintBacklogDateInfo.
	 * GetProjectLeftTreeItem.
	// * AjaxGetCustomIssueType
	 * validateUserEvent.
	 * GetTopTitleInfo.
	 * GetProjectDescription.
	 * GetTaskBoardDescription.
	 * getSprintBurndownChartData.
	 * getSprintBurndownChartData.
	// * getConfigPluginList
	 */
	@Test
	public void testScenario1() {
		long totalTime = 0;
		long time1, time2;
		// set viewProjectList action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/viewProjectList");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("viewProjectList：" + (time2 - time1));
		clearRequestParameters();
		
		// set AjaxGetTagList action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/AjaxGetTagList");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("AjaxGetTagList：" + (time2 - time1));
		clearRequestParameters();
		
		// set AjaxGetHandlerList action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/AjaxGetHandlerList");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("AjaxGetHandlerList：" + (time2 - time1));
		clearRequestParameters();
		
		// set AjaxGetSprintBacklogDateInfo action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/AjaxGetSprintBacklogDateInfo");
		addRequestParameter("sprintID", "");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("AjaxGetSprintBacklogDateInfo：" + (time2 - time1));
		clearRequestParameters();
		
		// set GetProjectLeftTreeItem action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/GetProjectLeftTreeItem");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("GetProjectLeftTreeItem：" + (time2 - time1));
		clearRequestParameters();
		
		// set validateUserEvent action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/validateUserEvent");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("validateUserEvent：" + (time2 - time1));
		clearRequestParameters();
		
		// set GetTopTitleInfo action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/GetTopTitleInfo");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("GetTopTitleInfo：" + (time2 - time1));
		clearRequestParameters();
		
		// set GetProjectDescription action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/GetProjectDescription");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("GetProjectDescription：" + (time2 - time1));
		clearRequestParameters();
		
		// set GetTaskBoardDescription action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/GetTaskBoardDescription");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("GetTaskBoardDescription：" + (time2 - time1));
		clearRequestParameters();
		
		// set getSprintBurndownChartData story action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/getSprintBurndownChartData");
		addRequestParameter("SprintID", "-1");
		addRequestParameter("Type", "story");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("getSprintBurndownChartData story：" + (time2 - time1));
		clearRequestParameters();
		
		// set getSprintBurndownChartData task action info
		setContextDirectory(new File(mConfig.getBaseDirPath() + "/WebContent"));
		setServletConfigFile("/WEB-INF/struts-config.xml");
		setRequestPathInfo("/getSprintBurndownChartData");
		addRequestParameter("SprintID", "-1");
		addRequestParameter("Type", "task");
		request.setHeader("Referer", "?PID=" + mProject.getName());
		request.getSession().setAttribute("UserSession", mConfig.getUserSession());
		time1 = System.currentTimeMillis();
		actionPerform();
		time2 = System.currentTimeMillis();
		totalTime += (time2 - time1);
		System.out.println("getSprintBurndownChartData task：" + (time2 - time1));
		clearRequestParameters();
		
		System.out.println("total：" + totalTime);
	}
}
