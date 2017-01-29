package com.mycompany;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class DefaultRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Object> list;

	public DefaultRecord(List<Object> list) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.list = list;

	}

}
