<% import grails.persistence.Event %><%=packageName ? "package ${packageName}\n\n" : ''%>import org.zkoss.zk.ui.Component
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.grails.plugins.zkui.util.ZkThemeManager // for usage example only
import ${domainClass.fullName}

class ListComposer {
    Grid grid
    ListModelList listModel = new ListModelList()
    Paging paging
    Longbox idLongbox
    Listbox themeName

    def afterCompose = {Component comp ->
        grid.setRowRenderer(rowRenderer as RowRenderer)
        grid.setModel(listModel)
        redraw()
    }

    void onClick_searchButton(Event e) {
        redraw()
    }

    void onPaging_paging(ForwardEvent fe) {
        def event = fe.origin
        redraw(event.activePage)
    }

    private redraw(int activePage = 0) {
        int offset = activePage * paging.pageSize
        int max = paging.pageSize
        def ${propertyName}List = ${className}.createCriteria().list(offset: offset, max: max) {
            order('id','desc')
            if (idLongbox.value) {
                eq('id', idLongbox.value)
            }
        }
        paging.totalSize = ${propertyName}List.totalCount
        listModel.clear()
        listModel.addAll(${propertyName}List.id)
    }

    private rowRenderer = {Row row, Object id, int index ->
        def ${propertyName} = ${className}.get(id)
        row << {
                <%  excludedProps = Event.allEvents.toList() << 'version'
                    allowedNames = domainClass.persistentProperties*.name << 'id' << 'dateCreated' << 'lastUpdated'
                    props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && !Collection.isAssignableFrom(it.type) }
                    Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                    props.eachWithIndex { p, i ->
                        if (i == 0) {%>a(href: g.createLink(controller:"${domainClass.propertyName}",action:"edit",id:id), label: ${propertyName}.id)
                <%}else if (i < 6) {%>label(value: ${propertyName}.${p.name})
                <%}   } %>hlayout{
                    toolbarbutton(label: g.message(code: "default.button.edit.label", default: "Edit"),image:g.assetPath(src:"/skin/database_edit.png"),href:g.createLink(controller: "${domainClass.propertyName}", action: 'edit', id: id))
                    toolbarbutton(label: g.message(code: "default.button.delete.label", default: "Delete"), image:g.assetPath(src:"/skin/database_delete.png"),  onClick: {
                        if (Messagebox.show(g.message(code: 'default.button.delete.confirm.message', default: 'Are you sure?'), g.message(code: 'default.button.delete.label', default: 'Delete') + " ${domainClass.propertyName} " + id , Messagebox.YES | Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES){
                            ${className}.get(id).delete(flush: true)
                            listModel.remove(id)
                        }
                    })
                }
        }
    }
    
    // Usage example for switching themes
    void onSelect_themeName(Event e){
        def themeManager = new ZkThemeManager()
        themeManager.switchTheme(themeName.selectedItem.value.toString())
    }
}