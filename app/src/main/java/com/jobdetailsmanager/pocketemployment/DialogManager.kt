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

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast

class DialogManager(private val context: Context)
{

    private val dialog = AlertDialog.Builder(context)

    fun showDialog(title: String, dialogMessage: String, yesMessage: String, noMessage: String)
    {
        dialog.setTitle(title)
                .setMessage(dialogMessage)
                .setPositiveButton("Yes") { dialog, which ->
                    Toast.makeText(context, yesMessage, Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No") { dialog, which ->
                    Toast.makeText(context, noMessage, Toast.LENGTH_LONG).show() }
        val ad = dialog.create()

        ad.show()
    }

    fun showOkDialog(title: String, dialogMessage: String)
    {
        dialog.setTitle(title).setMessage(dialogMessage).setNeutralButton("OK") { dialog, which -> dialog.dismiss() }

        val ad = dialog.create()

        ad.show()
    }

    fun showDialogFromService(title: String, incomingNumber: String)
    {
        dialog.setTitle(title).setMessage("Is $incomingNumber from a recruiter?").setPositiveButton("Yes") { dialog, which ->
                    //isRecruiter = true;
                    Toast.makeText(context, "Do something if it is a Recruiter", Toast.LENGTH_LONG).show()
                }.setNegativeButton("No") { dialog, which ->
                    //isRecruiter = false;
                }
        val ad = dialog.create()

        ad.show()
    }


}
