package com.huashao.Transition3d;

import java.io.Serializable;

public interface ITabHostMenuHandler extends Serializable {

	public static final class TabHostSubClazzSimpleName {
		public static final String TAG_MODULE1 = "ModuleView1";
		public static final String TAG_MODULE2 = "ModuleView2";
	
	}

	public void applyRotation(int position, float start, float end);
}
