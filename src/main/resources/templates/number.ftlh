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
        <h2>Ваш номер в очереди</h2>
        <h1>${number}</h1>
        <form action="/queue" method="get">
            <input hidden value="${number}" name="washingId">
            <button>Посмотреть очередь</button>
        </form>
        <h4>Автоматическое перенаправление через</h4><h4 id="seconds">5</h4><h4>секунд</h4>

    </div>
</div>

</body>

<script>

    setTimeout(function () {
        window.location.href = '/queue?washingId=${number}';
    }, 5000);
    setInterval(function () {
        document.getElementById('seconds').innerText = document.getElementById('seconds').innerText - 1;
    }, 1000)
</script>
</html>