package ntut.csie.ezScrum.web.action.backlog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ntut.csie.ezScrum.web.action.PermissionAction;
import ntut.csie.ezScrum.web.dataObject.ProjectObject;
import ntut.csie.ezScrum.web.helper.ProductBacklogHelper;
import ntut.csie.ezScrum.web.support.SessionManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AjaxDeleteStoryAction extends PermissionAction {
	private static Log log = LogFactory.getLog(AjaxDeleteStoryAction.class);

	@Override
	public boolean isValidAction() {
		return super.getScrumRole().getAccessProductBacklog();
	}

	@Override
	public boolean isXML() {
		// html
		return false;
	}

	@Override
	public StringBuilder getResponse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		long time1 = System.currentTimeMillis();
		log.info("Delete Story in AjaxDeleteStoryAction.");
		// get session info
		ProjectObject project = SessionManager.getProjectObject(request);
		
		// get parameter info
		long storyId;
		
		try{
			storyId = Long.parseLong(request.getParameter("issueID"));
		} catch (NumberFormatException e){
			storyId = -1;
		}
		
		ProductBacklogHelper productBacklogHelper = new ProductBacklogHelper(project);
		StringBuilder result = productBacklogHelper.deleteStory(storyId);
		long time2 = System.currentTimeMillis();
		System.out.println("AjaxDeleteStoryAction:" + (time2 - time1));
		return result;
	}
}
