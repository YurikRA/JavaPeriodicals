<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="List subscriptions" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <tr >
            <td class="content">

                <form id="listSubscriptionForm" action="controller">
                    <input type="hidden" name="command" value="listSubscriptions"/>
    		        <table id="listCategoryTable" border='1' class="tables">
    			        <head>
    				        <tr>
    					        <td><fmt:message key="list_category.table.header.zn"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.edition_name"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.price"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.starting_month"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.end_month"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.cost"/>
                                <input type="submit" value="<fmt:message key="list_subs_jsp.table.count"/>"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.year"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.payment_state"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.date"/></td>
    					        <td><fmt:message key="list_subs_jsp.table.choose_to_pay"/>
                                <input type="submit" value="<fmt:message key="list_subs_jsp.button.pay"/>"/></td>
    				        </tr>
    			        </head>
                        <c:set var="k" value="0"/>
                        <c:forEach var="item" items="${checkSub}">
                        <c:set var="k" value="${k+1}"/>
                            <tr>
                                <td>${k}</td>
                                <td>${item.nameEdition}</td>
                                <td>${item.priceEd}</td>
                                <td><%@ include file="/WEB-INF/jspf/utilPage/chooseStartMonth.jsp" %></td>
                                <td><%@ include file="/WEB-INF/jspf/utilPage/chooseEndMonth.jsp" %></td>
                                <td>${item.sum}</td>
                                <td><%@ include file="/WEB-INF/jspf/utilPage/chooseYear.jsp" %></td>
                                <td><c:choose>
                                    <c:when test="${item.statusId == 0}">
                                        <p><fmt:message key="list_subs_jsp.label.not_paid"/></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p><fmt:message key="list_subs_jsp.label.paid"/></p>
                                    </c:otherwise>
                                </c:choose></td>
                                <td>${item.date}</td>
                                <td><c:choose>
                                    <c:when test="${item.startMonth == 0}">
                                        <fmt:message key="list_subs_jsp.label.pay"/></c:when>
                                    <c:when test="${item.statusId == 1}">
                                        <fmt:message key="list_subs_jsp.label.paid"/></c:when>
                                    <c:otherwise><input type="checkbox" name="itemSubId" value="${k}"/></c:otherwise>
                                </c:choose></td>
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
