<%@page contentType="application/xml" pageEncoding="UTF-8"%>
 <%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"%>
<doorList>
<d:forEach items="${doorList}" var="doorList">
<door>
<doorName>${doorList.doorName}</doorName>
<state>${doorList.state}</state>
</course>
</d:forEach>
</doorList>