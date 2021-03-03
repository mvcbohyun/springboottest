<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
 .active{ color : red !important;} 
 </style>
 <sitemesh:write property="head"/>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">bohyun</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link " aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link ${menuType.name() == 'community' ? 'active' : ''}" href="/community"><spring:message code="menu.community"></spring:message></a>
        </li>
		<li class="nav-item">
          <a class="nav-link ${menuType.name() == 'notice' ? 'active' : ''}" href="/notice"><spring:message code="menu.notice"></spring:message></a>
        </li>
        <li class="nav-item">
          <a class="nav-link ${menuType.name() == 'faq' ? 'active' : ''}" href="/faq"><spring:message code="menu.faq"></spring:message></a>
        </li>
        <li class="nav-item">
          <a class="nav-link ${menuType.name() == 'inquity' ? 'active' : ''}" href="/inquity"><spring:message code="menu.inquity"></spring:message></a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
<sitemesh:write property="body"/>
</body>

</html>