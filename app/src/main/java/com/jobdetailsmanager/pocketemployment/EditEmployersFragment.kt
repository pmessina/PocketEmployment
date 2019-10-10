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


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class EditEmployersFragment : Fragment()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //DatabaseHelper helper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);

        //TableUtils.createTableIfNotExists(helper.getConnectionSource(), Employer.class);

        //Dao<Employer, Integer> employerDao = helper.getEmployerDao();

        //val employerDao = greenDaoHelper.initSession().employerDao

//        val employer = Employer()
//        employerDao.insert(employer)

        //greenDaoHelper.closeSession()

    }

    //
    //    Dao<Contact, Integer> contactDao = OpenHelperManager.getHelper(getBaseContext(), DatabaseHelper.class).getContactDao();
    //
    //    UpdateBuilder<Contact, Integer> updateContact = contactDao.updateBuilder();
    //
    //    updateContact.updateColumnValue("recruiterFirstName", fn);
    //    updateContact.updateColumnValue("recruiterLastName", ln);
    //    updateContact.updateColumnValue("recruiterCompanyName", cn);
    //    updateContact.updateColumnValue("recruiterCallState", cs.getValue());
    //    updateContact.updateColumnValue("recruiterCompanyAddress", em);
    //
    //    updateContact.where().eq("contactId", contactId);
    //    Log.i("SQL Update", updateContact.prepareStatementInfo().getStatement());
    //
    //    contactDao.update(updateContact.prepare());
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_edit_employers, container, false)
    }

}
