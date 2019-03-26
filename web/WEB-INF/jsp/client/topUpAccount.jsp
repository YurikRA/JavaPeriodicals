<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="TopUpAccount" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr >
            <td class="content">

                <form id="user_top_up_account" action="controller" class="words">
                <input type="hidden" name="command" value="userTopUpAccount"/>

                    <label><fmt:message key="top_up_jsp.bank_card"/></label>
                    <br><input name="cardNumber" placeholder="XXXX-XXXX-XXXX-XXXX"
                            required pattern="[\\s]*[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}[\\s]*" > </br>
                    <br><label><fmt:message key="top_up_jsp.valid_card"/></label>
                        <input name="month" type="month" required></br>
                    <br><label><fmt:message key="top_up_jsp.sum"/></label>
                        <input name="sum" type="number" min="0" required></br>
                    <br><input type="submit" value="<fmt:message key="top_up_jsp.button.replenish"/>"/></br>

                </form>
            </td>
        </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </table>
</body>
</html>