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

@Entity(nameInDb = "job_positions")
public class JobPosition
{
    public JobPosition(){}

    @Generated(hash = 1000231783)
    public JobPosition(long jobPositionId, String jobPositionTitle,
            String jobPositionDescription, int jobPositionLevel, String applyDate,
            String interviewDate) {
        this.jobPositionId = jobPositionId;
        this.jobPositionTitle = jobPositionTitle;
        this.jobPositionDescription = jobPositionDescription;
        this.jobPositionLevel = jobPositionLevel;
        this.applyDate = applyDate;
        this.interviewDate = interviewDate;
    }

    @Id(autoincrement = true)
    private long jobPositionId;

    private String jobPositionTitle;

    private String jobPositionDescription;

    private int jobPositionLevel;

    private String applyDate;

    private String interviewDate;

    @ToMany(referencedJoinProperty = "employerId")
    private List<Employer> employers;

    @ToMany(referencedJoinProperty = "interviewId")
    private List<Interview> interviews;

    /** Used to resolve relations */
//    @Generated(hash = 2040040024)
//    private transient DaoSession daoSession;
//
//    /** Used for active entity operations. */
//    @Generated(hash = 2120035555)
//    private transient JobPositionDao myDao;

    public String getJobPositionTitle()
    {
        return jobPositionTitle;
    }

    public void setJobPositionTitle(String jobPositionTitle)
    {
        this.jobPositionTitle = jobPositionTitle;
    }

    public String getJobPositionDescription()
    {
        return jobPositionDescription;
    }

    public void setJobPositionDescription(String jobPositionDescription)
    {
        this.jobPositionDescription = jobPositionDescription;
    }

    public int getJobPositionLevel()
    {
        return jobPositionLevel;
    }

    public void setJobPositionLevel(int jobPositionLevel)
    {
        this.jobPositionLevel = jobPositionLevel;
    }

    public String getApplyDate()
    {
        return applyDate;
    }

    public void setApplyDate(String applyDate)
    {
        this.applyDate = applyDate;
    }

    public String getInterviewDate()
    {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate)
    {
        this.interviewDate = interviewDate;
    }

//    public Employer getEmployer()
//    {
//        return employer;
//    }

//    public void setEmployer(Employer employer)
//    {
//        this.employer = employer;
//    }

    public long getJobPositionId() {
        return this.jobPositionId;
    }

    public void setJobPositionId(long jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
//    @Generated(hash = 1585748693)
//    public List<Employer> getEmployers() {
//        if (employers == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            EmployerDao targetDao = daoSession.getEmployerDao();
//            List<Employer> employersNew = targetDao
//                    ._queryJobPosition_Employers(jobPositionId);
//            synchronized (this) {
//                if (employers == null) {
//                    employers = employersNew;
//                }
//            }
//        }
//        return employers;
//    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 34751883)
    public synchronized void resetEmployers() {
        employers = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
//    @Generated(hash = 772299490)
//    public List<Interview> getInterviews() {
//        if (interviews == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            InterviewDao targetDao = daoSession.getInterviewDao();
//            List<Interview> interviewsNew = targetDao
//                    ._queryJobPosition_Interviews(jobPositionId);
//            synchronized (this) {
//                if (interviews == null) {
//                    interviews = interviewsNew;
//                }
//            }
//        }
//        return interviews;
//    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 338530904)
    public synchronized void resetInterviews() {
        interviews = null;
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
//    /** called by internal mechanisms, do not call yourself. */
//    @Generated(hash = 313387886)
//    public void __setDaoSession(DaoSession daoSession) {
//        this.daoSession = daoSession;
//        myDao = daoSession != null ? daoSession.getJobPositionDao() : null;
//    }
}
