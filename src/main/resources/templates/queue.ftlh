<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мойка</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css"/>
</head>


<body>
<div class="topnav">
    <a href=/carwashing>Записаться</a>
    <a class="active" href=/queue>Очередь</a>

</div>
<div class="history-page">
    <div class="form">
        <#if washingId??>
            <h2>Вы ${washing.getNumInQueue()} в очереди</h2>
            <h2>Осталось ждать ${washing.getMinutesToWait()} минут</h2>
            <h2>Цена ${washing.getPrice()} руб</h2>

            <h2>Очередь перед вами</h2>
            <table>
                <tr>
                    <th>Мойка №</th>
                    <th>Время начала</th>
                    <th>Время окончания</th>
                </tr>
                <#list queue as washing>
                    <tr>
                        <td>${washing.id}</td>
                        <td>${washing.startTime.format(formatter)}</td>
                        <td>${washing.endTime.format(formatter)}</td>
                    </tr>
                </#list>
            </table>

        <#else>
            <form action="/queue" method="GET">
                <h2>Узнать ваше место в очереди</h2>
                <h3>Введите номер записи</h3>
                <input name="washingId" placeholder="Номер мойки">

                <button id="btn">Узнать</button>
            </form>
        </#if>


    </div>
</div>

</body>

<script>

    function updateSelected() {
        let select = document.getElementById("operations-select");
        let selectedOptions = document.getElementById("hidden-selected-operations");

        let arr = []
        let options = select.selectedOptions
        for (let i = 0; i < options.length; i++) {
            let value = options[i].attributes.name.value;
            arr.push(value)
        }
        selectedOptions.setAttribute('value', arr.join(','))
    }
</script>
</html>