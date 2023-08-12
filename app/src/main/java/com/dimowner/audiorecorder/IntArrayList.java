/*
 * © Copyright 2023 Harald Rudell <harald.rudell@gmail.com>
 * Copyright 2018 Dmytro Ponomarenko
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

package com.dimowner.audiorecorder;

import android.util.Log;

public class IntArrayList {

	private int[] data = new int[100];
	private int size = 0;
	private final String LOG_TAG = getClass().getSimpleName();


	public synchronized void add(int val) {
		if (data.length == 0 ) {
			Log.e(LOG_TAG, "data.length 0 size " + String.valueOf(size));
			data = new int[100];
		}
		if (data.length == size) {
			grow();
			//add(val);
		}
		if (data.length <= size) {
			Log.e(LOG_TAG, "data.length "+ String.valueOf(data.length)+" size " + String.valueOf(size));
			grow2(size+1);
		}
		data[size] = val;
		size++;
	}

	public synchronized int get(int index) {
		return data[index];
	}

	public synchronized int[] getData() {
		int [] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = data[i];
		}
		return arr;
	}

	public synchronized void clear() {
		data = new int[100];
		size = 0;
	}

	public synchronized int size() {
		return size;
	}

	private void grow() {
		int[] backup = data;
		data = new int[data.length * 2];
		for (int i = 0; i < backup.length; i++) {
			data[i] = backup[i];
		}
	}

	private void grow2(int n) {
		int[] backup = data;
		data = new int[n];
		for (int i = 0; i < backup.length; i++) {
			data[i] = backup[i];
		}
	}
}
