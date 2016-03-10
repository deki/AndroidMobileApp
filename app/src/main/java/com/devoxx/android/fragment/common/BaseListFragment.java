package com.devoxx.android.fragment.common;

import com.devoxx.R;
import com.devoxx.data.schedule.filter.ScheduleFilterManager;
import com.devoxx.data.schedule.search.SearchManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;
import org.lucasr.twowayview.ItemClickSupport;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

@EFragment
public abstract class BaseListFragment extends BaseFragment
		implements ItemClickSupport.OnItemClickListener {

	@ViewById(R.id.tracksList)
	protected RecyclerView recyclerView;

	@SystemService
	protected InputMethodManager inputMethodManager;

	@Bean
	protected SearchManager searchManager;

	@Bean
	protected ScheduleFilterManager filterManager;

	@AfterViews
	protected void afterViews() {
		setupList();

		recyclerView.setOnTouchListener((v, event) -> {
			if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_UP) {
				inputMethodManager.hideSoftInputFromWindow(recyclerView.getWindowToken(), 0);
			}
			return false;
		});
	}

	private void setupList() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLongClickable(false);
		ItemClickSupport clickSupport = ItemClickSupport.addTo(recyclerView);
		recyclerView.setAdapter(getAdapter());
		clickSupport.setOnItemClickListener(this);
	}

	public abstract void onItemClick(RecyclerView parent, View view, int position, long id);

	protected abstract RecyclerView.Adapter getAdapter();
}
