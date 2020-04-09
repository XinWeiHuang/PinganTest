package com.test.pingan;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ResultSetUtil {
	public static <T> List<T> mapRersultSetToObject(ResultSet rs, Class<T> clazz) throws Exception {
		if (rs == null) {
			return null;
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		// 获取结果集的元素个数
		int colCount = rsmd.getColumnCount();
		// 返回结果的列表集合
		List<T> list = new ArrayList<T>();
		// 业务对象的属性数组
		Field[] fields = clazz.getDeclaredFields();
		while (rs.next()) {// 对每一条记录进行操作
			T obj = clazz.newInstance();// 构造业务对象实体
			// 将每一个字段取出进行赋值
			for (int i = 1; i <= colCount; i++) {
				Object value = rs.getObject(i);
				// 寻找该列对应的对象属性
				for (int j = 0; j < fields.length; j++) {
					Field f = fields[j];
					// 如果匹配进行赋值
					if (f.getName().equalsIgnoreCase(rsmd.getColumnName(i))) {
						boolean flag = f.isAccessible();
						f.setAccessible(true);
						f.set(obj, value);
						f.setAccessible(flag);
					}
				}
			}
			list.add(obj);
		}
		return list;
	}
	
	public static <T> T getOneResult(ResultSet rs, Class<T> clazz) throws Exception {
		List<T> datas = mapRersultSetToObject(rs, clazz);
		if (datas == null || datas.size() == 0) {
			return null;
		}
		return datas.get(0);
	}
}
