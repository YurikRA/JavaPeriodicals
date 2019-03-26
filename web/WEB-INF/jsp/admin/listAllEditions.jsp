<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="List editions" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr >
            <td class="content">

                <form id="add_edition" action="controller">
                <input type="hidden" name="command" value="editEdition"/></form>

                <form id="editButton" action="controller">
                    <input type="hidden" name="command" value="editEdition"/>
                </form>

                <form id="deleteButton" action="controller">
                    <input type="hidden" name="command" value="listAllEditions"/>
                </form>

                <button form="add_edition" name="addEdition" value="add">
                    <fmt:message key="list_all_editions_jsp.button.add"/></button>

                <form id="edit_edition" action="controller">
                <input type="hidden" name="command" value="listAllEditions"/>

                    <table id="list_category_table" border='1' class="tables">
    			        <head>
    				        <tr>
    					        <td><fmt:message key="list_category.table.header.zn"/></td>
    					        <td><fmt:message key="list_editions.table.edition_name"/></td>
    					        <td><fmt:message key="list_editions.table.price"/></td>
    					        <td><fmt:message key="list_editions.table.category"/></td>
    					        <td><fmt:message key="list_editions.table.frequency"/></td>
    					        <td><fmt:message key="list_all_editions_jsp.label.check"/></td>
    					        <td><fmt:message key="list_all_editions_jsp.label.number_editions"/></td>
    				        </tr>
    			        </head>
                        <c:set var="k" value="0"/>
                        <c:set var="n" value="0"/>
                        <c:forEach var="item" items="${editionsItems}">
                        <c:set var="k" value="${k+1}"/>
                            <tr>
                                <td>${k}</td>
                                <td>${item.edName}</td>
                                <td>${item.priceMonth}</td>
                                <td>${item.category}</td>
                                <td>${item.frequency}</td>
                                <td><button form="editButton" name="itemButEdit" value="${item.id}">
                                        <fmt:message key="list_all_editions_jsp.button.edit"/></button>
                                    <button form="deleteButton" name="itemButDelete" value="${item.id}">
                                        <fmt:message key="list_all_editions_jsp.button.delete"/></button></td>
                                <td>${countEd[n]}</td>
                            </tr>
                        <c:set var="n" value="${n+1}"/>
                        </c:forEach>
                    </table>
                </form>
            </td>
        </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </table>
</body>
</html>