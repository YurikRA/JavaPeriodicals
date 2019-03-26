<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Settings" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr >
            <td class="content">

				<form id="settings_form" action="controller" method="post" class="words">
			    <input type="hidden" name="command" value="updateSettings" />

					<div>
						<p><fmt:message key="settings_jsp.label.language"/> &nbsp;
						<select name="localeSet">
							<option value="en"><fmt:message key="registration_jsp.label.language.english"/></option>
							<option value="ru"><fmt:message key="registration_jsp.label.language.russian"/></option>
						</select></p>
					</div>
					
					<div>
						<p><fmt:message key="settings_jsp.label.first_name"/></p>
						<input name="firstName" type="text" maxlength="30">
					</div>
					
					<div>
						<p><fmt:message key="settings_jsp.label.last_name"/></p>
						<input name="lastName" type="text" maxlength="30">
					</div>

                    <div>
						<p><fmt:message key="settings_jsp.label.password"/></p>
						<input name="password" minlength="6" maxlength="30" type="password">
					</div>
					
					<br><input type="submit" value="<fmt:message key="settings_jsp.button.update"/>"><br/>
				</form>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>