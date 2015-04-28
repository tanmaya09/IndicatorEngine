package com.indicator_engine.indicator_system.Number;

import com.indicator_engine.model.indicator_system.Number.EntityValues;
import com.indicator_engine.model.indicator_system.Number.UserSearchSpecifications;
import org.drools.definition.process.*;

import java.util.List;

/**
 * Created by Tanmaya Mahapatra on 26-04-2015.
 */
@SuppressWarnings({"unused", "unchecked"})
public class ProcessUserFilters implements ProcessUserFiltersDao {

    @Override
    public String processEntities( List<EntityValues> entityValues , String filter){
        String hibernateQuery = "(";
        String key, type, eValue;
        key = type = eValue = null;
        int counter = 1;
        int totalLength = entityValues.size();
        for (EntityValues eV : entityValues)
        {
            key = eV.getKey();
            type = eV.getType();
            eValue = eV.geteValues();
            if(type.equals("Text"))
            {
                if(eValue.equals("ALL"))
                {
                    if(counter == 1){
                        hibernateQuery += " ( SELECT key FROM GLAEntity WHERE key = '"+key +"' OR  ";
                    }
                    else if(counter == totalLength)
                        hibernateQuery += "  key = '"+key +"' ) ";
                    else
                        hibernateQuery += "  key = '"+key +"' OR  ";
                }
                else
                {
                    if(totalLength == 1)
                    {
                        hibernateQuery += " ( SELECT key FROM GLAEntity WHERE ( key = '"+key +"' "+ filter + " value LIKE'"+eValue+"' ) ) ";
                    }
                    else
                    {
                        if(counter == 1){
                            hibernateQuery += " ( SELECT key FROM GLAEntity WHERE ( key = '"+key +"' "+ filter + " value LIKE '"+eValue+"' ) OR ";
                        }
                        else if(counter == totalLength)
                            hibernateQuery += " (  key = '"+key +"' "+ filter + " value LIKE '"+eValue+"' ) ) ";
                        else
                            hibernateQuery += " (  key = '"+key +"' "+ filter + " value LIKE '"+eValue+"' ) OR";
                    }
                }
            }
            if(type.equals("Regex"))
            {
                if(eValue.equals("ALL"))
                {
                    if(counter == 1){
                        hibernateQuery += " ( SELECT key FROM GLAEntity WHERE key LIKE '%"+key +"%' OR  ";
                    }
                    else if(counter == totalLength)
                        hibernateQuery += "  key LIKE '%"+key +"%' ) ";
                    else
                        hibernateQuery += "  key LIKE '%"+key +"%' OR  ";
                }
                else
                {
                    if(totalLength == 1)
                    {
                        hibernateQuery += " ( SELECT key FROM GLAEntity WHERE ( key LIKE '%"+key +"%' " + filter + " value LIKE '%"+eValue+"%' ) ) ";
                    }
                    else
                    {
                        if(counter == 1){
                            hibernateQuery += " ( SELECT key FROM GLAEntity WHERE ( key LIKE '%"+ key +"%' " + filter + " value LIKE '%"+eValue+"%' ) OR ";
                        }
                        else if(counter == totalLength)
                            hibernateQuery += " (  key LIKE '%"+key +"%' " + filter + " value LIKE '%"+eValue+"%' ) ) ";
                        else
                            hibernateQuery += " (  key LIKE '%"+key +"%' " + filter + " value LIKE '%"+eValue+"%' ) OR";
                    }
                }
            }

            counter++;
        }
        hibernateQuery += " )";
        return hibernateQuery;
    }

    @Override
    public String processUsers( List<UserSearchSpecifications>  userSpecifications, String filter){
        String hibernateQuery = "(";
        String pattern, type;
        List<String> user;
        pattern = type = null;
        int counter = 1;
        int totalLength = userSpecifications.size();
        for(UserSearchSpecifications userSpec : userSpecifications){
            user = userSpec.getUserSearch();
            type = userSpec.getUserSearchType();
            pattern = userSpec.getSearchPattern();
            if(pattern.equals("EXACT")) {
                if(type.equals("UserName")) {

                }
            }
        }
        hibernateQuery += " )";
        return hibernateQuery;

    }

}
