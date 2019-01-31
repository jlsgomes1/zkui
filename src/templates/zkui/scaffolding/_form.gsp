<%=packageName%>
<% import grails.persistence.Event %>
<z:rows>
<%  excludedProps = Event.allEvents.toList() << 'version' << 'id' << 'dateCreated' << 'lastUpdated'
    persistentPropNames = domainClass.persistentProperties*.name
    props = domainClass.properties.findAll { persistentPropNames.contains(it.name) && !excludedProps.contains(it.name) }
    Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
    display = true
    boolean hasHibernate = pluginManager?.hasGrailsPlugin('hibernate') || pluginManager?.hasGrailsPlugin('hibernate4')
    props.each { p ->
        if (!Collection.class.isAssignableFrom(p.type)) {
            if (hasHibernate) {
                cp = domainClass.constrainedProperties[p.name]
                display = (cp ? cp.display : true)
            }
            if (display) { %>
    <z:row>
        <z:label value="\${message(code:'${domainClass.propertyName}.${p.name}.label',default:'${p.naturalName}')}"/>
        ${renderEditor(p)}
    </z:row>
<%  }   }   } %> 
</z:rows>







