/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package de.appwerft.firebase;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// This proxy can be created by calling Tifirebase.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = TifirebaseModule.class)
public class DatabaseReferenceProxy extends KrollProxy {
	private FirebaseDatabase database;
	private static DatabaseReference reference;
	private static final String LCAT = "FiBaProx";
	private static final String ONCHANGE = "onDataChange";
	private static KrollProxy proxy;

	public DatabaseReferenceProxy(KrollProxy proxy) {
		super();
		this.proxy = proxy;

	}

	public static DatabaseReferenceProxy createReference(DatabaseProxy db,
			String refString) {
		DatabaseReferenceProxy referenceP = new DatabaseReferenceProxy(proxy);
		Log.d(LCAT, "new reference " + refString + " created");
		reference = db.database.getReference(refString);
		if (proxy.hasListeners(ONCHANGE)) {
			reference.addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					String value = dataSnapshot.getValue(String.class);
					KrollDict payload = new KrollDict();
					payload.put("value", value);
					proxy.fireEvent(ONCHANGE, payload);
					Log.d(LCAT, "Value is: " + value);
				}

				@Override
				public void onCancelled(DatabaseError error) {
					Log.w(LCAT, "Failed to read value.", error.toException());
				}
			});
		}
		return referenceP;
	}

	@Kroll.method
	public void setValue(String _ref, String _value) {
		DatabaseReference ref = database.getReference(_ref);
		if (ref != null && _value != null) {
			ref.setValue(_value);
		}
	}

}