<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if>
</div>
<div>
    <form action="frontController?command=makeorder" method="post">
        <div>
            <div style="width: 33%; float: left;">
                <div>
                    <div>Страна</div>
                    <select class="countryForm" name="countryForm" style="width: 180px">
                        <c:forEach var="country" items="${countries}" varStatus="status">
                            <option class="countryForm" value="${country.id}">${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <br>
                <div>
                    <div>Курорт</div>
                    <select class="cityForm" name="cityForm" size="5" style="width: 180px">
                        <option class="cityOption" style="text-align: center" disabled value="0"><--Выберите страну-->
                        <%--<c:forEach var="city" items="${cities}" varStatus="status">--%>
                        <%--<option class="cityOption" value="${city.id}">${city.name}--%>
                        <%--</c:forEach>--%>
                    </select>
                </div>
            </div>
            <div style="width: 33%; float: left;">
                <div>
                    <div>Отель</div>
                    <select class="hotelForm" name="hotelForm" size="5" style="width: 180px">
                        <option class="hotelOption" style="text-align: center" disabled value="0"><--Выберите курорт-->
                        <%--<c:forEach var="hotel" items="${hotels}" varStatus="status">--%>
                        <%--<option value="${hotel.id}">${hotel.name}--%>
                        <%--</c:forEach>--%>
                    </select>
                </div>
                <br>
                <div>
                    <div>Питание</div>
                    <div class="boardForm">
                        <c:forEach var="board" items="${boards}" varStatus="status">
                            <span class="boardOption">
                                <input name="boardValue" type="radio" value="${board.id}" disabled> ${board.name}&nbsp;
                            </span>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div style="width: 33%; float: left;">
                <div>
                    <div>Рейс</div>
                    <select class="flightForm" name="flightForm" size="5" style="width: 330px">
                        <option class="flightOption" style="text-align: center" disabled value="0"><--Выберите страну-->
                        <%--<c:forEach var="flight" items="${flights}" varStatus="status">--%>
                        <%--<option value="${flight.id}">Вылет: ${flight.departure} Возвращение: ${flight.arrival}--%>
                        <%--</c:forEach>--%>
                    </select>
                </div>
                <br>
                <span>Количество человек</span>
                <input name="quantityForm" type="number" min="1" max="10" value="1" style="width: 33px;">
            </div>
        </div>
        <div style="clear: left;">
            <br>
            <span>Количество ночей: </span>
            <div class="tourDuration">
                <input type="text" readonly style="width: 33px;">
            </div>
            <br>
            <br>
            <c:if test="${not empty user}"><input type="submit" value="Заказать"></c:if>
            <c:if test="${empty user}">Для заказа вам нужно <a href="${pageContext.request.contextPath}/frontController?command=login">войти</a></c:if>
        </div>
    </form>
</div>




