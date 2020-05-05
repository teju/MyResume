package com.amazon.market.etc.generics;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;

public class GenericFragment extends Fragment {
	private static final Field sChildFragmentManagerField;

	// To prevent error in implementing nested fragment
	static {
		Field f = null;
		try {
			f = Fragment.class.getDeclaredField("mChildFragmentManager");
			f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// Error getting mChildFragmentManager field
			e.printStackTrace();
		}
		sChildFragmentManagerField = f;
	}

	protected boolean isThere() {
		if (!getUserVisibleHint() || !isVisible() || !isAdded()) {
			return false;
		}

		return true;
	}
	
	public void setTitle(int resTitle){
		getActivity().setTitle(resTitle);
	}
	
	public void setTitle(String title){
		getActivity().setTitle(title);
	}

	@Override
	public void onDetach() {
		super.onDetach();

//		if (sChildFragmentManagerField != null) {
//			try {
//				sChildFragmentManagerField.set(this, null);
//			} catch (Exception e) {
//				// Error setting mChildFragmentManager field
//				e.printStackTrace();
//			}
//		}
	}
}
