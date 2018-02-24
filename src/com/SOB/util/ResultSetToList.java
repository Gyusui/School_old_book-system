package com.SOB.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetToList {
	
	public static List resultSetSetToList(ResultSet rs) throws java.sql.SQLException{
		if(rs ==null){
			return Collections.EMPTY_LIST;
		}
		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount();
		List list = new ArrayList();
		Map rowData = new HashMap();
		while(rs.next()){
			rowData = new HashMap(columnCount);
			for(int i = 1;i<= columnCount; i++){
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}

}
