<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Конвертер</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css"/>
</head>


<body>
<div class="topnav">
    <a class="active" href=/carwashing>Мойка</a>
</div>
<div class="history-page">
    <div class="form">
        <form action="/carwashing" method="POST">
            <h2>Услуги</h2>

            <div>

                <select multiple id="operations-select">
                    <#list operations as op>
                        <option onclick="updateSelected()" name="${op.name}">${op.label}</option>
                    </#list>
                </select>
                <input name="operations" hidden id="hidden-selected-operations">
                <table>
                    <tr>
                        <th>Мойка №</th>
                        <th>Время начала</th>
                        <th>Время окончания</th>
                    </tr>
                    <#list washings as washing>
                        <tr>
                            <td>${washing.id}</td>
                            <td>${washing.startTime}</td>
                            <td>${washing.endTime}</td>
                        </tr>
                    </#list>
                </table>
            </div>
            <button onclick="updateSelected()" id="btn"> Записаться</button>

        </form>
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