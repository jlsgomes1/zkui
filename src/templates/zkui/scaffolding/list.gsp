<% import grails.persistence.Event %><html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <z:window style="padding:5px" apply="${domainClass.packageName}.${className.toLowerCase()}.ListComposer">
            <z:hlayout>
                <z:toolbarbutton href="\${createLink(action:'create')}" image="\${assetPath(src: 'skin/database_add.png')}" label="\${message(code:'default.new.label',args:[entityName])}"/>
                <z:space/>
                <z:label value="\${message(code:'${domainClass.propertyName}.id',default:'Id')}"/>
                <z:longbox id="idLongbox"/>
                <z:space/>
                <z:button id="searchButton" label="\${message(code:'search')}"/>
                <z:space/>
                <%-- Example for dynamic theme switch. The themes libraries (sapphire.jar, iceblue.jar, etc.) should be in the lib folder --%>
                <z:label value="\${message(code:'default.theme.label',default:'theme')}"/>
                    <z:listbox id="themeName"  mold="select">
                        <z:listitem value="" selected="true">
                            <z:listcell label="\${message(code:'default.select.option',default:'choose one')}"/> 
                        </z:listitem>
                        <z:listitem value="atlantic">
                            <z:listcell label="atlantic"/>
                        </z:listitem>
                        <z:listitem value="iceblue">
                            <z:listcell label="iceblue"/>
                        </z:listitem>
                        <z:listitem value="breeze">
                            <z:listcell label="breeze"/>
                        </z:listitem>
                        <z:listitem value="sapphire">
                            <z:listcell label="sapphire"/>
                        </z:listitem>
                        <z:listitem value="silvertail">
                            <z:listcell label="silvertail"/>
                        </z:listitem>
                    </z:listbox>
                  <%-- end of example  --%>
            </z:hlayout>
            <g:if test="\${flash.message}">
                <z:window mode="popup" border="normal">
                    <z:hlayout>
                        <z:image src="\${assetPath(src: 'skin/information.png')}"/>
                        <z:div>
                            \${flash.message}
                        </z:div>
                    </z:hlayout>
                </z:window>
            </g:if>
            <z:grid id="grid" emptyMessage="\${message(code:'emptyMessage',default:'No Record')}">
                <z:columns sizable="true">
                    <%  excludedProps = Event.allEvents.toList() << 'version'
                        allowedNames = domainClass.persistentProperties*.name << 'id' << 'dateCreated' << 'lastUpdated'
                        props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && !Collection.isAssignableFrom(it.type) }
                        Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                        props.eachWithIndex { p, i ->
                            if (i < 6) {%><z:column label="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}"/>
                    <%}   } %><z:column width="150px"/>
                </z:columns>
            </z:grid>
            <z:paging autohide="true" id="paging" pageSize="15"/>
        </z:window>
    </body>
</html>