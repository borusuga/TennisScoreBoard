<%--
  Created by IntelliJ IDEA.
  User: borush.nova
  Date: 05.01.2024
  Time: 00:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Новый матч</title>
    <link href="<%=request.getContextPath()%>/css/primaryStyle.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="container bc-blue">
        <div class="table-header p-20">
            <span class="h1">New match</span>
        </div>
    </div>
</header>
<section>
    <div class="container">
        <div class="main">
            <form action="new-match" method="POST">
                <div class="">
                    <input type="text" placeholder="Player #1 Name" id="1" name="player-1" required/>
                </div>
                <div class="">
                    <input type="text" placeholder="Player #2 Name" id="2" name="player-2" required/>
                </div>
                <div class="" style="padding-top: 5px">

                    <p>Please select the quantity of sets in the match:</p>
                    <input type="radio" id="two" name="match-sets" value="2">
                    <label for="two">2</label>
                    <br>
                    <input type="radio" id="three" name="match-sets" value="3">
                    <label for="three">3</label>

                </div>
                <div>
                    <button class="card_button" type="submit">Start game</button>
                </div>
            </form>
        </div>
    </div>
</section>

</body>
</html>