<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo List</title>

    <%-- Webjars --%>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.1.0/css/bootstrap.min.css"/>'/>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-select/1.12.0/css/bootstrap-select.min.css"/>'/>
    <script type='text/javascript' src='<c:url value="/webjars/jquery/2.2.4/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.1.0/js/bootstrap.min.js"/>'></script>
    <script type='text/javascript'
            src='<c:url value="/webjars/bootstrap-select/1.12.0/js/bootstrap-select.min.js"/>'></script>

    <%-- Local styles and scripts --%>
    <script type='text/javascript' src='<c:url value="/resources/js/main.js"/>'></script>
    <link rel='stylesheet' href='<c:url value="/resources/css/main.css"/>'/>
</head>
<body>
<div class="container-fluid col-sm-6 col-sm-offset-3">
    <div class="row">
        <c:url var="actionAddUrl" value="/todo/add"/>
        <form:form action="${actionAddUrl}" modelAttribute="todoItem">
            <form:input path="content" type="text" class="form-control large-input"
                        placeholder="Write your ToDo here..."/>
            <form:hidden path="id"/>
        </form:form>
    </div>
    <br/>
    <div class="row">
        <div class="btn-group btn-group-justified" role="group">
            <c:url value="/todo/all/1" var="filterAllUrl"/>
            <c:url value="/todo/active/1" var="filterActiveUrl"/>
            <c:url value="/todo/done/1" var="filterDoneUrl"/>
            <a href="${filterAllUrl}" role="button"
               class="btn ${filter == 'all' ? 'btn-success' : 'btn-default'}" id="filterAll">
                <b>All</b>
            </a>
            <a href="${filterActiveUrl}" role="button" class="btn ${filter == 'active' ? 'btn-success' : 'btn-default'}"
               id="filterActive">
                <b>Active</b>
            </a>
            <a href="${filterDoneUrl}" role="button" class="btn ${filter == 'done' ? 'btn-success' : 'btn-default'}"
               id="filterDone">
                <b>Done</b>
            </a>
            <%--<input type="hidden" value="${filter}" name="filter">--%>
        </div>
    </div>
    <br/>
    <br/>
    <div class="btn-group btn-group-justified">
        <div class="btn-group">
            <c:url value="/todo/${filter}/${currentPage-1}" var="prevPage"/>
            <a class="btn btn-default ${currentPage == 1 ? 'disabled':''}" href="${currentPage == 1 ? '#': prevPage}">Prev</a>
        </div>
        <div class="btn-group">
            <c:url var="nextPage" value="/todo/${filter}/${currentPage+1}"/>
            <a class="btn btn-default ${!hasTodos ? 'disabled':''}" href="${nextPage}">Next</a>
        </div>
    </div>

    <br>
    <div class="row">
        <table class="table test">
            <c:forEach items="${listTodo}" var="todoItem">
                <tr class="tr-lg">
                    <td class="col-sm-1 text-center">
                        <div class="form-group onhover">
                            <c:set value="${todoItem.done ? 'notfinish' : 'finish'}" var="todoState" />
                            <c:url value="/todo/${todoState}/${todoItem.id}" var="finishTodoUrl"/>
                            <a href="${finishTodoUrl}" class="btn btn-default">
                                <span class="glyphicon glyphicon-${todoItem.done ? 'ok':'remove'}"></span>
                            </a>
                        </div>
                    </td>
                    <td>
                        <div class="${todoItem.done ? 'doneState':''}">
                                ${todoItem.content}
                        </div>
                    </td>
                    <td class="col-sm-2">
                        <div class="form-group onhover">
                            <a class="btn btn-warning" href="<c:url value='/todo/edit/${todoItem.id}'/>">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>
                            <a class="btn btn-danger" href="<c:url value='/todo/remove/${todoItem.id}'/>">
                                <span class="glyphicon glyphicon-remove"></span>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>

</body>
</html>
