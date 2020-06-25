package com.jobdetailsmanager.pocketemployment.recruiters

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.NavUtils
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import com.jobdetailsmanager.pocketemployment.DialogManager
import com.jobdetailsmanager.pocketemployment.R
import com.jobdetailsmanager.pocketemployment.inTransaction


open class RecruiterCallsActivity : AppCompatActivity()
{
    internal var intent: Intent? = null

    private val recruiterCallService: RecruiterCallService? = null

    private var broadcaster: LocalBroadcastManager? = null

    //lateinit var contactArrayAdapter: ArrayAdapter<Contact>

    internal var listView: ListView? = null

    //private DrawerLayout drawerLayout;

    private val dialogManager: DialogManager? = null

    private var drawerToggle: ActionBarDrawerToggle? = null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_recruiter_calls);
        setContentView(R.layout.activity_pocket_employment_drawer_layout)

        val toolbar = this.findViewById<View>(R.id.tbNavDrawer) as Toolbar

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        val drawerLayout = this.findViewById<View>(R.id.drawer_layout) as DrawerLayout
        //R.drawable.ic_drawer,
        drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        {
            override fun onDrawerOpened(drawerView: View)
            {
                drawerToggle!!.setHomeAsUpIndicator(com.google.android.material.R.drawable.abc_cab_background_top_mtrl_alpha)

                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View)
            {
                drawerToggle!!.setHomeAsUpIndicator(R.drawable.ic_drawer)
                invalidateOptionsMenu()
            }
        }

        drawerToggle!!.setHomeAsUpIndicator(R.drawable.ic_drawer)
        drawerLayout.addDrawerListener(drawerToggle!!)
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)

        //NavDrawerManager.CreateNavDrawer(this, drawerLayout);

        //        TODO:Code to implement later for scheduling follow-ups on Calendar
        //        AlarmManager am = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        //        AlarmManager.AlarmClockInfo aci = new AlarmManager.AlarmClockInfo()
        //
        //        am.setAlarmClock();

        //TODO: test
        val sfm = this.supportFragmentManager

        supportFragmentManager.inTransaction {
            //replace(R.id.drawer_layout_container, RecruitersFragment())
            addToBackStack(null)
        }
        //sfm.beginTransaction().replace(R.id.drawer_layout_container, RecruitersFragment()).addToBackStack(null).commit()

    }

    override fun onBackPressed()
    {
        if (supportFragmentManager.backStackEntryCount > 1)
        {
            supportFragmentManager.popBackStackImmediate()
            supportFragmentManager.beginTransaction().commit()
        }
        finish()
    }

    override fun onResume()
    {
        super.onResume()

        broadcaster = LocalBroadcastManager.getInstance(this)

        //        final SharedPreferences spIncomingNumber = PreferenceManager.getDefaultSharedPreferences(this);
        //        final String incomingNumberCache = spIncomingNumber.getString("incomingNumber", "");
        //
        //        if (!incomingNumberCache.isEmpty())
        //        {
        //            dialogManager.showDialogFromService("Recruiter?", incomingNumberCache);
        //            spIncomingNumber.edit().remove("incomingNumber").apply();
        //
        //            spIncomingNumber.edit().putBoolean("isRecruiter", isRecruiter).apply();
        //
        //            sendIsRecruiter(isRecruiter);
        //
        //        }
        //
        //        boolean incomingNumberExists = this.getIntent().getBooleanExtra("incomingNumberExists",false);

    }
    //
    //    public void sendIsRecruiter(boolean isRecruiter)
    //    {
    //        Intent intent = new Intent(RecruiterCallService.IS_RECRUITER);
    //        intent.putExtra("isRecruiter", isRecruiter);
    //
    //        broadcaster.sendBroadcast(intent);
    //    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        //this.getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menuInflater.inflate(R.menu.action_bar_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (drawerToggle!!.onOptionsItemSelected(item))
        {
            return true
        }

        when (item.itemId)
        {
            android.R.id.home ->
            {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?)
    {
        super.onPostCreate(savedInstanceState, persistentState)
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration)
    {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }


    override fun onDestroy()
    {
        super.onDestroy()

        //this.unbindService(serviceConnection);

    }


}






