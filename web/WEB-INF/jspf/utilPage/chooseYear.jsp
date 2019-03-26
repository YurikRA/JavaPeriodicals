<c:choose>
    <c:when test="${item.yearEd == 0}">
        <p><select size="1" name="ye">
        <option value="2019">2019</option>
        <option value="2020">2020</option>
        <option value="2021">2021</option>
        <option value="2022">2022</option>
        <option value="2023">2023</option>
        </p>
    </c:when>
    <c:otherwise>
     <p><c:choose>
            <c:when test="${item.yearEd == 2019}">2019</c:when>
            <c:when test="${item.yearEd == 2020}">2020</c:when>
            <c:when test="${item.yearEd == 2021}">2021</c:when>
            <c:when test="${item.yearEd == 2022}">2022</c:when>
            <c:when test="${item.yearEd == 2023}">2023</c:when>
     </c:choose></p>
    </c:otherwise>
</c:choose>