<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
    <head>
        <title>Domains Page</title>

        <style type="text/css">
            .tg {
                border-collapse: collapse;
                border-spacing: 0;
                border-color: #ccc;
            }

            .tg td {
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

            .tg th {
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

            /*.tg .tg-4eph {
                background-color: #f9f9f9
            }*/
        </style>
    </head>

    <body>
    <a href="../../index.jsp">Back to main menu</a>
    <br/>
    <br/>

    <h1>Domains List</h1>

    <c:if test="${!empty listDomains}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="120">Title</th>
                <th width="120">Status</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
                <th width="60">Check</th>
            </tr>
            <c:forEach items="${listDomains}" var="domain">
                <tr>
                    <td>${domain.id}</td>
                    <td><a href="/domaindata/${domain.id}" target="_blank">${domain.domainTitle}</a></td>
                    <td>${domain.domainStatus}</td>
                    <td><a href="<c:url value='/edit/${domain.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${domain.id}'/>">Delete</a></td>
                    <td><a href="<c:url value='/check/${domain.id}'/>">Check</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


    <h1>Add a Domain</h1>

    <c:url var="addAction" value="/domains/add"/>

        <form:form action="${addAction}" commandName="domain">
            <table>
                <%--Table titles--%>
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

                <%--Buttons--%>
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
                        <c:if test="${empty domain.domainTitle}">
                            <input type="submit"
                                   value="<spring:message text="Add Domain"/>"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>