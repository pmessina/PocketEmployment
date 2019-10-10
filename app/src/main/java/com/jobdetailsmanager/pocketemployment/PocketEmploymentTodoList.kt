/*
 * Copyright (c) 2016. Patrick Messina
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jobdetailsmanager.pocketemployment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_pocket_employment_drawer_layout.*
import kotlinx.android.synthetic.main.app_bar_navigation_drawer.*

//import com.pirateman.callslogmanager.RecruiterCallsLogActivity;
//import com.pirateman.preferencesmanager.RecruiterPreferencesActivity;
//import com.pirateman.recruitercallscontactdetails.CallsFragment;
//import com.pirateman.recruitercallscontactdetails.JobContactDetailsFragment;
//import com.pirateman.recruitercallscontactdetails.JobMarketDetailsMenuFragment;
//import com.pirateman.recruitercallsdbmanager.CallsNotificationManager;
//import com.pirateman.recruitercallsinterviews.InterviewsFragment;
//import com.pirateman.recruitercallsservice.RecruiterCallService;
//import com.pirateman.staffingcompanies.StaffingCompaniesFragment;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import permissions.dispatcher.NeedsPermission;
//import permissions.dispatcher.OnPermissionDenied;
//import permissions.dispatcher.OnShowRationale;
//import permissions.dispatcher.PermissionRequest;
//import permissions.dispatcher.RuntimePermissions;
//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//@RuntimePermissions
class PocketEmploymentTodoList : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ServiceConnection /*,  JobPositionsDialogFragment.AddJobPositionsListener, JobContactDetailsFragment.OnAddContactListener*/
{
    lateinit var drawerToggle: ActionBarDrawerToggle
    //internal var actionBar: ActionBar? = null

    //    @BindView(R.id.drawer_listview)
    //    ListView drawerListView;

    //@BindView(R.id.drawer_layout)
    internal var drawerLayout: DrawerLayout? = null

    //@BindView(R.id.tbNavDrawer)
    //internal var toolbar: Toolbar? = null

    //@BindView(R.id.nav_view)
    internal var navigationView: NavigationView? = null

    private val SERVICE_STARTED = 1

