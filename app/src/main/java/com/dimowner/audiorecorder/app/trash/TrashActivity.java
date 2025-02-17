/*
 * © Copyright 2023 Harald Rudell <harald.rudell@gmail.com>
 * Copyright 2020 Dmytro Ponomarenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dimowner.audiorecorder.app.trash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dimowner.audiorecorder.ARApplication;
import com.dimowner.audiorecorder.Mapper;
import com.dimowner.audiorecorder.R;
import com.dimowner.audiorecorder.app.info.ActivityInformation;
import com.dimowner.audiorecorder.app.info.RecordInfo;
import com.dimowner.audiorecorder.app.lostrecords.RecordItem;
import com.dimowner.audiorecorder.util.AndroidUtils;
import com.dimowner.audiorecorder.util.RippleUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created on 15.12.2019.
 * @author Dimowner
 */
public class TrashActivity extends Activity implements TrashContract.View {

	private TrashContract.UserActionsListener presenter;

	private TrashAdapter adapter;
	private TextView txtEmpty;
	private TextView btnDeleteAll;

	public static Intent getStartIntent(Context context) {
		return new Intent(context, TrashActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setTheme(ARApplication.getInjector().provideColorMap(getApplicationContext()).getAppThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trash);

		ImageButton btnBack = findViewById(R.id.btn_back);
		btnBack.setOnClickListener(v -> {
			ARApplication.getInjector().releaseTrashPresenter();
			finish();
		});

		btnDeleteAll = findViewById(R.id.btn_delete_all);
		btnDeleteAll.setBackground(RippleUtils.createRippleShape(
				ContextCompat.getColor(getApplicationContext(), R.color.white_transparent_80),
				ContextCompat.getColor(getApplicationContext(), R.color.white_transparent_50),
				getApplicationContext().getResources().getDimension(R.dimen.spacing_normal)
		));
		btnDeleteAll.setOnClickListener(view ->
			AndroidUtils.showDialogYesNo(
				TrashActivity.this,
				R.drawable.ic_delete_forever_dark,
				getString(R.string.warning),
				getString(R.string.delete_all_records),
				v -> presenter.deleteAllRecordsFromTrash()
		));

		txtEmpty = findViewById(R.id.txtEmpty);
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		adapter = new TrashAdapter();
		adapter.setOnItemClickListener(new TrashAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(RecordItem record) {
				presenter.onRecordInfo(Mapper.toRecordInfoInTrash(record));
			}

			@Override
			public void onDeleteItemClick(final RecordItem record) {
				AndroidUtils.showDialogYesNo(
						TrashActivity.this,
						R.drawable.ic_delete_forever_dark,
						getString(R.string.warning),
						getString(R.string.delete_record_forever, record.getName()),
						v -> presenter.deleteRecordFromTrash(record.getId(), record.getPath())
				);
			}

			@Override
			public void onRestoreItemClick(final RecordItem record) {
				AndroidUtils.showDialogYesNo(
						TrashActivity.this,
						R.drawable.ic_restore_from_trash,
						getString(R.string.warning),
						getString(R.string.restore_record, record.getName()),
						v -> presenter.restoreRecordFromTrash(record.getId())
				);
			}
		});
		recyclerView.setAdapter(adapter);
		presenter = ARApplication.getInjector().provideTrashPresenter(getApplicationContext());
	}

	@Override
	protected void onStart() {
		super.onStart();
		presenter.bindView(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (presenter != null) {
			presenter.unbindView();
		}
	}

	@Override
	public void showProgress() {

	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showError(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showError(int resId) {
		Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showMessage(int resId) {
		Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showRecords(List<RecordItem> items) {
		adapter.setData(items);
	}

	@Override
	public void showRecordInfo(RecordInfo info) {
		startActivity(ActivityInformation.getStartIntent(getApplicationContext(), info));
	}

	@Override
	public void recordDeleted(int resId) {
		adapter.removeItem(resId);
		if (adapter.getItemCount() == 0) {
			showEmpty();
		}
	}

	@Override
	public void recordRestored(int resId) {
		adapter.removeItem(resId);
		if (adapter.getItemCount() == 0) {
			showEmpty();
		}
	}

	@Override
	public void allRecordsRemoved() {
		adapter.clearData();
		showEmpty();
	}

	@Override
	public void showEmpty() {
		txtEmpty.setVisibility(View.VISIBLE);
		btnDeleteAll.setVisibility(View.GONE);
	}

	@Override
	public void hideEmpty() {
		txtEmpty.setVisibility(View.GONE);
		btnDeleteAll.setVisibility(View.VISIBLE);
	}
}
