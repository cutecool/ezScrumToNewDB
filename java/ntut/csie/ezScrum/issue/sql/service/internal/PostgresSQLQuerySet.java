package ntut.csie.ezScrum.issue.sql.service.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ntut.csie.ezScrum.issue.sql.service.core.IQueryValueSet;
import ntut.csie.ezScrum.issue.sql.service.tool.IValue;

public class PostgresSQLQuerySet implements IQueryValueSet {
	List<IValue> _list;
	List<String> _conditionList;
	List<String> _tableList;
	List<String> _leftJoinList;
	List<String> _crossJoinList;
	List<String> _rowList;
	String _orderBy = "";
	String _orderDirection = ASC_ORDER;

	public PostgresSQLQuerySet() {
		_list = new ArrayList<IValue>();
		_conditionList = new ArrayList<String>();
		_tableList = new ArrayList<String>();
		_leftJoinList = new ArrayList<String>();
		_crossJoinList = new ArrayList<String>();
		_rowList = new ArrayList<String>();
	}

	@Override
	public void addResultRow(String rowName) {
		_rowList.add(rowName);
	}

	@Override
	public void addInsertValue(IValue value) {
		_list.add(value);
	}

	@Override
	public void addTableName(String tableName) {
		this._tableList.add(tableName);
	}

	@Override
	public void joinTableName(String joinName, String on) {
		this._tableList.add(joinName + " on " + on);
	}

	@Override
	public void addInsertValue(String name, String value) {
		// format是為了要避免有特殊字元發生
		this.addInsertValue(new PSQLQueryValue(name, format(value)));
	}

	@Override
	public void addInsertValue(String name, long value) {
		addInsertValue(name, String.valueOf(value));
	}

	@Override
	public void addLeftJoin(String tableName, String name1, String name2) {
		this._leftJoinList.add(tableName + " ON " + name1 + " = " + name2);
	}

	@Override
	public void addNotNullCondition(String name) {
		this._conditionList.add(name + " IS NOT NULL");
	}

	@Override
	public void addEqualCondition(String name, String value) {
		this._conditionList.add(name + " = " + value);
	}

	@Override
	public void addEqualCondition(String name, long value) {
		addEqualCondition(name, String.valueOf(value));
	}

	@Override
	public void addLessCondition(String name, String value) {
		this._conditionList.add(name + " < " + value);
	}

	@Override
	public void addBigCondition(String name, String value) {
		this._conditionList.add(name + " > " + value);
	}

	@Override
	public void addFieldEqualCondition(String name1, String name2) {
		this._conditionList.add(name1 + " = " + name2);
	}

	@Override
	public void addLikeCondition(String name, String value) {
		this._conditionList.add(name + " LIKE " + value);
	}

	@Override
	public void addTextFieldEqualCondition(String name, String value) {
		this._conditionList.add(name + " = " + format(value));
	}

	@Override
	public void addLikeConditionXtable(String name, String value) {
		this._conditionList.add(name + " LIKE " + value);
	}

	@Override
	public String getColumns() {
		StringBuffer columns = new StringBuffer();
		// 列出所有Value的Name,因為Name代表欄位
		Iterator<IValue> iter = _list.iterator();

		// 判斷是否有元素要取得
		if (_list.size() == 0) {
			return columns.toString();
		} else {
			columns.append(iter.next().getName());
		}

		// 如果還有其他的要附加上去 , 就必須在之前加個 ","
		while (iter.hasNext()) {
			columns.append(", " + iter.next().getName());
		}

		return columns.toString();
	}

	@Override
	public String getColumnValues() {
		StringBuffer columnValues = new StringBuffer();
		// 列出所有Value的Name,因為Name代表欄位
		Iterator<IValue> iter = _list.iterator();

		// 判斷是否有元素要取得
		if (_list.size() == 0) {
			return columnValues.toString();
		} else {
			columnValues.append(iter.next().getValue());
		}

		// 如果還有其他的要附加上去 , 就必須在之前加個 ","
		while (iter.hasNext()) {
			columnValues.append(", " + iter.next().getValue());
		}

		return columnValues.toString();
	}

	@Override
	public String getConditions() {
		StringBuffer conditions = new StringBuffer();
		// 列出所有Value的Name,因為Name代表欄位
		Iterator<String> iter = _conditionList.iterator();

		// 判斷是否有元素要取得
		if (_conditionList.size() == 0) {
			return conditions.toString();
		} else {
			conditions.append(iter.next());
		}

		// 如果還有其他的要附加上去 , 就必須在之前加個 "AND"
		while (iter.hasNext()) {
			conditions.append(" AND " + iter.next());
		}

		return conditions.toString();
	}

