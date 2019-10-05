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
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

//@Entity(tableName = "recruiter_companies")
public class RecruiterCompany
{
    public RecruiterCompany(){}

    @Generated(hash = 1244470350)
    public RecruiterCompany(int recruiterCompanyId, String address,
            String website) {
        this.recruiterCompanyId = recruiterCompanyId;
        this.address = address;
        this.website = website;
    }

    public int getRecruiterCompanyId() {
        return this.recruiterCompanyId;
    }

    public void setRecruiterCompanyId(int recruiterCompanyId) {
        this.recruiterCompanyId = recruiterCompanyId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
//    @Generated(hash = 232104174)
//    public List<Recruiter> getRecruiters() {
//        if (recruiters == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            RecruiterDao targetDao = daoSession.getRecruiterDao();
//            List<Recruiter> recruitersNew = targetDao
//                    ._queryRecruiterCompany_Recruiters(recruiterCompanyId);
//            synchronized (this) {
//                if (recruiters == null) {
//                    recruiters = recruitersNew;
//                }
//            }
//        }
//        return recruiters;
//    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1628980993)
    public synchronized void resetRecruiters() {
        recruiters = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
//    @Generated(hash = 128553479)
//    public void delete() {
//        if (myDao == null) {
//            throw new DaoException("Entity is detached from DAO context");
//        }
//        myDao.delete(this);
//    }
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

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
//    @Generated(hash = 713229351)
//    public void update() {
//        if (myDao == null) {
//            throw new DaoException("Entity is detached from DAO context");
//        }
//        myDao.update(this);
//    }

    /** called by internal mechanisms, do not call yourself. */
//    @Generated(hash = 353464922)
//    public void __setDaoSession(DaoSession daoSession) {
//        this.daoSession = daoSession;
//        myDao = daoSession != null ? daoSession.getRecruiterCompanyDao() : null;
//    }

    @Id
    private int recruiterCompanyId;

    private String address;

    private String website;

    @ToMany(referencedJoinProperty = "recruiterId")
    private List<Recruiter> recruiters;

    /** Used to resolve relations */
//    @Generated(hash = 2040040024)
//    private transient DaoSession daoSession;
//
//    /** Used for active entity operations. */
//    @Generated(hash = 997927586)
//    private transient RecruiterCompanyDao myDao;


}
