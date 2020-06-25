package com.jobdetailsmanager.pocketemployment.recruiters

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

import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.telephony.PhoneNumberUtils
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import com.jobdetailsmanager.pocketemployment.PocketEmploymentTodoList
import com.jobdetailsmanager.pocketemployment.calls.CallsNotificationManager
import java.util.*


//val callsNotificationManager = Kodein.Module{
//    bind<CallsNotificationManager>() with factory { context:Context -> CallsNotificationManager(context) }
//}


class RecruiterCallService : Service() {

    internal var binder: IBinder = RecruiterCallBinder()

    internal var incomingNumberExists: Boolean = false

    private var telephonyManager: TelephonyManager? = null

    private var callStateListener: PhoneStateListener? = null

    private val SERVICE_STARTED = 0
    private val CALL_INCOMING = 1
    private val CALL_RECEIVED = 2

    //val recruiterViewModel: RecruiterCallsViewModel by inject()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        //        callsNotificationManager = com.jobdetailsmanager.pocketemployment.calls.CallsNotificationManager.getInstance(this);
        //        callsNotificationManager.setUpNotification(1, "Service Started", PendingIntent.getActivity(this, 0, intent, 0), true);

        //        IntentFilter filter= new IntentFilter();
        //        filter.addAction("android.intent.action.PHONE_STATE");
        //        filter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        //        registerReceiver(new IncomingCallReceiver(), filter);

        return Service.START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {

        super.onCreate()

        val intent = Intent(this@RecruiterCallService, PocketEmploymentTodoList::class.java)

        val callsNotificationManager = CallsNotificationManager(this)
        //callsNotificationManager.setUpNotification(SERVICE_STARTED, "Service Started", PendingIntent.getActivity(this, 0, intent, 0), true)

        //TODO: Refactor to query calls log


        telephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        try {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                //telephonyManager!!.listen(callStateListener, PhoneStateListener.LISTEN_NONE)

                callStateListener = object : PhoneStateListener() {



                    var ring = false
                    var callReceived = false

                    override fun onCallStateChanged(state: Int, incomingNumber: String?) {
                        super.onCallStateChanged(state, incomingNumber)


//                        Toast.makeText(this@RecruiterCallService, when (state) {
//                            0 -> "Idle"
//                            1 -> "Ringing"
//                            2 -> "Active"
//                            else -> "Invalid state"
//                        }, Toast.LENGTH_LONG).show()

                        if (incomingNumber.isNullOrEmpty()) return

                        if (state == TelephonyManager.CALL_STATE_IDLE) {

                            val pendingIntent = PendingIntent.getActivity(this@RecruiterCallService, 0, intent, 0)
                            callsNotificationManager.setUpNotification(CALL_RECEIVED, "Call Received from $incomingNumber", pendingIntent, false)
                            //processIncomingNumber(baseContext, incomingNumber, PreferenceManager.getDefaultSharedPreferences(this@RecruiterCallService))

                            if (callReceived) {
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

                                callsNotificationManager.setUpAddRecruiterCustomNotification(CALL_INCOMING, "Add $formattedPhoneNumber to the database?", yesPendingIntent!!, noPendingIntent!!)

                                //callsNotificationManager.setDialogNotification(0, "Add $formattedPhoneNumber to contacts?", yesPendingIntent!!, noPendingIntent!!)
                            }
                        }
                        if (state == TelephonyManager.CALL_STATE_RINGING) {

                            //Toast.makeText(baseContext, "${telephonyManager!!.callState} Incoming Number: $incomingNumber", Toast.LENGTH_SHORT).show()

                            val toRecruiterFragment = Intent(this@RecruiterCallService, PocketEmploymentTodoList::class.java)
                            toRecruiterFragment.flags = toRecruiterFragment.flags or Intent.FLAG_ACTIVITY_SINGLE_TOP
                            toRecruiterFragment.putExtra("recruitersFragment", "addRecruiterFragment")
                            toRecruiterFragment.putExtra("phoneNumber", incomingNumber)

                            val pendingIntent = PendingIntent.getActivity(this@RecruiterCallService, 0, toRecruiterFragment, PendingIntent.FLAG_CANCEL_CURRENT)
                            //callsNotificationManager = CallsNotificationManager.getInstance(RecruiterCallService.this);
//                            callsNotificationManager.setUpCustomNotification(CALL_INCOMING, "You have a phone call from: " + incomingNumber!!, pendingIntent)
                            callReceived = false

                            ring = true
                        }
                        if (state == TelephonyManager.CALL_STATE_OFFHOOK) {

                            callReceived = true
                            ring = false

                        }
                        if (!ring && callReceived) {
                            Toast.makeText(this@RecruiterCallService, "Answer call to add number to database", Toast.LENGTH_LONG).show()
                        }


                    }


                }

            }


            telephonyManager!!.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE)

        }
        catch(ex: NullPointerException){
            telephonyManager!!.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE)
        }

    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    inner class RecruiterCallBinder : Binder() {
        val service: RecruiterCallService
            get() = this@RecruiterCallService
    }

    //TODO: move to Fragment or class that handles data processing
    fun processIncomingNumber(context: Context, incomingNumber: String?, preferences: SharedPreferences) {
        //For Testing
        //Toast.makeText(getBaseContext(), state + " " + currentIncomingNumber, Toast.LENGTH_SHORT).show();
        //ring = true;
        //TODO: give user option to add into database or reject

        if (preferences.getBoolean("addToContacts", false)) {
            try {

                //val contactDao = greenDaoHelper.initSession().contactDao

                //val queryContact = contactDao.queryBuilder().where(ContactDao.Properties.ContactPhoneNumber.eq(incomingNumber))


//                if (queryContact.list().isEmpty())
//                {
//                    val contact = Contact()
//
//                    contact.contactPhoneNumber = incomingNumber
//                    contact.dateCallReceived = DateTime.now().toString()
//                    contact.contactCallState = RecruiterCallState.INITIAL_CALL.value
//
//                    Log.i("Contact", contact.toString())
//
//                    contactDao.insert(contact)
//
//                    greenDaoHelper.closeSession()
//
//                    Toast.makeText(context, incomingNumber!! + " stored in database", Toast.LENGTH_LONG).show()
//
//                } else
//                {
//                    Toast.makeText(context, incomingNumber!! + " exists in database", Toast.LENGTH_LONG).show()
//                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroy() {
        //  telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_NONE);

        //        CallsNotificationManager.Companion.getInstance(this).stopNotification(1);
        //        CallsNotificationManager.Companion.getInstance(this).stopNotification(2);

    }
}