	@Override
	public String getTableNames() {
		StringBuffer tableNames = new StringBuffer();
		// 列出所有Value的Name,因為Name代表欄位
		Iterator<String> iter = _tableList.iterator();

		// 判斷是否有元素要取得
		if (_tableList.size() == 0) {
			return tableNames.toString();
		} else {
			tableNames.append(iter.next());
		}

		// 如果還有其他的要附加上去 , 就必須在之前加個 "AND"
		while (iter.hasNext()) {
			tableNames.append(" join " + iter.next());
		}

		return tableNames.toString();
	}

	@Override
	public String getLeftJoins() {
		StringBuffer leftJoins = new StringBuffer();

		// 列出所有Value的Name,因為Name代表欄位
		Iterator<String> iter = _leftJoinList.iterator();

		// 判斷是否有元素要取得
		if (_leftJoinList.size() == 0) {
			return leftJoins.toString();
		} else {
			leftJoins.append(iter.next());
		}

		// 如果還有其他的要附加上去 , 就必須在之前加個 "Left Join"
		while (iter.hasNext()) {
			leftJoins.append(" Left Join " + iter.next());
		}

		return leftJoins.toString();
	}

	@Override
	public String getColumnsAndValues() {
		StringBuffer modifyValue = new StringBuffer();
		// 列出所有Value的Name,因為Name代表欄位
		Iterator<IValue> iter = _list.iterator();

		// 判斷是否有元素要取得
		if (_list.size() == 0) {
			return modifyValue.toString();
		} else {
			IValue value = iter.next();
			modifyValue.append(value.getName() + " = " + value.getValue());
		}

		// 如果還有其他的要附加上去 , 就必須在之前加個 ","
		while (iter.hasNext()) {
			IValue value = iter.next();
			modifyValue.append(", " + value.getName() + " = "
					+ value.getValue());
		}

		return modifyValue.toString();
	}

	@Override
	public String getSelectQuery() {
		StringBuffer query = new StringBuffer();
		if (_rowList.isEmpty()) {
			query.append("SELECT * FROM " + getTableNames());
		} else {
			query.append("SELECT ");
			for (String rowName : _rowList) {
				query.append(rowName + ",");
			}
			// 刪除最後一個逗點
			query.deleteCharAt(query.length() - 1);
			query.append(" FROM " + getTableNames());
		}

		if (this._leftJoinList.size() != 0) query.append(" LEFT JOIN" + getLeftJoins());
		if (this._crossJoinList.size() != 0) query.append(" CROSS JOIN" + getCrossJoins());
		if (this._conditionList.size() != 0) query.append(" WHERE " + getConditions());
		if (!this._orderBy.equals("")) query.append(" ORDER BY " + this._orderBy + " " + this._orderDirection);
		return query.toString();
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO " + getTableNames() + " ( " + this.getColumns()
				+ " ) VALUES ( " + this.getColumnValues() + " )";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE " + this.getTableNames() + " SET " + this.getColumnsAndValues()
				+ " WHERE " + this.getConditions();
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM " + this.getTableNames() + " WHERE " + this.getConditions();
	}

	@Override
	public String getMaxQuery(String tableName, String column) {
		return "SELECT MAX(" + column + ") FROM " + tableName;
	}

	@Override
	public void setOrderBy(String name, String desc) {
		this._orderBy = name;
		this._orderDirection = desc;
	}

	@Override
	public void clear() {
		this._list.clear();
		this._conditionList.clear();
		this._tableList.clear();
		this._leftJoinList.clear();
		this._rowList.clear();
		_orderBy = "";
	}

	private String format(String query) {
		if (query == null) return query;
		query = query.replaceAll("\\\\", "\\\\\\\\");
		query = query.replaceAll("\"", "\\\"");
		query = query.replaceAll("'", "\\\\\'");  //  ' -> \'
		return query;
	}

	public String getCrossJoins() {
		StringBuffer crossJoins = new StringBuffer();

		// 列出所有Value的Name,因為Name代表欄位
		Iterator<String> iter = _crossJoinList.iterator();

		// 判斷是否有元素要取得
		if (_crossJoinList.size() == 0) {
			return crossJoins.toString();
		} else {
			crossJoins.append(iter.next());
		}

		// 如果還有其他的要附加上去 , 就必須在之前加個 "cross Join"
		while (iter.hasNext()) {
			crossJoins.append(" cross join " + iter.next());
		}

		return crossJoins.toString();
	}
}
