package ntut.csie.ezScrum.issue.sql.service.internal;

import ntut.csie.ezScrum.issue.sql.service.tool.IValue;

public class PSQLQueryValue implements IValue {
	private String mName;
	private String mValue;

	public PSQLQueryValue(String name, String value) {
		mName = name;
		mValue = value;
	}
	
	@Override
	public String getName() {
		return mName;
	}

	@Override
	public String getValue() {
		if (isSQLKeyWord())
			return mValue;
		else
			return "'" + mValue + "'";
	}

	private boolean isSQLKeyWord() {
		if (mValue == null)
			return false;
		if (mValue.equals("NOW()"))
			return true;
		return false;
	}
}
