<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Edit Domain</title>
    <style type="text/css">

        .tg td
        {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }
        .tg th
        {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
    <h1>Edit Domain</h1>
    <c:url var="addAction" value="/domains/add"/>
    <form:form action="${addAction}" commandName="domain">
        <table>
            <c:if test="${!empty domain.domainTitle}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>

            <tr>
                <td>
                    <form:label path="domainTitle">
                        <spring:message text="Title"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="domainTitle"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="domainStatus">
                        <spring:message text="Status"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="domainStatus"/>
                </td>
            </tr>

            <tr>
                <td colspan="2">

                    <c:if test="${!empty domain.domainTitle}">
                        <input type="submit"
                               value="<spring:message text="Edit Domain"/>"/>

                    </c:if>

                </td>
            </tr>
        </table>
</form:form>

</body>
</html>