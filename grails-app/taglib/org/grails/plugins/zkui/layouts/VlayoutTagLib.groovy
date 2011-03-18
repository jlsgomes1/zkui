package org.grails.plugins.zkui.layouts

import org.grails.plugins.zkui.AbstractTagLib

class VlayoutTagLib extends AbstractTagLib {
    static namespace = "z"

    def vlayout = {attrs, b ->
        doTag(attrs, b, servletContext, request, response, pageScope, out)
    }

    @Override
    Class getComponentClass() {
        return org.zkoss.zul.Vlayout
    }

}