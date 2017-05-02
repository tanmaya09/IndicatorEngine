/*
 * Open Learning Analytics Platform (OpenLAP) : Indicator Engine

 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.indicator_engine.dao;

import com.indicator_engine.datamodel.GLAIndicator;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Provides Methods to perform CURD Operations on GLAIndicator
 * @author Tanmaya Mahapatra
 * @since 08/05/2015
 *
 */
public class GLAIndicatorDaoImpl implements GLAIndicatorDao {

    static Logger log = Logger.getLogger(GLAIndicatorDaoImpl.class.getName());
    @Autowired
    private SessionFactory factory;

    /**
     * Adds a new Indicator to the Database.
     * @param glaIndicator
     *          New Indicator which is to be created in the system.
     * @param glaQueries
     *          Every Indicator has a set of Queries/Questions associated with them. Must not be null.
     * @return
     *          The ID of the Indicator generated by Hibernate after Saving the Indicator. -1 on error.
     *
     */
    /* @Override
    @Transactional
    public long add(GLAIndicator glaIndicator) {
        if(glaIndicator == null || glaQueries == null)
            return -1;
        log.info("Executing add() : GLAIndicatorDaoImpl : MODE : ADD OR UPDATE");
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        factory.getCurrentSession().saveOrUpdate(glaIndicator);
        log.info("Indicator Saved..." + glaIndicator.getId());
        log.info("Saving associated Qeries \n");
        glaQueries.setGlaIndicator(glaIndicator);
        glaIndicator.getQueries().add(glaQueries);
        log.info("Finished Saving associated Qeries \n");
        factory.getCurrentSession().save(glaIndicator);
        GLAIndicatorProps glaIndicatorProps  = new GLAIndicatorProps();
        glaIndicatorProps.setTotalExecutions(1);
        glaIndicatorProps.setLast_executionTime(new java.sql.Timestamp(now.getTime()));
        glaIndicatorProps.setGlaIndicator(glaIndicator);
        glaIndicator.setGlaIndicatorProps(glaIndicatorProps);
        factory.getCurrentSession().save(glaIndicator);
        return glaIndicator.getId();
    } */

    /**
     * Loads an Indicator from Database.
     * @param ID Indicator ID to be searched and loaded.
     * @return The loaded Indicator.
     */

    @Override
    @Transactional
    public GLAIndicator loadByIndicatorID(long ID){
        Session session = factory.getCurrentSession();
        GLAIndicator glaIndicator = null;
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("glaQuestions", FetchMode.JOIN);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", ID));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Object result = criteria.uniqueResult();
        if (result != null) {
            glaIndicator = (GLAIndicator) result;
        }
        return glaIndicator;

    }

    /**
     * Loads an existing Indicator from the Database. Looks up using name in the Database. It does a similarity search in the database.
     * @param indicatorName Name of the Indicator used for lookup.
     * @return A List of similarly named Indicators present in the Database.
     */
    @Override
    @Transactional
    public List<GLAIndicator> loadByIndicatorByName(String indicatorName, boolean exact) {
        Session session = factory.getCurrentSession();
        indicatorName = "%"+indicatorName+"%";
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("queries", FetchMode.JOIN);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        if(exact)
            criteria.add(Restrictions.eq("indicator_name", indicatorName));
        else
            criteria.add(Restrictions.ilike("indicator_name", indicatorName));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return  criteria.list();

    }

