package duanqing.test;

import java.io.Serializable;

public interface ITabHostMenuHandler extends Serializable {

	public static final class SUB_NAME {
		public static final String TAG_ONE = "one";
		public static final String TAG_TWO = "two";
	
	}
	public void applyRotation(int position, float start, float end);
}
