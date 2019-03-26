<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Registration" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr >
            <td class="content">

                <form id="registration" action="controller" method="post" class="words">
                <input type="hidden" name="command" value="registration" />

                <br><label><fmt:message key="registration_jsp.label.enter_login"/></label></br>
                <br><input name="login" type="text" maxlength="30" required>*</br>

                <br><label><fmt:message key="registration_jsp.label.password"/></label></br>
                <br><input name="password" type="password" minlength="8" maxlength="30" required
                    pattern="([\S]*[A-ZЁ-Я0-9]+[\S]*[A-ZЁ-Я0-9]+[\S]*)">**</br>

                <br><label><fmt:message key="registration_jsp.label.first_name"/></label></br>
                <br><input name="firstName" type="text" maxlength="30" required>*</br>

                <br><label><fmt:message key="registration_jsp.label.last_name"/></label></br>
                <br><input name="lastName" type="text" maxlength="30" required>*</br>

                <br><label><fmt:message key="registration_jsp.label.language"/></label>
                    <select size="1" name="language">
                        <option value="en"><fmt:message key="registration_jsp.label.language.english"/></option>
                        <option value="ru"><fmt:message key="registration_jsp.label.language.russian"/></option>
                    </select>
                </br>
                <br><input type="submit" value="<fmt:message key="registration_jsp.button.registration"/>"/></br>
                <label><fmt:message key="registration_jsp.text.length"/></label>
                <label><fmt:message key="list_all_editions_jsp.label.text1"/></label>
                </form>
            </td>
        </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </table>
</body>
</html>