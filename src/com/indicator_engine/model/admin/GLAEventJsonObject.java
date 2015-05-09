/*
 *
 *  * Copyright (C) 2015  Tanmaya Mahapatra
 *  *
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version 2
 *  * of the License, or (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package com.indicator_engine.model.admin;

import com.google.gson.annotations.Expose;
import com.indicator_engine.datamodel.GLAEvent;

import java.util.List;

/**
 * Created by Tanmaya Mahapatra on 23-03-2015.
 */
@SuppressWarnings({"unused", "unchecked"})
public class GLAEventJsonObject {

    @Expose
    int iTotalRecords;

    @Expose
    int iTotalDisplayRecords;

    @Expose
    String sEcho;

    @Expose
    String sColumns;
    @Expose
    List<GLAEvent> aaData;

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    public List<GLAEvent> getAaData() {
        return aaData;
    }

    public void setAaData(List<GLAEvent> aaData) {
        this.aaData = aaData;
    }
}
