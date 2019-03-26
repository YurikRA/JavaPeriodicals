<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="EditEdition" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr >
            <td class="content">

                <form id="editEdition" action="controller" class="words">
                <input type="hidden" name="command" value="editEdition"/>

                    <div>
			            <p><fmt:message key="list_subs_jsp.table.edition_name"/></p>
			            <input type="text" name="edName"/> &nbsp; ${edition.edName}
	                </div>

                    <div>
			            <p><fmt:message key="list_subs_jsp.table.price"/></p>
			            <input type="number" min="0" name="priceMonth">&nbsp; ${edition.priceMonth}
	                </div>

                    <div>
			            <p><fmt:message key="list_editions.table.category"/></p>
			                <select size="1" name="selectCategory">
			                    <option value="0"></option>
                                <c:forEach var="item" items="${cate}">
                                    <option value="${item.categoryId}">${item.category}</option>
                                </c:forEach>
                            </select> &nbsp;
                            <c:forEach var="itemCategory" items="${cate}">
                                <c:if test="${itemCategory.categoryId == edition.categoryId}">${itemCategory.category}</c:if>
                            </c:forEach>
	                </div>

                    <div>
			            <p><fmt:message key="list_editions.table.frequency"/></p>
			            <input type="number" min="0" name="frequency">&nbsp; ${edition.frequency}
	                </div>

	                <c:choose>
	                    <c:when test="${newEd}">
                            <br><button form="editEdition" name="buttonEd" value="addEd">
                                    <fmt:message key="list_all_editions_jsp.button.add"/></button></br>
                        </c:when>
                        <c:otherwise>
                        <br><button form="editEdition" name="buttonEd" value="updEd">
                                <fmt:message key="edit_edition_jsp.button.update"/></button></br>
                        </c:otherwise>
	                </c:choose>
                </form>
            </td>
        </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </table>
</body>
</html>