    internal var serviceIntent: Intent? = null

    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pocket_employment_drawer_layout)

        fragmentManager = supportFragmentManager
        //
        //        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        //        {
        //            JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(), getLocalClassName()))
        //                    .setPeriodic(5000)
        //                    .build();
        //
        //            JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        //            tm.schedule(jobInfo);
        //
        //        }

        serviceIntent = Intent(this, RecruiterCallService::class.java)
        //region Action bar setup
        //        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        this.setSupportActionBar(tbNavDrawer)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
            //Show the activity icon/logo
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        //endregion

        //TODO: use service to query particular phone numbers
        this.bindService(serviceIntent, this, Context.BIND_AUTO_CREATE)

        //fragmentManager = this.supportFragmentManager

        val recruiterFragmentAction = intent.getStringExtra("recruitersFragment")
        val recruiterPhoneNumber = intent.getStringExtra("phoneNumber")



        if (recruiterFragmentAction != null)
        {
            if (recruiterFragmentAction == "addRecruiterFragment")
            {
                //                CallsFragment callsFragment = new CallsFragment();
                //
                //                setFragment(callsFragment);
            }
        }


        //region Manage Fragment Backstack
        //        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager
        // .OnBackStackChangedListener()
        //        {
        //            @Override
        //            public void onBackStackChanged()
        //            {
        //                int currentCount = getSupportFragmentManager().getBackStackEntryCount();
        //
        //                if (currentCount > 0)
        //                {
        //                    FragmentManager.BackStackEntry currentBackStackEntry =
        // getSupportFragmentManager().getBackStackEntryAt(currentCount - 1);
        //                    int fragmentPosition = currentBackStackEntry.getId();
        //                    drawerListView.setItemChecked(fragmentPosition, true);
        //                }
        //            }
        //        });
        //endregion

        //region Setup Navigation Drawer

        drawerToggle = ActionBarDrawerToggle(this, drawer_layout,
                tbNavDrawer, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)


        drawerToggle!!.setHomeAsUpIndicator(R.drawable.ic_action_name);
        drawer_layout.addDrawerListener(drawerToggle!!)
        //        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.END);

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.itemIconTintList = null


        //        JobMarketDetailsMenuFragment jmdmf = new JobMarketDetailsMenuFragment();
        if (recruiterPhoneNumber != null)
        {
            val data = Bundle()
            data.putString("phoneNumber", recruiterPhoneNumber)
            //            jmdmf.setArguments(data);
        }

        //        setFragment(jmdmf);

    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?)
    {
        super.onPostCreate(savedInstanceState, persistentState)
        drawerToggle!!.syncState()
    }

    //    @Override
    //    public boolean onSupportNavigateUp()
    //    {
    //        getSupportFragmentManager().popBackStack();
    //        return true;
    //    }

    //
    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu)
    //    {
    //        // Inflate the menu; this adds items to the action bar if it is present.
    //        this.getMenuInflater().inflate(R.menu.menu_recruiter_calls_todo_list, menu);
    //
    //        return true;
    //    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return if (drawerToggle.onOptionsItemSelected(item))
        {
            true
        } else super.onOptionsItemSelected(item)

        //        if (item.getItemId() == R.id.calls_history)
        //        {
        //            Intent intent = new Intent(this, RecruiterCallsLogActivity.class);
        //            startActivity(intent);
        //        }

    }

    override fun setTitle(title: CharSequence)
    {
        if (actionBar != null)
        {
            actionBar!!.title = title
        }
    }

    override fun onBackPressed()
    {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else
        {
            //Add dialog "Are you sure you want to exit?"
            if (supportFragmentManager.backStackEntryCount > 1)
            {
                //TODO: fix this exception:
                // java.lang.IllegalArgumentException: No view found for id 0x7f1100b7 (com.pirateman.recruitercallsactivity:id/child_job_market_details_menu) for fragment TodoListFragment{a1da317 #3 id=0x7f1100b7}
                //                getSupportFragmentManager().popBackStackImmediate();
                //                getSupportFragmentManager().beginTransaction().commit();
            }
            finish()
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        //        switch (item.getItemId())
        //        {
        //            case R.id.nav_recruitingCompanies:
        //                setFragment(new StaffingCompaniesFragment());
        //                break;
        //            case R.id.nav_calls:
        //                //startActivity(new Intent(this, CallsActivity.class));
        //                RecruiterCallsTodoListPermissionsDispatcher.setCallsFragmentWithCheck(this, new CallsFragment());
        //
        //                break;
        //            case R.id.nav_recruiters:
        ////                startActivity(new Intent(this, RecruiterCallsActivity.class));
        ////                setFragment(new RecruitersFragment());
        //                break;
        //            case R.id.nav_settings:
        ////                startActivity(new Intent(this, RecruiterPreferencesActivity.class));
        //                break;
        //            case R.id.nav_calls_log:
        ////                startActivity(new Intent(this, RecruiterCallsLogActivity.class));
        //                break;
        //            case R.id.nav_interviews:
        ////                RecruiterCallsTodoListPermissionsDispatcher.setInterviewsFragmentWithCheck(this, new InterviewsFragment());
        //                break;
        //        }

        fragmentManager.executePendingTransactions()

        drawerLayout!!.closeDrawer(GravityCompat.START)
        return false
    }


    //    @Override
    //    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    //    {
    //        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    //        RecruiterCallsTodoListPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
    //                grantResults);
    //    }

    //    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    //    @OnShowRationale({Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG})
    protected fun ShowGetCallsRationale(/*PermissionRequest request*/)
    {
        //ShowRationaleDialog("This app needs your permission to read and write to your calls log", request);
    }


    //    @OnShowRationale({Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR})
    protected fun ShowGetAccountsRationale( /*PermissionRequest request*/)
    {
        //ShowRationaleDialog("This app needs your permission to read and write to your calendar", request);
    }

    //    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    //    @OnPermissionDenied({Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR})
    protected fun onPermissionDenied()
    {
        this.recreate()
    }

    private fun ShowRationaleDialog(message: String /*, final PermissionRequest request*/)
    {
        AlertDialog.Builder(this).setPositiveButton("Allow") { dialog, which ->
            //request.proceed();
        }.setNegativeButton("Deny") { dialog, which ->
            //request.cancel();
        }.setCancelable(false).setMessage(message).show()
    }

    override fun onPause()
    {
        stopService(serviceIntent)
        super.onPause()
    }

    //    @Override
    fun sendData(data: String)
    {
        //        JobMarketDetailsMenuFragment jmdmf = new JobMarketDetailsMenuFragment();
        //        Bundle b = new Bundle();
        //        b.putString("data", data);
        //        jmdmf.setArguments(b);
        //        setFragment(jmdmf);
    }

    //Works in the activity and not the Application Context
    //    @Override
    //    protected void attachBaseContext(Context base)
    //    {
    //        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    //    }

    override fun onServiceConnected(name: ComponentName, service: IBinder)
    {
        //        RecruiterCallService.RecruiterCallBinder binder = (RecruiterCallService.RecruiterCallBinder) service;
        //
        //        recruiterCallService = binder.getService();

        //        Context context = recruiterCallService.getBaseContext();
        //TODO: add to notification manager

        //        Intent intent = new Intent(context, RecruiterCallsTodoList.class);
        //        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //        Notification notification = CallsNotificationManager.getInstance(recruiterCallService).setUpNotification(SERVICE_STARTED, "Service Started", pendingIntent, true);
        //
        //        recruiterCallService.startForeground(SERVICE_STARTED, notification);

    }

    override fun onServiceDisconnected(name: ComponentName)
    {
        //        CallsNotificationManager.getInstance(recruiterCallService).stopNotification(SERVICE_STARTED);
    }

    //    @Override
    fun onAddContact(message: String)
    {
        if (message.equals("RecruiterUpdated", ignoreCase = true))
        {
            //            JobMarketDetailsMenuFragment jmdmf = new JobMarketDetailsMenuFragment();
            //            Bundle data = new Bundle();
            //            data.putString("JobContactDetailsFragment", message);
            //            jmdmf.setArguments(data);
            //
            //            setFragment(jmdmf);
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()

        //        this.unbindService(this);
    }

    companion object
    {

        //    private RecruiterCallService recruiterCallService;

        private var fragmentManager: FragmentManager? = null

        //    @NeedsPermission({Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR})
        fun setInterviewsFragment(fragment: Fragment)
        {
            //        if (!fragment.isAdded())
            //        {
            //State is lost when permission is revoked; when permission is granted; commit after
            // activity state is saved
            //            fragmentManager.beginTransaction()
            //                    .add(R.id.drawer_layout_container, fragment)
            //                    .addToBackStack("InterviewsFragment")
            //                    .commitAllowingStateLoss();

            //        }
            //        else
            //        {
            //        fragmentManager.beginTransaction()
            //                .replace(R.id.drawer_layout_container, fragment)
            //                .addToBackStack("InterviewsFragment")
            //                .commitAllowingStateLoss();
            //        }
        }


        fun setFragment(b: Bundle, fragment: Fragment)
        {
            fragment.arguments = b

            //        fragmentManager.beginTransaction()
            //                .replace(R.id.drawer_layout_container, fragment)
            //                .addToBackStack(null)
            //                .commit();
        }


        //    @NeedsPermission({Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG})
        fun setCallsFragment(fragment: Fragment)
        {
            //        fragmentManager.beginTransaction()
            //                .replace(R.id.drawer_layout_container, fragment)
            //                .addToBackStack(null)
            //                .commitAllowingStateLoss();
        }

        fun setFragment(fragment: Fragment)
        {
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.drawer_layout_container, fragment)
//                            .addToBackStack(null)
//                            .commit();
        }

        fun replaceFragment(fragment: Fragment)
        {
            if (!fragment.isAdded)
            {
                //            fragmentManager.beginTransaction()
                //                    .replace(R.id.drawer_layout_container, fragment)
                //                    .addToBackStack(null)
                //                    .commit();
            }
        }


        fun setFragmentInActivity(fragment: Fragment)
        {
            if (!fragment.isAdded)
            {
                //            fragmentManager.beginTransaction()
                //                    .add(R.id.tbNavDrawer, fragment)
                //                    .addToBackStack(null)
                //                    .commit();
            }
        }
    }

}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


