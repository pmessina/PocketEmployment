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

package com.jobdetailsmanager.pocketemployment.recruiters

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jobdetailsmanager.pocketemployment.R

//TODO: Program Add To Contacts button
class RecruiterCallsDetailsActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recruiter_calls_details)

        //getFragmentManager().beginTransaction().replace(R.layout.activity_recruiter_calls_details, new JobContactDetailsFragment()).commit();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_recruiter_calls_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings)
        {
            true
        } else super.onOptionsItemSelected(item)

    }
}

/**
 * Code for adding a contact to the phone's contacts
 *
 * private var emailAddress: EditText? = null
private var phoneNumber: EditText? = null
...
/* Assumes EditText fields in your UI contain an email address
 * and a phone number.
 *
*/
emailAddress = findViewById(R.id.email)
phoneNumber = findViewById(R.id.phone)
...
/*
 * Inserts new data into the Intent. This data is passed to the
 * contacts app's Insert screen
*/
intent.apply {
// Inserts an email address
putExtra(ContactsContract.Intents.Insert.EMAIL, emailAddress?.text)
/*
 * In this example, sets the email type to be a work email.
 * You can set other email types as necessary.
*/
putExtra(
ContactsContract.Intents.Insert.EMAIL_TYPE,
ContactsContract.CommonDataKinds.Email.TYPE_WORK
)
// Inserts a phone number
putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber?.text)
/*
 * In this example, sets the phone type to be a work phone.
 * You can set other phone types as necessary.
*/
putExtra(
ContactsContract.Intents.Insert.PHONE_TYPE,
ContactsContract.CommonDataKinds.Phone.TYPE_WORK
)
}
 *
 *
 * Code for editing a contact
 *
 *     // The Cursor that contains the Contact row
var mCursor: Cursor? = null
// The index of the lookup key column in the cursor
var lookupKeyIndex: Int = 0
// The index of the contact's _ID value
var idIndex: Int = 0
// The lookup key from the Cursor
var currentLookupKey: String? = null
// The _ID value from the Cursor
var currentId: Long = 0
// A content URI pointing to the contact
var selectedContactUri: Uri? = null
...
/*
 * Once the user has selected a contact to edit,
 * this gets the contact's lookup key and _ID values from the
 * cursor and creates the necessary URI.
*/
mCursor?.apply {
// Gets the lookup key column index
lookupKeyIndex = getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)
// Gets the lookup key value
currentLookupKey = getString(lookupKeyIndex)
// Gets the _ID column index
idIndex = getColumnIndex(ContactsContract.Contacts._ID)
currentId = getLong(idIndex)
selectedContactUri = ContactsContract.Contacts.getLookupUri(currentId, mCurrentLookupKey)
}

// Creates a new Intent to edit a contact
val editIntent = Intent(Intent.ACTION_EDIT).apply {
/*
 * Sets the contact URI to edit, and the data type that the
 * Intent must match
*/
setDataAndType(selectedContactUri, ContactsContract.Contacts.CONTENT_ITEM_TYPE)
}
 *
 */