    /**
     * Lists all the Indicators present in the Database.
     * @param colName
     *            Column Name to be used for Sorting the results
     * @param sortDirection
     *            Sort Direction : Ascending/Descending
     * @param sort
     *            True for sorting Required and False to set sorting of results off.
     * @return
     *           Listing of all the Indicators.
     *
     */
    @Override
    @Transactional
    public List<GLAIndicator> displayall(String colName, String sortDirection, boolean sort){
        Session session = factory.getCurrentSession();
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("queries", FetchMode.JOIN);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        criteria.setFetchMode("glaQuestions", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if(sort) {
            if(sortDirection.equals("asc"))
                criteria.addOrder(Order.asc(colName));
            else
                criteria.addOrder(Order.desc(colName));
        }
        return criteria.list();
    }

    /**
     * Lists all the Non-Composite Indicators present in the Database.
     * @param colName
     *            Column Name to be used for Sorting the results
     * @param sortDirection
     *            Sort Direction : Ascending/Descending
     * @param sort
     *            True for sorting Required and False to set sorting of results off.
     * @return
     *           Listing of all the Non-Composite Indicators.
     *
     */
    @Override
    @Transactional
    public List<GLAIndicator> displayAllNonComposite(String colName, String sortDirection, boolean sort){
        Session session = factory.getCurrentSession();
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.createAlias("glaIndicatorProps", "indProps");
        criteria.setFetchMode("indProps", FetchMode.JOIN);
        criteria.setFetchMode("glaQuestions", FetchMode.JOIN);
        criteria.add(Restrictions.eq("indProps.isComposite", false));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if(sort) {
            if(sortDirection.equals("asc"))
                criteria.addOrder(Order.asc(colName));
            else
                criteria.addOrder(Order.desc(colName));
        }
        return criteria.list();
    }


    /**
     * Loads Indicators based on a given ID Range.
     * @param startRange Starting ID
     * @param endRange Ending ID
     * @return List of Indicators loaded from the Database.
     */
    @Override
    @Transactional
    public List<GLAIndicator> loadIndicatorsRange(long startRange, long endRange){
        Session session = factory.getCurrentSession();
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("queries", FetchMode.JOIN);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        criteria.add(Restrictions.ge("id", startRange));
        criteria.add(Restrictions.le("id", endRange));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return  criteria.list();

    }

    /**
     * Searches for Indicators based on their Name
     *
     * @param searchParameter
     *            Name to be searched for
     * @param exactSearch
     *            True for exact Search, False for Similarity Search
     * @param colName
     *            Column Name to be used for Sorting the results
     * @param sortDirection
     *            Sort Direction : Ascending/Descending
     * @param sort
     *            True for sorting Required and False to set sorting of results off.
     *
     * @return Returns the Result of Similar or Exact search as a List<GLAIndicator>
     */

    @Override
    @Transactional
    public List<GLAIndicator> searchIndicatorsName(String searchParameter, boolean exactSearch,
                                                   String colName, String sortDirection, boolean sort){
        if(!exactSearch)
            searchParameter = "%"+searchParameter+"%";
        Session session = factory.getCurrentSession();
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("queries", FetchMode.JOIN);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        if(!exactSearch)
            criteria.add(Restrictions.ilike("indicator_name", searchParameter));
        else
            criteria.add(Restrictions.eq("indicator_name", searchParameter));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if(sort) {
            if(sortDirection.equals("asc"))
                criteria.addOrder(Order.asc(colName));
            else
                criteria.addOrder(Order.desc(colName));
        }
        return criteria.list();
    }

    /**
     * Deletes an existing Indicator using its ID. Deletes the Indicator if found else ignores.
     * @param indicator_id
     *          Specific Indicator with this ID to be deleted.
     */

    @Override
    @Transactional
    public void deleteIndicator(long indicator_id){
        GLAIndicator glaIndicator = null;
        Session session = factory.getCurrentSession();
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("queries", FetchMode.JOIN);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", indicator_id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Object result = criteria.uniqueResult();
        if (result != null) {
            glaIndicator = (GLAIndicator) result;
        }
        session.delete(glaIndicator);
    }

    /**
     * Counts the total number of Indicators present in the Database.
     * @return
     *      Total count of Indicators
     */
    @Override
    @Transactional
    public int getTotalIndicators() {
        Session session = factory.getCurrentSession();
        return ((Number) session.createCriteria(GLAIndicator.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();

    }

    /**
     * Finds an existing Indicator using its name. It does an exact lookup.
     * @param indicatorName Name of Indicator to be searched in the Database.
     * @return
     *      Returns the Indicator ID if a match is found.
     */
    @Override
    @Transactional
    public long findIndicatorID(String indicatorName) {
        Session session = factory.getCurrentSession();
        long indicatorID = 0;
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("queries", FetchMode.JOIN);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        criteria.setProjection(Projections.property("id"));
        criteria.add(Restrictions.eq("indicator_name", indicatorName));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Object result = criteria.uniqueResult();
        if (result != null) {
            indicatorID = (long) result;
        }
        return indicatorID;
    }

    /**
     * Updates the Execution Statistics Counter of a Specific Indicator.
     * @param ID Indicator ID to be searched and updated.
     *
     */

    @Override
    @Transactional
    public void updateStatistics(long ID){
        Session session = factory.getCurrentSession();
        GLAIndicator glaIndicator = null;
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("glaQuestions", FetchMode.JOIN);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", ID));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Object result = criteria.uniqueResult();
        if (result != null) {
            glaIndicator = (GLAIndicator) result;
        }
        glaIndicator.getGlaIndicatorProps().setLast_executionTime(new java.sql.Timestamp(now.getTime()));
        glaIndicator.getGlaIndicatorProps().setTotalExecutions(glaIndicator.getGlaIndicatorProps().getTotalExecutions()+1);
        factory.getCurrentSession().saveOrUpdate(glaIndicator);

    }

    @Override
    @Transactional
    public long findQuestionID(long indicatorID){
        Session session = factory.getCurrentSession();
        Criteria criteria = session.createCriteria(GLAIndicator.class);
        criteria.setFetchMode("glaIndicatorProps", FetchMode.JOIN);
        criteria.createAlias("glaQuestions", "questions");
        criteria.setFetchMode("questions", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", indicatorID));
        criteria.setProjection(Projections.property("questions.id"));

        return ((Long) criteria.uniqueResult()).longValue();

    }
}
