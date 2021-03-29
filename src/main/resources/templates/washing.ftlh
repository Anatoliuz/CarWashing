<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мойка</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css"/>
</head>


<body>
<div class="topnav">
    <a class="active" href=/carwashing>Записаться</a>
    <a href=/queue>Очередь</a>

</div>
<div class="history-page">
    <div class="form">
        <form action="/carwashing" method="POST">
            <h2>Записаться на мойку</h2>
            <h3>Выберите услуги</h3>

            <div>
                <select multiple id="operations-select">
                    <#list operations as op>
                        <option onclick="updateSelected()" name="${op.getOperation().name}">${op.getOperation()
                            .label}       ${op.price} рублей
                        </option>
                    </#list>
                </select>
                <input name="operations" hidden id="hidden-selected-operations">
            </div>

            <button onclick="updateSelected()" id="btn"> Записаться</button>
        </form>
        <h2>Текущая очередь</h2>
        <table>
            <tr>
                <th>Мойка №</th>
                <th>Время начала</th>
                <th>Время окончания</th>
            </tr>
            <#list washings as washing>
                <tr>
                    <td>${washing.id}</td>
                    <td>${washing.startTime.format(formatter)}</td>
                    <td>${washing.endTime.format(formatter)}</td>
                </tr>
            </#list>
        </table>
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