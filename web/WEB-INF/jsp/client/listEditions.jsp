<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="List check editions" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content">

                <form id="radioButtonSort" action="controller" class="words">

                    <p><input type="hidden" name="command" value="listEditions"/>
                    <label><fmt:message key="list_editions.sort"/></label> &nbsp;

                    <input type="radio" id="sortChoice1" name="sort" value="edName">
                    <fmt:message key="list_editions.sort.by_name"/></input>

                    <input type="radio" id="sortChoice2" name="sort" value="priceMonth">
                    <fmt:message key="list_editions.sort.by_price"/></input>&nbsp;

                    <select size="1" name="selectSet">
                        <option disabled><fmt:message key="list_editions.sort.select"/></option>
                        <option value="ASC"><fmt:message key="list_editions.sort.ascending"/></option>
                        <option value="DESC"><fmt:message key="list_editions.sort.descending"/></option>
                    </select> &nbsp;
                    <input type="submit" value="<fmt:message key="list_editions.sort"/>"/></p>

                    <br><input type="text" id="textSearch"
                        placeholder="<fmt:message key="list_editions.search_by_name"/>" name="textSearch"> &nbsp;
                    <input type="submit" value="<fmt:message key="list_editions.search"/>"/></br>
                </form>

	            <form id="list_editions_form" action="controller">

    	            <input type="hidden" name="command" value="listSubscriptions"/>
    	            <input type="submit" value="<fmt:message key="list_editions.button.go_to_subscription"/>"/>

    		        <table id="listCategoryTable" border='1' class="tables">
    			        <head>
    				        <tr>
    					        <td><fmt:message key="list_category.table.header.zn"/></td>
    					        <td><fmt:message key="list_editions.table.edition_name"/></td>
    					        <td><fmt:message key="list_editions.table.price"/></td>
    					        <td><fmt:message key="list_editions.table.category"/></td>
    					        <td><fmt:message key="list_editions.table.frequency"/></td>
    					        <td><fmt:message key="list_editions.table.mark_for_subscription"/></td>
    				        </tr>
    			        </head>
                        <c:set var="k" value="0"/>
                        <c:forEach var="item" items="${editionsItems}">
                        <c:set var="k" value="${k+1}"/>
                            <tr>
                                <td>${k}</td>
                                <td>${item.edName}</td>
                                <td>${item.priceMonth}</td>
                                <td>${item.category}</td>
                                <td>${item.frequency}</td>
                                <td><input type="checkbox" name="itemEdId" value="${item.id}"/></td>
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
