package com.devoxx.data;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@EBean(scope = EBean.Scope.Singleton)
public class RealmProvider {

	private static final String DATABASE_NAME = "devoxx_db";

	@RootContext
	Context context;

	public Realm getRealm() {
		final RealmConfiguration configuration =
				new RealmConfiguration.Builder(context)
						.name(DATABASE_NAME)
						.schemaVersion(1)
						.deleteRealmIfMigrationNeeded()
						.build();
		return Realm.getInstance(configuration);
	}
}
