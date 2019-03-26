<c:choose>
    <c:when test="${item.endMonth == 0}">
        <p><select size="1" name="edMonth">
        <%@ include file="/WEB-INF/jspf/utilPage/month.jsp" %></p>
    </c:when>
    <c:otherwise>
        <p><c:choose>
            <c:when test="${item.endMonth == 1}"><fmt:message key="month_jsp.january"/></c:when>
            <c:when test="${item.endMonth == 2}"><fmt:message key="month_jsp.february"/></c:when>
            <c:when test="${item.endMonth == 3}"><fmt:message key="month_jsp.march"/></c:when>
            <c:when test="${item.endMonth == 4}"><fmt:message key="month_jsp.april"/></c:when>
            <c:when test="${item.endMonth == 5}"><fmt:message key="month_jsp.may"/></c:when>
            <c:when test="${item.endMonth == 6}"><fmt:message key="month_jsp.june"/></c:when>
            <c:when test="${item.endMonth == 7}"><fmt:message key="month_jsp.july"/></c:when>
            <c:when test="${item.endMonth == 8}"><fmt:message key="month_jsp.august"/></c:when>
            <c:when test="${item.endMonth == 9}"><fmt:message key="month_jsp.september"/></c:when>
            <c:when test="${item.endMonth == 10}"><fmt:message key="month_jsp.october"/></c:when>
            <c:when test="${item.endMonth == 11}"><fmt:message key="month_jsp.november"/></c:when>
            <c:when test="${item.endMonth == 12}"><fmt:message key="month_jsp.december"/></c:when>
        </c:choose></p>
    </c:otherwise>
</c:choose>