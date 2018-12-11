package com.mansourappdevelopment.androidapp.kidsafe;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AppAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ArrayList<App> mApps;
    private AppAdapter mAppAdapter;
    private PackageManager mPackageManager;
    private List<ApplicationInfo> mApplicationInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initializeData();
        initializeAdapter();

        mAppAdapter.setmOnItemClickListener(MainActivity.this);

    }

    private void initializeData() {
        mApps = new ArrayList<>();
        getInstalledApplication();
        for (ApplicationInfo applicationInfo : mApplicationInfoList) {
            if (applicationInfo.packageName != null) {

                mApps.add(new App((String) applicationInfo.loadLabel(mPackageManager), applicationInfo.loadIcon(mPackageManager)));
            }
        }
    }

    private void initializeAdapter() {
        mAppAdapter = new AppAdapter(this, mApps);
        mRecyclerView.setAdapter(mAppAdapter);
    }

    private List<ApplicationInfo> getInstalledApplication() {
        mPackageManager = getPackageManager();
        mApplicationInfoList = mPackageManager.getInstalledApplications(0);
        Collections.sort(mApplicationInfoList, new ApplicationInfo.DisplayNameComparator(mPackageManager));
        Iterator<ApplicationInfo> iterator = mApplicationInfoList.iterator();
        while (iterator.hasNext()) {
            ApplicationInfo applicationInfo = iterator.next();
            if (applicationInfo.packageName.contains("com.google") || applicationInfo.packageName.matches("com.android.chrome"))
                continue;
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                iterator.remove();
            }
        }
        return mApplicationInfoList;
    }


    @Override
    public void onItemClick(int position) {
        App clickedApp = mApps.get(position);

        Toast.makeText(this, String.valueOf(clickedApp.getmAppName()) + " Turned Off", Toast.LENGTH_SHORT).show();

    }
}
