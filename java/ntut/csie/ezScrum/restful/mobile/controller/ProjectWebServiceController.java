package ntut.csie.ezScrum.restful.mobile.controller;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.ezScrum.restful.mobile.service.ProjectWebService;
import ntut.csie.ezScrum.restful.mobile.support.InformationDecoder;
import ntut.csie.jcis.account.core.LogonException;

import org.codehaus.jettison.json.JSONException;

@Path("/")
public class ProjectWebServiceController {
	private ProjectWebService mService = null;

	/***
	 * 取得所有專案資訊。 Get
	 * http://IP:8080/ezScrum/web-service/projects?username={userName
	 * }&password={password}
	 * */
	@GET
	@Path("projects")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProjectList(@QueryParam("username") String username,
			@QueryParam("password") String password) {
		String jsonString = "";

		try {
			InformationDecoder decodeInformation = new InformationDecoder();
			decodeInformation.decode(username, password);
			mService = new ProjectWebService(
					decodeInformation.getDecodeUsername(),
					decodeInformation.getDecodePwd());
			jsonString += mService.getRESTFulResponseString();
		} catch (LogonException e) {
			System.out
					.println("class: ProjectWebServiceController, "
							+ "method: getProjectList, " + "exception: "
							+ e.toString());
			e.printStackTrace();
			Response.status(410).entity("Parameter error.").build();
		} catch (JSONException e) {
			System.out.println("class: ProjectWebServiceController, "
					+ "method: getRESTFulResponseString, " + "exception: "
					+ e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("class: ProjectWebServiceController, "
					+ "method: decode, " + "exception: " + e.toString());
			e.printStackTrace();
		}
		return jsonString;
	}

	/***
	 * 取得單一專案資訊。 Get
	 * http://IP:8080/ezScrum/web-service/projects/{projectName}?userName
	 * ={userName}&password={password}
	 * */
	@GET
	@Path("projects/{projectName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProjectInformation(
			@QueryParam("username") String username,
			@QueryParam("password") String password,
			@PathParam("projectName") String projectName) {
		String jsonString = "";

		try {
			InformationDecoder decodeInformation = new InformationDecoder();
			decodeInformation.decode(username, password, projectName);
			mService = new ProjectWebService(
					decodeInformation.getDecodeUsername(),
					decodeInformation.getDecodeUsername(),
					decodeInformation.getDecodeProjectName());
			jsonString += mService.getRESTFulResponseString();
		} catch (LogonException e) {
			System.out
					.println("class: ProjectWebServiceController, "
							+ "method: getProjectList, " + "exception: "
							+ e.toString());
			e.printStackTrace();
			Response.status(410).entity("Parameter error.").build();
		} catch (JSONException e) {
			System.out.println("class: ProjectWebServiceController, "
					+ "method: getRESTFulResponseString, " + "exception: "
					+ e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("class: ProjectWebServiceController, "
					+ "method: decode, " + "exception: " + e.toString());
			e.printStackTrace();
		}
		return jsonString;
	}
}
