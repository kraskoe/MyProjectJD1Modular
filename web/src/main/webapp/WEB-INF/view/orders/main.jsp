<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <tr>
        <th>№</th>
        <th>Страна</th>
        <th>Курорт</th>
        <th>Отель</th>
        <th>Питание</th>
        <th>Вылет</th>
        <th>Прибытие</th>
        <th>Кол-во ночей</th>
        <th>Кол-во человек</th>
        <th>Стоимость</th>
        <th>Дата заказа</th>
    </tr>

    <c:forEach var="orderDto" items="${orders}" varStatus="status">
        <div name="orderValue">
            <tr>
                <td>${status.index + 1}</td>
                <td>${orderDto.country}</td>
                <td>${orderDto.city}</td>
                <td>${orderDto.hotel}</td>
                <td>${orderDto.board}</td>
                <td>${orderDto.departure}</td>
                <td>${orderDto.arrival}</td>
                <td>${orderDto.duration}</td>
                <td>${orderDto.quantity}</td>
                <td>${orderDto.fullCost}</td>
                <td>${orderDto.orderDate}</td>
                <td><form action="frontController?command=deleteOrder" method="post"><input type="text" name="orderId" value="${orderDto.id}" style="display: none;"><input type="submit" title="Удалить заказ" value="x"></form></td>
            </tr>
        </div>
    </c:forEach>
</table>
