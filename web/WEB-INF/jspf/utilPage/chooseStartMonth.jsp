<c:choose>
    <c:when test="${item.startMonth == 0}">
        <p><select size="1" name="stMonth">
        <%@ include file="/WEB-INF/jspf/utilPage/month.jsp" %></p>
    </c:when>
    <c:otherwise>
    <p><c:choose>
        <c:when test="${item.startMonth == 1}"><fmt:message key="month_jsp.january"/></c:when>
        <c:when test="${item.startMonth == 2}"><fmt:message key="month_jsp.february"/></c:when>
        <c:when test="${item.startMonth == 3}"><fmt:message key="month_jsp.march"/></c:when>
        <c:when test="${item.startMonth == 4}"><fmt:message key="month_jsp.april"/></c:when>
        <c:when test="${item.startMonth == 5}"><fmt:message key="month_jsp.may"/></c:when>
        <c:when test="${item.startMonth == 6}"><fmt:message key="month_jsp.june"/></c:when>
        <c:when test="${item.startMonth == 7}"><fmt:message key="month_jsp.july"/></c:when>
        <c:when test="${item.startMonth == 8}"><fmt:message key="month_jsp.august"/></c:when>
        <c:when test="${item.startMonth == 9}"><fmt:message key="month_jsp.september"/></c:when>
        <c:when test="${item.startMonth == 10}"><fmt:message key="month_jsp.october"/></c:when>
        <c:when test="${item.startMonth == 11}"><fmt:message key="month_jsp.november"/></c:when>
        <c:when test="${item.startMonth == 12}"><fmt:message key="month_jsp.december"/></c:when>
    </c:choose></p>
    </c:otherwise>
</c:choose>