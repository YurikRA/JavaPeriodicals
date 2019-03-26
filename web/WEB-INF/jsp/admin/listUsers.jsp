<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="List users" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content">

                <form id="list_users" action="controller">
                <input type="hidden" name="command" value="listUsers"/>

                    <table id="list_users_table" border='1' class="tables">
    			        <head>
    				        <tr>
    					        <td><fmt:message key="list_category.table.header.zn"/></td>
    					        <td><fmt:message key="login_jsp.label.login"/></td>
    					        <td><fmt:message key="settings_jsp.label.first_name"/></td>
    					        <td><fmt:message key="settings_jsp.label.last_name"/></td>
    					        <td><fmt:message key="list_users_jsp.label.locale_name"/></td>
    					        <td><fmt:message key="list_users_jsp.label.user_active"/></td>
    					        <td><fmt:message key="list_users_jsp.label.Select"/></td>
                            </tr>
    			        </head>
                        <c:set var="k" value="0"/>
                        <c:forEach var="item" items="${usersItems}">
                        <c:set var="k" value="${k+1}"/>
                            <tr>
                                <td>${k}</td>
                                <td>${item.login}</td>
                                <td>${item.firstName}</td>
                                <td>${item.lastName}</td>
                                <td>${item.localeName}</td>
                                <td><c:choose>
                                    <c:when test="${item.activeId == 0}">
                                        <fmt:message key="list_users_jsp.label.is_active"/></c:when>
                                    <c:when test="${item.activeId == 1}">
                                        <fmt:message key="list_users_jsp.label.not_active"/></c:when>
                                    </c:choose></td>
                                <td><button form="list_users" name="itemButBlocking" value="${item.id}">
                                        <fmt:message key="list_users_jsp.button.block_user"/></button>
                                    <button form="list_users" name="itemButUnlocking" value="${item.id}">
                                        <fmt:message key="list_users_jsp.button.unlock_user"/></button>
                                    <button form="list_users" name="itemButDelete" value="${item.id}">
                                        <fmt:message key="list_users_jsp.button.delete_user"/></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </td>
        </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </table>
</body>
</html>