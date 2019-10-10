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

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText

class JobPositionsDialogFragment : DialogFragment()
{
    //@BindView(R.id.actvJobPositionTitle)
    internal var actvJobPositionTitle: AutoCompleteTextView? = null

    //@BindView(R.id.edtClientName)
    internal var edtClientName: EditText? = null

    lateinit var jobMarketDetailsMenuFragment: AddJobPositionsListener

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        jobMarketDetailsMenuFragment = context as AddJobPositionsListener
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        //TODO: change layout name
        val view = inflater.inflate(R.layout.fragment_dialog_add_job_positions, container, false)

        //TODO: Query database; insert existing job positions

        //val jobTitles = greenDaoHelper.initSession().jobPositionDao.queryBuilder().list() //Arrays.asList("Developer", "Engineer", "Programmer");

        //actvJobPositionTitle!!.setAdapter(ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, jobTitles))

        return view
    }

    //@OnClick(R.id.btnOkAddJobPosition)
    fun onButtonClick(view: View)
    {
//        val jobPositionDao = greenDaoHelper.initSession().jobPositionDao
//        //Won't need listener when database is implemented
//        val jobPosition = JobPosition()
//        jobPosition.jobPositionTitle = actvJobPositionTitle!!.text.toString()
//        val employer = Employer()
//        employer.employerName = edtClientName!!.text.toString()

        //TODO: fix employers variable and test relationship
        //jobPosition.employers = employer

        //jobPositionDao.insert(jobPosition)

        jobMarketDetailsMenuFragment.sendData("isFromViewPager")

        dialog?.dismiss()

    }


    //    @Override
    //    public Dialog onCreateDialog(Bundle savedInstanceState) {
    //        //String title = getArguments().getString("title");
    //        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    //        alertDialogBuilder.setTitle("Dialog");
    //        alertDialogBuilder.setMessage("Are you sure?");
    //        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
    //            @Override
    //            public void onClick(DialogInterface dialog, int which) {
    //                // on success
    //            }
    //        });
    //        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    //            @Override
    //            public void onClick(DialogInterface dialog, int which) {
    //                dialog.dismiss();
    //            }
    //        });
    //
    //        return alertDialogBuilder.create();
    //    }

    interface AddJobPositionsListener
    {
        fun sendData(data: String)
    }
}
