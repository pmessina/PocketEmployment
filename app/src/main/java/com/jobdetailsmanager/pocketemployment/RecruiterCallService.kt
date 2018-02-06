package com.jobdetailsmanager.pocketemployment

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

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.preference.PreferenceManager
import android.support.v4.app.TaskStackBuilder
import android.telephony.PhoneNumberUtils
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.KodeinService
import com.github.salomonbrys.kodein.android.ServiceInjector
import com.github.salomonbrys.kodein.android.appKodein

import org.joda.time.DateTime

import java.util.Locale

import greendao.Contact
import greendao.ContactDao


//val callsNotificationManager = Kodein.Module{
//    bind<CallsNotificationManager>() with factory { context:Context -> CallsNotificationManager(context) }
//}

class RecruiterCallService : Service()
{
    private val kodein = LazyKodein(appKodein)

    internal var binder: IBinder = RecruiterCallBinder()

    internal var incomingNumberExists: Boolean = false

    val callsNotificationManager:CallsNotificationManager by kodein.instance()

    private var telephonyManager: TelephonyManager? = null

    private var callStateListener: PhoneStateListener? = null

    private val SERVICE_STARTED = 0
    private val CALL_INCOMING = 1
    private val CALL_RECEIVED = 2


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {

        //        callsNotificationManager = com.jobdetailsmanager.pocketemployment.CallsNotificationManager.getInstance(this);
        //        callsNotificationManager.setUpNotification(1, "Service Started", PendingIntent.getActivity(this, 0, intent, 0), true);

        //        IntentFilter filter= new IntentFilter();
        //        filter.addAction("android.intent.action.PHONE_STATE");
        //        filter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        //        registerReceiver(new IncomingCallReceiver(), filter);

        return Service.START_STICKY
    }


    override fun onCreate()
    {
        //initializeInjector()

        //injector.inject(appKodein())

        super.onCreate()

        val intent = Intent(this@RecruiterCallService, PocketEmploymentTodoList::class.java)


        //callsNotificationManager = kodein().instance()

        //callsNotificationManager = CallsNotificationManager.Companion.getInstance(this);
        callsNotificationManager.setUpNotification(SERVICE_STARTED, "Service Started", PendingIntent.getActivity(this, 0, intent, 0), true)

        //TODO: Refactor to query calls log

        telephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        telephonyManager!!.listen(callStateListener, PhoneStateListener.LISTEN_NONE)

        callStateListener = object : PhoneStateListener()
        {
            internal var ring = false
            internal var callReceived = false

            @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.M)
            override fun onCallStateChanged(state: Int, incomingNumber: String?)
            {
                super.onCallStateChanged(state, incomingNumber)

                if (state == TelephonyManager.CALL_STATE_IDLE && incomingNumber != null && incomingNumber.isEmpty()) return

                if (state == TelephonyManager.CALL_STATE_IDLE)
                {

                    val pendingIntent = PendingIntent.getActivity(this@RecruiterCallService, 0, intent, 0)
                    callsNotificationManager.setUpNotification(CALL_RECEIVED, "Call Received from " + incomingNumber!!, pendingIntent, false)
                    processIncomingNumber(baseContext, incomingNumber, PreferenceManager.getDefaultSharedPreferences(this@RecruiterCallService))
                }
                if (state == TelephonyManager.CALL_STATE_RINGING)
                {
                    Toast.makeText(baseContext, telephonyManager!!.callState.toString() + " " + "Incoming Number: " + incomingNumber, Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@RecruiterCallService, PocketEmploymentTodoList::class.java)
                    intent.putExtra("recruitersFragment", "addRecruiterFragment")
                    intent.putExtra("phoneNumber", incomingNumber)

                    val pendingIntent = PendingIntent.getActivity(this@RecruiterCallService, 0, intent, 0)
                    //callsNotificationManager = CallsNotificationManager.Companion.getInstance(RecruiterCallService.this);
                    callsNotificationManager.setUpCustomNotification(CALL_INCOMING, "You have a phone call from: " + incomingNumber!!, pendingIntent)


                    ring = true
                }
                if (state == TelephonyManager.CALL_STATE_OFFHOOK)
                {
                    val taskStackBuilder = TaskStackBuilder.create(this@RecruiterCallService)

                    taskStackBuilder.addParentStack(PocketEmploymentTodoList::class.java)

                    //TODO: refactor to say Job contact and not recruiter
                    val yesIntent = Intent(this@RecruiterCallService, PocketEmploymentTodoList::class.java)
                    yesIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    yesIntent.putExtra("recruitersFragment", "addRecruiterFragment")
                    yesIntent.putExtra("phoneNumber", incomingNumber)
                    yesIntent.putExtra("addToContacts", true)

                    taskStackBuilder.addNextIntent(yesIntent)

                    val noIntent = Intent(this@RecruiterCallService, PocketEmploymentTodoList::class.java)
                    noIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    noIntent.putExtra("addToContacts", false)

                    taskStackBuilder.addNextIntent(noIntent)

                    val yesPendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
                    val noPendingIntent = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT)

                    val formattedPhoneNumber = PhoneNumberUtils.formatNumber(incomingNumber, Locale.getDefault().country)

                    callsNotificationManager!!.setDialogNotification(0, "Add $formattedPhoneNumber to contacts?", yesPendingIntent, noPendingIntent)


                    callReceived = true


                }
                if (!ring && callReceived)
                {
                    Toast.makeText(this@RecruiterCallService, "Missed call from " + incomingNumber!!, Toast.LENGTH_LONG).show()
                }

            }


        }

        telephonyManager!!.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE)
    }

    //    public class IncomingCallReceiver extends BroadcastReceiver
    //    {
    //        @Override
    //        public void onReceive(Context context, Intent intent)
    //        {
    //            Toast.makeText(context, "Incoming Call Received", Toast.LENGTH_LONG).show();
    //        }
    //    }

    override fun onBind(intent: Intent): IBinder?
    {
        return binder
    }

    inner class RecruiterCallBinder : Binder()
    {
        val service: RecruiterCallService
            get() = this@RecruiterCallService
    }

    //TODO: move to Fragment or class that handles data processing
    fun processIncomingNumber(context: Context, incomingNumber: String?, preferences: SharedPreferences)
    {
        //For Testing
        //Toast.makeText(getBaseContext(), state + " " + currentIncomingNumber, Toast.LENGTH_SHORT).show();
        //ring = true;
        //TODO: give user option to add into database or reject

        if (preferences.getBoolean("addToContacts", false))
        {
            try
            {
                val greenDaoHelper = GreenDaoHelper(context)


                val contactDao = greenDaoHelper.initSession().contactDao

                val queryContact = contactDao.queryBuilder().where(ContactDao.Properties.ContactPhoneNumber.eq(incomingNumber))


                if (queryContact.list().isEmpty())
                {
                    val contact = Contact()

                    contact.contactPhoneNumber = incomingNumber
                    contact.dateCallReceived = DateTime.now().toString()
                    contact.contactCallState = RecruiterCallState.INITIAL_CALL.value

                    Log.i("Contact", contact.toString())

                    contactDao.insert(contact)

                    greenDaoHelper.closeSession()

                    Toast.makeText(context, incomingNumber!! + " stored in database", Toast.LENGTH_LONG).show()

                } else
                {
                    Toast.makeText(context, incomingNumber!! + " exists in database", Toast.LENGTH_LONG).show()
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroy()
    {
        //  telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_NONE);

        //        CallsNotificationManager.Companion.getInstance(this).stopNotification(1);
        //        CallsNotificationManager.Companion.getInstance(this).stopNotification(2);
        super.onDestroy()
    }
}
