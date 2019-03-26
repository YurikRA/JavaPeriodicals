<%@ page import="ua.ruban.db.Role" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="List category" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr >
    	    <td class="content">

    	        <br><c:if test="${userRole.name == 'admin'}">
    	        <button form="addCategory" name="addCategory" value="add">
                <fmt:message key="list_category.button.add_category"/></button>
    	        </c:if></br>

    	        <form id="addCategory" action="controller">
                <input type="hidden" name="command" value="addCategory"/>
                </form>

	            <form id="listCategoryForm" action="controller" >

    	            <input type="hidden" name="command" value="listEditions"/>
    	            <c:if test="${userRole.name == 'client'}">
    	            <input type="submit" value="<fmt:message key="list_category.button.go_to_editions"/>"/>
    	            </c:if>

    		        <table id="list_category_table" border='1' class="tables">
    			        <head>
    				        <tr>
    					    <td><fmt:message key="list_category.table.header.zn"/></td>
    					    <td><fmt:message key="list_category.table.header.name_of_category"/></td>
    					    <c:if test="${userRole.name == 'client'}">
    					    <td><fmt:message key="list_category.table.header.check"/></td>
    					    </c:if>
    				        </tr>
    			        </head>
                        <c:set var="k" value="0"/>
                        <c:forEach var="item" items="${categoryItems}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td>${k}</td>
                            <td>${item.category}</td>
                            <c:if test="${userRole.name == 'client'}">
                            <td><input type="checkbox" name="itemCategoryId" value="${item.categoryId}"/></td>
                            </c:if>
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
