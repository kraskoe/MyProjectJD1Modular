$(document).ready(function(){
    $('.countryForm').change(function () {
        getCities();
    });
});

$(document).ready(function(){
    $('.cityForm').change(function () {
        getHotels();
    });
});

$(document).ready(function(){
    $('.flightForm').change(function () {
        getDuration();
    });
});

$(document).ready(function(){
    $('.hotelForm').change(function () {
        getBoards();
    });
});


function getCities(){
    var countryId = $(".countryForm option:selected").val();
    var baseUrl = document.location.origin;
    $.ajax({
        type: "POST",
        url: baseUrl + '/web/frontController?command=getCities',
        dataType: "json",
        data: {countryId: countryId}
    }).done(function (data) {
        //     $(".cityForm").html(data);
        $(".cityOption").remove();
        $.each(data, function(c, city){
            $(".cityForm").append("<option class='cityOption' value=" + city.id +">" + city.name);
        });
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    }).always(function () {
        $.ajax({
            type: "POST",
            url: baseUrl + '/web/frontController?command=getFlights',
            dataType: "json",
            data: {countryId: countryId}
        }).done(function (data) {
            $(".flightOption").remove();
            $.each(data, function(f, flight){
                $(".flightForm").append("<option class='flightOption' value=" + flight.id +">Вылет: " + flight.departure + " Возвращение: " + flight.arrival);
            });
        }).fail(function (data) {
            if (console && console.log) {
                console.log("Error data:", data);
            }
        });
    });
}

function getHotels(){
    var cityId = $(".cityForm option:selected").val();
    var baseUrl = document.location.origin;
    $.ajax({
        type: "POST",
        url: baseUrl + '/web/frontController?command=getHotels',
        dataType: "json",
        data: {cityId: cityId}
    }).done(function (data) {
        $(".hotelOption").remove();
        $.each(data, function(h, hotel){
            $(".hotelForm").append("<option class='hotelOption' value=" + hotel.id +">" + hotel.name);
        });
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function getDuration(){
    var flightId = $(".flightForm option:selected").val();
    var baseUrl = document.location.origin;
    $.ajax({
        type: "POST",
        url: baseUrl + '/web/frontController?command=getDuration',
        dataType: "json",
        data: {flightId: flightId}
    }).done(function (data) {
        $(".tourDuration").html("<input type='text' readonly style='width: 33px;' value=" + data +">");
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function getBoards(){
    var hotelId = $(".hotelForm option:selected").val();
    var baseUrl = document.location.origin;
    $.ajax({
        type: "POST",
        url: baseUrl + '/web/frontController?command=getBoards',
        dataType: "json",
        data: {hotelId: hotelId}
    }).done(function (data) {
        $(".boardOption").remove();
        $.each(data, function(b, board){
            $(".boardForm").append("<span class='boardOption'><input name='boardValue' type='radio' value=" + board.id + "> " +board.name + "&nbsp;</span>");
        });
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}