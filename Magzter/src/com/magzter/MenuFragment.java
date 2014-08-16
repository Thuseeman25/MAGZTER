package com.magzter;

import java.nio.channels.GatheringByteChannel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MenuFragment extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
//		setListAdapter(new ArrayAdapter<String>(getActivity(),
//				android.R.layout.simple_list_item_1, new String[] { " First", " Second", " Third", " Fourth", " Fifth", " Sixth"}));
//		getListView().setCacheColorHint(0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View app = inflater.inflate(R.layout.menu_fragment, container);
		
		final RelativeLayout menuHome = (RelativeLayout) app.findViewById(R.id.menu_home);
//		menuHome.setBackgroundResource(R.color.side_menu_background_selected_color);
		menuHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				menuHome.setBackgroundResource(R.color.side_menu_background_selected_color);
				Toast.makeText(getActivity(), "Home", Toast.LENGTH_SHORT).show();
//				((MenuActivity)getActivity()).getSlideoutHelper().close();
				getActivity().finish();
				startActivity(new Intent(getActivity(), HomeActivity.class));
			}
		});
		
		final RelativeLayout menuPurchase = (RelativeLayout) app.findViewById(R.id.menu_purchases);	
		menuPurchase.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				menuPurchase.setBackgroundResource(R.color.side_menu_background_selected_color);
				Toast.makeText(getActivity(), "Purchases", Toast.LENGTH_SHORT).show();
//				((MenuActivity)getActivity()).getSlideoutHelper().close();
				getActivity().finish();
				startActivity(new Intent(getActivity(), MapActivity.class));
			}
		});
		return super.onCreateView(inflater, container, savedInstanceState);
	}
//	@Override
//	public void onListItemClick(ListView l, View v, int position, long id) {
//		super.onListItemClick(l, v, position, id);
//		((MenuActivity)getActivity()).getSlideoutHelper().close();
//	}
}
