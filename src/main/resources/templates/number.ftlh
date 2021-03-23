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
        <form action="/queue?washingId=${number}">
            <button>Посмотреть очередь</button>
        </form>
        <h4>Автоматическое перенаправление через 5 секунд</h4>
    </div>
</div>

</body>

<script>

    setTimeout(function () {
        window.location.href = '/queue?washingId=${number}';
    }, 5000);
</script>
</html>