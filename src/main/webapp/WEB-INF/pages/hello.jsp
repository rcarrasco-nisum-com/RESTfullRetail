<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Spring MVC Application</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://getbootstrap.com/dist/css/bootstrap-responsive.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<c:if test="${!empty products}">
    <h1>Hibernate products</h1>
    <table class="table table-bordered table-striped table-hover">
        <thead>
        <tr>
            <th>Id</th>
            <th>price</th>
            <th>Size</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="products">
            <tr>
                <td>${products.id}</td>
                <td>${products.price}</td>
                <td>${products.size}</td>
                <td>${products.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</div>
</body>
</html>