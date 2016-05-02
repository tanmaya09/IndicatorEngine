<div id="indicatorDefinition" class="col s12 m6 card">
    <div class="panel panel-default">
        <div class="panel-heading light-blue darken-2">Indicator</div>
        <div class="panel-body">
            <ul class="collapsible" data-collapsible="expandable">
                <li>
                    <div class="light-blue lighten-5 collapsible-header active">Basic Information</div>
                    <div class="collapsible-body panel-body">
                        <table class="table table-striped">
                            <tbody>
                            <tr>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="indicatorNaming">Indicator Name </label>
                                        <form:input path="indicatorName" type="text" class="form-control" name ="indicatorNaming"
                                                    id="indicatorNaming" onchange="validateIndicatorName()" required="required"
                                                    placeholder="Type your Indicator Name" title="Indicator Name msut be unique & must be more than 3 characters."/>
                                    </div>
                                </div>
                            </tr>
                            <tr>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="sourceSelection">Select a Source</label>
                                        <form:select multiple="true" class="form-control" title="You can select multiple sources i.e from where the data comes."
                                                     path="selectedSource" items="${selectNumberParameters.source}" name ="sourceSelection" id="sourceSelection" onchange="sourceChanged();" />
                                    </div>
                                </div>
                            </tr>
                            <tr>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="pull-left">
                                            <label for="PlatformSelection">Select a Platform &nbsp;</label>
                                        </div>
                                        <div style="padding-top: 10px">
                                            <div class="preloader-wrapper small active">
                                                <div class="spinner-layer spinner-green-only">
                                                    <div class="circle-clipper left">
                                                        <div class="circle"></div>
                                                    </div>
                                                    <div class="gap-patch">
                                                        <div class="circle"></div>
                                                    </div>
                                                    <div class="circle-clipper right">
                                                        <div class="circle"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <form:select class="form-control" title="You can select a single platform."
                                                     path="selectedPlatform" items="${selectNumberParameters.platform}" name ="PlatformSelection" id="PlatformSelection" onchange="platformChanged();" onfocus="this.selectedIndex = -1;"/>
                                    </div>
                                </div>
                            </tr>
                            <tr>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="actionSelection">Select an Action </label>
                                        <form:select class="form-control" title="Please select an action to poulate the Available Minors."
                                                     path="selectedAction" items="${selectNumberParameters.action}" name ="actionSelection" id="actionSelection"  onchange="populateCategories();" onfocus="this.selectedIndex = -1;"/>
                                    </div>
                                </div>
                            </tr>
                            <tr>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="actionSelection">Select a Category (Minors) </label>
                                        <form:select class="form-control" title="Please select a Minor to populate the relevant Attributes"
                                                     path="selectedMinor" items="${selectNumberParameters.minors}" name ="selectedMinor" id="selectedMinor" onchange="populateEntities();" onfocus="this.selectedIndex = -1;"/>
                                    </div>
                                </div>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </li>
                <li>
                    <div class="light-blue lighten-5 collapsible-header active">Filters</div>
                    <div class="collapsible-body panel-body">

                        <div class="row">
                            <div class="col s12">
                                <ul class="tabs" id="templatemo-tabs">
                                    <li class="tab col s3"><a href="#attribute" class="active">Attribute</a></li>
                                    <li class="tab col s3"><a href="#user">User</a></li>
                                    <li class="tab col s3"><a href="#session">Session</a></li>
                                    <li class="tab col s3"><a href="#time">Time</a></li>
                                </ul>
                            </div>
                            <div id="attribute" class="col s12">
                                <div class="row">
                                    <div class="col-md-7">
                                        <br/>
                                        <label for="entityKeySelection">Select an Attribute </label>
                                        <form:select class="form-control" path="selectedKeys" items="${selectNumberParameters.keys}" name ="entityKeySelection" id="entityKeySelection" title="Select Attribute"/>
                                        <br/>
                                        <div class="right-align">
                                            <button class="waves-effect waves-light btn light-blue darken-2" type="button" name="_eventId_searchAttributes" value="Search" onfocus="searchAttributes()" title="Search for Attribute" >Search</button>
                                        </div>
                                        <br/>
                                        <label for="multipleSelect">Search Results </label>
                                        <form:select class="form-control" path="evalue"  name="entityValue" id ="entityValue" title="Select from Search Results">
                                            <form:options items="${selectNumberParameters.searchResults}" />
                                        </form:select>
                                        <label for="specificationType">Select Specification Type </label>
                                        <form:select class="form-control" path="selectedentityValueTypes" items="${selectNumberParameters.entityValueTypes}" name ="specificationType" id="specificationType" title="Select Specification Type" />
                                        <br/>
                                        <div class="alert alert-success alert-dismissible" role="alert">
                                            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                            <strong>Note !</strong> Please use 'ALL' to search all values.
                                        </div>
                                        <div id="entity_filter_add_msg">
                                        </div>
                                        <div class="right-align">
                                            <button class="waves-effect waves-light btn light-blue darken-2" type="button" name="attributebutton" id ="attributebutton"  value="Add" onclick="addEntity()" title="Apply Attribute Filter" >Apply</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="user" class="col s12">
                                <div class="row">
                                    <div class="col-md-7">
                                        <br/>
                                        <label for="userType">User Type </label>
                                        <form:select class="form-control" path="selecteduserSearchTypes" items="${selectNumberParameters.userSearchTypes}" name ="userType" id="userType" title="Select User Type"/>
                                        <label for="searchUserString" class="control-label">Search Keyword</label>
                                        <form:input class="form-control" path="searchUserString"  name="searchUserString" id ="searchUserString" title="Enter Keywords"/>
                                        <br/>
                                        <div class="right-align">
                                            <button class="waves-effect waves-light btn light-blue darken-2" type="button" name="_eventId_searchUser" value="Search" onfocus="searchUser()" title="Search for Users">Search</button>
                                        </div>
                                        <br/>
                                        <label for="multipleSelect">Search Results </label>
                                        <form:select class="form-control" path="selectedUserString" name="multipleSelect" id="usersearchResults" title="Select from Search Results">
                                            <form:options items="${selectNumberParameters.searchResults}" />
                                        </form:select>
                                        <label for="UsersearchType">Search Type </label>
                                        <form:select class="form-control" path="selectedSearchType" items="${selectNumberParameters.searchType}" name ="UsersearchType" id="UsersearchType" title="Select Search Type"/>
                                        <br/>
                                        <div class="right-align">
                                            <button class="waves-effect waves-light btn light-blue darken-2" type="button" name="_eventId_specifyUser" value="Add" onclick="addUserFilter()" title="Apply User Filter">Apply</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="session" class="col s12">
                                <div class="row">
                                    <div class="col-md-7">
                                        <br/>
                                        <label for="sessionSearchType">Session Search Type </label>
                                        <form:select class="form-control" path="selectedsessionSearchType" items="${selectNumberParameters.sessionSearchType}" name ="sessionSearchType" id="sessionSearchType" title="Select Session Type" />
                                        <label for="sessionSearchString" class="control-label">Search Keyword</label>
                                        <input class="form-control" path="sessionSearch"  name="sessionSearchString" id ="sessionSearchString"/>
                                        <br/>
                                        <div class="right-align">
                                            <button class="waves-effect waves-light btn light-blue darken-2" type="button" name="_eventId_searchSession" value="Search" onclick="searchSession()" title="Search for Session" >Search</button>
                                        </div>
                                        <br/>
                                        <label for="multipleSelect">Search Results </label>
                                        <form:select class="form-control"  path="selectedUserString" name="multipleSelect" id="SessionsearchResults" title="Select from Search Results">
                                            <form:options items="${selectNumberParameters.searchResults}" />
                                        </form:select>
                                        <label for="SessionsearchType">Session Search Type</label>
                                        <form:select class="form-control" path="selectedSearchType" items="${selectNumberParameters.searchType}" name ="SessionsearchType" id="SessionsearchType" title="Select Search Type" />
                                        <br/>
                                        <div class="right-align">
                                            <button class="waves-effect waves-light btn light-blue darken-2" type="button" name="_eventId_specifySession" value="Add" onclick="addSessionFilter()" title="Apply Session Filter">Apply</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="time" class="col s12">
                                <div class="row">
                                    <div class="col-md-7">
                                        <br/>
                                        <label for="timeSearchType">Timestamp Search Type </label>
                                        <form:select class="form-control" path="selectedTimeSearchType" items="${selectNumberParameters.timeSearchType}" name ="timeSearchType" id="timeSearchType" title="Select Timestamp Type"/>
                                        <label for="timeSearchString" class="control-label">Search Keyword</label>
                                        <input class="form-control" path="timeSearch"  name="searchString" id ="timeSearchString" title="Enter Search Keywords"/>
                                        <br/>
                                        <div class="right-align">
                                            <button class="waves-effect waves-light btn light-blue darken-2" type="button" name="_eventId_searchTime" onclick="searchTime()" value="Search" title="Search for Timestamp">Search</button>
                                        </div>
                                        <br/>
                                        <label for="multipleSelect">Search Results </label>
                                        <form:select class="form-control" path="selectedSearchStrings" id = "timeSearchResults" name="multipleSelect" title="Select from Search Results">
                                            <form:options items="${selectNumberParameters.searchResults}" />
                                        </form:select>
                                        <br/>
                                        <label for="fromDate">From Date: </label>
                                        <input type="text" id="fromDate" title="Enter From Date">
                                        <label for="fromDate">To Date: </label>
                                        <input type="text" id="toDate" title="Enter To Date">
                                        <label for="timeSelectionType">Timestamp Search Type</label>
                                        <form:select class="form-control" path="selectedTimeType" items="${selectNumberParameters.timeType}" name ="timeSelectionType" id="timeSelectionType" title="Search Type" />
                                        <br/>
                                        <div class="right-align">
                                            <button class="waves-effect waves-light btn light-blue darken-2" type="button" name="_eventId_specifyTime" onclick="addTimeFilter()" value="Add" title="Apply Timestamp Filter">Apply</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="light-blue lighten-5 collapsible-header">Analytics Method</div>
                    <div class="collapsible-body panel-body">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="timeSelectionType">Select a Method</label>
                                <form:select class="form-control" title="Please select a method." path="selectedPlatform" name ="MethodSelection" id="MethodSelection" onfocus="this.selectedIndex = -1;">
                                    <form:option value="Count" label="Count" />
                                    <form:option value="Sum" label="Sum" />
                                    <form:option value="Average" label="Average" />
                                </form:select>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="light-blue lighten-5 collapsible-header">Visualization</div>
                    <div class="collapsible-body panel-body">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="selectedChartType">Select Graph Type </label>
                                <form:select class="form-control" path="selectedChartType"
                                             items="${selectNumberParameters.chartTypes}" name ="selectedChartType" id="selectedChartType" title="Select Graph type for Visulaization" />
                                <label for="EngineSelect">Select Graph Engine </label>
                                <form:select class="form-control" path="selectedChartEngine"
                                             items="${selectNumberParameters.chartEngines}" name ="EngineSelect" id="EngineSelect" title="Select Graph Engine for Visulaization" />
                                <br/>
                                <div class="right-align">
                                    <button class="btn waves-effect waves-light light-blue darken-2" type="button" title="Apply to generate Graph."
                                            name="generateGraph" id="generateGraph" value="Generate Graph"onclick="refreshGraph()">Apply
                                    </button>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div id="graphGeneration">
                                    <img src="/graphs/jgraph?default=true" id="graphImage" title="Generated Graph" style="width: 100%;"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="col s12 m6 l10 right-align">
                <button class="btn waves-effect waves-light light-blue darken-2" type="button" title="Click to save the current Indicator."
                        id="saveIndicator" name="savelIndicator" value="Finalize Settings" onclick="finalizeIndicator()">Save
                </button>
                <button class="btn waves-effect waves-light light-blue darken-2" type="button" title="Click to cancel the current Indicator."
                        name="cancelIndicator" id="cancelIndicator" value="Cancel Indicator">Cancel
                </button>
            </div>
        </div>
    </div>
</div>