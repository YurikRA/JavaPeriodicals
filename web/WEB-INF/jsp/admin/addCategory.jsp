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
                <form id="addCategoryForm" action="controller" method="post" class="words">
                <input type="hidden" name="command" value="addCategory" />

                    <div>
						<p><fmt:message key="list_category.label.name_category"/></p>
						<input name="categoryName" type="text" maxlength="50" required>
					</div>

                    <div>
						<p><fmt:message key="list_category.label.name_category_ru"/></p>
						<input name="categoryNameRu" type="text" maxlength="50" required>
					</div>

                    <br><input type="submit" value="<fmt:message key="list_category.button.add_category"/>"><br/>
                </form>
            </td>
        </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </table>
</body>
</html>