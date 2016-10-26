<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
    <head>
        <title>DomainData</title>
            <style type="text/css">
                .tg
                {
                    border-collapse: collapse;
                    border-spacing: 0;
                    border-color: #ccc;
                }
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
        <h1>Domain Details</h1>
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="120">Title</th>
                <th width="120">Status</th>
            </tr>
            <tr>
                <td>${domain.id}</td>
                <td>${domain.domainTitle}</td>
                <td>${domain.domainStatus}</td>
            </tr>
        </table>
    </body>
</html>