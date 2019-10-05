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

package com.jobdetailsmanager.pocketemployment;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

@Entity(nameInDb = "recruiters")
public class Recruiter
{
    public Recruiter(){}


    @Generated(hash = 1554464229)
    public Recruiter(long recruiterId, String phoneNumber, String emailAddress) {
        this.recruiterId = recruiterId;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Id(autoincrement = true)
    private long recruiterId;

    private String phoneNumber;

    private String emailAddress;

    @ToOne(joinProperty = "recruiterId")
    private RecruiterCompany recruiterCompany;

//    /** Used to resolve relations */
//    @Generated(hash = 2040040024)
//    private transient DaoSession daoSession;
//
//    /** Used for active entity operations. */
//    @Generated(hash = 1952583829)
//    private transient RecruiterDao myDao;

    @Generated(hash = 1263727731)
    private transient Long recruiterCompany__resolvedKey;


    public long getRecruiterId() {
        return recruiterId;
    }


    public void setRecruiterId(long recruiterId) {
        this.recruiterId = recruiterId;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getEmailAddress() {
        return this.emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /** To-one relationship, resolved on first access. */
//    @Generated(hash = 68063210)
//    public RecruiterCompany getRecruiterCompany() {
//        long __key = this.recruiterId;
//        if (recruiterCompany__resolvedKey == null
//                || !recruiterCompany__resolvedKey.equals(__key)) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            RecruiterCompanyDao targetDao = daoSession.getRecruiterCompanyDao();
//            RecruiterCompany recruiterCompanyNew = targetDao.load(__key);
//            synchronized (this) {
//                recruiterCompany = recruiterCompanyNew;
//                recruiterCompany__resolvedKey = __key;
//            }
//        }
//        return recruiterCompany;
//    }
//
//
//    /** called by internal mechanisms, do not call yourself. */
//    @Generated(hash = 808989431)
//    public void setRecruiterCompany(@NotNull RecruiterCompany recruiterCompany) {
//        if (recruiterCompany == null) {
//            throw new DaoException(
//                    "To-one property 'recruiterId' has not-null constraint; cannot set to-one to null");
//        }
//        synchronized (this) {
//            this.recruiterCompany = recruiterCompany;
//            recruiterId = recruiterCompany.getRecruiterCompanyId();
//            recruiterCompany__resolvedKey = recruiterId;
//        }
//    }
//
//
//    /**
//     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
//     * Entity must attached to an entity context.
//     */
//    @Generated(hash = 128553479)
//    public void delete() {
//        if (myDao == null) {
//            throw new DaoException("Entity is detached from DAO context");
//        }
//        myDao.delete(this);
//    }
//
//
//    /**
//     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
//     * Entity must attached to an entity context.
//     */
//    @Generated(hash = 1942392019)
//    public void refresh() {
//        if (myDao == null) {
//            throw new DaoException("Entity is detached from DAO context");
//        }
//        myDao.refresh(this);
//    }
//
//
//    /**
//     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
//     * Entity must attached to an entity context.
//     */
//    @Generated(hash = 713229351)
//    public void update() {
//        if (myDao == null) {
//            throw new DaoException("Entity is detached from DAO context");
//        }
//        myDao.update(this);
//    }
//
//
//    /** called by internal mechanisms, do not call yourself. */
//    @Generated(hash = 2038956903)
//    public void __setDaoSession(DaoSession daoSession) {
//        this.daoSession = daoSession;
//        myDao = daoSession != null ? daoSession.getRecruiterDao() : null;
//    }

}
