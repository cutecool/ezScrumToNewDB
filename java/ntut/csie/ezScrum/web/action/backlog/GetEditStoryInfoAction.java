package ntut.csie.ezScrum.web.action.backlog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ntut.csie.ezScrum.web.action.PermissionAction;
import ntut.csie.ezScrum.web.dataObject.ProjectObject;
import ntut.csie.ezScrum.web.dataObject.StoryObject;
import ntut.csie.ezScrum.web.helper.ProductBacklogHelper;
import ntut.csie.ezScrum.web.support.SessionManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GetEditStoryInfoAction extends PermissionAction {
	private static Log log = LogFactory.getLog(GetEditStoryInfoAction.class);
	
	@Override
	public boolean isValidAction() {
		return super.getScrumRole().getAccessProductBacklog();
	}

	@Override
	public boolean isXML() {
		// XML
		return true;
	}

	@Override
	public StringBuilder getResponse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		long time1 = System.currentTimeMillis();
		log.info("Get Edit Story Information in GetEditStoryInfoAction.");
		ProjectObject project = SessionManager.getProjectObject(request);
		
		// get parameter info
		long issueId = Long.parseLong(request.getParameter("issueID"));
		StoryObject story = StoryObject.get(issueId);
		StringBuilder result = new ProductBacklogHelper(project).translateStoryToXML(story);
		long time2 = System.currentTimeMillis();
		System.out.println("GetEditStoryInfoAction:" + (time2 - time1));
		return result;
	}
}