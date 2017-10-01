<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources/messages" var="i18n"/>
<c:url var="path" value="/frontController?command=${sessionScope.pageName}"></c:url>

<div class="container text-center">
    <div class="error">${errorMsg}</div>
    <div>&nbsp</div>
    <div>
    <form action="frontController?command=login" method="post">
        <table align="center" cellpadding="10" cellspacing="10">
            <tr>
                <td width="85" style="text-align: left"><b><fmt:message key="login.login" bundle="${i18n}"/><br>&nbsp</b></td>
                <td><input type="text" name="login" maxlength="30"/><br>&nbsp</td>
            </tr>
            <tr>
                <td style="text-align: left"><b><fmt:message key="login.password" bundle="${i18n}"/></b></td>
                <td><input type="password" name="password" maxlength="20"/></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="<fmt:message key="login.submit" bundle="${i18n}"/>">
        <br>
        <br>
        <div><fmt:message key="account.no" bundle="${i18n}"/></div>
        <div><a href="${pageContext.request.contextPath}/frontController?command=register"><fmt:message key="register" bundle="${i18n}"/></a></div>
    </form>
    </div>
</div>
