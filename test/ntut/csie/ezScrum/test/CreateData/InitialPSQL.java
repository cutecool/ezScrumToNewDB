package ntut.csie.ezScrum.test.CreateData;

import ntut.csie.ezScrum.issue.sql.service.core.Configuration;
import ntut.csie.ezScrum.issue.sql.service.tool.internal.PostgreSQLControl;

public class InitialPSQL {
	PostgreSQLControl mPSQLControl = null;
	StringBuilder mInitQuery = new StringBuilder();
	
	public InitialPSQL(Configuration config) {
		mPSQLControl = new PostgreSQLControl(config);
		mPSQLControl.connect();
		mInitQuery.append("DROP TABLE task;")
				.append("CREATE TABLE task(")
				.append("id bigserial NOT NULL,")
				.append("serial_id bigint NOT NULL,")
				.append("project_id bigint NOT NULL,")
				.append("story_id bigint,")
				.append("name text NOT NULL,")
				.append("handler_id bigint,")
				.append("status character varying(255),")
				.append("estimate integer NOT NULL DEFAULT 0,")
				.append("remain integer NOT NULL DEFAULT 0,")
				.append("actual integer NOT NULL DEFAULT 0,")
				.append("notes text,")
				.append("create_time bigint NOT NULL,")
				.append("update_time bigint NOT NULL,")
				.append("CONSTRAINT task_pkey PRIMARY KEY (id),")
				.append("CONSTRAINT task_create_time_check CHECK (create_time >= 0),")
				.append("CONSTRAINT task_project_id_check CHECK (project_id >= 0),")
				.append("CONSTRAINT task_serial_id_check CHECK (serial_id >= 0),")
				.append("CONSTRAINT task_update_time_check CHECK (update_time >= 0))")
				.append("WITH (OIDS=FALSE);");
		mPSQLControl.execute(mInitQuery.toString(), false);
	}
}
