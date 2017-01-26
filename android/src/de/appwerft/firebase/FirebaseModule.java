/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package de.appwerft.firebase;

import java.io.IOException;
import java.io.InputStream;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFileFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.*;

@Kroll.module(name = "Tifirebase", id = "de.appwerft.firebase")
public class FirebaseModule extends KrollModule {
	public static FirebaseAuth auth;
	public static Context ctx;
	private static String apiKey;
	private static String applicationId;
	private static String databaseUrl;
	private static String gcmSenderId;
	private static String storageBucket;
	public static Activity activity;

	private static TiApplication app;
	public static final String LCAT = "FiBa";

	public FirebaseModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication _app) {
		ctx = _app.getApplicationContext();
		app = _app;
		activity = _app.getCurrentActivity();
	}

	@Kroll.method
	public boolean initializeApp() {
		return initFirebase();
	}

	@Kroll.method
	public boolean initFirebase() {
		String packageName = TiApplication.getAppCurrentActivity()
				.getPackageName();
		try {
			JSONObject json = new JSONObject(loadJSONFromAsset());
			JSONObject projectInfo = json.getJSONObject("project_info");
			storageBucket = projectInfo.getString("storage_bucket");
			databaseUrl = projectInfo.getString("firebase_url");
			gcmSenderId = projectInfo.getString("project_number");
			JSONArray clients = json.getJSONArray("client");
			Log.d(LCAT, "I found " + clients.length() + " clients.");
			for (int i = 0; i < clients.length(); i++) {
				JSONObject client = clients.getJSONObject(i);
				JSONObject clientInfo = client.getJSONObject("client_info");
				String pName = clientInfo.getJSONObject("android_client_info")
						.getString("package_name");
				Log.d(LCAT, "packageName in clients of json = " + pName);
				if (pName.equals(packageName)) {
					Log.d(LCAT, "packageName found in json ");
					Log.d(LCAT, clients.getJSONObject(i).toString());
					applicationId = client.getJSONObject("client_info")
							.getString("mobilesdk_app_id");
					apiKey = client.getJSONArray("api_key").getJSONObject(0)
							.getString("current_key");
				}
			}
			if (apiKey == null)
				Log.e(LCAT, packageName
						+ " is not part of google-services.json");
			FirebaseApp.initializeApp(ctx, new FirebaseOptions.Builder()
					.setApiKey(apiKey).setApplicationId(applicationId)
					.setDatabaseUrl(databaseUrl).setGcmSenderId(gcmSenderId)
					.setStorageBucket(storageBucket).build());
			auth = FirebaseAuth.getInstance();
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String loadJSONFromAsset() {
		String json = null;
		try {
			String url = resolveUrl(null, "google-services.json");
			Log.d(LCAT, "path of google-services.json = " + url);
			InputStream inStream = TiFileFactory.createTitaniumFile(
					new String[] { url }, false).getInputStream();
			byte[] buffer = new byte[inStream.available()];
			inStream.read(buffer);
			inStream.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}
}
